package com.project.legendsofleague.domain.order.dto;

import com.project.legendsofleague.domain.item.domain.Item;
import com.project.legendsofleague.domain.order.domain.OrderItem;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OrderItemInfoQueryDto {

    private Long itemId;
    private String itemName;
    private Integer quantity;
    private Integer discountedPrice;


    public static OrderItemInfoQueryDto from(OrderItem orderItem){
        OrderItemInfoQueryDto dto = new OrderItemInfoQueryDto();
        Item item = orderItem.getItem();
        dto.itemId = item.getId();
        dto.itemName = item.getName();
        dto.quantity = orderItem.getCount();
        dto.discountedPrice = orderItem.getCount() * orderItem.getOrderPrice();
        return dto;
    }
}
