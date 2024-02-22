package com.project.legendsofleague.domain.purchase.exception;

import com.project.legendsofleague.common.exception.CustomException;
import org.springframework.http.HttpStatus;

public class ExternalApiResponseException extends CustomException {

    public ExternalApiResponseException() {
        super("외부 API와 통신중 오류가 발생했습니다.", HttpStatus.SERVICE_UNAVAILABLE);
    }
}
