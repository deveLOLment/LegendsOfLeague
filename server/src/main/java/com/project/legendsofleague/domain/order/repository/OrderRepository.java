package com.project.legendsofleague.domain.order.repository;

import com.project.legendsofleague.domain.order.domain.Order;
import com.project.legendsofleague.domain.order.domain.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface OrderRepository extends JpaRepository<Order, Long> {

    void deleteByOrderDateBeforeAndOrderStatusEquals(LocalDateTime time, OrderStatus status);
}
