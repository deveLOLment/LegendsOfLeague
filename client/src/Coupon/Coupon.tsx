import React from "react";
import { CouponResponseModel, CouponTypeRecord } from "./CouponModel";

import axios from "axios";
import "./Coupon.css";
import AxiosInstance from "../common/AxiosInstance";

const Coupon = ({
  coupon,
  onCouponRegistered,
}: {
  coupon: CouponResponseModel;
  onCouponRegistered: () => void;
}) => {
  const isAmountDiscountedCoupon = () => {
    const couponType = coupon.couponType;
    if (
      couponType === "ITEM_AMOUNT_DISCOUNT" ||
      couponType === "CATEGORY_AMOUNT_DISCOUNT"
    ) {
      return true;
    } else {
      return false;
    }
  };

  const registerCoupon = async () => {
    const url = "http://localhost:8080/member-coupons/";

    try {
      //TODO : 로그인한 쿠키값이 존재하면 넘기기
      const response = await AxiosInstance.post(url, {
        couponId: coupon.id,
      });

      alert("쿠폰이 등록되었습니다!");
      onCouponRegistered();
    } catch (e) {}
  };

  return (
    <dl className="PCommonCoupon">
      <dt className="PCommonCoupon__title">{coupon.name}</dt>
      <dd className="PCommonCoupon__information">
        <div className="PCommonCoupon__period">
          <span>
            {coupon.validityStartDate.toString()} -{" "}
            {coupon.validityEndDate.toString()}
          </span>
        </div>

        <div className="PCommonCoupon__bottom">
          <div className="PCommonCoupon__discount">
            {isAmountDiscountedCoupon() ? (
              <strong className="PCommonCoupon__discount__rate">
                {coupon.discountPrice}원
              </strong>
            ) : (
              <span className="PCommonCoupon__discount__rate">
                {coupon.discountPrice}%
              </span>
            )}
            <div className="PCommonCoupon__discount__information"></div>
          </div>
          <div className="PCommonCoupon__buttons">
            <span className="PCommonCoupon__button">적용상품</span>
            <span className="PCommonCoupon__button PCommonCoupon__button--download">
              쿠폰받기 <a onClick={registerCoupon}></a>
            </span>
          </div>
        </div>
      </dd>
    </dl>
  );
};

export default Coupon;
