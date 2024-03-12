package com.project.legendsofleague.domain.rate.controller;

import com.project.legendsofleague.domain.member.domain.CurrentMember;
import com.project.legendsofleague.domain.member.domain.Member;
import com.project.legendsofleague.domain.rate.dto.RateRequestDto;
import com.project.legendsofleague.domain.rate.dto.RateResponseDto;
import com.project.legendsofleague.domain.rate.service.RateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rate")
@Slf4j
public class RateController {

    private final RateService rateService;

    @GetMapping("/{gameId}")
    public ResponseEntity<List<RateResponseDto>> getRates(@CurrentMember Member member, @PathVariable Long gameId) {
        log.info("RateController.getRates");
        List<RateResponseDto> rates = rateService.findRates(member, gameId);
        if (rates.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(rates, HttpStatus.OK);
        }
    }

    @PostMapping("/{gameId}")
    public String ratePlayers(@CurrentMember Member member, @RequestBody RateRequestDto rateRequestDto, @PathVariable Long gameId) {
        log.info("RateController.ratePlayers");
        log.info("넘어온 데이터들={}", rateRequestDto);

        rateService.saveRate(member, rateRequestDto, gameId);
        return "ok";
    }
}