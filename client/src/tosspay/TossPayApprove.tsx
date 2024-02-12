import React from "react";
import axios from "axios";
import { useSearchParams, useNavigate } from "react-router-dom";
import { useEffect } from "react";

const TossPayApprove = () => {
  const [searchParams] = useSearchParams();
  const navigate = useNavigate();

  useEffect(() => {
    const fetchPayUrl = async () => {
      try {
        const orderId = searchParams.get("orderId");
        const paymentKey = searchParams.get("paymentKey");
        const amount = searchParams.get("amount");
        const url = "http://localhost:8080/purchase/toss-pay/approve";
        const response = await axios.post(url, {
          orderId: orderId,
          paymentKey: paymentKey,
          amount: amount,
        });

        navigate("/purchase/success");
      } catch (error) {}
    };

    fetchPayUrl();
  }, []);

  return <div>TossPayApprove</div>;
};

export default TossPayApprove;
