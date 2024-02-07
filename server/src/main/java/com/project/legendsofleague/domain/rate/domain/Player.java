package com.project.legendsofleague.domain.rate.domain;

import jakarta.persistence.*;
import lombok.Getter;

import static jakarta.persistence.EnumType.*;
import static jakarta.persistence.FetchType.*;
import static jakarta.persistence.GenerationType.*;

@Entity
@Getter
public class Player {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "player_id")
    private Long id;

    private String name;
    private String playerName;

    @Enumerated(STRING)
    private Position position;

    private Integer rate;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "team_id")
    private Team team;
}
