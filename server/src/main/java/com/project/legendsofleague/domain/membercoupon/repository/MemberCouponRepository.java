package com.project.legendsofleague.domain.membercoupon.repository;

import com.project.legendsofleague.domain.membercoupon.domain.MemberCoupon;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberCouponRepository extends JpaRepository<MemberCoupon, Long>,
    CustomMemberCouponRepository {

    Optional<MemberCoupon> findByCouponId(Long couponId);
}
