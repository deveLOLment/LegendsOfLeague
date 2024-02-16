package com.project.legendsofleague.domain.member.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {

    @GetMapping("/admin")
    public String getAdmin(){
        return "Admin Controller";
    }
}

