// import React, { useState, useEffect, MouseEvent, useRef } from "react";
// import { useNavigate, useParams } from "react-router-dom";
// import AxiosInstance from "../common/AxiosInstance";
// import { count } from "console";

// interface ItemImageReponseDto {
//   itemImageId: number;
//   imageUrl: string;
// }

// interface ItemDetailResponseDto {
//   itemId: number;
//   name: string;
//   price: number;
//   description: string;
//   stock: number;
//   category: string;
//   thumbnailImage: string;
//   itemImageList: ItemImageReponseDto[];
// }

// const Item = () => {
//   const [itemDetail, setItemDetail] = useState<ItemDetailResponseDto | null>(
//     null
//   );

//   useEffect(() => {
//     const fetchItemDetail = async () => {
//       try {
//         const response = await AxiosInstance.get<ItemDetailResponseDto>(
//           `http://localhost:8080/item/${itemId}`
//         );
//         setItemDetail(response.data);
//       } catch (error) {
//         console.error("Error fetching item detail:", error);
//       }
//     };

//     fetchItemDetail();
//   }, []);

//   console.log(itemDetail);

//   const navigate = useNavigate();
//   const [quantity, setQuantity] = useState<number>(1);
//   const { itemId } = useParams<{ itemId: string }>();

//   const handleChange = (event: React.ChangeEvent<HTMLSelectElement>) => {
//     setQuantity(parseInt(event.target.value));
//   };

//   const orderItem = async () => {
//     const url = "http://localhost:8080/order/single";

//     try {
//       const response = await AxiosInstance.post(
//         url,
//         {
//           itemId: itemId,
//           count: quantity, // 수량 값을 전달합니다.
//         },
//         {
//           headers: {
//             "Content-Type": "application/json",
//           },
//         }
//       );
//       console.log("hello");
//       console.log(response.data);
//       // 주문 요청 후 orderId를 받아옴
//       const orderId = response.data;
//       navigate("/order", {
//         state: { orderId: orderId },
//       });
//     } catch (error) {
//       console.error("Error ordering item: ", error);
//     }
//   };

//   const handleOrderButtonClick = () => {
//     orderItem();
//   };

//   const addToCart = async () => {
//     console.log(quantity);
//     console.log(itemId);
//     const url = "http://localhost:8080/cart/add";

//     try {
//       const response = await AxiosInstance.post(
//         url,
//         {
//           itemId: itemId,
//           count: quantity, // 수량 값을 전달합니다.
//         },
//         {
//           headers: {
//             "Content-Type": "application/json",
//           },
//         }
//       );
//       // 아이템을 카트에 성공적으로 추가한 후에 페이지를 이동시킵니다.
//       navigate("/carts"); // 예시로 '/cart'로 이동하도록 설정되어 있습니다.
//     } catch (error) {
//       console.error("Error deleting cart item and updating list: ", error);
//     }
//   };

