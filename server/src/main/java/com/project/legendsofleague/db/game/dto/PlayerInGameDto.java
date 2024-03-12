package com.project.legendsofleague.db.game.dto;

import com.project.legendsofleague.db.player.domain.Position;
import com.project.legendsofleague.db.playerInGame.domain.PlayerInGame;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PROTECTED;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
public class PlayerInGameDto {

    private Long playerId;
    private String playerName;
    private Position position;

    public static PlayerInGameDto toDto(PlayerInGame player) {
        PlayerInGameDto playerInGameDto = new PlayerInGameDto();
        playerInGameDto.playerId = player.getId();
        playerInGameDto.playerName = player.getPlayer().getPlayerName();
        playerInGameDto.position = player.getPlayer().getPosition();
        return playerInGameDto;
    }
}
