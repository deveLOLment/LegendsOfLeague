package com.project.legendsofleague.domain.coupon.validation.validator;

import com.project.legendsofleague.domain.coupon.validation.condition.AmountDiscountedPriceCondition;
import com.project.legendsofleague.domain.coupon.validation.condition.CategoryCouponCondition;
import com.project.legendsofleague.domain.coupon.validation.condition.CouponValidationCondition;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CategoryAmountCouponValidator implements CouponValidator {

    private final CategoryCouponCondition categoryCouponCondition;
    private final AmountDiscountedPriceCondition amountDiscountedPriceCondition;

    @Override
    public List<CouponValidationCondition> getValidationCondtionList() {
        return List.of(
            categoryCouponCondition,
            amountDiscountedPriceCondition
        );
    }
}
