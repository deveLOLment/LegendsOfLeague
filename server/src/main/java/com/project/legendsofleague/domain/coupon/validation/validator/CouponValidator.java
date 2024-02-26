package com.project.legendsofleague.domain.coupon.validation.validator;

import com.project.legendsofleague.domain.coupon.domain.Coupon;
import com.project.legendsofleague.domain.coupon.validation.condition.CouponValidationCondition;
import com.project.legendsofleague.domain.item.domain.Item;
import java.util.List;

public interface CouponValidator {

    default boolean validate(Coupon coupon, Item item, Integer price, Integer quantity) {
        Boolean flag = true;
        List<CouponValidationCondition> validationList = getValidationCondtionList();

        for (CouponValidationCondition condition : validationList) {
            if (!condition.checkValidation(coupon, item, price, quantity)) {
                flag = false;
                break;
            }
        }
        return flag;
    }

    List<CouponValidationCondition> getValidationCondtionList();
}
