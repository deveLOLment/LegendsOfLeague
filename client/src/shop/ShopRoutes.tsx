import React from "react";
import Shop from "./Shop";
import { Route, Routes } from "react-router-dom";

const ShopRoutes = () => {
  return (
    <Routes>
      <Route path="/shop" element={<Shop />} />
    </Routes>
  );
};

export default ShopRoutes;
