package com.project.legendsofleague.domain.reviewComment.domain;

import com.project.legendsofleague.db.game.domain.Game;
import com.project.legendsofleague.domain.member.domain.Member;
import jakarta.persistence.*;
import lombok.Getter;

import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Getter
public class ReviewComment {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "review_comment_id")
    private Long id;

    private String comment;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "game_id")
    private Game game;
}
