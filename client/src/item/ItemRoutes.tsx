import React from "react";
import Item from "./Item";
import { Route, Routes } from "react-router-dom";
import ItemCreate from "./ItemCreate";

const ItemRoutes = () => {
  return (
    <Routes>
      <Route path={`/item/:itemId`} element={<Item />} />
      <Route path="/admin/item/add" element={<ItemCreate />} />
    </Routes>
  );
};

export default ItemRoutes;
