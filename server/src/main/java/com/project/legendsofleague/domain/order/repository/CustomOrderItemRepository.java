package com.project.legendsofleague.domain.order.repository;

import com.project.legendsofleague.domain.order.domain.OrderItem;

import java.util.List;

public interface CustomOrderItemRepository {

    List<OrderItem> queryOrderItemByOrder(Long orderId);
}
