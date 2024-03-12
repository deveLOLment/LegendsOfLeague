import { CouponResponseModel } from "../coupon/CouponModel";
import { ItemCouponAppliedModel, OrderItemModel } from "./OrderModels";
import { useState, useEffect } from "react";

const OrderItemList: React.FC<{
  itemList: OrderItemModel[];
  onCouponChange: (itemId: number, couponId: CouponResponseModel) => void;
  onFinalPriceChange: (itemId: number, finalPrice: number) => void;
  selectedCoupons: Record<number, CouponResponseModel>;
}> = ({ itemList, onCouponChange, onFinalPriceChange, selectedCoupons }) => {
  return (
    <table className="table">
      <thead>
        <tr>
          <th className="col-1">Item ID</th>
          <th className="col-6">Product Name</th>
          <th className="col-1">Quantity</th>
          <th className="col-1">Price</th>
          <th className="col-3">Coupon</th>
          <th className="col-2">Final Price</th>
        </tr>
      </thead>
      <tbody style={{ fontSize: "18px" }}>
        {itemList.map((item) => (
          <ItemTableRow
            key={item.id}
            item={item}
            onCouponChange={onCouponChange}
            onFinalPriceChange={onFinalPriceChange} // 추가
            selectedCoupons={selectedCoupons}
          />
        ))}
      </tbody>
    </table>
  );
};

const ItemTableRow: React.FC<{
  item: OrderItemModel;
  onCouponChange: (itemId: number, coupon: CouponResponseModel) => void;
  onFinalPriceChange: (itemId: number, finalPrice: number) => void;
  selectedCoupons: Record<number, CouponResponseModel>;
}> = ({ item, onCouponChange, onFinalPriceChange, selectedCoupons }) => {
  const [selectedCouponIds, setSelectedCouponIds] = useState(new Set<number>());

  useEffect(() => {
    // selectedCoupons의 모든 키 값을 하나의 Set으로 만듭니다.
    setSelectedCouponIds(new Set(Object.keys(selectedCoupons).map(Number)));
    console.log(selectedCoupons);
  }, [selectedCoupons]);

  const [selectedCouponId, setSelectedCouponId] = useState<number>();

  const [finalPrice, setFinalPrice] = useState<number>(item.price * item.count);

  const convertCouponListToRecord = (
    couponList: CouponResponseModel[]
  ): Record<number, CouponResponseModel> => {
    const couponRecord: Record<number, CouponResponseModel> = {};

    couponList.forEach((coupon) => {
      couponRecord[coupon.id] = coupon;
    });

    return couponRecord;
  };

  const calculateDiscountPrice = (
    item: OrderItemModel,
    coupon: CouponResponseModel
  ) => {
    console.log("herer!!!!!");
    const couponType = coupon.couponType;
    if (
      couponType == "CATEGORY_AMOUNT_DISCOUNT" ||
      couponType == "ITEM_AMOUNT_DISCOUNT"
    ) {
      return item.price - coupon.discountPrice;
    } else {
      console.log("here!");
      return (item.price * (100 - coupon.discountPrice)) / 100;
    }
  };

  const couponMap: Record<number, CouponResponseModel> =
    convertCouponListToRecord(item.couponList);

  const handleCouponChange = (
    event: React.ChangeEvent<HTMLSelectElement>,
    itemId: number
  ) => {
    const selectedCouponId = parseInt(event.target.value, 10);
    const coupon = couponMap[selectedCouponId];
    console.log(coupon);
    const calculatedPrice = calculateDiscountPrice(item, coupon);
    setFinalPrice(calculatedPrice);
    setSelectedCouponId(selectedCouponId);
    onCouponChange(itemId, coupon);
    onFinalPriceChange(itemId, calculatedPrice); // 추가
    console.log("heere!!!!!");
  };

  return (
    <tr>
      <td>{item.id}</td>
      <td>{item.name}</td>
      <td>{item.count}</td>
      <td>{item.price}</td>
      <td>
        <select
          className="form-control"
          onChange={(event) => handleCouponChange(event, item.id)}
          value={selectedCouponId || ""}
        >
          <option value="">Select a Coupon</option>
          {item.couponList
            .filter(
              (coupon) =>
                !selectedCouponIds.has(coupon.id) ||
                selectedCouponId === coupon.id
            )
            .map((coupon) => (
              <option key={coupon.id} value={coupon.id}>
                {coupon.name} =
                {item.price - calculateDiscountPrice(item, coupon)}원 할인
              </option>
            ))}
        </select>
      </td>
      <td>{finalPrice}</td>
    </tr>
  );
};

export default OrderItemList;
