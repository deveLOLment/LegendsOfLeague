package com.project.legendsofleague.domain.purchase.dto;

import com.project.legendsofleague.common.EnumValidator.Enum;
import com.project.legendsofleague.domain.purchase.domain.PurchaseType;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PurchaseStartRequestDto {

    private Long orderId;
    private List<ItemCouponAppliedDto> itemList;
    private Integer purchaseTotalPrice;

    @Enum(enumClass = PurchaseType.class, ignoreCase = true)
    private String provider;

}
