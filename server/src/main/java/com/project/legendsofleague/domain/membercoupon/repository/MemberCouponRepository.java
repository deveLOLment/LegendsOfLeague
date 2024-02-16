package com.project.legendsofleague.domain.membercoupon.repository;

import com.project.legendsofleague.domain.membercoupon.domain.MemberCoupon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberCouponRepository extends JpaRepository<MemberCoupon, Long>,
    CustomMemberCouponRepository {

}
