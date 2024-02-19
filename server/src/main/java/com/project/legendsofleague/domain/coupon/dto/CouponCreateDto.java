package com.project.legendsofleague.domain.coupon.dto;

import com.project.legendsofleague.common.EnumValidator.Enum;
import com.project.legendsofleague.domain.coupon.domain.CouponType;
import com.project.legendsofleague.domain.item.domain.ItemCategory;
import jakarta.validation.constraints.Min;
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

    @Min(1)
    private Integer stock;

    private Integer minPrice;

    private Integer maxPrice;

    private LocalDate validityStartDate;

    private LocalDate validityEndDate;

    @NotNull
    private Integer discountPrice;

    @Enum(enumClass = ItemCategory.class, ignoreCase = true)
    private String appliedCategoryName;

    @Enum(enumClass = CouponType.class, ignoreCase = true)
    private String couponTypeName;

    private Long itemId;

}
