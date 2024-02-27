package com.project.legendsofleague.domain.purchase.repository;

import com.project.legendsofleague.domain.purchase.domain.Purchase;
import java.util.Optional;

public interface CustomPurchaseRepository {

    Optional<Purchase> queryPurchaseById(Long purchaseId);
}
