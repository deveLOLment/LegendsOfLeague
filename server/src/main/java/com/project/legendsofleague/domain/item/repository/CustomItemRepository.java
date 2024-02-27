package com.project.legendsofleague.domain.item.repository;

import com.project.legendsofleague.domain.item.domain.Item;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomItemRepository {
    Item queryItemById(Long itemId);
}
