package com.project.legendsofleague.domain.member.dto;

import com.project.legendsofleague.domain.member.domain.ROLE;
import lombok.Getter;

@Getter
public class RegisterDto {
    private String username;
    private String password;
    private ROLE role;
}
