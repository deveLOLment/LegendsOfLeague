import React, { useState, ChangeEvent, FormEvent, useEffect } from "react";
import { CouponCreateForm } from "./CouponStyled";
import { CouponTypeRecord } from "./CouponModel";
import axios from "axios";

interface FormData {
  name: string;
  description: string;
  stock: number;
  minPrice: number;
  maxPrice: number;
  validityStartDate: string;
  validityEndDate: string;
  discountPrice: number;
  appliedCategoryName: string;
  couponTypeName: string;
  itemId: number;
}

interface ItemSelectModel {
  id: number;
  itemName: string;
}

const CouponCreate = () => {
  const [formData, setFormData] = useState<FormData>({
    name: "",
    description: "",
    stock: 0,
    minPrice: 0,
    maxPrice: 0,
    validityStartDate: "",
    validityEndDate: "",
    discountPrice: 0,
    appliedCategoryName: "",
    couponTypeName: "",
    itemId: 0,
  });

  const [categoryList, setCategoryList] = useState<string[]>([]);
  const [itemSelectList, setItemSelectList] = useState<ItemSelectModel[]>([]);

  const handleChange = (
    e: ChangeEvent<HTMLInputElement | HTMLSelectElement>
  ) => {
    console.log(e.target.name + " " + e.target.value);
    setFormData({
      ...formData,

      [e.target.name]: e.target.value,
    });
  };

  const createCoupon = async () => {
    const url = "http://localhost:8080/coupons";

    try {
      const response = await axios.post(url, formData);
    } catch (e) {}
  };

  const handleSubmit = (e: FormEvent) => {
    e.preventDefault();

    createCoupon();
    alert("쿠폰이 생성되었습니다!");

    window.location.reload();

    console.log(formData); // 예시로 콘솔에 출력
  };

  const fetchCategoryList = async () => {
    const url = "http://localhost:8080/items/categories";

    const response = await axios.get(url);

    const responseData: string[] = response.data;

    setCategoryList(responseData);
  };

  const fetchItemSelectList = async () => {
    const url = "http://localhost:8080/items";

    const response = await axios.get(url);

    const responseData: ItemSelectModel[] = response.data;

    setItemSelectList(responseData);
  };

  useEffect(() => {
    fetchCategoryList();
    fetchItemSelectList();
  }, []);

  return (
    <div>
      {categoryList.length === 0 || itemSelectList.length === 0 ? (
        <div>Waiting..</div>
      ) : (
        <>
          <div>
            관리자 쿠폰 등록 페이지
            <CouponCreateForm onSubmit={handleSubmit}>
              <label>
                Name:
                <input
                  type="text"
                  name="name"
                  value={formData.name}
                  onChange={handleChange}
                />
              </label>
              <br />
              <label>
                Description:
                <input
                  type="text"
                  name="description"
                  value={formData.description}
                  onChange={handleChange}
                />
              </label>
              <br />
              <label>
                Stock:
                <input
                  type="number"
                  name="stock"
                  value={formData.stock}
                  onChange={handleChange}
                />
              </label>
              <br />
              <label>
                Min Price:
                <input
                  type="number"
                  name="minPrice"
                  value={formData.minPrice}
                  onChange={handleChange}
                />
              </label>
              <br />
              <label>
                Max Price:
                <input
                  type="number"
                  name="maxPrice"
                  value={formData.maxPrice}
                  onChange={handleChange}
                />
              </label>
              <br />
              <label>
                Validity Start Date:
                <input
                  type="date"
                  name="validityStartDate"
                  value={formData.validityStartDate}
                  onChange={handleChange}
                />
              </label>
              <br />
              <label>
                Validity End Date:
                <input
                  type="date"
                  name="validityEndDate"
                  value={formData.validityEndDate}
                  onChange={handleChange}
                />
              </label>
              <br />
              <label>
                Discount Price:
                <input
                  type="number"
                  name="discountPrice"
                  value={formData.discountPrice}
                  onChange={handleChange}
                />
              </label>
              <br />
              <label>
                Applied Category Name:
                <select
                  name="appliedCategoryName"
                  value={formData.appliedCategoryName}
                  onChange={handleChange}
                >
                  <option disabled value="">
                    선택해주세요
                  </option>
                  {categoryList.map((category, index) => (
                    <option key={index} value={category.toString()}>
                      {category}
                    </option>
                  ))}
                </select>
              </label>
              <br />
              <label>
                Coupon Type Name:
                <select
                  name="couponTypeName"
                  value={formData.couponTypeName || "-1"}
                  onChange={handleChange}
                >
                  {!formData.couponTypeName && (
                    <option disabled value="-1">
                      선택해주세요
                    </option>
                  )}
                  {Object.entries(CouponTypeRecord).map(([key, value]) => (
                    <option key={key} value={key}>
                      {value}
                    </option>
                  ))}
                </select>
              </label>
              <br />
              <label>
                Item ID:
                <select
                  name="itemId"
                  value={formData.itemId || ""} // Set default value to an empty string
                  onChange={handleChange}
                >
                  {!formData.itemId && ( // Render "선택해주세요" only when not selected
                    <option disabled value="">
                      선택해주세요
                    </option>
                  )}
                  {itemSelectList.map((item, index) => (
                    <option key={index} value={item.id}>
                      {item.itemName}
                    </option>
                  ))}
                </select>
              </label>
              <br />
              <button
                type="submit"
                disabled={
                  !formData.appliedCategoryName ||
                  !formData.couponTypeName ||
                  !formData.itemId
                }
              >
                전송
              </button>
            </CouponCreateForm>
          </div>
        </>
      )}
    </div>
  );
};
export default CouponCreate;
