package com.project.legendsofleague.domain.coupon.dto;

import com.project.legendsofleague.common.EnumValidator.Enum;
import com.project.legendsofleague.domain.coupon.domain.CouponType;
import com.project.legendsofleague.domain.item.domain.ItemCategory;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class CouponCreateDto {

    @NotBlank
    private String name;

    private String description;

    @DecimalMin(value = "1")
    private Integer stock;

    private Integer minPrice;

    private Integer maxPrice;

    private LocalDate validityStartDate;

    private LocalDate validityEndDate;

    @NotNull
    private Integer discountPrice;

    @Enum(enumClass = ItemCategory.class, ignoreCase = true, message = "잘못된 카테고리 이름입니다.")
    private String appliedCategoryName;

    @Enum(enumClass = CouponType.class, ignoreCase = true, message = "잘못된 쿠폰 타입 이름입니다.")
    private String couponTypeName;

    private Long itemId;

}
