import React, { useState, useEffect } from "react";
import { OrderListModel } from "./OrderModels";
import { Col, Row } from "react-bootstrap";
import { useNavigate } from "react-router-dom";
import AxiosInstance from "../common/AxiosInstance";
import BlogBanner from "../layout/BlogBanner";

const dummyData: OrderListModel[] = [
  {
    orderId: 1,
    orderCode: "ABC123",
    purchaseName: "Product A 외 2건",
    orderDate: new Date("2024-02-26T10:30:00"),
    totalPrice: 20000,
    orderStatus: "Processing",
  },
  {
    orderId: 2,
    orderCode: "DEF456",
    purchaseName: "Product B 외 2건",
    orderDate: new Date("2024-02-25T15:45:00"),
    totalPrice: 30000,
    orderStatus: "Shipped",
  },
  {
    orderId: 3,
    orderCode: "GHI789",
    purchaseName: "Product C 외 2건",
    orderDate: new Date("2024-02-24T08:20:00"),
    totalPrice: 40000,
    orderStatus: "Delivered",
  },
];

const OrderList = () => {
  const [orderList, setOrderList] = useState<OrderListModel[]>();

  const fetchData = async () => {
    const url = "/orders";

    const response = await AxiosInstance.get(url);
    const responseData: OrderListModel[] = response.data;

    setOrderList(responseData);

    console.log("q2312341245123");
  };

  useEffect(() => {
    fetchData();
  }, []);

  return (
    <>
      <BlogBanner title="OrderConfirmation"></BlogBanner>
      {!orderList || orderList.length === 0 ? (
        <div>Waiting</div>
      ) : (
        <section className="order_details section-margin--small">
          <div className="container">
            <p className="text-center billing-alert">
              Thank you. Your order has been received.
            </p>
            <Row>
              {orderList.map((order) => (
                <Col xs={6} key={order.orderId}>
                  <OrderThumbnailInfo key={order.orderId} order={order} />
                </Col>
              ))}
            </Row>
          </div>
        </section>
      )}
    </>
  );
};

const OrderThumbnailInfo: React.FC<{ order: OrderListModel }> = ({ order }) => {
  const navigate = useNavigate();
  console.log(order);

  const handleNavigation = () => {
    navigate(`/order-info/${order.orderId}`);
  };
  return (
    <div className="mb-4">
      <div
        className={`${
          order.orderStatus === "결제완료"
            ? "confirmation-card"
            : "failure-card"
        }`}
      >
        <h3 className="billing-title">Order Info</h3>
        <div style={{ textAlign: "right" }}>
          <button
            className={`${
              order.orderStatus === "결제완료"
                ? "btn btn-secondary"
                : "btn btn-light"
            }`}
            onClick={handleNavigation}
          >
            <span>Detail</span>
          </button>
        </div>
        <table className="order-rable">
          <tbody>
            <tr>
              <td>Order number</td>
              <td>: {order.orderCode}</td>
            </tr>
            <tr>
              <td>Order Name</td>
              <td>: {order.purchaseName}</td>
            </tr>
            <tr>
              <td>Date</td>
              <td>: {order.orderDate.toLocaleString()}</td>
            </tr>
            <tr>
              <td>Total</td>
              <td>: {order.totalPrice}</td>
            </tr>
            <tr>
              <td>State</td>
              <td
                style={{
                  color: order.orderStatus === "결제완료" ? "green" : "darkred",
                  fontSize: "15px",
                }}
              >
                : {order.orderStatus}
              </td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
  );
};

export default OrderList;
