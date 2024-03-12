package com.project.legendsofleague.db.game.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PROTECTED;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
public class GameResultResponseDto {

    private Long gameId;
    private TeamInGameDto blue;
    private TeamInGameDto red;

    public static GameResultResponseDto toDto(Long gameId) {
        GameResultResponseDto gameResultResponseDto = new GameResultResponseDto();
        gameResultResponseDto.gameId = gameId;
        return gameResultResponseDto;
    }

    public void setBlue(TeamInGameDto teamInGameDto) {
        blue = teamInGameDto;
    }

    public void setRed(TeamInGameDto teamInGameDto) {
        red = teamInGameDto;
    }
}
