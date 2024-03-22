package com.project.legendsofleague.domain.coupon.controller;

import com.project.legendsofleague.domain.coupon.dto.CouponCreateDto;
import com.project.legendsofleague.domain.coupon.dto.CouponResponseDto;
import com.project.legendsofleague.domain.coupon.service.CouponService;
import com.project.legendsofleague.domain.member.domain.CurrentMember;
import com.project.legendsofleague.domain.member.domain.Member;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name = "쿠폰", description = "쿠폰 관련 API")
@RequestMapping("/coupons")
public class CouponController {

    private final CouponService couponService;

    @Operation(summary = "쿠폰 생성 API")
    @PostMapping()
    public ResponseEntity<Void> createCoupon(@Valid @RequestBody CouponCreateDto couponCreateDto) {
        couponService.createCoupon(couponCreateDto);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @Operation(summary = "회원이 등록 가능한 쿠폰 전체 리스트 조회 API",
        description = "회원이 아직 등록하지 않았으나 수량이 남아 등록할수 있는 쿠폰 전체 조회 API")
    @GetMapping()
    public ResponseEntity<List<CouponResponseDto>> getApplicableCoupons(
        @CurrentMember Member member
    ) {

        List<CouponResponseDto> couponResponseDtoList
            = couponService.getApplicableCoupons(member.getId());
        return new ResponseEntity<List<CouponResponseDto>>(couponResponseDtoList, HttpStatus.OK);
    }
}
