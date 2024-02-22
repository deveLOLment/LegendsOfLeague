package com.project.legendsofleague.domain.membercoupon.controller;

import com.project.legendsofleague.domain.membercoupon.dto.MemberCouponCreateDto;
import com.project.legendsofleague.domain.membercoupon.dto.MemberCouponResponseDto;
import com.project.legendsofleague.domain.membercoupon.service.MemberCouponService;
import io.swagger.v3.oas.annotations.Operation;
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
@RequestMapping("/member-coupons")
public class MemberCouponController {

    private final MemberCouponService memberCouponService;

    @Operation(summary = "회원이 쿠폰을 등록하는 API")
    @PostMapping("/")
    public ResponseEntity<Void> createMemberCoupon(
        @RequestBody MemberCouponCreateDto memberCouponCreateDto) {
        //임시 코드
        Long memberId = 1L;
        memberCouponService.createMemberCoupon(memberId, memberCouponCreateDto);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @Operation(summary = "회원의 쿠폰 리스트 조회")
    @GetMapping("/")
    public ResponseEntity<List<MemberCouponResponseDto>> getMemberCoupons() {
        //임시 코드
        Long memberId = 1L;
        List<MemberCouponResponseDto> couponResponseDtoList = memberCouponService.getMemberCoupons(
            memberId);
        return new ResponseEntity<List<MemberCouponResponseDto>>(couponResponseDtoList,
            HttpStatus.OK);
    }


}
