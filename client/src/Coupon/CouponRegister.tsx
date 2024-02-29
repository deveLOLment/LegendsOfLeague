import React, { useEffect, useState } from "react";
import { CouponResponseModel } from "./CouponModel";
import axios from "axios";
import Coupon from "./Coupon";
import { CouponContainer, CouponWrapper, ListWrapper } from "./CouponStyled";
import axiosInstance from "../common/AxiosInstance";

const CouponRegister = () => {
  const [couponList, setCouponList] = useState<CouponResponseModel[]>([]);

  const url = "/coupons";
  const fetchCouponList = async () => {
    try {
      const response = await axiosInstance.get(url);
      //TODO : 로그인한 쿠키값이 존재하면 넘기기

      const responseData: CouponResponseModel[] = response.data;

      console.log(responseData);
      setCouponList(responseData);
    } catch (e) {}
  };

  useEffect(() => {
    fetchCouponList();
  }, []);

  const handleCouponRegistered = () => {
    // 쿠폰 등록 후 쿠폰 리스트를 다시 가져옴
    fetchCouponList();
  };

  return (
    <CouponContainer>
      <h2>쿠폰 등록 페이지</h2>

      <CouponWrapper>
        {couponList.map((coupon) => (
          <Coupon
            key={coupon.id}
            coupon={coupon}
            onCouponRegistered={handleCouponRegistered}
          />
        ))}
      </CouponWrapper>
    </CouponContainer>
  );
};

export default CouponRegister;
