package com.project.legendsofleague.domain.purchase.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ItemCouponAppliedDto {

    private Long itemId;
    private String itemName;
    private Integer quantity;
    private Long memberCouponId;
    private Integer price;
}
