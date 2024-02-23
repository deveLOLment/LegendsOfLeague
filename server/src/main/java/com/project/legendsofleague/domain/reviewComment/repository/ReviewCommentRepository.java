package com.project.legendsofleague.domain.reviewComment.repository;

import com.project.legendsofleague.domain.reviewComment.domain.ReviewComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewCommentRepository extends JpaRepository<ReviewComment, Long> {
}
