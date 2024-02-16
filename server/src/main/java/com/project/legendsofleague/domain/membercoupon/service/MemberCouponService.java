package com.project.legendsofleague.domain.membercoupon.service;

import com.project.legendsofleague.domain.coupon.domain.Coupon;
import com.project.legendsofleague.domain.coupon.domain.CouponType;
import com.project.legendsofleague.domain.coupon.dto.CouponResponseDto;
import com.project.legendsofleague.domain.item.domain.Item;
import com.project.legendsofleague.domain.item.domain.ItemCategory;
import com.project.legendsofleague.domain.membercoupon.domain.MemberCoupon;
import com.project.legendsofleague.domain.membercoupon.dto.MemberCouponCreateDto;
import com.project.legendsofleague.domain.membercoupon.repository.MemberCouponRepository;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberCouponService {

    private final MemberCouponRepository memberCouponRepository;

    @Transactional
    public void createMemberCoupon(Long memberId, MemberCouponCreateDto memberCouponCreateDto) {
        memberCouponRepository.save(MemberCoupon.toEntity(memberId, memberCouponCreateDto));

    }

    public List<CouponResponseDto> getMemberCoupons(Long memberId) {
        return memberCouponRepository.queryMemberCoupons(
                memberId).stream()
            .map(memberCoupon -> new CouponResponseDto(memberCoupon.getCoupon()))
            .collect(Collectors.toList());
    }

    public Map<Long, List<CouponResponseDto>> getMemberCouponsByOrder(Long memberId, Long orderId,
        List<Item> itemList) {
        Map<Long, List<CouponResponseDto>> couponMap = new HashMap<>();
        Map<Long, List<MemberCoupon>> itemMemberCouponMap = new HashMap<>();
        Map<Long, List<MemberCoupon>> itemCouponMap = new HashMap<>();
        Map<ItemCategory, List<MemberCoupon>> categoryCouponMap = new HashMap<>();

        //조회한 쿠폰 리스트가 어떤 카테고리에 적용가능한지, 어떤 아이템에 적용 가능한지 체크하는 로직
        memberCouponRepository.queryMemberCouponsByOrder(memberId, orderId)
            .forEach(memberCoupon -> {
                Coupon coupon = memberCoupon.getCoupon();

                if (coupon.getCouponType() == CouponType.ITEM_PERCENT_DISCOUNT
                    || coupon.getCouponType() == CouponType.ITEM_AMOUNT_DISCOUNT) {
                    Long itemId = coupon.getItem().getId();
                    if (itemCouponMap.containsKey(itemId)) {
                        itemCouponMap.get(itemId).add(memberCoupon);
                    } else {
                        itemCouponMap.put(itemId, List.of(memberCoupon));
                    }
                }

                if (coupon.getCouponType() == CouponType.CATEGORY_PERCENT_DISCOUNT
                    || coupon.getCouponType() == CouponType.CATEGORY_AMOUNT_DISCOUNT) {
                    ItemCategory appliedCategory = coupon.getAppliedCategory();
                    if (categoryCouponMap.containsKey(appliedCategory)) {
                        categoryCouponMap.get(appliedCategory).add(memberCoupon);
                    } else {
                        categoryCouponMap.put(appliedCategory, List.of(memberCoupon));
                    }
                }
            });

        //각 아이템별로 적용 가능한 쿠폰 리스트 매핑하기.
        itemList.forEach(item -> {
            Long itemId = item.getId();
            ItemCategory category = item.getCategory();

            if (itemCouponMap.containsKey(itemId)) {
                if (itemMemberCouponMap.containsKey(itemId)) {
                    itemMemberCouponMap.get(itemId).addAll(itemCouponMap.get(itemId));
                }
            }

            if (categoryCouponMap.containsKey(category)) {
                if (itemMemberCouponMap.containsKey(itemId)) {
                    itemMemberCouponMap.get(itemId).addAll(categoryCouponMap.get(category));
                }
            }
        });

        //DTO 변환
        itemMemberCouponMap.forEach((key, value) -> {
            couponMap.put(key, value.stream()
                .map(memberCoupon -> new CouponResponseDto(memberCoupon.getCoupon()))
                .collect(Collectors.toList()));
        });

        return couponMap;
    }
}
