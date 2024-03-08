package com.project.legendsofleague.domain.item.domain;

import lombok.Getter;

@Getter
public enum ItemCategory {
    CLOTHING("의류"),
    ACCESSORIES("액세서리"),
    STATIONERY("문구류"),
    SPORTS_OUTDOOR("스포츠 및 야외용품"),
    DOLL("인형");

    private final String displayName;

    ItemCategory(String displayName) {
        this.displayName = displayName;
    }

}
