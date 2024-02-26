package com.project.legendsofleague.domain.order.domain;

public enum OrderStatus {

    PENDING("결제준비"),
    SUCCESS("결제완료"),
    CANCEL("결제실패"),
    REFUND("결제환불");

    private final String displayName;

    OrderStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
