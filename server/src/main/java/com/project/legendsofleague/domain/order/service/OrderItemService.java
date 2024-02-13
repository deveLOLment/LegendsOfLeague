package com.project.legendsofleague.domain.order.service;

import com.project.legendsofleague.domain.item.domain.Item;
import com.project.legendsofleague.domain.order.domain.Order;
import com.project.legendsofleague.domain.order.domain.OrderItem;
import com.project.legendsofleague.domain.order.repository.OrderItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class OrderItemService {

    private final OrderItemRepository orderItemRepository;

    @Transactional
    public OrderItem createOrderItem(Order order, Item item, Integer count) {
        OrderItem orderItem = OrderItem.toEntity(order, item, count);
        orderItemRepository.save(orderItem);
        return orderItem;
    }
}
