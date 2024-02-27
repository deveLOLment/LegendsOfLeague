import React from "react";
import Cart from "./Cart";
import { Route, Routes } from "react-router-dom";

const CartRoutes = () => {
  return (
    <Routes>
      <Route path="/carts" element={<Cart />} />
    </Routes>
  );
};

export default CartRoutes;
