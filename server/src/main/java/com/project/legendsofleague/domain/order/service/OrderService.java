package com.project.legendsofleague.domain.order.service;


import com.project.legendsofleague.domain.item.domain.Item;
import com.project.legendsofleague.domain.item.repository.ItemRepository;
import com.project.legendsofleague.domain.member.domain.Member;
import com.project.legendsofleague.domain.member.repository.MemberRepository;
import com.project.legendsofleague.domain.order.domain.Order;
import com.project.legendsofleague.domain.order.dto.OrderRequestDto;
import com.project.legendsofleague.domain.order.dto.OrderResponseDto;
import com.project.legendsofleague.domain.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class OrderService {

    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;
    private final OrderItemService orderItemService;

    @Transactional
    public Long createOrder(OrderRequestDto orderRequestDto, Long memberId) {
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
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("유효하지 않은 회원입니다."));
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("유효하지 않은 주문 정보입니다."));

        return OrderResponseDto.toDto(order);
    }
}
