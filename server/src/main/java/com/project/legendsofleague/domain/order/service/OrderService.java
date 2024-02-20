package com.project.legendsofleague.domain.order.service;


import com.project.legendsofleague.domain.cartItem.domain.CartItem;
import com.project.legendsofleague.domain.cartItem.dto.CartItemOrderRequestDto;
import com.project.legendsofleague.domain.cartItem.service.CartItemService;
import com.project.legendsofleague.domain.item.domain.Item;
import com.project.legendsofleague.domain.item.repository.ItemRepository;
import com.project.legendsofleague.domain.member.domain.Member;
import com.project.legendsofleague.domain.membercoupon.service.MemberCouponService;
import com.project.legendsofleague.domain.order.domain.Order;
import com.project.legendsofleague.domain.order.domain.OrderItem;
import com.project.legendsofleague.domain.order.domain.OrderStatus;
import com.project.legendsofleague.domain.order.dto.OrderRequestDto;
import com.project.legendsofleague.domain.order.dto.OrderResponseDto;
import com.project.legendsofleague.domain.order.repository.order.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.webjars.NotFoundException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class OrderService {

    private final OrderRepository orderRepository;
    private final ItemRepository itemRepository;
    private final OrderItemService orderItemService;
    private final MemberCouponService memberCouponService;
    private final CartItemService cartItemService;


    public List<Order> getOrderList(Long memberId) {
        return orderRepository.queryOrderByMember(memberId);
    }

    public List<OrderResponseDto> findOrderList(Long memberId) {
        List<Order> orders = getOrderList(memberId);

        return orders.stream()
                .map(OrderResponseDto::toDto).toList();
    }

    /**
     * 단일 주문에 대한 주문 객체 생성
     *
     * @param orderRequestDto
     * @param memberId
     * @return
     */
    @Transactional
    public Long createOrder(OrderRequestDto orderRequestDto, Long memberId) { //멤버는 id 생성자 사용

        Item item = itemRepository.findById(orderRequestDto.getItemId())
                .orElseThrow(() -> new NotFoundException("유효하지 않은 아이템입니다."));

        OrderItem orderItem = OrderItem.createOrderItem(item, orderRequestDto.getCount());
        Order order = Order.toEntity(new Member(memberId), orderItem);
        orderRepository.save(order);

        return order.getId();
    }

    /**
     * 장바구니 목록에서 사용자가 체크한 1개 이상의 아이템에 대한 주문에 대한 주문 객체 생성
     *
     * @return
     */
    @Transactional
    public Long createOrderFromCart(List<CartItemOrderRequestDto> cartItemRequestList, Long memberId) {
        List<OrderItem> orderItems = new ArrayList<>();
        List<CartItem> cartItems = cartItemService.getCartItemList(memberId);
        List<Long> cartItemIds = cartItems.stream().map(CartItem::getId).toList();

        //요청의 cartItemId가 유효한(존재하는) id인지 검증
        validateCateList(cartItemIds, cartItemRequestList);

        for (CartItem cartItem : cartItems) {
            Item item = cartItem.getItem();
            OrderItem orderItem = OrderItem.createOrderItem(item, cartItem.getCount());
            orderItems.add(orderItem);
        }

        Order order = Order.toEntity(new Member(memberId), orderItems);
        orderRepository.save(order);
        return order.getId();
    }

    /**
     * 장바구니를 통해 요청했는데, 장바구니에 item 목록이 없는 요청이 있는 지 확인
     * ex) 장바구니를 통해 item2, item3에 대한 order를 만드려고 하는데, cart에 item2가 없는 지 확인
     *
     * @param cartItemIds
     * @param cartItemRequestList
     */
    private void validateCateList(List<Long> cartItemIds, List<CartItemOrderRequestDto> cartItemRequestList) {
        for (CartItemOrderRequestDto cartItemRequestDto : cartItemRequestList) {
            if (!cartItemIds.contains(cartItemRequestDto.getCartItemId())) {
                throw new RuntimeException("옳지 않은 요청입니다.");
            }
        }
    }

    /**
     * 사용자가 상세 아이템에서 주문하기 버튼을 클릭했을 때 보여지는 화면을 위한 서비스 메소드
     *
     * @param memberId (현재 로그인한 유저 정보)
     * @param orderId
     * @return
     */
    public OrderResponseDto detailOrderPage(Long memberId, Long orderId) {

        List<OrderItem> orderItems = orderItemService.getOrderItemList(orderId);

        //유효하지 않은 order를 요청한 거였다면
        if (orderItems.isEmpty()) {
            throw new NotFoundException("유효하지 않은 주문입니다.");
        }

        //Order의 주인
        Member member = orderItems.get(0).getOrder().getMember();

        //Order의 주인과 현재 로그인한 유저가 같지 않을 때
        if (!member.getId().equals(memberId)) {
            throw new RuntimeException("허용되지 않은 접근입니다.");
        }

        List<Item> items = orderItems.stream()
                .map((oi) -> oi.getItem()).toList();

        return OrderResponseDto.toDto(orderItems,
                memberCouponService.getMemberCouponsByOrder(memberId, orderId, items));
    }

    /**
     * 결제가 성공이 된 주문에 한하여 환불 요청이 들어왔을 때 실행되는 메소드
     *
     * @param orderId
     */
    @Transactional
    public void refundOrder(Long orderId) {
        List<OrderItem> orderItems = orderItemService.getOrderItemList(orderId);

        //유효하지 않은 order를 요청한 거였다면 (이미 삭제된 order라면)
        if (orderItems.isEmpty()) {
            throw new RuntimeException("유효하지 않은 요청입니다.");
        }
        Order order = orderItems.get(0).getOrder();

        if (order.getOrderStatus() != OrderStatus.SUCCESS) {
            throw new RuntimeException("유효하지 않은 요청입니다.");
        }

        order.refundOrder();
    }


    /**
     * 사용자가 결제를 성공적으로 완료했을 때, purchaseService로부터 order 변수에 대한 변경 요청을 하는 메소드
     *
     * @param time
     * @param orderId
     * @param totalPrice
     */
    @Transactional
    public boolean successPurchase(LocalDateTime time, Long orderId, Integer totalPrice) {
        List<OrderItem> orderItems = orderItemService.getOrderItemList(orderId);

        //유효하지 않은 order를 요청한 거였다면 (이미 삭제된 order라면)
        if (orderItems.isEmpty()) {
            return false;
        }

        Order order = orderItems.get(0).getOrder();

        if (checkItemStock(orderItems)) {
            removeStock(orderItems);

            //아이템 제고에 문제가 없다면 order의 상태를 SUCCESS로 변경, 결제 완료된 총 totalPrice 초기화, orderDate를 결제 완료 시간으로 변경
            order.changeStatusToSuccess(time, totalPrice);
            //주문 내역 중 장바구니에 있던 아이템이 있다면 장바구니에서 해당 아이템 삭제하기
            cartItemService.deleteOrderedCartItem(order.getMember(), orderItems);
            return true;
        } else {
            order.changeStatusToCancel();
            return false;
        }
    }


    /**
     * orderItem의 item의 재고에서 orderItme의 count 개수를 뺀 값이 0보다 큰 지 확인하는 메소드
     *
     * @param orderItems
     * @return
     */
    private boolean checkItemStock(List<OrderItem> orderItems) {
        for (OrderItem orderItem : orderItems) {
            Item item = orderItem.getItem();
            Integer count = orderItem.getCount();
            if (item.getStock() - count < 0) {
                return false;
            }
        }
        return true;
    }

    private void removeStock(List<OrderItem> orderItems) {
        for (OrderItem orderItem : orderItems) {
            Item item = orderItem.getItem();
            Integer count = orderItem.getCount();
            item.removeStock(count);
        }
    }
}
