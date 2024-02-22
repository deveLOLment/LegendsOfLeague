package com.project.legendsofleague.domain.purchase.exception;

import com.project.legendsofleague.common.exception.CustomException;
import org.springframework.http.HttpStatus;

public class NotEnoughStockException extends CustomException {

    public NotEnoughStockException() {
        super("재고가 부족합니다.", HttpStatus.OK);

    }
}
