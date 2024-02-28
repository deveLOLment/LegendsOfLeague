package com.project.legendsofleague.domain.purchase.service;

import com.project.legendsofleague.common.exception.GlobalExceptionFactory;
import com.project.legendsofleague.common.exception.NotFoundInputValueException;
import com.project.legendsofleague.domain.membercoupon.domain.MemberCoupon;
import com.project.legendsofleague.domain.order.service.OrderService;
import com.project.legendsofleague.domain.purchase.domain.Purchase;
import com.project.legendsofleague.domain.purchase.repository.PurchaseRepository;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AfterPurchaseService {

    private final PurchaseRepository purchaseRepository;
    private final OrderService orderService;

    /**
     * 구매를 성공적으로 마치고 이후 해야할 작업을 처리하는 과정
     *
     * @param purchaseId
     * @param code
     * @return
     */
    @Transactional
    public Boolean finishPurchase(Long purchaseId, String code) {
        Purchase purchase = purchaseRepository.findById(purchaseId).orElseThrow(() -> {
            throw GlobalExceptionFactory.getInstance(NotFoundInputValueException.class);
        });

        purchase.updatePurchaseCode(code);

        //사용한 쿠폰 처리
        LocalDate usedDate = LocalDate.now();
        purchase.getMemberCouponList().forEach(
            memberCoupon -> memberCoupon.updatedUsedHistory(usedDate));

        //OrderDate, OrderId, TotalPrice를 orderservice의 특정 메서드로 넘기기
        return orderService.successPurchase(LocalDateTime.now(), purchase.getOrder().getId(),
            purchase.getTotalPrice());
    }

    /**
     * 성공적으로 환불한 경우에 이후에 해야할 작업을 진행하는 과정
     *
     * @param purchase
     */
    @Transactional
    public void cancelPurchase(Purchase purchase) {
        purchase.cancelPurchase();

        //구매시 사용했던 쿠폰 복구하기.
        purchase.getMemberCouponList().forEach(MemberCoupon::revertUsedHistory);

        //orderId를 넘기면 해당 order를 REFUND로 바꾸는 로직 수행
        purchase.getOrder().refundOrder();

    }

}
