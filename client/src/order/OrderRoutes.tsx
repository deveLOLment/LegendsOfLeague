import React from "react";
import Order from "./Order";
import { Route, Routes } from "react-router-dom";
import KakaoPaySuccess from "../kakaopay/KakaoPaySuccess";

const OrderRoutes = () => {
  return (
    <Routes>
      <Route path="/order" element={<Order />} />
      <Route path="purchase/success" element={<KakaoPaySuccess />} />
    </Routes>
  );
};

export default OrderRoutes;
