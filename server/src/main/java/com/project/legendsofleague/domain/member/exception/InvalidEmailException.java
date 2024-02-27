package com.project.legendsofleague.domain.member.exception;

import com.project.legendsofleague.common.exception.CustomException;
import org.springframework.http.HttpStatus;

public class InvalidEmailException extends CustomException {
    public InvalidEmailException(String email) {
        super(email + "는 올바르지 않은 입력입니다.", HttpStatus.BAD_REQUEST);
    }
}
