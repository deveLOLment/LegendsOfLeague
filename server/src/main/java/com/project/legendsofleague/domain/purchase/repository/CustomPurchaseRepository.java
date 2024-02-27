package com.project.legendsofleague.domain.purchase.repository;

import com.project.legendsofleague.domain.purchase.domain.Purchase;
import java.util.Optional;

public interface CustomPurchaseRepository {

    Optional<Purchase> queryPurchaseByIdWithMemberCoupon(Long purchaseId);

    Optional<Purchase> queryPurchase(Long purchaseId);

    Optional<Purchase> queryPurchaseByOrderId(Long orderId);
}
