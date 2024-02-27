import React from "react";
import { Route, Routes } from "react-router-dom";
import KakaoPayStart from "./KakaoPayStart";
import KakaoPayReady from "./KakaoPayReady";
import KakaoPayApprove from "./KakaoPayApprove";

const KakaoPayRoutes = () => {
  return (
    <Routes>
      <Route path="/purchase/kakaoPay" element={<KakaoPayStart />} />
      <Route path="/purchase/kakaoPay/ready" element={<KakaoPayReady />} />
      <Route path="/purchase/kakaoPay/success" element={<KakaoPayApprove />} />
    </Routes>
  );
};

export default KakaoPayRoutes;