//   return (
//     <div className="product_image_area">
//       <div className="container">
//         <div className="row s_product_inner">
//           <div className="col-lg-6">
//             <div className="owl-carousel owl-theme s_Product_carousel owl-loaded owl-drag">
//               <img src={itemDetail?.thumbnailImage} alt="" />
//             </div>
//           </div>
//           <div className="col-lg-5 offset-lg-1">
//             <div className="s_product_text">
//               <h3>{itemDetail?.name}</h3>
//               <h2>{itemDetail?.price.toLocaleString()} 원</h2>
//               <ul className="list">
//                 <li>
//                   <span>Category</span> : {itemDetail?.category}
//                 </li>
//                 <li>
//                   <span>
//                     Availability:{" "}
//                     {itemDetail?.stock && itemDetail.stock > 0
//                       ? "Available"
//                       : "Sold Out"}
//                   </span>
//                 </li>
//               </ul>
//               <p>{itemDetail?.description}</p>
//               <div className="nice-select">
//                 <label htmlFor="qty">Quantity:</label>
//                 <select
//                   name="qty"
//                   id="qty"
//                   value={quantity}
//                   onChange={handleChange}
//                   // className="input-text qty"
//                   className=""
//                 >
//                   {/* 옵션 값은 1부터 5까지 */}
//                   {[...Array(5)].map((_, index) => (
//                     <option key={index} value={index + 1}>
//                       {index + 1}
//                     </option>
//                   ))}
//                 </select>
//                 <br />
//                 {/* 카트에 추가 버튼 */}
//                 <button onClick={addToCart} className="button primary-btn">
//                   Add to Cart
//                 </button>
//                 <button onClick={handleOrderButtonClick}>주문하기</button>
//               </div>
//             </div>
//           </div>
//         </div>
//       </div>
//       <section className="product_description_area">
//         <div className="container">
//           <ul className="nav nav-tabs" id="myTab" role="tablist">
//             <li className="nav-item">
//               <a
//                 className="nav-link active show"
//                 id="home-tab"
//                 data-toggle="tab"
//                 href="#home"
//                 role="tab"
//                 aria-controls="home"
//                 aria-selected="true"
//               >
//                 Description
//               </a>
//             </li>
//             <li className="nav-item">
//               <a
//                 className="nav-link"
//                 id="profile-tab"
//                 data-toggle="tab"
//                 href="#profile"
//                 role="tab"
//                 aria-controls="profile"
//                 aria-selected="false"
//               >
//                 Specification
//               </a>
//             </li>
//             <li className="nav-item">
//               <a
//                 className="nav-link"
//                 id="contact-tab"
//                 data-toggle="tab"
//                 href="#contact"
//                 role="tab"
//                 aria-controls="contact"
//                 aria-selected="false"
//               >
//                 Comments
//               </a>
//             </li>
//             <li className="nav-item">
//               <a
//                 className="nav-link"
//                 id="review-tab"
//                 data-toggle="tab"
//                 href="#review"
//                 role="tab"
//                 aria-controls="review"
//                 aria-selected="false"
//               >
//                 Reviews
//               </a>
//             </li>
//           </ul>
//           <div className="tab-content" id="myTabContent">
//             <div
//               className="tab-pane fade active show"
//               id="home"
//               role="tabpanel"
//               aria-labelledby="home-tab"
//             >
//               <div className="owl-carousel owl-theme s_Product_carousel owl-loaded owl-drag">
//                 {itemDetail?.itemImageList.map((image, index) => (
//                   <img
//                     key={index}
//                     src={image.imageUrl}
//                     alt={`Item Image ${index}`}
//                   />
//                 ))}
//               </div>
//             </div>
//             <div
//               className="tab-pane fade"
//               id="profile"
//               role="tabpanel"
//               aria-labelledby="profile-tab"
//             >
//               <div className="table-responsive">
//                 <table className="table">
//                   <tbody>
//                     <tr>
//                       <td>
//                         <h5>Width</h5>
//                       </td>
//                       <td>
//                         <h5>128mm</h5>
//                       </td>
//                     </tr>
//                     {/* 나머지 specification 데이터 */}
//                   </tbody>
//                 </table>
//               </div>
//             </div>
//             <div
//               className="tab-pane fade"
//               id="contact"
//               role="tabpanel"
//               aria-labelledby="contact-tab"
//             >
//               {/* Comments 내용 */}
//             </div>
//             <div
//               className="tab-pane fade"
//               id="review"
//               role="tabpanel"
//               aria-labelledby="review-tab"
//             >
//               {/* Reviews 내용 */}
//             </div>
//           </div>
//         </div>
//       </section>
//     </div>
//   );
// };
// export default Item;

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
          `http://localhost:8080/item/${itemId}`
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
    const url = "http://localhost:8080/order/single";

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
    const url = "http://localhost:8080/cart/add";

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
            <div className="owl-carousel owl-theme s_Product_carousel owl-loaded owl-drag">
              <div className="owl-stage-outer">
                <div className="owl-stage">
                  <div className="owl-item active">
                    <div className="item-single-prd">
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
                  <span>
                    Availability:{" "}
                    {itemDetail?.stock && itemDetail.stock > 0
                      ? "Available"
                      : "Sold Out"}
                  </span>
                </li>
              </ul>
              <p>{itemDetail?.description}</p>
              <div className="nice-select">
                <label htmlFor="qty">Quantity:</label>
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
                <br />
                {/* 카트에 추가 버튼 */}
                <button onClick={addToCart} className="button primary-btn">
                  Add to Cart
                </button>
                <button onClick={handleOrderButtonClick}>주문하기</button>
              </div>
            </div>
          </div>
        </div>
      </div>
      <ItemInfo itemId={parseInt(itemId!)} />
    </div>
  );
};
export default Item;
