import React from "react";
import { CouponResponseModel, CouponTypeRecord } from "./CouponModel";
import {
  BottomWrapper,
  CommonCouponWrapper,
  CouponLabel,
  CouponTitle,
  DiscountInformationWrapper,
  DiscountWrapper,
  InformationWrapper,
  PeriodWrapper,
} from "./CouponStyled";
import axios from "axios";

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
      const response = await axios.post(url, {
        couponId: coupon.id,
      });

      alert("쿠폰이 등록되었습니다!");
      onCouponRegistered();
    } catch (e) {}
  };

  return (
    <CommonCouponWrapper>
      <InformationWrapper>
        <CouponLabel>{CouponTypeRecord[coupon.couponType]}</CouponLabel>
        <CouponTitle>{coupon.name}</CouponTitle>
      </InformationWrapper>
      <PeriodWrapper>
        {coupon.validityStartDate + " ~ " + coupon.validityEndDate}
      </PeriodWrapper>

      <BottomWrapper>
        <DiscountWrapper>
          {isAmountDiscountedCoupon() === false ? (
            <div className="DiscountWrapper__rate">
              {coupon.discountPrice} % 할인
            </div>
          ) : (
            <div className="DiscountWrapper__amount">
              {coupon.discountPrice}원 할인
            </div>
          )}

          <DiscountInformationWrapper>
            {/* Add other discount information */}
          </DiscountInformationWrapper>
        </DiscountWrapper>
        <button onClick={registerCoupon}>등록</button>
      </BottomWrapper>
    </CommonCouponWrapper>
  );
};

export default Coupon;
