package com.project.legendsofleague.domain.purchase.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseResponseDto {

    private Long id;
    private Integer amount;
    private String providerName;
    private String orderName;
    private String orderCode;
    private String memberNickname;

}
