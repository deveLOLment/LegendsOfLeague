package com.project.legendsofleague.domain.rate.domain;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

import static jakarta.persistence.FetchType.*;
import static jakarta.persistence.GenerationType.*;

@Entity
@Getter
public class Game {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "game_id")
    private Long id;

    private LocalDateTime matchDate;

    private boolean isBlueTeamVictory;
    private boolean isRedTeamVictory;
    private Integer blueTeamScore;
    private Integer redTeamScore;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "blue_team_id")
    private Team blueTeam;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "red_team_id")
    private Team redTeam;
}