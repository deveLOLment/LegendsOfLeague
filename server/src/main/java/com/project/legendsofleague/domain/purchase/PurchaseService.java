package com.project.legendsofleague.domain.purchase;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.legendsofleague.domain.purchase.dto.KakaoReadyRequestDto;
import com.project.legendsofleague.domain.purchase.dto.KakaoReadyResponseDto;
import com.project.legendsofleague.domain.purchase.dto.KakaoSuccessRequestDto;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class PurchaseService {

    private final WebClient.Builder webClientBuilder;
    private final ObjectMapper objectMapper;

    @Value("${kakaoPay.cid}")
    String cid;
    String partner_order_id = "partner_order_id";
    String partner_user_id = "partner_user_id";
    String item_name = "item_name";
    Integer quantity = 1;
    Integer total_amount = 1000;
    Integer tax_free_amount = 100;

    @Value("${kakaoPay.approval_url}")
    String approval_url;
    @Value("${kakaoPay.cancel_url}")
    String cancel_url;

    @Value("${kakaoPay.fail_url}")
    String fail_url;

    @Value("${kakaoPay.secretKey}")
    private String secretKey;

    @Transactional
    public KakaoReadyResponseDto kakaoPay() {

        KakaoReadyRequestDto kakaoReadyRequestDto
            = KakaoReadyRequestDto.toDto(cid, partner_order_id, partner_user_id, item_name,
            quantity, total_amount, tax_free_amount, approval_url, cancel_url, fail_url);

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
    public String kakaoPaySuccess(String pgToken, String tid) {

        KakaoSuccessRequestDto kakaoSuccessRequestDto = KakaoSuccessRequestDto.toDto(cid, tid,
            partner_order_id, partner_user_id, pgToken);
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

        String aid = map.get("aid");

        return aid;
    }
}
