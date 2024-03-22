package com.project.legendsofleague.domain.order.dto;

import com.project.legendsofleague.domain.item.domain.Item;
import com.project.legendsofleague.domain.order.domain.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemStockDto {
    private Item item;
    private Integer quantity;

    public OrderItemStockDto (OrderItem orderItem) {
       this.item = orderItem.getItem();
       this.quantity = orderItem.getCount();
    }
}
