package com.project.legendsofleague.domain.purchase.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.project.legendsofleague.domain.member.domain.CurrentMember;
import com.project.legendsofleague.domain.member.domain.Member;
import com.project.legendsofleague.domain.purchase.dto.PurchaseResponseDto;
import com.project.legendsofleague.domain.purchase.dto.PurchaseStartRequestDto;
import com.project.legendsofleague.domain.purchase.dto.PurchaseSuccessResponseDto;
import com.project.legendsofleague.domain.purchase.dto.kakao.KakaoReadyResponseDto;
import com.project.legendsofleague.domain.purchase.dto.toss.TossPayApproveRequestDto;
import com.project.legendsofleague.domain.purchase.service.BeforePurchaseService;
import com.project.legendsofleague.domain.purchase.service.KakaoService;
import com.project.legendsofleague.domain.purchase.service.TossService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.io.UnsupportedEncodingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "결제", description = "결제 관련 API")
@RequiredArgsConstructor
public class PurchaseController {

    private final BeforePurchaseService beforePurchaseService;
    private final KakaoService kakaoService;
    private final TossService tossService;

    @Operation(summary = "결제 정보 조회 API")
    @GetMapping("/purchases/{purchaseId}")
    public ResponseEntity<PurchaseSuccessResponseDto> queryPurchaseInfo(
        @PathVariable("purchaseId") Long purchaseId
    ) {
        PurchaseSuccessResponseDto dto
            = beforePurchaseService.queryPurchaseInfo(purchaseId);

        return new ResponseEntity<PurchaseSuccessResponseDto>(dto, HttpStatus.OK);
    }

    @Operation(summary = "결제 시작 API")
    @PostMapping("/purchase/ready")
    public ResponseEntity<PurchaseResponseDto> startPurchase(
        @RequestBody PurchaseStartRequestDto purchaseStartRequestDto,
        @CurrentMember Member member
    ) {

        PurchaseResponseDto purchaseResponseDto
            = beforePurchaseService.startPurchase(member,purchaseStartRequestDto);

        return new ResponseEntity<PurchaseResponseDto>(purchaseResponseDto, HttpStatus.OK);
    }

    @Operation(summary = "kakaoPay Ready API.")
    @GetMapping("/purchase/kakao-pay/ready")
    public ResponseEntity<KakaoReadyResponseDto> kakaoPay(
        @RequestParam(value = "purchaseId") Long purchaseId,
        @CurrentMember Member member
    ) {
        KakaoReadyResponseDto dto = kakaoService.kakaoPay(member, purchaseId);
        return new ResponseEntity<KakaoReadyResponseDto>(dto, HttpStatus.OK);
    }

    @Operation(summary = "kakaoPay Approve API.")
    @GetMapping("/purchase/approve")
    public ResponseEntity<Void> success(@RequestParam("pg_token") String pgToken,
        @RequestParam(value = "tid", required = false) String tid,
        @RequestParam(value = "purchaseId") Long purchaseId,
        @CurrentMember Member member
    ) throws JsonProcessingException {

        if(!kakaoService.kakaoPaySuccess(member, purchaseId, pgToken, tid)) {
            purchaseCancel(member, purchaseId);
        }
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @Operation(summary = "주문 취소 API.")
    @GetMapping("/purchase/cancel")
    public ResponseEntity<Void> purchaseCancel(@CurrentMember Member member,
        @RequestParam("orderId") Long orderId
    ) throws JsonProcessingException {
        beforePurchaseService.cancelPurchase(member, orderId);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @Operation(summary = "tossPay Approve API")
    @PostMapping("/purchase/toss-pay/approve")
    public ResponseEntity<Void> tossPaySuccess(@RequestBody TossPayApproveRequestDto requestDto,
        @RequestParam(value = "purchaseId") Long purchaseId,
        @CurrentMember Member member)
        throws UnsupportedEncodingException, JsonProcessingException {

        if (!tossService.tossPaySuccess(purchaseId, requestDto)) {
            purchaseCancel(member, purchaseId);
        }

        return new ResponseEntity<Void>(HttpStatus.OK);
    }


}
