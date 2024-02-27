package com.project.legendsofleague.domain.order.repository.order;

import com.project.legendsofleague.domain.order.domain.Order;

import java.util.List;

public interface CustomOrderRepository {

    List<Order> queryOrderByMember(Long memberId);
}
