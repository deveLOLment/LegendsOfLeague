package com.project.legendsofleague.domain.membercoupon.repository;

import com.project.legendsofleague.domain.membercoupon.domain.MemberCoupon;
import java.util.List;
import java.util.Map;

public interface CustomMemberCouponRepository {

    List<MemberCoupon> queryMemberCoupons(Long memberId);

    List<MemberCoupon> queryMemberCouponsByOrder(Long memberId, Long orderId);

    Map<Long, MemberCoupon> queryMemberCouponsByIdList(Long memberId,
        List<Long> memberCouponIdList);
}
