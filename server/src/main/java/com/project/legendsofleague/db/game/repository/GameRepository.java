package com.project.legendsofleague.db.game.repository;

import com.project.legendsofleague.db.game.domain.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, Long> {
    Game findByUniqueGame(String uniqueGame);
}
