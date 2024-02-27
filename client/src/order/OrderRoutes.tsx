import React from "react";
import Order from "./Order";
import { Route, Routes } from "react-router-dom";
import KakaoPaySuccess from "../kakaopay/PurchaseSuccess";
import OrderInfo from "./OrderInfo";

import "../aroma-master/css/main.css";
import "../aroma-master/css/style.css";
import "../aroma-master/vendors/bootstrap/bootstrap.min.css";
import OrderList from "./OrderList";

const OrderRoutes = () => {
  return (
    <Routes>
      <Route path="/order" element={<Order />} />
      <Route path="purchase/success" element={<KakaoPaySuccess />} />
      <Route path="/order-list" element={<OrderList />} />
      <Route path="/order-info/:orderId" element={<OrderInfo />} />
    </Routes>
  );
};

export default OrderRoutes;
