package com.project.legendsofleague.domain.rate.repository;

import com.project.legendsofleague.db.game.domain.Game;
import com.project.legendsofleague.db.playerInGame.domain.PlayerInGame;
import com.project.legendsofleague.domain.member.domain.Member;
import com.project.legendsofleague.domain.rate.domain.Rate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RateRepository extends JpaRepository<Rate, Long> {

    List<Rate> findAllByMemberAndGame(Member member, Game game);
    List<Rate> findAllByPlayerInGameAndScoreNot(PlayerInGame playerInGame, int score);

    List<Rate> findAllByGame(Game game);
}
