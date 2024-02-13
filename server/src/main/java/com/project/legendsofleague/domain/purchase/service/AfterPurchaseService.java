package com.project.legendsofleague.domain.purchase.service;

import com.project.legendsofleague.domain.purchase.domain.Purchase;
import com.project.legendsofleague.domain.purchase.repository.PurchaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.webjars.NotFoundException;

@Service
@RequiredArgsConstructor
public class AfterPurchaseService {

    private final PurchaseRepository purchaseRepository;

    @Transactional
    public void finishPurchase(Long purchaseId, String code) {
        Purchase purchase = purchaseRepository.findById(purchaseId)
            .orElseThrow(() -> new NotFoundException(
                "주문 정보를 찾을수 없습니다."));

        purchase.updatePurchaseCode(code);

        //OrderDate, OrderId, TotalPrice를 orderservice의 특정 메서드로 넘기기
    }

    @Transactional
    public void cancelPurchase(Purchase purchase) {
        purchase.cancelPurchase();

        //orderId를 넘기면 해당 order를 REFUND로 바꾸는 로직 수행
    }

}
