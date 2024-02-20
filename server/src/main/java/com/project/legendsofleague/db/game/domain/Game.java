package com.project.legendsofleague.db.game.domain;

import com.project.legendsofleague.db.teamInGame.domain.TeamInGame;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Game {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "game_id")
    private Long id;

    @OneToMany(mappedBy = "game")
    private List<TeamInGame> games = new ArrayList<>();

    private String uniqueGame;

    private LocalDateTime dateTime;

    public static Game toEntity(Map<String, String> match) {
        Game game = new Game();
        game.uniqueGame = match.get("MatchId");
        return game;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}