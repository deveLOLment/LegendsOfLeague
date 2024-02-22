import axios from "axios";
import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import ItemList from "./OrderTableComponent";
import {
  ItemCouponAppliedModel,
  OrderItemModel,
  OrderResponseDto,
  PurchaseStartResponseModel,
} from "./OrderModels";
import { CouponResponseModel } from "../Coupon/CouponModel";

const Order = () => {
  const navigate = useNavigate();
  const exampleOrderId = 1;

  const [selectedCoupons, setSelectedCoupons] = useState<
    Record<number, CouponResponseModel>
  >({});
  const [finalPriceMap, setFinalPriceMap] = useState<Record<number, number>>(
    {}
  );

  const [orderId, setOrderId] = useState<Number>(-1);
  const [orderItemList, setOrderItemList] = useState<OrderItemModel[]>([]);
  const [provider, setProvider] = useState<String>("");

  const handleCouponChange = (itemId: number, coupon: CouponResponseModel) => {
    setSelectedCoupons((prev) => ({ ...prev, [itemId]: coupon }));
  };

  const initFinalTotalPrice = (orderItemList: OrderItemModel[]) => {
    let sum = 0;
    console.log(orderItemList);
    orderItemList.forEach((item) => {
      sum += item.price;
    });
    return sum;
  };

  const [finalTotalPrice, setFinalTotalPrice] = useState<number>(0);

  const fetchOrderItemList = async () => {
    const url = "http://localhost:8080/order/" + exampleOrderId;
    try {
      const response = await axios.get(url);
      console.log(response);

      const responseData: OrderResponseDto = response.data;
      setOrderId(responseData.id);
      setOrderItemList(responseData.orderItemList);
      return responseData.orderItemList;
    } catch (e) {}
  };

  useEffect(() => {
    const fetchData = async (): Promise<void> => {
      const orderItemList: OrderItemModel[] | undefined =
        await fetchOrderItemList();

      if (orderItemList) {
        setFinalTotalPrice(initFinalTotalPrice(orderItemList));
        orderItemList.forEach((item) => {
          handleFinalPriceChange(item.id, item.price);
        });
      }
    };

    fetchData();
  }, []);

  const handleFinalPriceChange = (itemId: number, finalPrice: number) => {
    setFinalPriceMap((prev) => ({ ...prev, [itemId]: finalPrice }));
  };

  useEffect(() => {
    // Object.values를 사용하여 finalPriceMap의 값들을 배열로 얻음
    const values = Object.values(finalPriceMap);

    // reduce 함수를 사용하여 배열의 합을 계산
    const total = values.reduce((acc, currentValue) => acc + currentValue, 0);

    // total을 출력하거나 다른 작업 수행
    setFinalTotalPrice(total);
    console.log(finalPriceMap);
  }, [finalPriceMap]);

  const convertItemCouponAppliedModelList = (): ItemCouponAppliedModel[] => {
    return orderItemList.map((orderItem) => ({
      itemId: orderItem.id,
      itemName: orderItem.name,
      quantity: orderItem.count,
      memberCouponId: selectedCoupons[orderItem.id]?.id,
      price: finalPriceMap[orderItem.id],
    }));
  };

  const purchaseStart = async () => {
    const url = "http://localhost:8080/purchase/ready";

    const itemList = convertItemCouponAppliedModelList();
    let totalPrice = 0;
    itemList.forEach((item) => {
      totalPrice += item.price;
    });
    console.log();
    try {
      const response = await axios.post(url, {
        orderId: exampleOrderId,
        itemList: itemList,
        purchaseTotalPrice: totalPrice,
        provider: provider,
      });

      const responseData: PurchaseStartResponseModel = response.data;

      if (provider === "KAKAO") {
        navigate("/purchase/kakaoPay/ready", {
          state: { id: response.data.id },
        });
      } else if (provider === "TOSS") {
        navigate("/purchase/tossPay", {
          state: { id: response.data.id, data: responseData },
        });
      }
    } catch (e) {}
  };

  return (
    <div>
      {!orderItemList || orderItemList.length === 0 ? (
        <div>waiting</div>
      ) : (
        <>
          <div>
            <ItemList
              itemList={orderItemList}
              onCouponChange={handleCouponChange}
              onFinalPriceChange={handleFinalPriceChange}
              selectedCoupons={selectedCoupons}
            />
            <div>{}</div>
            <div>
              {" "}
              결제수단<br></br>
              <button onClick={() => setProvider("KAKAO")}>카카오 결제</button>
              <button onClick={() => setProvider("TOSS")}>토스 결제</button>
            </div>
            최종 가격 : {finalTotalPrice}
            <div>
              <span>selected Provider = {provider}</span>
            </div>
            <button onClick={purchaseStart}>결제하기</button>
          </div>
        </>
      )}
    </div>
  );
};

export default Order;
