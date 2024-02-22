package com.project.legendsofleague.db.game.service;

import com.project.legendsofleague.db.game.domain.Game;
import com.project.legendsofleague.db.game.repository.GameRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class GameService {

    private final GameRepository gameRepository;
    @Transactional
    public void saveGame(List<Map<String, String>> matchSchedules) {

        for (Map<String, String> match : matchSchedules) {
            String dateTimeUtc = match.get("DateTime_UTC");

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime localDateTime = LocalDateTime.parse(dateTimeUtc, formatter);

            Game entity = Game.toEntity(match);
            entity.setDateTime(localDateTime);
            gameRepository.save(entity);
        }
    }
}
