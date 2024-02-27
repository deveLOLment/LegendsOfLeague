import React, { useState } from "react";
import { OrderInfoQueryModel, OrderItemQueryModel } from "./OrderModels";
import { useParams, useNavigate } from "react-router-dom";
import AxiosInstance from "../common/AxiosInstance";

const dummyOrderInfo: OrderInfoQueryModel = {
  orderCode: 12345,
  orderDate: new Date(),
  originalTotalPrice: 60000,
  discountedTotalPrice: 58000,
  purchaseProvider: "KAKAO",
  orderItemList: [
    {
      itemId: 1,
      itmeName: "Product A",
      quantity: 2,
      originalPrice: 20000,
      appliedCouponName: "COUPON25",
      discountedPrice: 18000,
    },
    {
      itemId: 2,
      itmeName: "Product B",
      quantity: 1,
      originalPrice: 40000,
      appliedCouponName: "",
      discountedPrice: 40000,
    },
  ],
};

const OrderInfo = () => {
  const navigate = useNavigate();
  const [orderInfo, setOrderInfo] =
    useState<OrderInfoQueryModel>(dummyOrderInfo);

  const { orderId } = useParams();
  console.log(orderId);

  const refundOrder = async () => {
    const url = "/purcahse/cancel?orderId=" + orderId;

    try {
      const response = await AxiosInstance.get(url);

      navigate("/order-info/" + orderId);
    } catch (e) {}
  };

  return (
    <div className="container">
      <h4 className="text-left">주문 조회</h4>
      <div className="row mb-5">
        <div className="col-md-6 col-xl-4 mb-4 mb-xl-0">
          <div className="confirmation-card">
            <h3 className="billing-title">Order Info</h3>
            <table className="order-table">
              <tbody>
                <tr>
                  <td>Order number</td>
                  <td>: {orderInfo.orderCode}</td>
                </tr>
                <tr>
                  <td>Date</td>
                  <td>: {orderInfo.orderDate.toDateString()}</td>
                </tr>
                <tr>
                  <td>Total</td>
                  <td>: {orderInfo.discountedTotalPrice}</td>
                </tr>
                <tr>
                  <td>Provider</td>
                  <td>: {orderInfo.purchaseProvider}</td>
                </tr>
              </tbody>
            </table>
          </div>
        </div>
        <div className="col-md-5"></div>
        <div className="col-md-1">
          <button className="btn btn-primary" onClick={refundOrder}>
            환불 요청
          </button>
        </div>
      </div>
      <div className="order_details_table">
        <h2>Order Details</h2>
        <div className="table-responsive">
          <table className="table">
            <thead>
              <tr>
                <th scope="col">물품 이름</th>
                <th scope="col">수량</th>
                <th scope="col">총액</th>
                <th scope="col">적용 쿠폰 & 할인 금액</th>
              </tr>
            </thead>
            <tbody>
              {orderInfo.orderItemList.map((orderItem) => (
                <OrderItem key={orderItem.itemId} item={orderItem} />
              ))}

              <tr>
                <td>
                  <h4>총 금액</h4>
                </td>
                <td>
                  <h5></h5>
                </td>
                <td>
                  <p>{orderInfo.originalTotalPrice}원</p>
                </td>
              </tr>
              <tr>
                <td>
                  <h4>할인 금액</h4>
                </td>
                <td>
                  <h5></h5>
                </td>
                <td>
                  <p>
                    {orderInfo.originalTotalPrice -
                      orderInfo.discountedTotalPrice}
                    원
                  </p>
                </td>
              </tr>
              <tr>
                <td>
                  <h4>총 결제 금액</h4>
                </td>
                <td>
                  <h5></h5>
                </td>
                <td>
                  <h4>{orderInfo.discountedTotalPrice}원</h4>
                </td>
              </tr>
            </tbody>
          </table>
        </div>
      </div>
    </div>
  );
};

const OrderItem: React.FC<{
  item: OrderItemQueryModel;
}> = ({ item }) => {
  return (
    <tr>
      <td>
        <p>{item.itmeName}</p>
      </td>
      <td>
        <h5>{item.quantity}</h5>
      </td>
      <td>
        <p>{item.discountedPrice}</p>
      </td>
      <td>
        {item.appliedCouponName ? (
          <p>
            {item.appliedCouponName} &{" "}
            {item.originalPrice - item.discountedPrice}원 할인
          </p>
        ) : (
          <p></p>
        )}
      </td>
    </tr>
  );
};

export default OrderInfo;
