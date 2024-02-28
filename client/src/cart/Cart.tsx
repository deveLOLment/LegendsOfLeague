import axios from "axios";
import React, { useState, useEffect, MouseEvent, useRef } from "react";
import { useNavigate } from "react-router-dom";
import { TypePredicateKind } from "typescript";
import AxiosInstance from "../common/AxiosInstance";

const Cart = () => {
  const modalBackground = useRef();

  const [showModal, setShowModal] = useState<boolean>(false);
  const [selectedItemToUpdate, setSelectedItemToUpdate] = useState<{
    cartItemId: number;
    count: number;
  } | null>(null);

  const navigate = useNavigate();
  const [cartItemList, setCartItemList] = useState<
    {
      cartItemId: number;
      thumbnailImage: string;
      name: string;
      price: number;
      count: number;
    }[]
  >([]);
  const [selectedItems, setSelectedItems] = useState<number[]>([]);

  const selectedItemCount = selectedItems.length;

  const [count, setCount] = useState<number>(selectedItemToUpdate?.count || 0);

  const handleCountChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setCount(parseInt(e.target.value));
  };
  useEffect(() => {
    fetchCartItemList();
  }, []);

  const fetchCartItemList = async () => {
    const url = "http://localhost:8080/carts";
    try {
      const response = await AxiosInstance.get(url);
      setCartItemList(response.data);
    } catch (error) {
      console.error("Error fetching cart items: ", error);
    }
  };

  const handleUpdateQuantity = async (
    updatedCount: number,
    cartItemId: number
  ) => {
    const url = `http://localhost:8080/cart/${cartItemId}/update`;
    console.log("hello");

    try {
      // 예시로 서버에 업데이트 요청을 보내는 코드
      const response = await AxiosInstance.post(url, {
        cartItemId: cartItemId,
        count: updatedCount,
      });

      // 모달 닫기
      setShowModal(false);
      fetchCartItemList(); // 삭제 후 아이템 리스트 다시 불러오기
      setSelectedItems([]); // 선택된 아이템 초기화
    } catch (error) {
      console.error("Error updating item quantity: ", error);
    }
  };

  const handleCheckboxChange = (itemId: number) => {
    if (selectedItems.includes(itemId)) {
      setSelectedItems(selectedItems.filter((id) => id !== itemId));
    } else {
      setSelectedItems([...selectedItems, itemId]);
    }
  };

  const deleteCartItemAndUpdateList = async (cartItemDeleteList: number[]) => {
    const url = "http://localhost:8080/cart/delete";

    const cartItemDeleteRequestList = cartItemDeleteList.map((itemId) => {
      return { cartItemId: itemId };
    });

    console.log(cartItemDeleteRequestList);

    try {
      await AxiosInstance.post(url, cartItemDeleteRequestList, {
        headers: {
          "Content-Type": "application/json",
        },
      });
      fetchCartItemList(); // 삭제 후 아이템 리스트 다시 불러오기
      setSelectedItems([]); // 선택된 아이템 초기화
    } catch (error) {
      console.error("Error deleting cart item and updating list: ", error);
    }
  };

  const handleDeleteClick = async (event: MouseEvent<HTMLButtonElement>) => {
    event.preventDefault();
    const confirmed = window.confirm("선택한 아이템을 삭제할까요?");
    if (confirmed) {
      await deleteCartItemAndUpdateList(selectedItems);
      setSelectedItems([]);
    }
  };

  // 주문하기 버튼을 클릭할 때 호출되는 함수
  const handleOrderClick = async () => {
    const selectItems = cartItemList.filter((item) =>
      selectedItems.includes(item.cartItemId)
    );
    // 선택된 아이템들의 cartItemId 배열
    const cartItemOrderRequestList = selectItems.map((item) => {
      return { cartItemId: item.cartItemId };
    });

    console.log(cartItemOrderRequestList);

    const url = "http://localhost:8080/order/cart";
    // 서버로 선택된 아이템들의 정보를 보내는 요청을 보냄

    try {
      const response = await AxiosInstance.post(url, cartItemOrderRequestList, {
        headers: {
          "Content-Type": "application/json",
        },
      });

      console.log(response.data);
      // 주문 요청 후 orderId를 받아옴
      const orderId = response.data;
      navigate("/order", {
        state: { orderId: orderId },
      });
    } catch (error) {
      console.error("Error deleting cart item and updating list: ", error);
    }
  };

  return (
    <section className="cart_area">
      <div className="container">
        <div className="cart_inner">
          <div className="table-responsive">
            <table className="table">
              <thead>
                <tr>
                  <th scope="col">Select</th>
                  <th scope="col">Product</th>
                  <th scope="col">Price</th>
                  <th scope="col">Quantity</th>
                  <th scope="col">주문관리</th>
                  <th scope="col">Total</th>
                </tr>
              </thead>
              <tbody>
                {cartItemList.length === 0 ? (
                  <tr>
                    <td>장바구니가 비어 있습니다.</td>
                  </tr>
                ) : (
                  cartItemList.map(
                    (
                      item: {
                        cartItemId: number;
                        thumbnailImage: string;
                        name: string;
                        price: number;
                        count: number;
                      },
                      index: number
                    ) => (
                      <tr key={index}>
                        <td>
                          <input
                            type="checkbox"
                            checked={selectedItems.includes(item.cartItemId)}
                            onChange={() =>
                              handleCheckboxChange(item.cartItemId)
                            }
                          />
                        </td>
                        <td>
                          <div className="media">
                            <div className="d-flex">
                              <img
                                className="thumbnail_url"
                                src={item.thumbnailImage}
                                alt=""
                              />
                            </div>
                            <div className="media-body">
                              <p>{item.name}</p>
                            </div>
                          </div>
                        </td>
                        <td>
                          <h5>{item.price.toLocaleString()} 원</h5>
                        </td>
                        <td>
                          <p>{item.count}</p>
                        </td>
                        <td>
                          {/* 모달 열기 버튼 */}
                          <button
                            onClick={() => {
                              setShowModal(true);
                              setSelectedItemToUpdate({
                                cartItemId: item.cartItemId,
                                count: item.count,
                              });
                              console.log(showModal);
                              console.log(selectedItemToUpdate);
                            }}
                            // className="plain-btn btn"
                            className={"modal-open-btn"}
                          >
                            수량변경
                          </button>
                          <a
                            onClick={() => {
                              const confirmed =
                                window.confirm("선택한 아이템을 삭제할까요?");
                              if (confirmed) {
                                deleteCartItemAndUpdateList([item.cartItemId]);
                              }
                            }}
                            className="plain-btn btn"
                          >
                            삭제하기
                          </a>
                        </td>

                        <td>
                          <h5>
                            {(item.price * item.count).toLocaleString()} 원
                          </h5>
                        </td>
                      </tr>
                    )
                  )
                )}

                {cartItemList.length !== 0 && (
                  <tr>
                    <td></td>
                    <td></td>
                    <td>
                      <h5>Subtotal</h5>
                    </td>
                    <td>
                      <h5>
                        {cartItemList
                          .reduce(
                            (total: number, item) =>
                              (total += item.price * item.count),
                            0
                          )
                          .toLocaleString()}
                        원
                      </h5>
                    </td>
                  </tr>
                )}
                <tr className="out_button_area">
                  <td className="d-none-l"></td>
                  <td></td>
                  <td></td>
                  <td>
                    <div className="checkout_btn_inner d-flex align-items-center">
                      <button
                        onClick={handleDeleteClick}
                        disabled={selectedItemCount === 0} // 선택된 아이템이 없을때 비활성화
                      >
                        선택 삭제
                      </button>
                      <button
                        className="primary-btn ml-2"
                        onClick={handleOrderClick}
                        disabled={selectedItemCount === 0} // 선택된 아이템이 없을때 비활성화
                      >
                        주문하기
                      </button>
                    </div>
                  </td>
                </tr>
              </tbody>
            </table>
          </div>
        </div>
      </div>
      {showModal && (
        <div
          className={"modal-container"}
          onClick={(e) => {
            if (e.target === modalBackground.current) {
              setShowModal(false);
            }
          }}
        >
          <div className={"modal-content"}>
            <p>수량: {selectedItemToUpdate?.count}</p>
            <input type="number" value={count} onChange={handleCountChange} />
            <button
              className={"modal-open-btn"}
              onClick={() =>
                handleUpdateQuantity(
                  count || 0,
                  selectedItemToUpdate?.cartItemId || 0
                )
              }
            >
              확인
            </button>
            <button
              className={"modal-close-btn"}
              onClick={() => setShowModal(false)}
            >
              닫기
            </button>
          </div>
        </div>
      )}
    </section>
  );
};
export default Cart;
