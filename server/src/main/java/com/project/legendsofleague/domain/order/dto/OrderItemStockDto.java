package com.project.legendsofleague.domain.order.dto;

import com.project.legendsofleague.domain.item.domain.Item;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OrderItemStockDto {
    private Item item;
    private Integer quantity;
}
