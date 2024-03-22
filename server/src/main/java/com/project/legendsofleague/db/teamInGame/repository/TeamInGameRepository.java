package com.project.legendsofleague.db.teamInGame.repository;

import com.project.legendsofleague.db.team.domain.Team;
import com.project.legendsofleague.db.teamInGame.domain.TeamInGame;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeamInGameRepository extends JpaRepository<TeamInGame, Long> {

    TeamInGame findByUniqueGameAndTeam(String uniqueGame, Team team);

    List<TeamInGame> findAllByGameId(Long gameId);
}
