package com.project.legendsofleague.domain.purchase.domain;

import lombok.Getter;

@Getter
public enum PurchaseStatus {
    PENDING("PENDING"),
    SUCCESS("SUCCESS"),
    CANCEL("CANCEL"),
    FAIL("FAIL");

    //TODO : 결제중 취소와 결제후 환불을 다른 상태로 두기.

    private final String type;

    PurchaseStatus(String type) {
        this.type = type;
    }
}
