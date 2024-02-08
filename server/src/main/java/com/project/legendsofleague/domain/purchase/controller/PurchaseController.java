package com.project.legendsofleague.domain.purchase.controller;

import com.project.legendsofleague.domain.purchase.dto.KakaoReadyResponseDto;
import com.project.legendsofleague.domain.purchase.repository.PurchaseService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PurchaseController {

    private final PurchaseService purchaseService;

    @Operation(summary = "테스트 컨트롤러입니다.")
    @GetMapping("/purchase/test")
    public ResponseEntity<String> test() {
        return new ResponseEntity<>("test body", HttpStatus.OK);
    }

    @Operation(summary = "kakaoPay Ready API.")
    @GetMapping("/purchase/kakao-pay/ready")
    public ResponseEntity<KakaoReadyResponseDto> kakaoPay() {
        KakaoReadyResponseDto dto = purchaseService.kakaoPay();
        return new ResponseEntity<KakaoReadyResponseDto>(dto, HttpStatus.OK);
    }

    @Operation(summary = "kakaoPay Approve API.")
    @GetMapping("/purchase/approve")
    public ResponseEntity<String> success(@RequestParam("pg_token") String pgToken,
        @RequestParam(value = "tid", required = false) String tid) {

        String purchaseId = purchaseService.kakaoPaySuccess(pgToken, tid);
        return new ResponseEntity<String>(purchaseId, HttpStatus.OK);
    }
}
