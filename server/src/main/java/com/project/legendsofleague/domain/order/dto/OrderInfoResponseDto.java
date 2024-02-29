package com.project.legendsofleague.domain.order.dto;

import com.project.legendsofleague.domain.order.domain.Order;
import com.project.legendsofleague.domain.order.domain.OrderItem;
import com.project.legendsofleague.domain.purchase.domain.Purchase;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OrderInfoResponseDto {

    private Long id;
    private String orderCode;
    private LocalDateTime orderDate;
    private Integer totalPrice;
    private String purchaseProvider;
    private List<OrderItemInfoQueryDto> orderItemList = new ArrayList<>();

    public static OrderInfoResponseDto from(Purchase purchase){
        Order order = purchase.getOrder();
        List<OrderItem> findOrderItemList = order.getOrderItemList();

        OrderInfoResponseDto dto = new OrderInfoResponseDto();
        dto.id = order.getId();
        dto.orderCode = order.getOrderCode();
        dto.orderDate = order.getOrderDate();

        dto.totalPrice = order.getTotalOrderPrice();
        dto.purchaseProvider = purchase.getPurchaseType().name();
        dto.orderItemList = findOrderItemList.stream()
            .map(OrderItemInfoQueryDto::from)
            .collect(Collectors.toList());
        return dto;

    }

}
