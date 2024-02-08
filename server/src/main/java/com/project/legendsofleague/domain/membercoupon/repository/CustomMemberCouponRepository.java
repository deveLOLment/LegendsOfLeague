package com.project.legendsofleague.domain.membercoupon.repository;

import com.project.legendsofleague.domain.membercoupon.domain.MemberCoupon;
import java.util.List;

public interface CustomMemberCouponRepository {

    List<MemberCoupon> queryMemberCoupons(Long memberId);
}
