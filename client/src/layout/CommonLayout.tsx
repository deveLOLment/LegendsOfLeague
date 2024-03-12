import React, { ReactNode } from "react";
import Header from "./Header";
import "../naver/css/layout.css";

const CommonLayout = ({ children }: { children: ReactNode }) => {
  return (
    <div>
      <Header />
      {children}
    </div>
  );
};

export default CommonLayout;
