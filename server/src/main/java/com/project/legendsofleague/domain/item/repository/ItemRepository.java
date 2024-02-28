package com.project.legendsofleague.domain.item.repository;

import com.project.legendsofleague.domain.item.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long>, CustomItemRepository {

}
