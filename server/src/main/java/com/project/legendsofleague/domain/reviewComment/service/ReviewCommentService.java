package com.project.legendsofleague.domain.reviewComment.service;

import com.project.legendsofleague.db.game.domain.Game;
import com.project.legendsofleague.db.game.repository.GameRepository;
import com.project.legendsofleague.domain.member.domain.Member;
import com.project.legendsofleague.domain.reviewComment.domain.ReviewComment;
import com.project.legendsofleague.domain.reviewComment.dto.ReviewCommentRequest;
import com.project.legendsofleague.domain.reviewComment.repository.ReviewCommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReviewCommentService {

    private final ReviewCommentRepository reviewCommentRepository;
    private final GameRepository gameRepository;

    //한줄평 저장
    @Transactional
    public void saveReviewComment(Member member, ReviewCommentRequest reviewCommentRequest) {
        //TODO 예외 처리 추가
        Game game = gameRepository.findById(reviewCommentRequest.getGameId()).orElseThrow();
        ReviewComment reviewComment = ReviewComment.toEntity(reviewCommentRequest);
        reviewComment.setMemberAndGame(member, game);
        reviewCommentRepository.save(reviewComment);
    }
}
