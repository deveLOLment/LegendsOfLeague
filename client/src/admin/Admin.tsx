import React from "react";
import { Link, NavLink } from "react-router-dom";

const Admin = () => {
  return (
    <div>
      <NavLink to="/coupon/create">
        <h2>쿠폰 생성 페이지</h2>
      </NavLink>
      <br />
      <NavLink to="/admin/item/add">
        <h2>아이템 생성 페이지</h2>
      </NavLink>
    </div>
  );
};

export default Admin;
