package com.project.legendsofleague.domain.order.service;

import com.project.legendsofleague.domain.order.domain.OrderItem;
import com.project.legendsofleague.domain.order.repository.OrderItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class OrderItemService {

    private final OrderItemRepository orderItemRepository;


    /**
     * fetch join을 통해 orderItem의 order, item을 함께 가져옴 + order의 주인인 member까지
     *
     * @param orderId
     * @return
     */
    public List<OrderItem> getOrderItemList(Long orderId) {
        return orderItemRepository.queryOrderItemByOrder(orderId);
    }
}
