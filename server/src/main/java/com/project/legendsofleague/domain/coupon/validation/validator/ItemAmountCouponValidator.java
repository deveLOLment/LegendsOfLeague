package com.project.legendsofleague.domain.coupon.validation.validator;

import com.project.legendsofleague.domain.coupon.validation.condition.AmountDiscountedPriceCondition;
import com.project.legendsofleague.domain.coupon.validation.condition.CouponValidationCondition;
import com.project.legendsofleague.domain.coupon.validation.condition.ItemCouponCondition;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ItemAmountCouponValidator implements CouponValidator {

    private final ItemCouponCondition itemCouponCondition;
    private final AmountDiscountedPriceCondition amountDiscountedPriceCondition;

    @Override
    public List<CouponValidationCondition> getValidationCondtionList() {
        return List.of(
            itemCouponCondition,
            amountDiscountedPriceCondition
        );

    }
}
