import React, { ReactNode } from "react";
import Header from "./Header";
import "../naver/css/layout.css";

const CommonLayout = ({ children }: { children: ReactNode }) => {
  return (
    <div className="layout_wrapper__1VlyL">
      <Header />
      {children}
    </div>
  );
};

export default CommonLayout;
