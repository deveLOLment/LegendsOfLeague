package com.project.legendsofleague.domain.purchase.dto.kakao;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class KakaoCancelRequestDto {

    @Schema(description = "카카오페이와 통신하기 위한 private key", example = "ex")
    private String cid;

    @Schema(description = "카카오페이 결제 TransactionId", example = "T12312344")
    private String tid;

    @Schema(description = "환불할 금액", example = "20000")
    private Integer cancel_amount;

    @Schema(description = "환불할 금액 중 면세 금액", example = "0")
    private Integer cancel_tax_free_amount;
}