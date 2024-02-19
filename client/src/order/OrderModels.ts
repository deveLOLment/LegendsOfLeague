import { CouponResponseModel } from "../Coupon/CouponModel";

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
