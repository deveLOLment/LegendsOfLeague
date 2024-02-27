import React, { useState, useEffect } from "react";
import { OrderListModel } from "./OrderModels";
import { Col, Row } from "react-bootstrap";
import { useNavigate } from "react-router-dom";
import AxiosInstance from "../common/AxiosInstance";

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
    const url = "http://localhost:8080/orders";

    try {
      const response = await AxiosInstance.get(url);
      const responseData: OrderListModel[] = response.data;

      setOrderList(responseData);
    } catch (e) {}
  };

  useEffect(() => {
    fetchData();
  }, []);

  return (
    <>
      {!orderList || orderList.length === 0 ? (
        <div>Waiting</div>
      ) : (
        <div className="container">
          <div>
            <h2>주문 내역 조회</h2>
          </div>
          <Row>
            {orderList.map((order) => (
              <Col xs={6} key={order.orderId}>
                <OrderThumbnailInfo key={order.orderId} order={order} />
              </Col>
            ))}
          </Row>
        </div>
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
      <div className="confirmation-card">
        <h3 className="billing-title">Order Info</h3>
        <div style={{ textAlign: "right" }}>
          <button onClick={handleNavigation}>
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
              <td>: {order.orderStatus}</td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
  );
};

export default OrderList;
