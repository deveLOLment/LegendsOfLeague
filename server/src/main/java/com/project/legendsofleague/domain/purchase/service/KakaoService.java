package com.project.legendsofleague.domain.purchase.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.legendsofleague.domain.purchase.domain.Purchase;
import com.project.legendsofleague.domain.purchase.dto.kakao.KakaoCancelRequestDto;
import com.project.legendsofleague.domain.purchase.dto.kakao.KakaoReadyRequestDto;
import com.project.legendsofleague.domain.purchase.dto.kakao.KakaoReadyResponseDto;
import com.project.legendsofleague.domain.purchase.dto.kakao.KakaoSuccessRequestDto;
import com.project.legendsofleague.domain.purchase.repository.PurchaseRepository;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.webjars.NotFoundException;

@Service
@RequiredArgsConstructor
public class KakaoService {

    private final WebClient.Builder webClientBuilder;
    private final PurchaseRepository purchaseRepository;

    private final ObjectMapper objectMapper;

    private final AfterPurchaseService afterPurchaseService;


    //임시 코드
    String partner_user_id = "testMemberNickname";

    @Value("${kakaoPay.cid}")
    String cid;
    @Value("${kakaoPay.approval_url}")
    String approval_url;
    @Value("${kakaoPay.cancel_url}")
    String cancel_url;
    @Value("${kakaoPay.fail_url}")
    String fail_url;
    @Value("${kakaoPay.secretKey}")
    private String secretKey;

    @Transactional
    public KakaoReadyResponseDto kakaoPay(Long purchaseId) {

        final Integer tax_free_amount = 0;

        Purchase purchase = purchaseRepository.findById(purchaseId).orElseThrow(() -> {
            throw new NotFoundException("주문 내역을 찾을수 없습니다.");
        });

        KakaoReadyRequestDto kakaoReadyRequestDto
            = KakaoReadyRequestDto.toDto(cid, purchase.getOrder().getOrderCode(), partner_user_id,
            purchase.getName(),
            purchase.getQuantity(), purchase.getTotalPrice(), tax_free_amount, approval_url,
            cancel_url, fail_url);

        Map<String, String> map = webClientBuilder.build()
            .post()
            .uri(uriBuilder -> uriBuilder
                .scheme("https")
                .host("open-api.kakaopay.com")
                .path("/online/v1/payment/ready")
                .build()
            )
            .header("Content-Type", "Application/json")
            .header("Authorization", secretKey)
            .body(BodyInserters.fromValue(kakaoReadyRequestDto))
            .retrieve()
            .bodyToMono(Map.class)
            .onErrorResume(e -> {
                throw new RuntimeException("kakaoPay error : " + e.getMessage());
            })
            .block();

        String tid = map.get("tid");
        String next_redirect_pc_url = map.get("next_redirect_pc_url");

        return KakaoReadyResponseDto.toDto(next_redirect_pc_url, tid);

    }

    @Transactional
    public void kakaoPaySuccess(Long purchaseId, String pgToken, String tid) {

        Purchase purchase = purchaseRepository.findById(purchaseId).orElseThrow(() -> {
            throw new NotFoundException("주문 내역을 찾을수 없습니다.");
        });

        KakaoSuccessRequestDto kakaoSuccessRequestDto = KakaoSuccessRequestDto.toDto(cid, tid,
            purchase.getOrder().getOrderCode(), partner_user_id, pgToken);
        Map<String, String> map = webClientBuilder.build()
            .post()
            .uri(uriBuilder -> uriBuilder
                .scheme("https")
                .host("open-api.kakaopay.com")
                .path("/online/v1/payment/approve")
                .build()
            )
            .header("Content-Type", "Application/json")
            .header("Authorization", secretKey)
            .body(BodyInserters.fromValue(kakaoSuccessRequestDto))
            .retrieve()
            .bodyToMono(Map.class)
            .onErrorResume(e -> {
                throw new RuntimeException("kakaoPay Success error : " + e.getMessage());
            })
            .block();

        afterPurchaseService.finishPurchase(purchaseId, tid);
    }

    @Transactional
    public void cancelPurchase(Purchase purchase) throws JsonProcessingException {

        KakaoCancelRequestDto kakaoCancelRequestDto = new KakaoCancelRequestDto(cid,
            purchase.getCode(),
            purchase.getTotalPrice(), 0);

        String body = webClientBuilder.build()
            .post()
            .uri(uriBuilder -> uriBuilder
                .scheme("https")
                .host("open-api.kakaopay.com")
                .path("/online/v1/payment/cancel")
                .build()
            )
            .header("Content-Type", "Application/json")
            .header("Authorization", secretKey)
            .body(BodyInserters.fromValue(kakaoCancelRequestDto))
            .retrieve()
            .bodyToMono(String.class)
            .onErrorResume(e -> {
                throw new RuntimeException("kakaoPay Success error : " + e.getMessage());
            })
            .block();

        Integer cancel_amount = Integer.parseInt(
            objectMapper.readTree(body).get("approved_cancel_amount").get("total").toString());
        purchase.cancelPurchase(cancel_amount);
    }


}
