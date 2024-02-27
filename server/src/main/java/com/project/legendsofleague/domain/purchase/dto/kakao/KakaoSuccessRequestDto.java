package com.project.legendsofleague.domain.purchase.dto.kakao;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class KakaoSuccessRequestDto {

    private String cid;
    private String tid;
    private String partner_order_id;
    private String partner_user_id;
    private String pg_token;

    public static KakaoSuccessRequestDto toDto(String cid, String tid, String partner_order_id,
        String partner_user_id, String pg_token) {
        KakaoSuccessRequestDto dto = new KakaoSuccessRequestDto();
        dto.cid = cid;
        dto.tid = tid;
        dto.partner_order_id = partner_order_id;
        dto.partner_user_id = partner_user_id;
        dto.pg_token = pg_token;
        return dto;
    }
}
