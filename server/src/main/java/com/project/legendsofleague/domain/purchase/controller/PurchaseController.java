package com.project.legendsofleague.domain.purchase.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.project.legendsofleague.domain.purchase.dto.PurchaseResponseDto;
import com.project.legendsofleague.domain.purchase.dto.PurchaseStartRequestDto;
import com.project.legendsofleague.domain.purchase.dto.kakao.KakaoReadyResponseDto;
import com.project.legendsofleague.domain.purchase.dto.toss.TossPayApproveRequestDto;
import com.project.legendsofleague.domain.purchase.service.BeforePurchaseService;
import com.project.legendsofleague.domain.purchase.service.KakaoService;
import com.project.legendsofleague.domain.purchase.service.TossService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;

@RestController
@RequiredArgsConstructor
public class PurchaseController {

    private final BeforePurchaseService beforePurchaseService;
    private final KakaoService kakaoService;
    private final TossService tossService;

    @Operation(summary = "결제 시작 API")
    @PostMapping("/purchase/ready")
    public ResponseEntity<PurchaseResponseDto> startPurchase(
            @RequestBody PurchaseStartRequestDto purchaseStartRequestDto) {
        //임시 코드
        Long memberId = 1L;
        PurchaseResponseDto purchaseResponseDto = beforePurchaseService.startPurchase(memberId,
                purchaseStartRequestDto);

        return new ResponseEntity<PurchaseResponseDto>(purchaseResponseDto, HttpStatus.OK);
    }

    @Operation(summary = "kakaoPay Ready API.")
    @GetMapping("/purchase/kakao-pay/ready")
    public ResponseEntity<KakaoReadyResponseDto> kakaoPay(
            @RequestParam(value = "purchaseId") Long purchaseId) {
        KakaoReadyResponseDto dto = kakaoService.kakaoPay(purchaseId);
        return new ResponseEntity<KakaoReadyResponseDto>(dto, HttpStatus.OK);
    }

    @Operation(summary = "kakaoPay Approve API.")
    @GetMapping("/purchase/approve")
    public ResponseEntity<Void> success(@RequestParam("pg_token") String pgToken,
                                        @RequestParam(value = "tid", required = false) String tid,
                                        @RequestParam(value = "purchaseId") Long purchaseId) {

        kakaoService.kakaoPaySuccess(purchaseId, pgToken, tid);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @Operation(summary = "주문 취소 API.")
    @GetMapping("/purchase/{purchaseId}/cancel")
    public ResponseEntity<Void> purchaseCancel(@PathVariable("purchaseId") Long purchaseId)
            throws JsonProcessingException {

        //임시 코드
        Long memberId = 1L;
        beforePurchaseService.cancelPurchase(memberId, purchaseId);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @Operation(summary = "tossPay Approve API")
    @PostMapping("/purchase/toss-pay/approve")
    public ResponseEntity<Void> tossPaySuccess(@RequestBody TossPayApproveRequestDto requestDto,
                                               @RequestParam(value = "purchaseId") Long purchaseId)
            throws UnsupportedEncodingException {

        tossService.tossPaySuccess(purchaseId, requestDto);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }


}
