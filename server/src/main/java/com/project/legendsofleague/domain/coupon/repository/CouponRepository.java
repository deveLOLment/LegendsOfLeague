package com.project.legendsofleague.domain.coupon.repository;

import com.project.legendsofleague.domain.coupon.domain.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponRepository extends JpaRepository<Coupon, Long>, CustomCouponRepository {

}
