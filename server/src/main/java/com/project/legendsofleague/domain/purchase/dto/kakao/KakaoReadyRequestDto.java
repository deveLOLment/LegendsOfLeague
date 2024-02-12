package com.project.legendsofleague.domain.purchase.dto.kakao;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class KakaoReadyRequestDto {

    private String cid;
    private String partner_order_id;
    private String partner_user_id;
    private String item_name;
    private Integer quantity;
    private Integer total_amount;
    private Integer tax_free_amount;
    private String approval_url;
    private String cancel_url;
    private String fail_url;

    public static KakaoReadyRequestDto toDto(String cid, String partner_order_id,
        String partner_user_id,
        String item_name, Integer quantity,
        Integer total_amount, Integer tax_free_amount, String approval_url,
        String cancel_url, String fail_url) {

        KakaoReadyRequestDto dto = new KakaoReadyRequestDto();
        dto.cid = cid;
        dto.partner_order_id = partner_order_id;
        dto.partner_user_id = partner_user_id;
        dto.item_name = item_name;
        dto.quantity = quantity;
        dto.total_amount = total_amount;
        dto.tax_free_amount = tax_free_amount;
        dto.approval_url = approval_url;
        dto.cancel_url = cancel_url;
        dto.fail_url = fail_url;
        return dto;
    }
}
