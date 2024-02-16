package com.project.legendsofleague.domain.purchase.dto.kakao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class KakaoCancelRequestDto {

    private String cid;
    private String tid;
    private Integer cancel_amount;
    private Integer cancel_tax_free_amount;
}