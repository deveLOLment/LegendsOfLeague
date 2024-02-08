import axios from "axios";
import React, { useEffect, useState } from "react";

export interface KakaoPayReadyResponseDto {
  tid: string;
  redirectUrl: string;
}

const KakaoPayReady = () => {
  useEffect(() => {
    const fetchPayUrl = async () => {
      try {
        console.log("hello!!!!");
        const url = "http://localhost:8080/purchase/kakao-pay/ready";
        const response = await axios.get(url);
        const paymentUrl = response.data.redirectUrl;
        const tid = response.data.tid;
        console.log(paymentUrl);
        console.log(tid);

        localStorage.setItem("tid", tid);

        window.location.href = paymentUrl;
      } catch (error) {}
    };

    fetchPayUrl();
  }, []);

  return <div></div>;
};

export default KakaoPayReady;
