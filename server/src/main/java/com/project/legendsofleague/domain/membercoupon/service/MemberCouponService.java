package com.project.legendsofleague.domain.membercoupon.service;

import com.project.legendsofleague.domain.coupon.dto.CouponResponseDto;
import com.project.legendsofleague.domain.membercoupon.domain.MemberCoupon;
import com.project.legendsofleague.domain.membercoupon.dto.MemberCouponCreateDto;
import com.project.legendsofleague.domain.membercoupon.repository.MemberCouponRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberCouponService {

    private final MemberCouponRepository memberCouponRepository;

    @Transactional
    public void createMemberCoupon(Long memberId, MemberCouponCreateDto memberCouponCreateDto) {
        memberCouponRepository.save(MemberCoupon.toEntity(memberId, memberCouponCreateDto));

    }

    public List<CouponResponseDto> getMemberCoupons(Long memberId) {
        return memberCouponRepository.queryMemberCoupons(
                memberId).stream()
            .map(memberCoupon -> new CouponResponseDto(memberCoupon.getCoupon()))
            .collect(Collectors.toList());
    }
}
