package com.project.legendsofleague.domain.rate.domain;

import com.project.legendsofleague.db.game.domain.Game;
import com.project.legendsofleague.db.playerInGame.domain.PlayerInGame;
import com.project.legendsofleague.domain.member.domain.Member;
import jakarta.persistence.*;
import lombok.Getter;

import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Getter
public class Rate {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "rate_id")
    private Long id;

    private Integer score;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "player_in_game_id")
    private PlayerInGame playerInGame;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "game_id")
    private Game game;

    public static Rate toEntity(Integer score) {
        Rate rate = new Rate();
        rate.score = score;
        return rate;
    }

    public void setPlayerInGameAndMemberAndGame(PlayerInGame playerInGame, Member member, Game game) {
        this.playerInGame = playerInGame;
        this.member = member;
        this.game = game;
    }
}
