package com.project.legendsofleague.member.controller;

import com.project.legendsofleague.member.domain.Member;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @GetMapping("/")
    public String home(){
       return   "return value";
    }

    @GetMapping("/login")
    public Member login(){
        return null;
    }
}
