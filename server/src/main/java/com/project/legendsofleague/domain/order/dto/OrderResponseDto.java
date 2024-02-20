package com.project.legendsofleague.domain.order.dto;

import com.project.legendsofleague.domain.membercoupon.dto.MemberCouponResponseDto;
import com.project.legendsofleague.domain.order.domain.Order;
import com.project.legendsofleague.domain.order.domain.OrderItem;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Slf4j
public class OrderResponseDto {

    private Long id;

    private List<OrderItemResponseDto> orderItemList = new ArrayList<>();


    public static OrderResponseDto toDto(List<OrderItem> orderItems,
        Map<Long, List<MemberCouponResponseDto>> couponResponseList) {
        OrderResponseDto orderResponseDto = new OrderResponseDto();

        Order order = orderItems.get(0).getOrder(); //매개변수로 온 orderItems의 order는 모두 같은 order이다.

        orderResponseDto.id = order.getId();
        orderResponseDto.orderItemList = orderItems
                .stream().map((oi) -> OrderItemResponseDto.toDto(oi, couponResponseList)).toList();

        return orderResponseDto;
    }

    /**
     * 주문 목록을 리스트로 보여주기 위한 정적 팩토리 메소드
     *
     * @param order
     * @return
     */
    public static OrderResponseDto toDto(Order order) {
        OrderResponseDto orderResponseDto = new OrderResponseDto();

        orderResponseDto.id = order.getId();
        orderResponseDto.orderItemList = order.getOrderItemList()
                .stream().map(OrderItemResponseDto::toDto).toList();

        return orderResponseDto;
    }
}
