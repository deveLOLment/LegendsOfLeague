package com.project.legendsofleague.domain.purchase.dto.toss;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TossPayApproveRequestDto {

    private String paymentKey;
    private String orderId;
    private Integer amount;
}
