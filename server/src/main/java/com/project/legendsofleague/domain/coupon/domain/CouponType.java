package com.project.legendsofleague.domain.coupon.domain;

import lombok.Getter;

@Getter
public enum CouponType {

    CATEGORY_AMOUNT_DISCOUNT("CATEGORY_AMOUNT_DISCOUNT"),
    CATEGORY_PERCENT_DISCOUNT("CATEGORY_PERCENT_DISCOUNT"),
    ITEM_AMOUNT_DISCOUNT("ITEM_AMOUNT_DISCOUNT"),
    ITEM_PERCENT_DISCOUNT("ITEM_PERCENT_DISCOUNT");
    private final String type;

    CouponType(String type) {
        this.type = type;
    }
}
