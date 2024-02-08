package com.project.legendsofleague.domain.coupon.service;

import com.project.legendsofleague.domain.coupon.domain.Coupon;
import com.project.legendsofleague.domain.coupon.dto.CouponCreateDto;
import com.project.legendsofleague.domain.coupon.dto.CouponResponseDto;
import com.project.legendsofleague.domain.coupon.repository.CouponRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CouponService {

    private final CouponRepository couponRepository;


    @Transactional
    public void createCoupon(CouponCreateDto couponCreateDto) {
        couponRepository.save(Coupon.toEntity(couponCreateDto));
    }

    public List<CouponResponseDto> getApplicableCoupons(Long memberId) {
        List<CouponResponseDto> couponResponseDtoList = couponRepository.queryApplicableCoupons(
                memberId).stream()
            .map(CouponResponseDto::new)
            .collect(Collectors.toList());

        return couponResponseDtoList;


    }
}
