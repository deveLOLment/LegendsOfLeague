package com.project.legendsofleague.domain.rate.dto;

import com.project.legendsofleague.domain.rate.domain.Rate;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PROTECTED;

@Getter
@NoArgsConstructor(access = PROTECTED)
public class RateResponseDto {
    private Long playerId;
    private String score;
    private String average;

    public static RateResponseDto toDto(Rate rate, String average) {
        RateResponseDto rateResponseDto = new RateResponseDto();
        rateResponseDto.playerId = rate.getPlayerInGame().getId();
        if (rate.getScore() == -1) {
            rateResponseDto.score = "";
        } else {
            rateResponseDto.score = String.valueOf(rate.getScore());
        }
        rateResponseDto.average = average;
        return rateResponseDto;
    }
}
