package com.project.legendsofleague.domain.reviewComment.domain;

import com.project.legendsofleague.common.BaseEntity;
import com.project.legendsofleague.db.game.domain.Game;
import com.project.legendsofleague.domain.member.domain.Member;
import com.project.legendsofleague.domain.reviewComment.dto.ReviewCommentRequest;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.*;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class ReviewComment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "review_comment_id")
    private Long id;

    private String comment;
    private boolean isDelete;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "game_id")
    private Game game;

    private int buffCount;

    public void updateBuffCount(Integer buffCount) {
        this.buffCount += buffCount;
    }
    public static ReviewComment toEntity(ReviewCommentRequest reviewCommentRequest) {
        ReviewComment reviewComment = new ReviewComment();
        reviewComment.comment = reviewCommentRequest.getComment();
        return reviewComment;
    }

    public void setMemberAndGame(Member member, Game game) {
        this.member = member;
        this.game = game;
    }

}
