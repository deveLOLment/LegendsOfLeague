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

export interface OrderInfoQueryModel {
  orderCode: number;
  orderDate: Date;
  originalTotalPrice: number; // 구매한 모든 item의 실제 가격
  discountedTotalPrice: number; // 해당 주문에서 실제로 결제한 금액
  purchaseProvider: string;
  orderItemList: OrderItemQueryModel[];
}

export interface OrderItemQueryModel {
  itemId: number;
  itmeName: string;
  quantity: number;
  originalPrice: number; //실제 itemPrice * quantity의 가격
  appliedCouponName: string;
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
