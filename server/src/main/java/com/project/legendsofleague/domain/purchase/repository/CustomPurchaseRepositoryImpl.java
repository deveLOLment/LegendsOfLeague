package com.project.legendsofleague.domain.purchase.repository;


import static com.project.legendsofleague.domain.purchase.domain.QPurchase.purchase;

import com.project.legendsofleague.domain.purchase.domain.Purchase;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.Optional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CustomPurchaseRepositoryImpl implements CustomPurchaseRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public Optional<Purchase> queryPurchaseById(Long purchaseId) {
        Purchase findPurchase = queryFactory.selectFrom(purchase)
            .leftJoin(purchase.memberCouponList).fetchJoin()
            .where(purchase.id.eq(purchaseId))
            .fetchOne();
        return Optional.ofNullable(findPurchase);
    }
}
