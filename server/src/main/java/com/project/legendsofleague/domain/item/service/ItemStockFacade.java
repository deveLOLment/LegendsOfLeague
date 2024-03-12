package com.project.legendsofleague.domain.item.service;

import com.project.legendsofleague.domain.item.domain.Item;
import com.project.legendsofleague.domain.item.repository.ItemLockRepository;
import com.project.legendsofleague.domain.order.dto.OrderItemStockDto;
import java.util.Comparator;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class ItemStockFacade {

    private final ItemLockRepository itemLockRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void decreaseStock(List<OrderItemStockDto> orderItemStockDtoList) {
        orderItemStockDtoList.sort(Comparator.comparing(dto -> dto.getItem().getId()));
        try{
            // 모든 아이템에 대해 락 획득
            for (OrderItemStockDto orderItemStockDto : orderItemStockDtoList) {
                Item item = orderItemStockDto.getItem();
                itemLockRepository.getLock(item.getId().toString());
            }

            // 재고 감소 로직 처리
            for (OrderItemStockDto orderItemStockDto : orderItemStockDtoList) {
                Item item = orderItemStockDto.getItem();
                item.removeStock(orderItemStockDto.getQuantity());
            }
        } finally {
            // 작업 완료 후 모든 아이템에 대해 락 해제
            for (OrderItemStockDto orderItemStockDto : orderItemStockDtoList) {
                Item item = orderItemStockDto.getItem();
                itemLockRepository.releaseLock(item.getId().toString());
            }
        }
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void increaseStock(List<OrderItemStockDto> orderItemStockDtoList) {
        try{
            // 모든 아이템에 대해 락 획득
            for (OrderItemStockDto orderItemStockDto : orderItemStockDtoList) {
                Item item = orderItemStockDto.getItem();
                itemLockRepository.getLock(item.getId().toString());
            }

            // 재고 감소 로직 처리
            for (OrderItemStockDto orderItemStockDto : orderItemStockDtoList) {
                Item item = orderItemStockDto.getItem();
                item.addStock(orderItemStockDto.getQuantity());
            }
        } finally {
            // 작업 완료 후 모든 아이템에 대해 락 해제
            for (OrderItemStockDto orderItemStockDto : orderItemStockDtoList) {
                Item item = orderItemStockDto.getItem();
                itemLockRepository.releaseLock(item.getId().toString());
            }
        }
    }
}
