import React from "react";
import { Route, Routes } from "react-router-dom";
import TossPayReady from "./TossPayReady";
import TossPayApprove from "./TossPayApprove";

const TossPayRoutes = () => {
  return (
    <Routes>
      <Route path="purchase/tossPay" element={<TossPayReady />} />
      <Route path="purchase/tossPay/success" element={<TossPayApprove />} />
    </Routes>
  );
};

export default TossPayRoutes;
