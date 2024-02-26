package com.project.legendsofleague.domain.member.exception;

import com.project.legendsofleague.common.exception.CustomException;
import org.springframework.http.HttpStatus;

public class InvalidPasswordException extends CustomException {
    public InvalidPasswordException(String password) {
        super(password + "는 올바르지 않은 입력입니다.", HttpStatus.BAD_REQUEST);
    }
}
