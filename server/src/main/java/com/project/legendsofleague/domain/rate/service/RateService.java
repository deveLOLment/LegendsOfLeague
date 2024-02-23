package com.project.legendsofleague.domain.rate.service;

import com.project.legendsofleague.db.playerInGame.domain.PlayerInGame;
import com.project.legendsofleague.db.playerInGame.repository.PlayerInGameRepository;
import com.project.legendsofleague.domain.member.domain.Member;
import com.project.legendsofleague.domain.rate.domain.Rate;
import com.project.legendsofleague.domain.rate.dto.RateRequest;
import com.project.legendsofleague.domain.rate.dto.RosterDto;
import com.project.legendsofleague.domain.rate.repository.RateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RateService {

    private final RateRepository rateRepository;
    private final PlayerInGameRepository playerInGameRepository;
    //평점 저장
    @Transactional
    public void saveRate(Member member, RateRequest rateRequest) {
        //TODO 예외 처리 추가
        for (RosterDto rosterDto : rateRequest.getBlue()) {
            Long playerInGameId = rosterDto.getPlayer();
            PlayerInGame playerInGame = playerInGameRepository.findById(playerInGameId).orElseThrow();
            Rate rate = Rate.toEntity(rosterDto.getScore());
            rate.setPlayerInGameAndMember(playerInGame, member);
            rateRepository.save(rate);
        }

        for (RosterDto rosterDto : rateRequest.getRed()) {
            Long playerInGameId = rosterDto.getPlayer();
            PlayerInGame playerInGame = playerInGameRepository.findById(playerInGameId).orElseThrow();
            Rate rate = Rate.toEntity(rosterDto.getScore());
            rate.setPlayerInGameAndMember(playerInGame, member);
            rateRepository.save(rate);
        }
    }
}
