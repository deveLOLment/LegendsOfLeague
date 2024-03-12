import axios from "axios";
import React, { useState, useEffect } from "react";
import { useLocation, useNavigate } from "react-router-dom";
import OrderItemList from "./OrderTableComponent";
import {
  ItemCouponAppliedModel,
  OrderItemModel,
  OrderResponseDto,
  PurchaseStartResponseModel,
} from "./OrderModels";
import { CouponResponseModel } from "../coupon/CouponModel";
import AxiosInstance from "../common/AxiosInstance";
import BlogBanner from "../layout/BlogBanner";

const Order = () => {
  const navigate = useNavigate();
  const location = useLocation();
  // const exampleOrderId = 1;
  const initialOrderId = location.state.orderId;
  console.log(initialOrderId);

  const [selectedCoupons, setSelectedCoupons] = useState<
    Record<number, CouponResponseModel>
  >({});
  const [finalPriceMap, setFinalPriceMap] = useState<Record<number, number>>(
    {}
  );

  const [totalPrice, setTotalPrice] = useState<number>(-1);
  const [orderId, setOrderId] = useState<number>(initialOrderId);
  const [orderItemList, setOrderItemList] = useState<OrderItemModel[]>([]);
  const [provider, setProvider] = useState<String>("");

  const handleCouponChange = (itemId: number, coupon: CouponResponseModel) => {
    setSelectedCoupons((prev) => ({ ...prev, [itemId]: coupon }));
  };

  const initFinalTotalPrice = (orderItemList: OrderItemModel[]) => {
    let sum = 0;
    console.log(orderItemList);
    orderItemList.forEach((item) => {
      sum += item.price * item.count;
    });
    return sum;
  };

  const initFinalPriceMap = (orderResponseDto: OrderResponseDto) => {
    const newFinalPriceMap: Record<number, number> = {};
    for (const item of orderResponseDto.orderItemList) {
      console.log(item);
      newFinalPriceMap[item.id] = item.price * item.count;
    }
    setFinalPriceMap(newFinalPriceMap);
  };
  const [finalTotalPrice, setFinalTotalPrice] = useState<number>(0);

  const fetchOrderItemList = async () => {
    const url = "http://localhost:8080/order/" + initialOrderId;
    try {
      const response = await AxiosInstance.get(url);
      console.log(response);

      const responseData: OrderResponseDto = response.data;
      setOrderId(responseData.id);
      setOrderItemList(responseData.orderItemList);
      setTotalPrice(responseData.totalPrice);
      // initFinalPriceMap(responseData);
      return responseData.orderItemList;
    } catch (e) {}
  };

  useEffect(() => {
    const fetchData = async (): Promise<void> => {
      const orderItemList: OrderItemModel[] | undefined =
        await fetchOrderItemList();

      if (orderItemList) {
        const sum = initFinalTotalPrice(orderItemList);
        setFinalTotalPrice(sum);
        setTotalPrice(sum);
        orderItemList.forEach((item) => {
          handleFinalPriceChange(item.id, item.price * item.count);
        });
      }
    };

    fetchData();
  }, []);

  const handleFinalPriceChange = (itemId: number, finalPrice: number) => {
    setFinalPriceMap((prev) => ({ ...prev, [itemId]: finalPrice }));
  };

  useEffect(() => {
    const values = Object.values(finalPriceMap);

    const total = values.reduce((acc, currentValue) => acc + currentValue, 0);

    setFinalTotalPrice(total);
    console.log(total);

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
      const response = await AxiosInstance.post(url, {
        orderId: orderId,
        itemList: itemList,
        purchaseTotalPrice: totalPrice,
        provider: provider,
      });

      const responseData: PurchaseStartResponseModel = response.data;
      console.log(responseData);

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
    <>
      <BlogBanner title="Purchase"></BlogBanner>
      {!orderItemList || orderItemList.length === 0 ? (
        <div>waiting</div>
      ) : (
        <section className="cart_area">
          <div className="container">
            <div className="cart_inner">
              <div>
                <table className="table">
                  <OrderItemList
                    itemList={orderItemList}
                    onCouponChange={handleCouponChange}
                    onFinalPriceChange={handleFinalPriceChange}
                    selectedCoupons={selectedCoupons}
                  />
                </table>
                <div className="billing_details">
                  <div className="row">
                    <div className="col-lg-8">
                      <div></div>
                    </div>
                    <div className="col-lg-4">
                      <div className="order_box">
                        <h2>Order</h2>
                        <ul className="list list_2">
                          <li>
                            <a>
                              {"기존 가격  "}
                              <span>{totalPrice}</span>
                            </a>
                          </li>
                          <li>
                            <a>
                              {"할인 가격  "}
                              <span>{totalPrice - finalTotalPrice}</span>
                            </a>
                          </li>
                          <li>
                            <a>
                              {"Total  "} <span>{finalTotalPrice}</span>
                            </a>
                          </li>
                        </ul>
                        <div className="payment_item">
                          <div className="radion_btn">
                            <input
                              type="radio"
                              id="kakaoRadio"
                              name="selector"
                              onChange={() => setProvider("KAKAO")}
                              checked={provider === "KAKAO"}
                            />
                            <label htmlFor="kakaoRadio">카카오 결제</label>
                            <div className="check"></div>
                          </div>
                          <div className="radion_btn">
                            <input
                              type="radio"
                              id="tossRadio"
                              name="selector"
                              onChange={() => setProvider("TOSS")}
                              checked={provider === "TOSS"}
                            />
                            <label htmlFor="tossRadio">토스 결제</label>

                            <div className="check"></div>
                          </div>
                          <div className="text-center">
                            {provider === "" ? (
                              <a
                                className="button button-paypal"
                                onClick={purchaseStart}
                              >
                                구매
                              </a>
                            ) : (
                              <a className="button button-paypal">구매</a>
                            )}
                            <a
                              className="button button-paypal"
                              onClick={purchaseStart}
                            >
                              구매
                            </a>
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </section>
      )}
    </>
  );
};

export default Order;
