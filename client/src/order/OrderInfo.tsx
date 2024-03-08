import React, { useState, useEffect } from "react";
import { OrderInfoQueryModel, OrderItemQueryModel } from "./OrderModels";
import { useParams, useNavigate } from "react-router-dom";
import AxiosInstance from "../common/AxiosInstance";
import BlogBanner from "../layout/BlogBanner";

const OrderInfo = () => {
  const navigate = useNavigate();
  const [orderInfo, setOrderInfo] = useState<OrderInfoQueryModel>();

  const { orderId } = useParams();
  console.log(orderId);

  const refundOrder = async () => {
    const url = "/purchase/cancel?orderId=" + orderId;

    try {
      const response = await AxiosInstance.get(url);
      alert("환불 완료!!");

      fetchData();
    } catch (e) {
      alert("환불에 실패했습니다. 다시 진행해주세요.");
    }
  };

  const fetchData = async () => {
    const url = `orders/${orderId}/info`;

    try {
      const response = await AxiosInstance.get(url);
      const responseData: OrderInfoQueryModel = response.data;
      setOrderInfo(responseData);
      console.log(responseData);
    } catch (e) {
      console.log(e);
    }
  };

  useEffect(() => {
    fetchData();
  }, []);

  return (
    <>
      <BlogBanner title="OrderDetails"></BlogBanner>
      {!orderInfo ? (
        <div>Waiting</div>
      ) : (
        <section className="order_details section-margin--small">
          <div className="container">
            <div className="row mb-5">
              <div className="col-md-6 col-xl-4 mb-4 mb-xl-0">
                <div
                  className={`${
                    orderInfo.orderStatus === "SUCCESS"
                      ? "confirmation-card"
                      : "failure-card"
                  }`}
                >
                  <h3 className="billing-title">Order Info</h3>
                  <table className="order-table">
                    <tbody>
                      <tr>
                        <td>Order number</td>
                        <td>: {orderInfo.orderCode}</td>
                      </tr>
                      <tr>
                        <td>Date</td>
                        <td>: {orderInfo.orderDate.toString()}</td>
                      </tr>
                      <tr>
                        <td>Total</td>
                        <td>: {orderInfo.totalPrice}</td>
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
                {orderInfo.orderStatus !== "REFUND" && (
                  <button className="btn btn-primary" onClick={refundOrder}>
                    환불 요청
                  </button>
                )}
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
                        <p>{orderInfo.totalPrice}원</p>
                      </td>
                    </tr>
                    <tr>
                      <td>Status</td>
                      <td
                        style={{
                          color:
                            orderInfo.orderStatus === "SUCCESS"
                              ? "green"
                              : "darkred",
                          fontSize: "15px",
                        }}
                      >
                        : {orderInfo.orderStatus}
                      </td>
                    </tr>
                    <tr>
                      <td>Status</td>
                      <td>: {orderInfo.orderStatus}</td>
                    </tr>
                  </tbody>
                </table>
              </div>
            </div>
          </div>
        </section>
      )}
    </>
  );
};

const OrderItem: React.FC<{
  item: OrderItemQueryModel;
}> = ({ item }) => {
  return (
    <tr>
      <td>
        <p>{item.itemName}</p>
      </td>
      <td>
        <h5>{item.quantity}</h5>
      </td>
      <td>
        <p>{item.discountedPrice}</p>
      </td>
    </tr>
  );
};

export default OrderInfo;
