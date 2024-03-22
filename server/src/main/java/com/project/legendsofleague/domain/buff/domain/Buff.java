package com.project.legendsofleague.domain.buff.domain;

import com.project.legendsofleague.domain.member.domain.Member;
import com.project.legendsofleague.domain.reviewComment.domain.ReviewComment;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Buff {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "buff_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "review_comment_id")
    private ReviewComment reviewComment;

    public static Buff toEntity(ReviewComment reviewComment, Member member) {
        Buff buff = new Buff();
        buff.reviewComment = reviewComment;
        buff.member = member;
        return buff;
    }
}

