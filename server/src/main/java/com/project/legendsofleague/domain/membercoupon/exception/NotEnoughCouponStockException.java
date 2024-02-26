package com.project.legendsofleague.domain.membercoupon.exception;

import com.project.legendsofleague.common.exception.CustomException;
import org.springframework.http.HttpStatus;

public class NotEnoughCouponStockException extends CustomException {

    public NotEnoughCouponStockException() {
        super("쿠폰의 재고가 부족합니다!", HttpStatus.OK);
    }
}
