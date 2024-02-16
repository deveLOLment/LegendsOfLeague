package com.project.legendsofleague.domain.order.repository;

import com.project.legendsofleague.domain.order.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
