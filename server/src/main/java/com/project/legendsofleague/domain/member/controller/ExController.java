package com.project.legendsofleague.domain.member.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExController {
    
    @GetMapping("/ex")
    public String ex() {
        return "Ex";
    }

}
