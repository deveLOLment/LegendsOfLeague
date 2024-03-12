export interface CouponResponseModel {
  id: number;
  name: string;
  discountPrice: number;
  description: string;
  validityStartDate: Date;
  validityEndDate: Date;
  appliedCategory: string;
  couponType: string;
  itemName: string;
  itemId: string;
}

export const CouponTypeRecord: Record<string, string> = {
  CATEGORY_AMOUNT_DISCOUNT: "카테고리 정량 쿠폰",
  CATEGORY_PERCENT_DISCOUNT: "카테고리 퍼센트 쿠폰",
  ITEM_AMOUNT_DISCOUNT: "상품 정량 쿠폰",
  ITEM_PERCENT_DISCOUNT: "상품 퍼센트 쿠폰",
};
