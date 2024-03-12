package com.project.legendsofleague.domain.purchase.domain;

import lombok.Getter;

@Getter
public enum PurchaseStatus {
    PENDING("PENDING"),
    SUCCESS("SUCCESS"),
    CANCEL("CANCEL"),
    FAIL("FAIL"),
    REFUND("REFUND");


    private final String type;

    PurchaseStatus(String type) {
        this.type = type;
    }
}
