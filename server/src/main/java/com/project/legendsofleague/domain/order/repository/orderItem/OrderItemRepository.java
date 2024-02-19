package com.project.legendsofleague.domain.order.repository.orderItem;

import com.project.legendsofleague.domain.order.domain.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long>, CustomOrderItemRepository {
}
