package com.project.legendsofleague.domain.coupon.service;

import com.project.legendsofleague.domain.coupon.domain.Coupon;
import com.project.legendsofleague.domain.coupon.domain.CouponType;
import com.project.legendsofleague.domain.coupon.dto.CouponCreateDto;
import com.project.legendsofleague.domain.coupon.dto.CouponResponseDto;
import com.project.legendsofleague.domain.coupon.repository.CouponRepository;
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


    @Transactional
    public void createCoupon(CouponCreateDto couponCreateDto) {
        couponRepository.save(Coupon.toEntity(couponCreateDto));
    }

    public List<CouponResponseDto> getApplicableCoupons(Long memberId) {
        List<CouponResponseDto> couponResponseDtoList = couponRepository.queryApplicableCoupons(
                memberId).stream()
            .map(CouponResponseDto::new)
            .collect(Collectors.toList());

        return couponResponseDtoList;
    }

    public Boolean checkValidity(
        Map<Long, MemberCoupon> memberCouponMap,
        List<ItemCouponAppliedDto> itemCouponList
    ) {

        Map<Long, Item> itemMap = itemRepository.findAllById(
                itemCouponList.stream().map(ItemCouponAppliedDto::getItemId)
                    .collect(Collectors.toList()))
            .stream()
            .collect(Collectors.toMap(Item::getId, item -> item));

        for (ItemCouponAppliedDto dto : itemCouponList) {
            Long itemId = dto.getItemId();
            Integer price = dto.getPrice();
            Long memberCouponId = dto.getMemberCouponId();
            Integer quantity = dto.getQuantity();
            Item item = itemMap.get(itemId);

            MemberCoupon memberCoupon = memberCouponMap.get(memberCouponId);

            //적용한 쿠폰이 없는 경우
            if (!checkPriceWithoutCoupon(item, price, quantity)) {
                return false;
            }

            //적용한 쿠폰이 있는 경우
            Coupon coupon = memberCoupon.getCoupon();

            /**
             * 해당 쿠폰이 해당 아이템에 적용 가능한지 검증
             */
            //카테고리에 적용하는 쿠폰의 경우 아이템의 카테고리와 쿠폰의 적용 카테고리가 일치하는지 검증
            if (!checkCategoryAppliedCoupon(item, coupon)) {
                return false;
            }

            //아이템에 적용하는 쿠폰의 경우 아이템과 쿠폰 아이템 비교
            if (!checkItemAppliedCoupon(item, coupon)) {
                return false;
            }

            /**
             * 최소 가격 검증
             */
            if (!checkMinPrice(coupon, price)) {
                return false;
            }

            /**
             * 적용 가격 검증
             */

            if (!checkAmountDiscountedPrice(item, coupon, price, quantity)) {
                return false;
            }

            if (!checkPercentDiscountedPrice(item, coupon, price, quantity)) {
                return false;
            }
        }

        return true;
    }

    public Boolean checkPriceWithoutCoupon(Item item, Integer price, Integer quantity) {
        //가격만 검증
        return item.getPrice() * quantity == price;
    }

    public Boolean checkCategoryAppliedCoupon(Item item, Coupon coupon) {
        CouponType couponType = coupon.getCouponType();
        if (couponType.equals(CouponType.CATEGORY_AMOUNT_DISCOUNT
        ) || couponType.equals(CouponType.CATEGORY_PERCENT_DISCOUNT)) {
            return coupon.getAppliedCategory().equals(item.getCategory());
        }

        return true;
    }

    public Boolean checkItemAppliedCoupon(Item item, Coupon coupon) {
        CouponType couponType = coupon.getCouponType();
        if (couponType.equals(CouponType.ITEM_AMOUNT_DISCOUNT
        ) || couponType.equals(CouponType.ITEM_PERCENT_DISCOUNT)) {
            return coupon.getItem().equals(item);
        }

        return true;
    }

    public Boolean checkMinPrice(Coupon coupon, Integer price) {
        CouponType couponType = coupon.getCouponType();
        if (couponType.equals(CouponType.ITEM_PERCENT_DISCOUNT
        ) || couponType.equals(CouponType.CATEGORY_PERCENT_DISCOUNT)) {
            return coupon.getMinPrice() <= price;
        }
        return true;
    }

    public Boolean checkAmountDiscountedPrice(Item item, Coupon coupon, Integer price,
        Integer quantity) {
        CouponType couponType = coupon.getCouponType();
        if (couponType.equals(CouponType.CATEGORY_AMOUNT_DISCOUNT
        ) || couponType.equals(CouponType.ITEM_AMOUNT_DISCOUNT)) {
            return price == item.getPrice() * quantity - coupon.getDiscountPrice();
        }
        return true;
    }

    public Boolean checkPercentDiscountedPrice(Item item, Coupon coupon, Integer price,
        Integer quantity) {
        CouponType couponType = coupon.getCouponType();
        if (couponType.equals(CouponType.CATEGORY_PERCENT_DISCOUNT
        ) || couponType.equals(CouponType.ITEM_PERCENT_DISCOUNT)) {
            Integer discountPrice =
                item.getPrice() * quantity * (100 - coupon.getDiscountPrice()) / 100;
            //최대 할인 가격 검증
            discountPrice =
                discountPrice > coupon.getMaxPrice() ? coupon.getMaxPrice() : discountPrice;
            return price != item.getPrice() - discountPrice;

        }
        return true;
    }
}
