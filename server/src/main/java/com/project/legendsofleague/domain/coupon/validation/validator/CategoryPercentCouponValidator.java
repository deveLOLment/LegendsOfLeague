package com.project.legendsofleague.domain.coupon.validation.validator;

import com.project.legendsofleague.domain.coupon.validation.condition.CategoryCouponCondition;
import com.project.legendsofleague.domain.coupon.validation.condition.CouponValidationCondition;
import com.project.legendsofleague.domain.coupon.validation.condition.PercentDiscountedPriceCondition;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CategoryPercentCouponValidator implements CouponValidator {

    private final CategoryCouponCondition categoryCouponCondition;
    private final PercentDiscountedPriceCondition percentDiscountedPriceCondition;

    @Override
    public List<CouponValidationCondition> getValidationCondtionList() {
        return List.of(
            categoryCouponCondition,
            percentDiscountedPriceCondition
        );
    }
}
