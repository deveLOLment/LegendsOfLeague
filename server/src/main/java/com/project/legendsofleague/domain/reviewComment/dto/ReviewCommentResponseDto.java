package com.project.legendsofleague.domain.reviewComment.dto;

import com.project.legendsofleague.domain.reviewComment.domain.ReviewComment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PROTECTED;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
public class ReviewCommentResponseDto {
    private Long reviewCommentId;
    private Long gameId;
    private String username;
    private String reviewComment;
    private int buffCount;

    public static ReviewCommentResponseDto toDto(ReviewComment reviewComment) {
        ReviewCommentResponseDto reviewCommentResponseDto = new ReviewCommentResponseDto();
        reviewCommentResponseDto.reviewCommentId = reviewComment.getId();
        reviewCommentResponseDto.gameId = reviewComment.getGame().getId();
        reviewCommentResponseDto.username = reviewComment.getMember().getUsername();
        reviewCommentResponseDto.reviewComment = reviewComment.getComment();
        reviewCommentResponseDto.buffCount = reviewComment.getBuffCount();
        return reviewCommentResponseDto;
    }
}
