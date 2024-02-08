package com.project.legendsofleague.domain.coupon.dto;

import com.project.legendsofleague.common.EnumValidator.Enum;
import com.project.legendsofleague.domain.coupon.domain.CouponType;
import com.project.legendsofleague.domain.item.domain.ItemCategory;
import java.time.LocalDate;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class CouponCreateDto {

    private String name;

    private String description;

    private Integer stock;

    private LocalDate validityStartDate;

    private LocalDate validityEndDate;

    private Integer discountPrice;

    @Enum(enumClass = ItemCategory.class, ignoreCase = true)
    private String appliedCategoryName;

    @Enum(enumClass = CouponType.class, ignoreCase = true)
    private String couponTypeName;

}
