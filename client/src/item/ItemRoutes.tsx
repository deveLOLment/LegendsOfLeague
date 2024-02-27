import React from "react";
import Item from "./Item";
import { Route, Routes } from "react-router-dom";

const ItemRoutes = () => {
  return (
    <Routes>
      <Route path="/item" element={<Item />} />
    </Routes>
  );
};

export default ItemRoutes;
