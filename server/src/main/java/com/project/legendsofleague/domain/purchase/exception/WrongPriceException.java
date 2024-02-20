package com.project.legendsofleague.domain.purchase.exception;

import com.project.legendsofleague.common.exception.CustomException;
import org.springframework.http.HttpStatus;

public class WrongPriceException extends CustomException {

    public WrongPriceException() {
        super("잘못된 가격 계산입니다.", HttpStatus.BAD_REQUEST);
    }

    
}
