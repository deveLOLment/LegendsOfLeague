import React from "react";
import "./App.css";
import LoginRoute from "./login/LoginRoute";
import KakaoPayRoutes from "./kakaopay/KakaoPayRoutes";
import CommonLayout from "./layout/CommonLayout";
import TossPayRoutes from "./tosspay/TossPayRoutes";
import OrderRoutes from "./order/OrderRoutes";
import CouponRoutes from "./Coupon/CouponRoutes";
const App: React.FC = () => {
  return (
    <CommonLayout>
      <>
        <KakaoPayRoutes />
        <TossPayRoutes />
        <LoginRoute />
        <OrderRoutes />
        <CouponRoutes />
        <ChatRouter />
      </>
    </CommonLayout>
  );
};
import ChatRouter from "./chat/ChatRouter";

export default App;
