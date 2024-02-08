package com.project.legendsofleague.domain.coupon.repository;

import static com.project.legendsofleague.domain.coupon.domain.QCoupon.coupon;
import static com.project.legendsofleague.domain.item.domain.QItem.item;

import com.project.legendsofleague.domain.coupon.domain.Coupon;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CustomCouponRepositoryImpl implements CustomCouponRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Coupon> queryApplicableCoupons(Long memberId) {
        return queryFactory.selectFrom(coupon)
            .leftJoin(coupon.item, item).fetchJoin()
            .where(coupon.stock.gt(0))
            .where((coupon.memberCouponList.any().member.id.eq(memberId).not()))
            .fetch();
    }
}
