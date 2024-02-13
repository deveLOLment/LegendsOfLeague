package com.project.legendsofleague.domain.coupon.dto;

import com.project.legendsofleague.domain.coupon.domain.Coupon;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CouponResponseDto {

    private Long id;
    private String name;
    private Integer discountPrice;
    private String description;
    private LocalDate validityStartDate;
    private LocalDate validityEndDate;
    private String appliedCategory;
    private String couponType;
    private String itemName;
    private Long itemId;

    public CouponResponseDto(Coupon coupon) {
        this.id = coupon.getId();
        this.name = coupon.getName();
        this.discountPrice = coupon.getDiscountPrice();
        this.description = coupon.getDescription();
        this.validityStartDate = coupon.getValidityStartDate();
        this.validityEndDate = coupon.getValidityEndDate();

        if (coupon.getAppliedCategory() != null) {
            this.appliedCategory = coupon.getAppliedCategory().name();
        }

        if (coupon.getCouponType() != null) {
            this.couponType = coupon.getCouponType().name();
        }

        if (coupon.getItem() != null) {
            this.itemName = coupon.getItem().getName();
            this.itemId = coupon.getItem().getId();
        }

    }

}
