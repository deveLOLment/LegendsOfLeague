import React, { ReactNode } from "react";
import Header from "./Header";
import { Outlet } from "react-router-dom";

const CommonLayout = ({ children }: { children: ReactNode }) => {
  return (
    <div className="home_container__1zgYr">
      <Header />
      {children}
    </div>
  );
};

export default CommonLayout;
