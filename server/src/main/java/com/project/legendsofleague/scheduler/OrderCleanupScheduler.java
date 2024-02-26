package com.project.legendsofleague.scheduler;

import com.project.legendsofleague.domain.order.domain.OrderStatus;
import com.project.legendsofleague.domain.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Component
@RequiredArgsConstructor
@Slf4j
public class OrderCleanupScheduler {

    private final OrderRepository orderRepository;

    /**
     * 애플리케이션이 실행될 때 1시간마다 Order 엔티티의 status가 PENDING + 현재 시간으로부터 1시간 이상 흘렀으면 삭제하는 로직
     */
    @Scheduled(fixedDelay = 3600000)
    @Transactional
    public void cleanupPendingOrders() {
        log.info("start cleanup order");
        log.info("=========================");
        LocalDateTime currentTime = LocalDateTime.now();

        LocalDateTime oneHourAgo = currentTime.minus(1, ChronoUnit.HOURS);
        // 현재 시간으로부터 1시간이 넘은 주문을 삭제하는 로직을 구현
        orderRepository.deleteByOrderDateBeforeAndOrderStatusEquals(oneHourAgo, OrderStatus.PENDING);
        log.info("=========================");
        log.info("finish cleanup order");
    }

}
