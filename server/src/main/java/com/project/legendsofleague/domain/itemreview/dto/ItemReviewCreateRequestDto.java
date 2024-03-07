package com.project.legendsofleague.domain.itemreview.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Getter;

@Getter
public class ItemReviewCreateRequestDto {

    @Min(1)
    @Max(5)
    private Integer score;


    private String content;

}
