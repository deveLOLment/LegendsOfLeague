import axios from "axios";
import React, { useEffect, useState } from "react";
import { useLocation } from "react-router-dom";

export interface KakaoPayReadyResponseDto {
  tid: string;
  redirectUrl: string;
}

const KakaoPayReady = () => {
  const location = useLocation();
  const purchaseId = location.state.id;
  console.log("", location);
  console.log(purchaseId);

  useEffect(() => {
    const fetchPayUrl = async () => {
      try {
        const url =
          "http://localhost:8080/purchase/kakao-pay/ready?purchaseId=" +
          purchaseId;
        const response = await axios.get(url);
        const paymentUrl = response.data.redirectUrl;
        const tid = response.data.tid;
        console.log(paymentUrl);
        console.log(tid);
        localStorage.setItem("purchaseId", purchaseId);
        localStorage.setItem("tid", tid);

        window.location.href = paymentUrl;
      } catch (error) {}
    };

    fetchPayUrl();
  }, []);

  return <div></div>;
};

export default KakaoPayReady;
