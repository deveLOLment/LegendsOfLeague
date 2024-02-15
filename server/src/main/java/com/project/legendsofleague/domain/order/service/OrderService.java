package com.project.legendsofleague.domain.order.service;


import com.project.legendsofleague.domain.item.domain.Item;
import com.project.legendsofleague.domain.item.repository.ItemRepository;
import com.project.legendsofleague.domain.member.domain.Member;
import com.project.legendsofleague.domain.membercoupon.service.MemberCouponService;
import com.project.legendsofleague.domain.order.domain.Order;
import com.project.legendsofleague.domain.order.domain.OrderItem;
import com.project.legendsofleague.domain.order.dto.OrderRequestDto;
import com.project.legendsofleague.domain.order.dto.OrderResponseDto;
import com.project.legendsofleague.domain.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
                .orElseThrow(() -> new RuntimeException("유효하지 않은 아이템입니다."));

        OrderItem orderItem = OrderItem.createOrderItem(item, orderRequestDto.getCount());
        Order order = Order.toEntity(new Member(memberId), orderItem);
        orderRepository.save(order);

        return order.getId();
    }

    /**
     * 사용자가 상세 아이템에서 주문하기 버튼을 클릭했을 때 보여지는 화면을 위한 서비스 메소드
     *
     * @param memberId
     * @param orderId
     * @return
     */
    public OrderResponseDto detailOrderPage(Long memberId, Long orderId) {

        //orderId가 유효한 지는 어디서 검증?
        List<OrderItem> orderItems = orderItemService.getOrderItemList(orderId);

        //유효하지 않은 order를 요청한 거였다면
        if (orderItems.isEmpty()) {
            throw new RuntimeException("유효하지 않은 주문입니다.");
        }

        List<Item> items = orderItems.stream()
                .map((oi) -> oi.getItem()).toList();

        return OrderResponseDto.toDto(orderItems, memberCouponService.getMemberCouponsByOrder(memberId, orderId, items));
    }
}
