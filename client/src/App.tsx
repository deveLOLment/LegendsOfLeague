import React from "react";
import "./App.css";
import { Route, Routes } from "react-router-dom";
import Ex from "./Example/Ex";
import KakaoPayReady from "./kakaopay/KakaoPayReady";
import KakaoPayApprove from "./kakaopay/KakaoPayApprove";
import KakaoPayStart from "./kakaopay/KakaoPayStart";
import KakaoPaySuccess from "./kakaopay/KakaoPaySuccess";

function App() {
  return (
    <Routes>
      <Route path="/ex" element={<Ex />}></Route>
      <Route path="/purchase/kakaoPay" element={<KakaoPayStart />}></Route>
      <Route
        path="/purchase/kakaoPay/ready"
        element={<KakaoPayReady />}
      ></Route>
      <Route
        path="/purchase/kakaoPay/success"
        element={<KakaoPayApprove />}
      ></Route>
      <Route path="purchase/success" element={<KakaoPaySuccess />}></Route>
    </Routes>
  );
}

export default App;
