import React, { useEffect, useState } from "react";
import axiosInstance from "../common/AxiosInstance";
import { useNavigate } from "react-router-dom";

interface PurchaseSuccessModel {
  id: number;
  orderCode: string;
  orderDate: Date;
  totalPrice: number;
}

const dummyPurchaseSuccessData: PurchaseSuccessModel = {
  id: 1,
  orderCode: "ABC123",
  orderDate: new Date("2024-02-26T10:30:00"),
  totalPrice: 150.99,
};

const PurchaseSuccess = () => {
  const purchaseId = localStorage.getItem("purchaseId");
  const navigate = useNavigate();

  const [purchaseModel, setPurchaseModel] = useState<PurchaseSuccessModel>(
    dummyPurchaseSuccessData
  );

  useEffect(() => {
    const fetchPurchaseInfo = async () => {
      const url = "/purchases/" + purchaseId;

      try {
        const response = await axiosInstance.get(url);
        const responseData: PurchaseSuccessModel = response.data;
        setPurchaseModel(responseData);
      } catch (e) {}
    };

    fetchPurchaseInfo();
  }, []);

  const handleNavigation = () => {
    navigate(`/order-list`);
  };

  return (
    <div className="confirmation-card">
      <h3 className="billing-title">결제에 성공했습니다!</h3>
      <div style={{ textAlign: "right" }}>
        <button onClick={handleNavigation}>
          <span>주문 내역 조회</span>
        </button>
      </div>
      <table className="order-table">
        <tbody>
          <tr>
            <td>OrderCode</td>
            <td>: {purchaseModel.orderCode}</td>
          </tr>
          <tr>
            <td>Date</td>
            <td>: {purchaseModel.orderDate.toLocaleString()}</td>
          </tr>
          <tr>
            <td>Total</td>
            <td>: {purchaseModel.totalPrice}</td>
          </tr>
        </tbody>
      </table>
    </div>
  );
};

export default PurchaseSuccess;
