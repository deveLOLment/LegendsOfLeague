package com.project.legendsofleague.domain.rate.controller;

import com.project.legendsofleague.domain.member.domain.CurrentMember;
import com.project.legendsofleague.domain.member.domain.Member;
import com.project.legendsofleague.domain.rate.dto.RateRequest;
import com.project.legendsofleague.domain.rate.service.RateService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rate")
public class RateController {

    private final RateService rateService;

    //TODO Member 필요
    @PostMapping
    public String ratePlayers(@CurrentMember Member member, @RequestBody RateRequest rateRequest) {
        rateService.saveRate(member, rateRequest);
        return "ok";
    }
}