package com.project.legendsofleague.common.exception;

import org.springframework.http.HttpStatus;

/**
 * 유효하지 않은 입력값을 넣은 경우 던지는 예외
 */
public class WrongInputException extends CustomException {

    public WrongInputException() {
        super("잘못된 입력입니다!", HttpStatus.BAD_REQUEST);
    }
}
