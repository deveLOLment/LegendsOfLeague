package com.project.legendsofleague.domain.rate.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
public class RateRequestDto {

    private List<RateDto> playerScores;
}
