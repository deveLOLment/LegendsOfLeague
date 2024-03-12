package com.project.legendsofleague.domain.reviewComment.repository;

import com.project.legendsofleague.domain.reviewComment.domain.ReviewComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReviewCommentRepository extends JpaRepository<ReviewComment, Long> {

    List<ReviewComment> findAllByGameId(Long gameId);
    Optional<List<ReviewComment>> findByGameIdAndIsDeleteFalse(Long gameId);
    Optional<ReviewComment> findFirstByBuffCountGreaterThanEqualOrderByBuffCountDesc(int bufferCount);
}
