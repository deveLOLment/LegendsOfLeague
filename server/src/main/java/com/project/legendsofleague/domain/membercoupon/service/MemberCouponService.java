package com.project.legendsofleague.domain.membercoupon.service;

import com.project.legendsofleague.common.exception.GlobalExceptionFactory;
import com.project.legendsofleague.common.exception.NotFoundInputValueException;
import com.project.legendsofleague.domain.coupon.domain.Coupon;
import com.project.legendsofleague.domain.coupon.domain.CouponType;
import com.project.legendsofleague.domain.coupon.repository.CouponRepository;
import com.project.legendsofleague.domain.item.domain.Item;
import com.project.legendsofleague.domain.item.domain.ItemCategory;
import com.project.legendsofleague.domain.membercoupon.domain.MemberCoupon;
import com.project.legendsofleague.domain.membercoupon.dto.MemberCouponCreateDto;
import com.project.legendsofleague.domain.membercoupon.dto.MemberCouponResponseDto;
import com.project.legendsofleague.domain.membercoupon.exception.AlreadyRegisteredCouponException;
import com.project.legendsofleague.domain.membercoupon.exception.NotEnoughCouponStockException;
import com.project.legendsofleague.domain.membercoupon.repository.MemberCouponRedisRepository;
import com.project.legendsofleague.domain.membercoupon.repository.MemberCouponRepository;
import java.util.ArrayList;
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
    private final CouponRepository couponRepository;
    private final MemberCouponRedisRepository memberCouponRedisRepository;

    /**
     * 등록 가능한 쿠폰을 회원이 쿠폰을 등록하는 메서드.
     *
     * @param memberId
     * @param memberCouponCreateDto
     */
    @Transactional
    public void registerMemberCoupon(Long memberId, MemberCouponCreateDto memberCouponCreateDto) {
        Long couponId = memberCouponCreateDto.getCouponId();
        Coupon coupon = couponRepository.findById(couponId)
            .orElseThrow(() -> {
                throw GlobalExceptionFactory.getInstance(NotFoundInputValueException.class);
            });

        //이미 등록한 쿠폰인지 체크하는 로직
        validateAlreadyRegisteredCoupon(coupon);

        Long memberCouponCount = memberCouponRedisRepository.increaseMemberCouponCount(couponId);

        if (memberCouponCount > coupon.getStock()) {
            throw GlobalExceptionFactory.getInstance(NotEnoughCouponStockException.class);
        }

        try{
            //MemberCoupon 엔티티 만들고 저장
            memberCouponRepository.save(MemberCoupon.toEntity(memberId, coupon));
        }catch (Exception e){
            //MemberCoupon Entity 생성 및 저장에 실패한 경우 -> Redis의 쿠폰 생성량 차감
            memberCouponRedisRepository.decreaseMemberCouponCount(couponId);
            throw e;
        }

    }

    /**
     * 회원이 쿠폰을 등록할때 이미 등록한 회원인지 체크하는 메서드
     *
     * @param coupon
     */
    private void validateAlreadyRegisteredCoupon(Coupon coupon) {
        memberCouponRepository.findByCouponId(coupon.getId()).ifPresent(memberCoupon -> {
            throw GlobalExceptionFactory.getInstance(AlreadyRegisteredCouponException.class);
        });
    }

    /**
     * 회원이 등록한 쿠폰의 전체 리스트 조회
     *
     * @param memberId
     * @return
     */
    public List<MemberCouponResponseDto> getMemberCoupons(Long memberId) {
        return memberCouponRepository.queryMemberCoupons(
                memberId).stream()
            .map(MemberCouponResponseDto::new)
            .collect(Collectors.toList());
    }

    /**
     * 주문페이지에서 각 아이템별로 적용 가능한 쿠폰 리스트를 조회
     *
     * @param memberId
     * @param orderId
     * @param itemList
     * @return <itemId, 사용가능한 쿠폰 리스트> 리턴
     */
    public Map<Long, List<MemberCouponResponseDto>> getMemberCouponsByOrder(Long memberId,
        Long orderId,
        List<Item> itemList) {
        //리턴해야할 <itemId, 사용가능한 쿠폰 리스트>
        Map<Long, List<MemberCouponResponseDto>> couponMap = new HashMap<>();

        //<itemId, MemberCoupon 엔티티 리스트>
        Map<Long, List<MemberCoupon>> itemMemberCouponMap = new HashMap<>();

        //<itemId, 아이템에 적용되는 쿠폰 리스트>
        Map<Long, List<MemberCoupon>> itemCouponMap = new HashMap<>();

        //<itemCategory, 카테고리에 적용되는 쿠폰 리스트>
        Map<ItemCategory, List<MemberCoupon>> categoryCouponMap = new HashMap<>();

        //조회한 쿠폰 리스트가 어떤 카테고리에 적용가능한지, 어떤 아이템에 적용 가능한지 체크하는 로직
        memberCouponRepository.queryMemberCouponsByOrder(memberId, orderId)
            .forEach(memberCoupon -> {
                Coupon coupon = memberCoupon.getCoupon();

                //아이템에 적용하는 쿠폰의 경우 아이템과 적용 아이템 같은지 체크
                if (coupon.getCouponType() == CouponType.ITEM_PERCENT_DISCOUNT
                    || coupon.getCouponType() == CouponType.ITEM_AMOUNT_DISCOUNT) {
                    itemCouponMap.computeIfAbsent(coupon.getItem().getId(), k -> new ArrayList<>())
                        .add(memberCoupon);
                }

                //카테고리의 적용하는 쿠폰의 경우, 아이템의 카테고리와 쿠폰의 카테고리가 일치하는지 체크
                if (coupon.getCouponType() == CouponType.CATEGORY_PERCENT_DISCOUNT
                    || coupon.getCouponType() == CouponType.CATEGORY_AMOUNT_DISCOUNT) {
                    categoryCouponMap.computeIfAbsent(coupon.getAppliedCategory(),
                        k -> new ArrayList<>()).add(memberCoupon);
                }
            });

        //각 아이템별로 적용 가능한 쿠폰 리스트 매핑하기.
        itemList.forEach(item -> {
            Long itemId = item.getId();
            ItemCategory category = item.getCategory();

            if (itemCouponMap.containsKey(itemId)) {
                itemMemberCouponMap.computeIfAbsent(itemId,
                        k -> new ArrayList<>(itemCouponMap.get(itemId)));

            }

            if (categoryCouponMap.containsKey(category)) {
                itemMemberCouponMap.computeIfAbsent(itemId,
                        k -> new ArrayList<>(categoryCouponMap.get(category)));
            }
        });

        //DTO 변환
        itemMemberCouponMap.forEach((key, value) -> {
            couponMap.put(key, value.stream()
                .map(MemberCouponResponseDto::new)
                .collect(Collectors.toList()));
        });

        return couponMap;
    }


}
