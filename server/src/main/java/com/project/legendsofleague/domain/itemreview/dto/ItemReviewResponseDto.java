package com.project.legendsofleague.domain.itemreview.dto;

import com.project.legendsofleague.domain.itemreview.domain.ItemReview;
import lombok.Getter;

@Getter
public class ItemReviewResponseDto {

    private final Long id;
    private final String nickname;
    private final String content;
    private final Integer score;

    public ItemReviewResponseDto(ItemReview itemReview) {
        this.id = itemReview.getId();
        this.nickname = itemReview.getMember().getNickname();
        this.content = itemReview.getContent();
        this.score = itemReview.getScore();
    }

}
