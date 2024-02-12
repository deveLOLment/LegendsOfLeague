package com.project.legendsofleague.domain.purchase.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.project.legendsofleague.domain.coupon.service.CouponService;
import com.project.legendsofleague.domain.member.repository.MemberRepository;
import com.project.legendsofleague.domain.membercoupon.domain.MemberCoupon;
import com.project.legendsofleague.domain.membercoupon.repository.MemberCouponRepository;
import com.project.legendsofleague.domain.order.domain.Order;
import com.project.legendsofleague.domain.purchase.domain.Purchase;
import com.project.legendsofleague.domain.purchase.domain.PurchaseType;
import com.project.legendsofleague.domain.purchase.dto.ItemCouponAppliedDto;
import com.project.legendsofleague.domain.purchase.dto.PurchaseResponseDto;
import com.project.legendsofleague.domain.purchase.dto.PurchaseStartRequestDto;
import com.project.legendsofleague.domain.purchase.repository.PurchaseRepository;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BeforePurchaseService {

    private final PurchaseRepository purchaseRepository;

    private final MemberRepository memberRepository;
    private final MemberCouponRepository memberCouponRepository;

    private final KakaoService kakaoService;
    private final TossService tossService;

    private final CouponService couponService;

    /*
    결제 시작 전 체크 로직
     */

    @Transactional
    public PurchaseResponseDto startPurchase(Long memberId,
        PurchaseStartRequestDto purchaseStartRequestDto) {
        //임시 코드
        String nickname = "test nickname";
        String orderCode = "TESTORDERCODE";

        //실제 코드
        Long orderId = purchaseStartRequestDto.getOrderId();
        List<ItemCouponAppliedDto> itemList = purchaseStartRequestDto.getItemList();
        Integer purchaseTotalPrice = purchaseStartRequestDto.getPurchaseTotalPrice();
        PurchaseType purchaseType = PurchaseType.valueOf(purchaseStartRequestDto.getProvider());

        //쿠폰 사용 여부, 유효성 검증
        List<Long> memberCouponIdList = itemList.stream()
            .map(ItemCouponAppliedDto::getMemberCouponId)
            .toList();
        Map<Long, MemberCoupon> memberCouponMap = memberCouponRepository.queryMemberCouponsByIdList(
            memberId,
            memberCouponIdList);

        if (memberCouponMap.size() != memberCouponIdList.size()) {
            throw new RuntimeException("잘못된 쿠폰이 적용되었습니다.");
        }

        //쿠폰의 유효성, 적용 여부, 적용 가격 검증
        if (!couponService.checkValidity(memberCouponMap, itemList)) {
            throw new RuntimeException("쿠폰 적용에 실패했습니다.");
        }

        //총 가격 검증
        int totalPrice = itemList.stream().mapToInt(ItemCouponAppliedDto::getPrice).sum();
        if (totalPrice != purchaseTotalPrice) {
            throw new RuntimeException("가격 계산이 틀렸습니다.");
        }

        //결제 진행
        int quantity = itemList.stream().mapToInt(ItemCouponAppliedDto::getQuantity).sum();
        String orderName = makeOrderName(
            itemList.stream().map(ItemCouponAppliedDto::getItemName).toList(), quantity);
        Purchase createdPurchase
            = Purchase.toEntity(quantity, orderName, purchaseTotalPrice, purchaseType,
            new Order(orderId));
        purchaseRepository.save(createdPurchase);

        return new PurchaseResponseDto(createdPurchase.getId(),
            purchaseTotalPrice, purchaseType.name(), orderName, orderCode, nickname);
    }


    private String makeOrderName(List<String> itemNameList, Integer quantity) {
        if (itemNameList.isEmpty()) {
            return null;
        } else {
            String exampleItemName = itemNameList.get(0);

            return exampleItemName + " 외 " + quantity + "건";
        }
    }

    public void cancelPurchase(Long memberId, Long purchaseId) throws JsonProcessingException {
        //TODO : 해당 회원이 한 주문을 취소하는지 검증

        Purchase purchase = purchaseRepository.findById(purchaseId).orElseThrow(() -> {
            throw new RuntimeException("주문 정보를 찾을수 없습니다.");
        });

        PurchaseType purchaseType = purchase.getPurchaseType();
        if (purchaseType == PurchaseType.KAKAO) {
            kakaoService.cancelPurchase(purchase);
        } else if (purchaseType == PurchaseType.TOSS) {
            tossService.cancelPurchase(purchase);
        }
    }
}
