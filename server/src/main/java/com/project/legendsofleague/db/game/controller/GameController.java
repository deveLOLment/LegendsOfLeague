package com.project.legendsofleague.db.game.controller;

import com.project.legendsofleague.db.game.dto.GameResultResponseDto;
import com.project.legendsofleague.db.game.dto.GameScheduleResponseDto;
import com.project.legendsofleague.db.game.service.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
@RequestMapping("/games")
public class GameController {

    private final GameService gameService;

    @GetMapping("/schedule")
    public ResponseEntity<List<GameScheduleResponseDto>> getGameSchedule() {
        List<GameScheduleResponseDto> schedule = gameService.getGames();
        return new ResponseEntity<>(schedule, OK);
    }

    @GetMapping("/{gameId}")
    public ResponseEntity<GameResultResponseDto> getGameResult(@PathVariable Long gameId) {
        GameResultResponseDto teamsAndRostersInGame = gameService.getTeamsAndRostersInGame(gameId);
        return new ResponseEntity<>(teamsAndRostersInGame, OK);
    }


}
