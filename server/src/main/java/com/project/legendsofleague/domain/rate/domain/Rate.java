package com.project.legendsofleague.domain.rate.domain;

import com.project.legendsofleague.domain.member.domain.Member;
import jakarta.persistence.*;
import lombok.Getter;

import static jakarta.persistence.FetchType.*;
import static jakarta.persistence.GenerationType.*;

@Entity
@Getter
public class Rate {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "rate_id")
    private Long id;

    private int score;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "game_id")
    private Game game;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "player_id")
    private Player player;
}
