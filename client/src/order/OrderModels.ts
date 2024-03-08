import { CouponResponseModel } from "../coupon/CouponModel";

export interface OrderResponseDto {
  id: number;
  totalPrice: number;
  orderItemList: OrderItemModel[];
}

export interface OrderItemModel {
  id: number;
  name: string;
  price: number;
  count: number;
  couponList: CouponResponseModel[];
}

export interface ItemCouponAppliedModel {
  itemId: number;
  itemName: String;
  quantity: number;
  memberCouponId: number | null;
  price: number;
}

export interface PurchaseStartResponseModel {
  id: number;
  amount: number;
  providerName: string;
  orderName: string;
  orderCode: string;
  memberNickname: string;
}

export interface OrderInfoQueryModel {
  id: number;
  orderCode: string;
  orderDate: Date;
  totalPrice: number;
  orderStatus: string;
  purchaseProvider: string;
  orderItemList: OrderItemQueryModel[];
}

export interface OrderItemQueryModel {
  itemId: number;
  itemName: string;
  quantity: number;
  discountedPrice: number; // 해당 아이템을 구매하는데 결제한 총 금액
}

export interface OrderListModel {
  orderId: number;
  orderCode: string;
  purchaseName: string;
  orderDate: Date;
  totalPrice: number;
  orderStatus: string;
}
