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
    }

}
