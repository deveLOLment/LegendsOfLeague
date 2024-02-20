package com.project.legendsofleague.common.exception;

import org.springframework.http.HttpStatus;

/**
 * 각종 엔티티의 id를 잘못 넣었을때 던지는 Exception
 */
public class NotFoundInputValueException extends CustomException {

    public NotFoundInputValueException() {
        super("해당 엔티티를 찾을수 없습니다.", HttpStatus.NOT_FOUND);
    }
}
