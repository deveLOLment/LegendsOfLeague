import React from "react";
import "./App.css";

import TossPayRoutes from "./tosspay/TossPayRoutes";
import OrderRoutes from "./order/OrderRoutes";
import CouponRoutes from "./Coupon/CouponRoutes";
import KakaoPayRoutes from "./kakaopay/KakaoPayRoutes";

function App() {
  return (
    <>
      <TossPayRoutes />
      <KakaoPayRoutes />
      <OrderRoutes />
      <CouponRoutes />
    </>
  );
}

export default App;
