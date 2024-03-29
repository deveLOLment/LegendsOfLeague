import { useEffect, useRef, useState } from "react";
import { loadTossPayments } from "@tosspayments/payment-sdk";
import { nanoid } from "nanoid";
import { useLocation } from "react-router-dom";
import { PurchaseStartResponseModel } from "../order/OrderModels";

const clientKey: string = process.env.REACT_APP_TOSS_CLIENT_KEY as string;

export function TossPayReady() {
  const location = useLocation();
  const purchaseInfo: PurchaseStartResponseModel = location.state.data;

  console.log(purchaseInfo);

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
              successUrl: "https://develolment.site/purchase/tossPay/success",
              failUrl: "https://develolment.site/fail",
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
