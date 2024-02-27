package com.project.legendsofleague.db.initDB;

import com.project.legendsofleague.domain.item.domain.Item;
import com.project.legendsofleague.domain.item.domain.ItemCategory;
import com.project.legendsofleague.domain.item.repository.ItemRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

//@Component
@RequiredArgsConstructor
public class ItemDataInitializer {

    private final ItemRepository itemRepository;

    @PostConstruct
    public void init() {
        for (int i = 1; i < 100; i++) {
            Item item = Item.forDummyData("cloth" + String.valueOf(i), ItemCategory.CLOTHING);
            itemRepository.save(item);
        }
        for (int i = 1; i < 100; i++) {
            Item item = Item.forDummyData("accessory" + String.valueOf(i), ItemCategory.ACCESSORIES);
            itemRepository.save(item);
        }
        for (int i = 1; i < 100; i++) {
            Item item = Item.forDummyData("stationery" + String.valueOf(i), ItemCategory.STATIONERY);
            itemRepository.save(item);
        }
        for (int i = 1; i < 100; i++) {
            Item item = Item.forDummyData("sport" + String.valueOf(i), ItemCategory.SPORTS_OUTDOOR);
            itemRepository.save(item);
        }
    }
}
