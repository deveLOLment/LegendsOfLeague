package com.project.legendsofleague.domain.membercoupon.exception;

import com.project.legendsofleague.common.exception.CustomException;
import org.springframework.http.HttpStatus;

public class AlreadyRegisteredCouponException extends CustomException {

    public AlreadyRegisteredCouponException() {
        super("이미 등록된 쿠폰입니다!", HttpStatus.BAD_REQUEST);

    }
}
