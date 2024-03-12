package com.project.legendsofleague.domain.reviewComment.dto;

import lombok.Getter;

@Getter
public class ReviewCommentRequest {
    private Long gameId;
    private String comment;
}
