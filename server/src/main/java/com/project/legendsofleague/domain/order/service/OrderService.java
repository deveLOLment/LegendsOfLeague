package com.project.legendsofleague.domain.order.service;


import com.project.legendsofleague.domain.item.domain.Item;
import com.project.legendsofleague.domain.item.repository.ItemRepository;
import com.project.legendsofleague.domain.member.domain.Member;
import com.project.legendsofleague.domain.member.repository.MemberRepository;
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
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;
    private final OrderItemService orderItemService;
    private final MemberCouponService memberCouponService;


    @Transactional
    public Long createOrder(OrderRequestDto orderRequestDto, Long memberId) { //멤버는 id 생성자 사용
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("유효하지 않은 회원입니다."));
        Item item = itemRepository.findById(orderRequestDto.getItemId())
                .orElseThrow(() -> new RuntimeException("존재하지 않은 아이템입니다."));

        Order order = Order.toEntity(member);
        orderRepository.save(order);
        orderItemService.createOrderItem(order, item, orderRequestDto.getCount());

        return order.getId();
    }

    public OrderResponseDto detailOrderPage(Long memberId, Long orderId) {

        List<OrderItem> orderItems = orderItemService.getOrderItemList(orderId);

        List<Item> items = orderItems.stream()
                .map((oi) -> oi.getItem()).toList();

        return OrderResponseDto.toDto(orderItems, memberCouponService.getMemberCouponsByOrder(memberId, orderId, items));
    }
}
