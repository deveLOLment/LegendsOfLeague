import React, { Component, useEffect, useState } from "react";
import CouponRegister from "./CouponRegister";
import CouponList from "./CouponList";
import { CouponResponseModel } from "./CouponModel";
import AxiosInstance from "../common/AxiosInstance";

const CouponTotal = () => {
  const [activeComponent, setActiveComponent] = useState<"register" | "list">(
    "register"
  );
  const [notRegisteredCouponList, setNotRegisteredCouponList] = useState<
    CouponResponseModel[]
  >([]);
  const [registeredCouponList, setRegisteredCouponList] = useState<
    CouponResponseModel[]
  >([]);

  const fetchNotRegisteredCouponList = async () => {
    const url = "/coupons";
    try {
      const response = await AxiosInstance.get(url);

      const responseData: CouponResponseModel[] = response.data;

      console.log(responseData);
      setNotRegisteredCouponList(responseData);
    } catch (e) {}
  };

  const fetchRegisteredCouponList = async () => {
    const url = "/member-coupons";
    try {
      const response = await AxiosInstance.get(url);
      const responseData: CouponResponseModel[] = response.data;
      setRegisteredCouponList(responseData);
    } catch (e) {}
  };

  const handleActiveComponent = (component: "register" | "list") => {
    setActiveComponent(component);
  };

  useEffect(() => {
    fetchNotRegisteredCouponList();
    fetchRegisteredCouponList();
  }, []);
  return (
    <div className="container">
      <div className="right_area">
        <div className="PCommonTopBox">
          <div className="PCommonTab">
            <a
              className="PCommonTab__button"
              onClick={() => handleActiveComponent("list")}
            >
              {" "}
              보유 쿠폰 <span>{registeredCouponList.length}</span>
            </a>
            <a
              className="PCommonTab__button"
              onClick={() => handleActiveComponent("register")}
            >
              {" "}
              쿠폰 받기 <span>{notRegisteredCouponList.length}</span>
            </a>
          </div>
        </div>
        {activeComponent === "register" ? (
          <CouponRegister
            couponList={notRegisteredCouponList}
            fetchNotRegisteredCouponList={fetchNotRegisteredCouponList}
            fetchRegisteredCouponList={fetchRegisteredCouponList}
          />
        ) : (
          <CouponList couponList={registeredCouponList} />
        )}
      </div>
    </div>
  );
};

export default CouponTotal;
