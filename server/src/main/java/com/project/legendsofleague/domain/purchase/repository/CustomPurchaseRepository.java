package com.project.legendsofleague.domain.purchase.repository;

import com.project.legendsofleague.domain.purchase.domain.Purchase;
import com.project.legendsofleague.domain.purchase.domain.PurchaseStatus;
import java.util.List;
import java.util.Optional;

public interface CustomPurchaseRepository {

    Optional<Purchase> queryPurchase(Long purchaseId);

    Optional<Purchase> queryPurchaseByOrderId(Long orderId);

    List<Purchase> queryPurchaseByPurchaseStatus(PurchaseStatus purchaseStatus);
}
