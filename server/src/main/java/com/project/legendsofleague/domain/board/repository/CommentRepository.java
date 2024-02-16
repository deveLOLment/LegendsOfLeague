package com.project.legendsofleague.domain.board.repository;

import com.project.legendsofleague.domain.board.domain.BoardEntity;
import com.project.legendsofleague.domain.board.domain.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<CommentEntity, Long> {
  List<CommentEntity> findAllByBoardEntityOrderByIdDesc(BoardEntity boardEntity);
}
