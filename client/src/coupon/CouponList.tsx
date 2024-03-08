import React, { useEffect, useState } from "react";
import { CouponContainer, CouponWrapper } from "./CouponStyled";
import Coupon from "./Coupon";
import { CouponResponseModel } from "./CouponModel";
import AxiosInstance from "../common/AxiosInstance";

type CouponListProps = {
  couponList: CouponResponseModel[];
};

const CouponList: React.FC<CouponListProps> = ({ couponList }) => {
  return (
    <div className="PCommonCouponList">
      {couponList.map((coupon) => (
        <Coupon key={coupon.id} coupon={coupon} />
      ))}
    </div>
  );
};

export default CouponList;
