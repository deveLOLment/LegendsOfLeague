import { useEffect, useRef, useState } from "react";
import { loadTossPayments } from "@tosspayments/payment-sdk";
import { nanoid } from "nanoid";
import { useLocation } from "react-router-dom";
import { PurchaseStartResponseDto } from "../order/Order";

const clientKey: string = process.env.REACT_APP_TOSS_CLIENT_KEY as string;

export function TossPayReady() {
  const location = useLocation();
  const purchaseInfo: PurchaseStartResponseDto = location.state.data;

  localStorage.setItem("purchaseId", purchaseInfo.id.toString());
  console.log(purchaseInfo);
  console.log(clientKey);

  useEffect(() => {
    const tossPay = async () => {
      try {
        const tossPayments = await loadTossPayments(clientKey);

        if (tossPayments != null) {
          try {
            await tossPayments.requestPayment("카드", {
              amount: purchaseInfo.amount,
              orderId: purchaseInfo.orderCode,
              orderName: purchaseInfo.orderName,
              customerName: purchaseInfo.memberNickname,
              successUrl: "http://localhost:3000/purchase/tossPay/success",
              failUrl: "http://localhost:8080/fail",
              flowMode: "DIRECT",
              easyPay: "토스페이",
            });
          } catch (error) {}
        }
      } catch (error) {}
    };

    tossPay();
  }, []);
  return <div></div>;
}

export default TossPayReady;
