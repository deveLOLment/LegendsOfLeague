package com.project.legendsofleague.domain.item.dto;

import com.project.legendsofleague.domain.item.domain.Item;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ItemSelectResponseDto {

    private Long id;
    private String itemName;

    public ItemSelectResponseDto(Item item) {
        this.id = item.getId();
        this.itemName = item.getName();
    }
}
