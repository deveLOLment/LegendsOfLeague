package com.project.legendsofleague.domain.purchase.domain;

import lombok.Getter;

@Getter
public enum PurchaseType {
    KAKAO("KAKAO"),
    TOSS("TOSS");

    private final String type;

    PurchaseType(String type) {
        this.type = type;
    }
}
