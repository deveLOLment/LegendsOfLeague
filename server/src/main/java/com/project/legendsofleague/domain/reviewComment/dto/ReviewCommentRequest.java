package com.project.legendsofleague.domain.reviewComment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ReviewCommentRequest {
    private Long gameId;
    private String comment;
}
