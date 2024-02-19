package com.project.legendsofleague.domain.purchase.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.project.legendsofleague.domain.coupon.service.CouponService;
import com.project.legendsofleague.domain.item.domain.Item;
import com.project.legendsofleague.domain.item.repository.ItemRepository;
import com.project.legendsofleague.domain.member.repository.MemberRepository;
import com.project.legendsofleague.domain.membercoupon.domain.MemberCoupon;
import com.project.legendsofleague.domain.membercoupon.repository.MemberCouponRepository;
import com.project.legendsofleague.domain.order.domain.Order;
import com.project.legendsofleague.domain.order.repository.OrderRepository;
import com.project.legendsofleague.domain.purchase.domain.Purchase;
import com.project.legendsofleague.domain.purchase.domain.PurchaseType;
import com.project.legendsofleague.domain.purchase.dto.ItemCouponAppliedDto;
import com.project.legendsofleague.domain.purchase.dto.PurchaseResponseDto;
import com.project.legendsofleague.domain.purchase.dto.PurchaseStartRequestDto;
import com.project.legendsofleague.domain.purchase.repository.PurchaseRepository;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BeforePurchaseService {

    private final PurchaseRepository purchaseRepository;
    private final ItemRepository itemRepository;

    private final MemberRepository memberRepository;
    private final MemberCouponRepository memberCouponRepository;
    private final OrderRepository orderRepository;

    private final KakaoService kakaoService;
    private final TossService tossService;

    private final CouponService couponService;

    private final AfterPurchaseService afterPurchaseService;

    /*
    결제 시작 전 체크 로직
     */

    private void checkItemStock(List<ItemCouponAppliedDto> itemList,
        Map<Long, Item> itemMap) {
        for (ItemCouponAppliedDto dto : itemList) {
            Integer quantity = dto.getQuantity();
            Long itemId = dto.getItemId();
            Item item = itemMap.get(itemId);
            if (item == null) {
                throw new RuntimeException("잘못된 입력입니다.");
            }
            Integer stock = item.getStock();
            if (stock < quantity) {
                throw new RuntimeException("재고가 부족합니다!");
            }
        }
    }

    @Transactional
    public PurchaseResponseDto startPurchase(Long memberId,
        PurchaseStartRequestDto purchaseStartRequestDto) {
        //임시 코드
        String nickname = "test nickname";

        //실제 코드
        Long orderId = purchaseStartRequestDto.getOrderId();
        Order order = orderRepository.findById(orderId)
            .orElseThrow(() -> new RuntimeException("잘못된 입력입니다."));
        String orderCode = order.getOrderCode();

        List<ItemCouponAppliedDto> itemList = purchaseStartRequestDto.getItemList();
        Integer purchaseTotalPrice = purchaseStartRequestDto.getPurchaseTotalPrice();
        PurchaseType purchaseType = PurchaseType.valueOf(purchaseStartRequestDto.getProvider());

        //전체 아이템 조회해서 Map으로 변환
        Map<Long, Item> itemMap = itemRepository.findAllById(
                itemList.stream().map(ItemCouponAppliedDto::getItemId)
                    .collect(Collectors.toList()))
            .stream()
            .collect(Collectors.toMap(Item::getId, item -> item));

        //쿠폰 사용 여부, 유효성 검증
        Map<Long, MemberCoupon> memberCouponMap = getMemberCouponMap(memberId, itemList);

        //쿠폰의 유효성, 적용 여부, 적용 가격 검증
        if (!couponService.checkValidity(memberCouponMap, itemList, itemMap)) {
            throw new RuntimeException("쿠폰 적용에 실패했습니다.");
        }

        //총 가격 검증
        int totalPrice = itemList.stream().mapToInt(ItemCouponAppliedDto::getPrice)
            .sum();
        if (totalPrice != purchaseTotalPrice) {
            throw new RuntimeException("가격 계산이 틀렸습니다.");
        }

        //재고 체크
        checkItemStock(itemList, itemMap);


        /*
        결제 진행
         */

        //결제 이름 생성
        int quantity = itemList.stream().mapToInt(ItemCouponAppliedDto::getQuantity).sum();
        String orderName = makeOrderName(
            itemList.stream().map(ItemCouponAppliedDto::getItemName).toList(), quantity);

        //결제 엔티티 생성
        Purchase createdPurchase
            = Purchase.toEntity(quantity, orderName, purchaseTotalPrice, purchaseType,
            order);
        purchaseRepository.save(createdPurchase);

        //쿠폰과 결제 연결하기
        for (MemberCoupon memberCoupon : memberCouponMap.values()) {
            memberCoupon.updatePurchase(createdPurchase);
        }

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
        //orderService에서 memberId, orderId를 넘기면 -> 검증 로직 진행

        Purchase purchase = purchaseRepository.findById(purchaseId).orElseThrow(() -> {
            throw new RuntimeException("주문 정보를 찾을수 없습니다.");
        });

        PurchaseType purchaseType = purchase.getPurchaseType();
        if (purchaseType == PurchaseType.KAKAO) {
            kakaoService.cancelPurchase(purchase);
        } else if (purchaseType == PurchaseType.TOSS) {
            tossService.cancelPurchase(purchase);
        }

        afterPurchaseService.cancelPurchase(purchase);
    }


    public Map<Long, MemberCoupon> getMemberCouponMap(Long memberId,
        List<ItemCouponAppliedDto> itemList) {
        List<Long> memberCouponIdList = itemList.stream()
            .filter(dto -> dto.getMemberCouponId() != null)
            .map(ItemCouponAppliedDto::getMemberCouponId)
            .toList();
        Map<Long, MemberCoupon> memberCouponMap = memberCouponRepository.queryMemberCouponsByIdList(
            memberId,
            memberCouponIdList);

        if (memberCouponMap.size() != memberCouponIdList.size()) {
            throw new RuntimeException("잘못된 쿠폰이 적용되었습니다.");
        }

        return memberCouponMap;
    }
}
