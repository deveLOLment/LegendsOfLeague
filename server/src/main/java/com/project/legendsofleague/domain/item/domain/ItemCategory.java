package com.project.legendsofleague.domain.item.domain;

import lombok.Getter;

@Getter
public enum ItemCategory {
    CLOTHING("의류"),
    ACCESSORIES("액세서리"),
    FIGURE("피규어"),
    DOLL("인형"),
    ;

    private final String displayName;

    ItemCategory(String displayName) {
        this.displayName = displayName;
    }

}
