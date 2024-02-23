package com.project.legendsofleague.domain.rate.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class RateRequest {

    private Long gameId;
    private List<RosterDto> blue;
    private List<RosterDto> red;
}
