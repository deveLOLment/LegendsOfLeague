package com.project.legendsofleague.domain.purchase.repository;


import static com.project.legendsofleague.domain.member.domain.QMember.member;
import static com.project.legendsofleague.domain.order.domain.QOrder.order;
import static com.project.legendsofleague.domain.purchase.domain.QPurchase.purchase;

import com.project.legendsofleague.domain.purchase.domain.Purchase;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.Optional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CustomPurchaseRepositoryImpl implements CustomPurchaseRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public Optional<Purchase> queryPurchaseByIdWithMemberCoupon(Long purchaseId) {
        Purchase findPurchase = queryFactory.selectFrom(purchase)
            .leftJoin(purchase.memberCouponList).fetchJoin()
            .where(purchase.id.eq(purchaseId))
            .fetchOne();
        return Optional.ofNullable(findPurchase);
    }

    @Override
    public Optional<Purchase> queryPurchase(Long purchaseId) {
        Purchase findPurchase = queryFactory.selectFrom(purchase)
            .leftJoin(purchase.order, order).fetchJoin()
            .leftJoin(order.member, member).fetchJoin()
            .where(purchase.id.eq(purchaseId))
            .fetchOne();

        return Optional.ofNullable(findPurchase);
    }

    @Override
    public Optional<Purchase> queryPurchaseByOrderId(Long orderId) {
        Purchase findPurchase = queryFactory.selectFrom(purchase).distinct()
            .leftJoin(purchase.order, order).fetchJoin()
            .leftJoin(order.orderItemList).fetchJoin()
            .leftJoin(order.member, member).fetchJoin()
            .where(purchase.id.eq(orderId))
            .fetchOne();

        return Optional.ofNullable(findPurchase);
    }
}
