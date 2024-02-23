import React from "react";
import "./App.css";
import LoginRoute from "./login/LoginRoute";

import TossPayRoutes from "./tosspay/TossPayRoutes";
import OrderRoutes from "./order/OrderRoutes";
import CouponRoutes from "./Coupon/CouponRoutes";
function App() {
  return (
    <>
      <TossPayRoutes />
      <KakaoPayRoutes />
      <OrderRoutes />
      <CouponRoutes />
      <LoginRoute />
    </>
  );
}

import KakaoPayRoutes from "./kakaopay/KakaoPayRoutes";

export default App;
