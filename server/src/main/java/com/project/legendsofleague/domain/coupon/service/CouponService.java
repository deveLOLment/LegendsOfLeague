package com.project.legendsofleague.domain.coupon.service;

import com.project.legendsofleague.domain.coupon.domain.Coupon;
import com.project.legendsofleague.domain.coupon.domain.CouponType;
import com.project.legendsofleague.domain.coupon.dto.CouponCreateDto;
import com.project.legendsofleague.domain.coupon.dto.CouponResponseDto;
import com.project.legendsofleague.domain.coupon.repository.CouponRepository;
import com.project.legendsofleague.domain.coupon.validation.condition.AmountDiscountedPriceCondition;
import com.project.legendsofleague.domain.coupon.validation.condition.CategoryCouponCondition;
import com.project.legendsofleague.domain.coupon.validation.condition.ItemCouponCondition;
import com.project.legendsofleague.domain.coupon.validation.condition.PercentDiscountedPriceCondition;
import com.project.legendsofleague.domain.coupon.validation.validator.CategoryAmountCouponValidator;
import com.project.legendsofleague.domain.coupon.validation.validator.CategoryPercentCouponValidator;
import com.project.legendsofleague.domain.coupon.validation.validator.CouponValidator;
import com.project.legendsofleague.domain.coupon.validation.validator.ItemAmountCouponValidator;
import com.project.legendsofleague.domain.coupon.validation.validator.ItemPercentCouponValidator;
import com.project.legendsofleague.domain.item.domain.Item;
import com.project.legendsofleague.domain.item.repository.ItemRepository;
import com.project.legendsofleague.domain.membercoupon.domain.MemberCoupon;
import com.project.legendsofleague.domain.purchase.dto.ItemCouponAppliedDto;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CouponService {

    private final CouponRepository couponRepository;
    private final ItemRepository itemRepository;

    private final CategoryCouponCondition categoryCouponCondition;
    private final ItemCouponCondition itemCouponCondition;
    private final PercentDiscountedPriceCondition percentDiscountedPriceCondition;
    private final AmountDiscountedPriceCondition amountDiscountedPriceCondition;


    @Transactional
    public void createCoupon(CouponCreateDto couponCreateDto) {
        couponRepository.save(Coupon.toEntity(couponCreateDto));
    }

    public List<CouponResponseDto> getApplicableCoupons(Long memberId) {
        return couponRepository.queryApplicableCoupons(
                memberId).stream()
            .map(CouponResponseDto::new)
            .collect(Collectors.toList());
    }


    public Boolean checkValidity(Map<Long, MemberCoupon> memberCouponMap,
        List<ItemCouponAppliedDto> itemCouponList,
        Map<Long, Item> itemMap) {
        for (ItemCouponAppliedDto dto : itemCouponList) {
            MemberCoupon memberCoupon = memberCouponMap.get(dto.getMemberCouponId());
            Integer price = dto.getPrice();
            Integer quantity = dto.getQuantity();
            Item item = itemMap.get(dto.getItemId());

            //쿠폰을 적용하지 않은 경우
            if (memberCoupon.getCoupon() == null) {
                if (!checkPriceWithoutCoupon(item, price, quantity)) {
                    return false;
                }
                continue;
            }

            //쿠폰의 종류에 따라서 검증
            CouponValidator couponValidator = getCouponValidator(
                memberCoupon.getCoupon().getCouponType());

            if (!couponValidator.validate(memberCoupon.getCoupon(), item,
                price, quantity)) {
                return false;
            }
        }
        return true;
    }

    private CouponValidator getCouponValidator(CouponType couponType) {
        return switch (couponType) {
            case CATEGORY_PERCENT_DISCOUNT ->
                new CategoryPercentCouponValidator(categoryCouponCondition,
                    percentDiscountedPriceCondition);
            case CATEGORY_AMOUNT_DISCOUNT ->
                new CategoryAmountCouponValidator(categoryCouponCondition,
                    amountDiscountedPriceCondition);
            case ITEM_PERCENT_DISCOUNT -> new ItemPercentCouponValidator(itemCouponCondition,
                percentDiscountedPriceCondition);
            case ITEM_AMOUNT_DISCOUNT -> new ItemAmountCouponValidator(itemCouponCondition,
                amountDiscountedPriceCondition);
            default -> throw new RuntimeException("잘못된 쿠폰 타입 이름입니다.");
        };
    }

    public Boolean checkPriceWithoutCoupon(Item item, Integer price, Integer quantity) {
        //가격만 검증
        return item.getPrice() * quantity == price;
    }


}
