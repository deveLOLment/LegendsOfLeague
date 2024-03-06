import React from "react";
import "./App.css";
import LoginRoute from "./login/LoginRoute";

import TossPayRoutes from "./tosspay/TossPayRoutes";
import OrderRoutes from "./order/OrderRoutes";
import CouponRoutes from "./Coupon/CouponRoutes";
import ItemRoutes from "./item/ItemRoutes";
function App() {
  return (
    <CommonLayout>
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
import ChatRouter from "./chat/ChatRouter";
import CommonLayout from "./layout/CommonLayout";
import CartRoutes from "./cart/CartRoutes";
import ShopRoutes from "./shop/ShopRoutes";

export default App;
