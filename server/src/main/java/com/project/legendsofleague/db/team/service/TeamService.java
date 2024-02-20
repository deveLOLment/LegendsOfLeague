package com.project.legendsofleague.db.team.service;

import com.project.legendsofleague.db.team.domain.Team;
import com.project.legendsofleague.db.team.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class TeamService {

    private final TeamRepository teamRepository;

    @Transactional
    public void saveTeam(List<Map<String, String>> teams) {
        for (Map<String, String> team : teams) {
            Team entity = Team.toEntity(team);
            teamRepository.save(entity);
        }
    }
}
