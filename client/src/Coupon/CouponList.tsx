import React, { useEffect, useState } from "react";
import { CouponContainer, CouponWrapper } from "./CouponStyled";
import Coupon from "./Coupon";
import { CouponResponseModel } from "./CouponModel";
import AxiosInstance from "../common/AxiosInstance";

const CouponList = () => {
  const [couponList, setCouponList] = useState<CouponResponseModel[]>([]);

  const url = "/member-coupons";

  const fetchData = async () => {
    try {
      const response = await AxiosInstance.get(url);
      const responseData: CouponResponseModel[] = response.data;
      setCouponList(responseData);
    } catch (e) {}
  };

  useEffect(() => {
    fetchData();
  }, []);

  return (
    <CouponContainer>
      <h2>쿠폰 조회 페이지</h2>

      <CouponWrapper>
        {couponList.map((coupon) => (
          <Coupon key={coupon.id} coupon={coupon} />
        ))}
      </CouponWrapper>
    </CouponContainer>
  );
};

export default CouponList;
