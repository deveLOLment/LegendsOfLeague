package com.project.legendsofleague.domain.coupon.validation.validator;

import com.project.legendsofleague.domain.coupon.validation.condition.CouponValidationCondition;
import com.project.legendsofleague.domain.coupon.validation.condition.ItemCouponCondition;
import com.project.legendsofleague.domain.coupon.validation.condition.PercentDiscountedPriceCondition;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ItemPercentCouponValidator implements CouponValidator {

    private final ItemCouponCondition itemCouponCondition;
    private final PercentDiscountedPriceCondition percentDiscountedPriceCondition;

    @Override
    public List<CouponValidationCondition> getValidationCondtionList() {
        return List.of(
            itemCouponCondition,
            percentDiscountedPriceCondition
        );
    }
}
