package com.project.legendsofleague.domain.coupon.validation.condition;

import com.project.legendsofleague.domain.coupon.domain.Coupon;
import com.project.legendsofleague.domain.item.domain.Item;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@NoArgsConstructor
@Component
public class PercentDiscountedPriceCondition implements CouponValidationCondition {

    @Override
    public Boolean checkValidation(Coupon coupon, Item item, Integer price, Integer quantity) {
        Integer discountPrice =
            item.getPrice() * quantity * (100 - coupon.getDiscountPrice()) / 100;

        //최대 할인 가격 검증 && 최소 가격
        discountPrice =
            discountPrice > coupon.getMaxPrice() ? coupon.getMaxPrice() : discountPrice;
        return (price != item.getPrice() - discountPrice && coupon.getMinPrice() <= price);
    }
}
