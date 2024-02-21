import React from "react";
import "./App.css";
import { Route, Routes } from "react-router-dom";
import KakaoPayReady from "./kakaopay/KakaoPayReady";
import KakaoPayApprove from "./kakaopay/KakaoPayApprove";
import KakaoPayStart from "./kakaopay/KakaoPayStart";
import KakaoPaySuccess from "./kakaopay/KakaoPaySuccess";
import TossPayReady from "./tosspay/TossPayReady";
import TossPayApprove from "./tosspay/TossPayApprove";
import Order from "./order/Order";
import GoogleLoginReady from "./login/GoogleLoginReady";
import LogOut from './login/LogOut';
import Login from "./login/LogIn";
import AdminPage from './admin/AdminPage';
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
            <LoginRoute/>
        </>

);
}

import KakaoPayRoutes from "./kakaopay/KakaoPayRoutes";

export default App;
