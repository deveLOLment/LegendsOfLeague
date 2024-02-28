import React from "react";
import "./App.css";
import LoginRoute from "./login/LoginRoute";
import KakaoPayRoutes from "./kakaopay/KakaoPayRoutes";
import CommonLayout from "./layout/CommonLayout";
import TossPayRoutes from "./tosspay/TossPayRoutes";
import OrderRoutes from "./order/OrderRoutes";
import CouponRoutes from "./Coupon/CouponRoutes";
import ItemRoutes from "./item/ItemRoutes";
function App() {
  return (
    <>
      <TossPayRoutes />
      <KakaoPayRoutes />
      <OrderRoutes />
      <CouponRoutes />
      <LoginRoute />
      <CartRoutes />
      <ItemRoutes />
      <ChatRouter />
      <ShopRoutes />
    </>
</CommonLayout>
  );
}

import KakaoPayRoutes from "./kakaopay/KakaoPayRoutes";
import CartRoutes from "./cart/CartRoutes";
import ChatRouter from "./chat/ChatRouter";

export default App;
