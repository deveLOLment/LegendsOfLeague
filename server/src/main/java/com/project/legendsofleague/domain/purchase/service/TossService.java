package com.project.legendsofleague.domain.purchase.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.legendsofleague.domain.purchase.domain.Purchase;
import com.project.legendsofleague.domain.purchase.dto.toss.TossCancelRequestDto;
import com.project.legendsofleague.domain.purchase.dto.toss.TossPayApproveRequestDto;
import com.project.legendsofleague.domain.purchase.repository.PurchaseRepository;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class TossService {

    private final WebClient.Builder webClientBuilder;
    private final PurchaseRepository purchaseRepository;
    private final AfterPurchaseService afterPurchaseService;

    private final ObjectMapper objectMapper;

    @Value("${toss.secret-key}")
    private String tossSecretKey;

    @Transactional
    public Boolean tossPaySuccess(Long purchaseId, TossPayApproveRequestDto requestDto)
        throws UnsupportedEncodingException {

        String authorizations = getAuthorizations();

        Map<String, String> map = webClientBuilder.build()
            .post()
            .uri(uriBuilder -> uriBuilder
                .scheme("https")
                .host("api.tosspayments.com")
                .path("/v1/payments/confirm")
                .build()
            )
            .header("Content-Type", "application/json")
            .header("Authorization", authorizations)
            .body(BodyInserters.fromValue(requestDto))
            .retrieve()
            .bodyToMono(Map.class)
            .onErrorResume(e -> {
                throw new RuntimeException("TossPay Success error : " + e.getMessage());
            })
            .block();

        return afterPurchaseService.finishPurchase(purchaseId, map.get("paymentKey"));

    }

    public void cancelPurchase(Purchase purchase) {
        String authorizations = getAuthorizations();

        TossCancelRequestDto requestDto = new TossCancelRequestDto("단순 변심");

        Map<String, String> map = webClientBuilder.build()
            .post()
            .uri(uriBuilder -> uriBuilder
                .scheme("https")
                .host("api.tosspayments.com")
                .path("/v1/payments/" + purchase.getCode() + "/cancel")
                .build()
            )
            .header("Content-Type", "application/json")
            .header("Authorization", authorizations)
            .body(BodyInserters.fromValue(requestDto))
            .retrieve()
            .bodyToMono(Map.class)
            .onErrorResume(e -> {
                throw new RuntimeException("TossPay Cancel error : " + e.getMessage());
            })
            .block();

        afterPurchaseService.cancelPurchase(purchase);
    }

    private String getAuthorizations() {
        Base64.Encoder encoder = Base64.getEncoder();
        byte[] encodedBytes = encoder.encode((tossSecretKey + ":").getBytes(
            StandardCharsets.UTF_8));
        return "Basic " + new String(encodedBytes);
    }


}
