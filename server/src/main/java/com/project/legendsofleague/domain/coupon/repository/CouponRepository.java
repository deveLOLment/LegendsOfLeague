package com.project.legendsofleague.domain.coupon.repository;

import com.project.legendsofleague.domain.coupon.domain.Coupon;
import java.util.List;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponRepository extends JpaRepository<Coupon, Long>, CustomCouponRepository {

    public List<Coupon> findAllByIdIn(Set<Long> couponIdSet);
}
