import React from "react";
import { Route, Routes } from "react-router-dom";
import CouponList from "./CouponList";
import CouponCreate from "./CouponCreate";

const CouponRoutes = () => {
  return (
    <Routes>
      <Route path="/coupon/list" element={<CouponList />} />
      <Route path="/coupon/create" element={<CouponCreate />} />
    </Routes>
  );
};

export default CouponRoutes;
