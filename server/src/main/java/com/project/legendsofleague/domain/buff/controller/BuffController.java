package com.project.legendsofleague.domain.buff.controller;

import com.project.legendsofleague.domain.buff.dto.BuffRequestDto;
import com.project.legendsofleague.domain.buff.service.BuffService;
import com.project.legendsofleague.domain.member.domain.CurrentMember;
import com.project.legendsofleague.domain.member.domain.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/buff")
@Slf4j
public class BuffController {

    private final BuffService buffService;

    @PostMapping("/{gameId}")
    public String saveBuff(@CurrentMember Member member, @RequestBody BuffRequestDto buffRequestDto) {
        log.info("BuffController.saveBuff");
        buffService.saveBuff(member, buffRequestDto);
        return "ok";
    }
}
