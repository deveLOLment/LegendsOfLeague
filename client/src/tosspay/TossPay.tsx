import { useEffect, useRef, useState } from "react";
import { loadTossPayments } from "@tosspayments/payment-sdk";
import { nanoid } from "nanoid";

const clientKey: string = process.env.REACT_APP_TOSS_CLIENT_KEY as string;

export function TossPayReady() {
  useEffect(() => {
    const tossPay = async () => {
      try {
        const tossPayments = await loadTossPayments(clientKey);

        if (tossPayments != null) {
          try {
            await tossPayments.requestPayment("카드", {
              amount: 15000,
              orderId: nanoid(),
              orderName: "토스 티셔츠 외 2건",
              customerName: "박토스",
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
