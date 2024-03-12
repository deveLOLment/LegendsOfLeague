import axios from "axios";
import React, { useEffect, useState } from "react";
import { useLocation, useNavigate } from "react-router-dom";
import AxiosInstance from "../common/AxiosInstance";

export interface KakaoPayReadyResponseDto {
  tid: string;
  redirectUrl: string;
}

const KakaoPayReady = () => {
  const location = useLocation();
  const navigate = useNavigate();
  const purchaseId = location.state.id;
  console.log("", location);
  console.log(purchaseId);

  useEffect(() => {
    const fetchPayUrl = async () => {
      try {
        const url = "/purchase/kakao-pay/ready?purchaseId=" + purchaseId;
        const response = await AxiosInstance.get(url);
        const paymentUrl = response.data.redirectUrl;
        const tid = response.data.tid;
        console.log(paymentUrl);
        console.log(tid);
        localStorage.setItem("purchaseId", purchaseId);
        localStorage.setItem("tid", tid);

        window.location.href = paymentUrl;
      } catch (error) {
        navigate("/purchase/fail");
      }
    };

    fetchPayUrl();
  }, []);

  return <div></div>;
};

export default KakaoPayReady;
