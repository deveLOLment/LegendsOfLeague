import axios from "axios";
import React, { useState } from "react";
import { useNavigate } from "react-router-dom";

export interface ItemCouponAppliedDto {
  itemId: number;
  itemName: String;
  quantity: number;
  memberCouponId: number | null;
  price: number;
}

export interface PurchaseStartRequestDto {
  orderId: number;
  itemList: ItemCouponAppliedDto[];
  purchaseTotalPrice: number;
  provider: string;
}

export interface PurchaseStartResponseDto {
  id: number;
  amount: number;
  providerName: string;
  orderName: string;
  orderCode: string;
  memberNickname: string;
}

const Order = () => {
  const navigate = useNavigate();
  const orderId = 1;
  const purchaseTotalPrice = 120000;
  const provider: String = "KAKAO";

  const [purchaseId, setPurchaseId] = useState<Number>(-1);

  // ItemList 컴포넌트 정의
  const ItemList: React.FC<{ itemList: ItemCouponAppliedDto[] }> = ({
    itemList,
  }) => {
    return (
      <div>
        <h2>Item List</h2>
        <table>
          <thead>
            <tr>
              <th>Item ID</th>
              <th>Item Name</th>
              <th>Quantity</th>
              <th>Price</th>
            </tr>
          </thead>
          <tbody>
            {/* 사용자 정의 컴포넌트를 이용하여 itemList를 매핑 */}
            {itemList.map((item) => (
              <ItemTableRow key={item.itemId} item={item} />
            ))}
          </tbody>
        </table>
      </div>
    );
  };

  // ItemTableRow 컴포넌트 정의
  const ItemTableRow: React.FC<{ item: ItemCouponAppliedDto }> = ({ item }) => {
    return (
      <tr>
        <td>{item.itemId}</td>
        <td>{item.itemName}</td>
        <td>{item.quantity}</td>
        <td>{item.price}</td>
      </tr>
    );
  };

  const examplItemList: ItemCouponAppliedDto[] = [
    {
      itemId: 1,
      itemName: "item1",
      quantity: 2,
      memberCouponId: null,
      price: 20000,
    },
    {
      itemId: 2,
      itemName: "item2",
      quantity: 2,
      memberCouponId: null,
      price: 40000,
    },
    {
      itemId: 3,
      itemName: "item3",
      quantity: 2,
      memberCouponId: null,
      price: 60000,
    },
  ];

  const purchaseStart = async () => {
    const url = "http://localhost:8080/purchase/ready";
    try {
      const response = await axios.post(url, {
        orderId: orderId,
        itemList: examplItemList,
        purchaseTotalPrice: purchaseTotalPrice,
        provider: provider,
      });
      setPurchaseId(response.data.id);
      console.log(response.data.id);

      const responseData: PurchaseStartResponseDto = response.data;

      if (provider == "KAKAO") {
        navigate("/purchase/kakaoPay/ready", {
          state: { id: response.data.id },
        });
      } else if (provider == "TOSS") {
        navigate("/purchase/tossPay", {
          state: { id: response.data.id, data: responseData },
        });
      }
    } catch (e) {}
  };

  return (
    <div>
      <ItemList itemList={examplItemList} />
      <button onClick={purchaseStart}>결제하기</button>
    </div>
  );
};

export default Order;
