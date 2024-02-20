package com.project.legendsofleague.domain.purchase.exception;

import com.project.legendsofleague.common.exception.CustomException;
import org.springframework.http.HttpStatus;

public class InvalidMemberCouponException extends CustomException {

    public InvalidMemberCouponException() {
        super("쿠폰 적용에 실패했습니다.", HttpStatus.BAD_REQUEST);
    }
}
