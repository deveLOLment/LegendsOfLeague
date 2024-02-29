import React from "react";
import { Route, Routes } from "react-router-dom";
import CouponRegister from "./CouponRegister";
import CouponCreate from "./CouponCreate";
import CouponList from "./CouponList";

const CouponRoutes = () => {
  return (
    <Routes>
      <Route path="/coupon/register" element={<CouponRegister />} />
      <Route path="/coupon/list" element={<CouponList />} />
      <Route path="/coupon/create" element={<CouponCreate />} />
    </Routes>
  );
};

export default CouponRoutes;
