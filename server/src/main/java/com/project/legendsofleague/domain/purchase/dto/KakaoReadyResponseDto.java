package com.project.legendsofleague.domain.purchase.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class KakaoReadyResponseDto {

    private String redirectUrl;
    private String tid;

    public static KakaoReadyResponseDto toDto(String redirectUrl, String tid) {
        KakaoReadyResponseDto dto = new KakaoReadyResponseDto();
        dto.redirectUrl = redirectUrl;
        dto.tid = tid;
        return dto;
    }
}
