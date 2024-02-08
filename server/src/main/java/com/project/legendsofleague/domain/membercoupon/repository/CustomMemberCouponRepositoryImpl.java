package com.project.legendsofleague.domain.membercoupon.repository;

import static com.project.legendsofleague.domain.coupon.domain.QCoupon.coupon;
import static com.project.legendsofleague.domain.membercoupon.domain.QMemberCoupon.memberCoupon;

import com.project.legendsofleague.domain.membercoupon.domain.MemberCoupon;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CustomMemberCouponRepositoryImpl implements CustomMemberCouponRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<MemberCoupon> queryMemberCoupons(Long memberId) {
        return queryFactory.selectFrom(memberCoupon)
            .join(memberCoupon.coupon, coupon).fetchJoin()
            .where(checkValidityDate())
            .where(memberCoupon.isUsed.isFalse())
            .fetch();
    }

    private BooleanExpression checkValidityDate() {
        LocalDate now = LocalDate.now();

        return memberCoupon.coupon.validityStartDate.loe(now)
            .and(memberCoupon.coupon.validityEndDate.goe(now));
    }
}
