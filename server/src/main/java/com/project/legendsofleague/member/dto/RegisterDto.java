package com.project.legendsofleague.member.dto;

import com.project.legendsofleague.member.domain.ROLE;
import lombok.Getter;

@Getter
public class RegisterDto {
    private String username;
    private String password;
    private ROLE role;
}
