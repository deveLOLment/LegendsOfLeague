import React, { useEffect, useState } from "react";
import { CouponResponseModel } from "./CouponModel";
import Coupon from "./Coupon";
import { CouponContainer, CouponWrapper } from "./CouponStyled";
import axiosInstance from "../common/AxiosInstance";

type CouponListProps = {
  couponList: CouponResponseModel[];
  fetchNotRegisteredCouponList: () => void;
  fetchRegisteredCouponList: () => void;
};

const CouponRegister: React.FC<CouponListProps> = ({
  couponList,
  fetchNotRegisteredCouponList,
  fetchRegisteredCouponList,
}) => {
  const handleCouponRegistered = () => {
    fetchNotRegisteredCouponList();
    fetchRegisteredCouponList();
  };

  return (
    <div className="PCommonCouponList">
      {couponList.map((coupon) => (
        <Coupon
          key={coupon.id}
          coupon={coupon}
          onCouponRegistered={handleCouponRegistered}
        />
      ))}
    </div>
  );
};

export default CouponRegister;
