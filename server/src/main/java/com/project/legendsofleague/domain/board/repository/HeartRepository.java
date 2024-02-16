package com.project.legendsofleague.domain.board.repository;

import com.project.legendsofleague.domain.board.domain.HeartEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HeartRepository extends JpaRepository<HeartEntity, Long> {
}
