import React, { useState, useEffect, MouseEvent, useRef } from "react";
import { useNavigate, useParams } from "react-router-dom";
import AxiosInstance from "../common/AxiosInstance";
import { count } from "console";
import ItemInfo from "../iteminfo/ItemInfo";

interface ItemImageReponseDto {
  itemImageId: number;
  imageUrl: string;
}

interface ItemDetailResponseDto {
  itemId: number;
  name: string;
  price: number;
  description: string;
  stock: number;
  category: string;
  thumbnailImage: string;
  itemImageList: ItemImageReponseDto[];
}

const Item = () => {
  const [itemDetail, setItemDetail] = useState<ItemDetailResponseDto | null>(
    null
  );

  useEffect(() => {
    const fetchItemDetail = async () => {
      try {
        const response = await AxiosInstance.get<ItemDetailResponseDto>(
          `/item/${itemId}`
        );
        setItemDetail(response.data);
      } catch (error) {
        console.error("Error fetching item detail:", error);
      }
    };

    fetchItemDetail();
  }, []);

  console.log(itemDetail);

  const navigate = useNavigate();
  const [quantity, setQuantity] = useState<number>(1);
  const { itemId } = useParams<{ itemId: string }>();

  const handleChange = (event: React.ChangeEvent<HTMLSelectElement>) => {
    setQuantity(parseInt(event.target.value));
  };

  const orderItem = async () => {
    const url = "/order/single";

    try {
      const response = await AxiosInstance.post(
        url,
        {
          itemId: itemId,
          count: quantity, // 수량 값을 전달합니다.
        },
        {
          headers: {
            "Content-Type": "application/json",
          },
        }
      );
      console.log("hello");
      console.log(response.data);
      // 주문 요청 후 orderId를 받아옴
      const orderId = response.data;
      navigate("/order", {
        state: { orderId: orderId },
      });
    } catch (error) {
      console.error("Error ordering item: ", error);
    }
  };

  const handleOrderButtonClick = () => {
    orderItem();
  };

  const addToCart = async () => {
    console.log(quantity);
    console.log(itemId);
    const url = "/cart/add";

    try {
      const response = await AxiosInstance.post(
        url,
        {
          itemId: itemId,
          count: quantity, // 수량 값을 전달합니다.
        },
        {
          headers: {
            "Content-Type": "application/json",
          },
        }
      );
      // 아이템을 카트에 성공적으로 추가한 후에 페이지를 이동시킵니다.
      navigate("/carts"); // 예시로 '/cart'로 이동하도록 설정되어 있습니다.
    } catch (error) {
      console.error("Error deleting cart item and updating list: ", error);
    }
  };

  return (
    <div className="product_image_area">
      <div className="container">
        <div className="row s_product_inner">
          <div className="col-lg-6">
            <div>
              <div className="owl-stage-outer">
                <div className="owl-stage">
                  <div className="owl-item active">
                    <div
                      className="item-single-prd"
                      style={{
                        display: "flex",
                        justifyContent: "center",
                        alignItems: "center",
                      }}
                    >
                      <img
                        className="item-image"
                        src={itemDetail?.thumbnailImage}
                      />
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
          <div className="col-lg-5 offset-lg-1">
            <div className="s_product_text">
              <h3>{itemDetail?.name}</h3>
              <h2>{itemDetail?.price.toLocaleString()} 원</h2>
              <ul className="list">
                <li>
                  <span>Category</span> : {itemDetail?.category}
                </li>
                <li>
                  <span>Sotck</span> : {itemDetail?.stock}
                </li>
              </ul>
              <p>{itemDetail?.description}</p>
              <div className="qty-container">
                <label htmlFor="qty" className="m-3">
                  <h6>수량:</h6>
                </label>
                <select
                  name="qty"
                  id="qty"
                  value={quantity}
                  onChange={handleChange}
                  className="input-text qty"
                >
                  {/* 옵션 값은 1부터 5까지 */}
                  {[...Array(5)].map((_, index) => (
                    <option key={index} value={index + 1}>
                      {index + 1}
                    </option>
                  ))}
                </select>
              </div>
              <br />
              {/* 카트에 추가 버튼 */}
              <button onClick={addToCart} className="white_button mr-3">
                장바구니 추가
              </button>
              <button onClick={handleOrderButtonClick} className="button">
                주문하기
              </button>
            </div>
          </div>
        </div>
      </div>
      <ItemInfo itemId={parseInt(itemId!)} />
    </div>
  );
};
export default Item;
