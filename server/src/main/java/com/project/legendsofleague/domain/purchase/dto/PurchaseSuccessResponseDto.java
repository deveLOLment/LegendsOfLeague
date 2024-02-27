package com.project.legendsofleague.domain.purchase.dto;

import com.project.legendsofleague.domain.order.domain.Order;
import com.project.legendsofleague.domain.purchase.domain.Purchase;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PurchaseSuccessResponseDto {

    private Long id;
    private String orderCode;
    private LocalDateTime orderDate;
    private Integer totalPrice;

    public static PurchaseSuccessResponseDto from(Purchase purchase) {
        PurchaseSuccessResponseDto dto = new PurchaseSuccessResponseDto();
        Order order = purchase.getOrder();
        dto.id = purchase.getId();
        dto.orderDate = order.getOrderDate();
        dto.orderCode = order.getOrderCode();
        dto.totalPrice = purchase.getTotalPrice();
        return dto;

    }
}
