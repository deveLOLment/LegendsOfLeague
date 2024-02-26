package com.project.legendsofleague.domain.rate.domain;

import com.project.legendsofleague.domain.member.domain.Member;
import com.project.legendsofleague.db.playerInGame.domain.PlayerInGame;
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

    private Integer rate;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "player_in_game_id")
    private PlayerInGame playerInGame;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
}
