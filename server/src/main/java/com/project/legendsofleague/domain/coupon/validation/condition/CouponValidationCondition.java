package com.project.legendsofleague.domain.coupon.validation.condition;

import com.project.legendsofleague.domain.coupon.domain.Coupon;
import com.project.legendsofleague.domain.item.domain.Item;
import org.springframework.stereotype.Component;

@Component
public interface CouponValidationCondition {

    Boolean checkValidation(Coupon coupon, Item item, Integer price, Integer quantity);

}
