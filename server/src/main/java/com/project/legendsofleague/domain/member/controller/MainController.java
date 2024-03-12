package com.project.legendsofleague.domain.member.controller;

import com.project.legendsofleague.domain.member.dto.CustomMemberDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Iterator;

@RestController
@Slf4j
public class MainController {

    @GetMapping("/")
    public String main(@AuthenticationPrincipal CustomMemberDetails memberDetails) {

//        log.info("principal={}", principal.getName());
//
        String username = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        Authentication authentication = SecurityContextHolder
                .getContext()
                .getAuthentication();

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        Iterator<? extends GrantedAuthority> iter = authorities.iterator();
        GrantedAuthority auth = iter.next();
        String role = auth.getAuthority();

        return "Main Controller" + username + role;
    }


}
