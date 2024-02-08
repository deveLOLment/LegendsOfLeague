package com.project.legendsofleague.domain.coupon.repository;

import com.project.legendsofleague.domain.coupon.domain.Coupon;
import java.util.List;

public interface CustomCouponRepository {

    List<Coupon> queryApplicableCoupons(Long memberId);

}
