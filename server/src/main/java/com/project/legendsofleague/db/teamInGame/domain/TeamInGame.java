package com.project.legendsofleague.db.teamInGame.domain;

import com.project.legendsofleague.db.team.domain.Team;
import com.project.legendsofleague.db.game.domain.Game;
import com.project.legendsofleague.db.playerInGame.domain.PlayerInGame;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class TeamInGame {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "team_in_game_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "team_id")
    private Team team;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "game_id")
    private Game game;

    private Integer score;

    @Enumerated(STRING)
    private Camp camp;

    private String uniqueGame;

    @OneToMany(mappedBy = "teamInGame")
    private List<PlayerInGame> rosters = new ArrayList<>();

    public static TeamInGame toEntity(String team1Score, Camp camp, String uniqueGame) {
        TeamInGame teamInGame = new TeamInGame();
        teamInGame.score = Integer.valueOf(team1Score);
        teamInGame.camp = camp;
        teamInGame.uniqueGame = uniqueGame;
        return teamInGame;
    }

    public void setTeamAndGame(Team team, Game game) {
        this.team = team;
        this.game = game;
    }
}