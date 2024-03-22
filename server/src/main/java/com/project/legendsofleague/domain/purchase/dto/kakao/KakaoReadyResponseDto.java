package com.project.legendsofleague.domain.purchase.dto.kakao;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class KakaoReadyResponseDto {

    @Schema(description = "로그인을 진행할 카카오에서 제공하는 페이지", example = "https://kakao.com")
    private String redirectUrl;

    @Schema(description = "카카오페이 Transaction Id", example = "T123123")
    private String tid;

    public static KakaoReadyResponseDto toDto(String redirectUrl, String tid) {
        KakaoReadyResponseDto dto = new KakaoReadyResponseDto();
        dto.redirectUrl = redirectUrl;
        dto.tid = tid;
        return dto;
    }
}
