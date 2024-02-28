package com.project.legendsofleague.domain.member.exception;

import com.project.legendsofleague.common.exception.CustomException;
import org.springframework.http.HttpStatus;

public class InvalidIdInputException extends CustomException {
    public InvalidIdInputException(String username) {
        super(username + "은 올바르지 않은 입력입니다.", HttpStatus.BAD_REQUEST);
    }
}
