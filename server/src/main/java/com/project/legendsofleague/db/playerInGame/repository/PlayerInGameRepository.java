package com.project.legendsofleague.db.playerInGame.repository;

import com.project.legendsofleague.db.playerInGame.domain.PlayerInGame;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerInGameRepository extends JpaRepository<PlayerInGame, Long> {
}
