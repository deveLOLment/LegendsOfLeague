package com.project.legendsofleague.domain.purchase.repository;

import com.project.legendsofleague.domain.purchase.domain.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {

}
