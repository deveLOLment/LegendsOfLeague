package com.project.legendsofleague.domain.buff.repository;

import com.project.legendsofleague.domain.buff.domain.Buff;
import com.project.legendsofleague.domain.member.domain.Member;
import com.project.legendsofleague.domain.reviewComment.domain.ReviewComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BuffRepository extends JpaRepository<Buff, Long> {
    Optional<Buff> findByMemberAndReviewComment(Member member, ReviewComment reviewComment);
}
