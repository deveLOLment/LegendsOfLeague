package com.project.legendsofleague.db.game.dto;

import com.project.legendsofleague.db.teamInGame.domain.TeamInGame;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TeamInGameDto {
    private Long teamInGameId;
    private String teamName;
    private Integer score;
    private List<PlayerInGameDto> rosters = new ArrayList<>();

    public static TeamInGameDto toDto(TeamInGame team) {
        TeamInGameDto teamInGameDto = new TeamInGameDto();
        teamInGameDto.teamInGameId = team.getId();
        teamInGameDto.teamName = team.getTeam().getTeamName();
        teamInGameDto.score = team.getScore();
        return teamInGameDto;
    }

    public void setRosters(PlayerInGameDto playerInGameDto) {
        rosters.add(playerInGameDto);
    }
}
