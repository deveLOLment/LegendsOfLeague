package com.project.legendsofleague.db.team.repository;

import com.project.legendsofleague.db.team.domain.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Long> {
    Team findByTeamName(String teamName);
}
