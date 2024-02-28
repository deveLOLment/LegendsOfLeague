import React from "react";
import ReactDOM from "react-dom/client";
import "./index.css";
import App from "./App";
import reportWebVitals from "./reportWebVitals";
import { BrowserRouter } from "react-router-dom";
import "./aroma-master/css/main.css";
import "./aroma-master/css/style.css";
import "./aroma-master/vendors/bootstrap/bootstrap.min.css";
import "bootstrap/dist/css/bootstrap.min.css";
import "./cart/cart.css";
import "./item/item.css";
import "./shop/shop.css";
import "./naver/css/layout.css";
import "./naver/css/main.css";

const root = ReactDOM.createRoot(
  document.getElementById("root") as HTMLElement
);
root.render(
  <BrowserRouter>
    <App />
  </BrowserRouter>
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
