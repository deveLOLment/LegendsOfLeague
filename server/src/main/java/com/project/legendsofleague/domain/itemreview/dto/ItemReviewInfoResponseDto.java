package com.project.legendsofleague.domain.itemreview.dto;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ItemReviewInfoResponseDto {

    private Double overall;
    private int[] reviewArr;
    private List<ItemReviewResponseDto> reviewList = new ArrayList<>();


}
