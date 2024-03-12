package com.project.legendsofleague.domain.purchase.repository;

import com.project.legendsofleague.domain.purchase.domain.Purchase;
import com.project.legendsofleague.domain.purchase.domain.PurchaseStatus;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseRepository extends JpaRepository<Purchase, Long>,
    CustomPurchaseRepository {

    void deleteAllByPurchaseDateBeforeAndPurchaseStatusIn(LocalDateTime time,
        List<PurchaseStatus> statusList);
}
