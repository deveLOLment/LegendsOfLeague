package com.project.legendsofleague.domain.order.dto;

import com.project.legendsofleague.domain.order.domain.Order;
import com.project.legendsofleague.domain.purchase.domain.Purchase;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class OrderListResponseDto {
    private Long orderId;
    private String orderCode;
    private String purchaseName;
    private LocalDateTime orderDate;
    private Integer totalPrice;
    private String orderStatus;


    public static OrderListResponseDto toDto(Purchase purchase) {
        OrderListResponseDto dto = new OrderListResponseDto();
        Order order = purchase.getOrder();
        dto.orderId = order.getId();
        dto.orderCode = order.getOrderCode();
        dto.purchaseName = purchase.getName();
        dto.orderDate = LocalDateTime.parse(order.getCreatedTime());
        dto.totalPrice = order.getTotalOrderPrice();
        dto.orderStatus = order.getOrderStatus().getDisplayName();
        return dto;
    }
}
