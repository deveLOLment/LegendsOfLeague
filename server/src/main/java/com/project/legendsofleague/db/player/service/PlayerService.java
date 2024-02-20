package com.project.legendsofleague.db.player.service;

import com.project.legendsofleague.db.player.domain.Player;
import com.project.legendsofleague.db.player.repository.PlayerRepository;
import com.project.legendsofleague.db.team.domain.Team;
import com.project.legendsofleague.db.team.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PlayerService {

    private final PlayerRepository playerRepository;
    private final TeamRepository teamRepository;

    @Transactional
    public void savePlayer(List<Map<String, String>> players) {
        for (Map<String, String> player : players) {
            Team team = teamRepository.findByTeamName(player.get("Team"));
            Player entity = Player.toEntity(player);
            entity.setTeam(team);
            playerRepository.save(entity);
        }
    }
}
