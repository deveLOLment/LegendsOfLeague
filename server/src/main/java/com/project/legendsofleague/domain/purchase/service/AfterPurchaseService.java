package com.project.legendsofleague.domain.purchase.service;

import com.project.legendsofleague.domain.membercoupon.domain.MemberCoupon;
import com.project.legendsofleague.domain.order.service.OrderService;
import com.project.legendsofleague.domain.purchase.domain.Purchase;
import com.project.legendsofleague.domain.purchase.repository.PurchaseRepository;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.webjars.NotFoundException;

@Service
@RequiredArgsConstructor
public class AfterPurchaseService {

    private final PurchaseRepository purchaseRepository;
    private final OrderService orderService;

    @Transactional
    public Boolean finishPurchase(Long purchaseId, String code) {
        Purchase purchase = purchaseRepository.findById(purchaseId)
            .orElseThrow(() -> new NotFoundException(
                "주문 정보를 찾을수 없습니다."));

        purchase.updatePurchaseCode(code);

        //사용한 쿠폰 처리
        LocalDate usedDate = LocalDate.now();
        purchase.getMemberCouponList().forEach(
            memberCoupon -> memberCoupon.updatedUsedHistory(usedDate));

        //OrderDate, OrderId, TotalPrice를 orderservice의 특정 메서드로 넘기기
        return orderService.successPurchase(LocalDateTime.now(), purchase.getOrder().getId(),
            purchase.getTotalPrice());
    }

    @Transactional
    public void cancelPurchase(Purchase purchase) {
        purchase.cancelPurchase();

        //구매시 사용했던 쿠폰 복구하기.
        purchase.getMemberCouponList().forEach(MemberCoupon::revertUsedHistory);

        //orderId를 넘기면 해당 order를 REFUND로 바꾸는 로직 수행

    }

}
