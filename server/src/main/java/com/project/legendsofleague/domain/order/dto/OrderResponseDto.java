package com.project.legendsofleague.domain.order.dto;

import com.project.legendsofleague.domain.order.domain.Order;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class OrderResponseDto {

    private Long id;

    private List<OrderItemResponseDto> orderItemList = new ArrayList<>();

    private Integer totalPrice;


    public static OrderResponseDto toDto(Order order) {
        OrderResponseDto orderResponseDto = new OrderResponseDto();
        orderResponseDto.id = order.getId();
        orderResponseDto.totalPrice = order.getTotalPrice();

        orderResponseDto.orderItemList = order.getOrderItemList()
                .stream().map((oi) -> OrderItemResponseDto.toDto(oi)).toList();

        return orderResponseDto;
    }
}
