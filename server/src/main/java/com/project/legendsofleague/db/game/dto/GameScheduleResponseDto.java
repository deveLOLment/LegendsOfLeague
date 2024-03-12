package com.project.legendsofleague.db.game.dto;

import com.project.legendsofleague.db.game.domain.Game;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import static lombok.AccessLevel.PROTECTED;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
public class GameScheduleResponseDto {
    private Long gameId;
    private LocalDateTime dateTime;
    private TeamInGameDto blue;
    private TeamInGameDto red;

    public static GameScheduleResponseDto toDto(Game game) {
        GameScheduleResponseDto gameScheduleResponseDto = new GameScheduleResponseDto();
        gameScheduleResponseDto.gameId = game.getId();
        gameScheduleResponseDto.dateTime = game.getDateTime();
        return gameScheduleResponseDto;
    }

    public void setBlue(TeamInGameDto teamInGameDto) {
        blue = teamInGameDto;
    }

    public void setRed(TeamInGameDto teamInGameDto) {
        red = teamInGameDto;
    }
}
