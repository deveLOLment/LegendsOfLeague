package com.project.legendsofleague.db.player.repository;

import com.project.legendsofleague.db.player.domain.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, Long> {

    Player findByPlayerName(String playerName);
}
