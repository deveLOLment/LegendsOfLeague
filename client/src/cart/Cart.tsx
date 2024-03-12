import React, { useState, useEffect, MouseEvent, useRef } from "react";
import { useNavigate } from "react-router-dom";
import AxiosInstance from "../common/AxiosInstance";
import BlogBanner from "../layout/BlogBanner";

const Cart = () => {
  const modalBackground = useRef();

  const [showModal, setShowModal] = useState<boolean>(false);
  const [selectedItemToUpdate, setSelectedItemToUpdate] = useState<{
    cartItemId: number;
    count: number;
    stock: number;
  } | null>(null);

  const navigate = useNavigate();
  const [cartItemList, setCartItemList] = useState<
    {
      cartItemId: number;
      thumbnailImage: string;
      name: string;
      price: number;
      count: number;
      stock: number;
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
    const url = "/carts";
    try {
      const response = await AxiosInstance.get(url);
      setCartItemList(response.data);
    } catch (error) {
      console.error("Error fetching cart items: ", error);
    }
  };

  const handleUpdateQuantity = async (
    updatedCount: number,
    cartItemId: number,
    stock: number
  ) => {
    const url = `/cart/${cartItemId}/update`;

    if (updatedCount > stock) {
      alert("입력한 수량이 재고를 초과합니다!");
      return;
    }

    if (updatedCount < 0) {
      alert("0보다 큰 값을 입력해주세요!");
      return;
    }

    try {
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
    const url = "/cart/delete";

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

    const url = "/order/cart";
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
    <>
      <BlogBanner title="Shopping Cart"></BlogBanner>
      <section className="cart_area">
        <div className="container">
          <div className="cart_inner">
            <div className="table-responsive">
              <table className="table">
                <thead>
                  <tr>
                    <th className="col-1">Select</th>
                    <th className="col-6">Product</th>
                    <th className="col-2">Price</th>
                    <th className="col-1">Quantity</th>
                    <th className="col-2">Management</th>
                    <th className="col-2">Total</th>
                  </tr>
                </thead>
                <tbody>
                  {cartItemList.length === 0 ? (
                    <tr>
                      <td className="empty-cart" colSpan={6}>
                        장바구니가 비어 있습니다.
                      </td>
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
                          stock: number;
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
                                <h5>{item.name}</h5>
                              </div>
                            </div>
                          </td>
                          <td>
                            <h5>{item.price.toLocaleString()}원</h5>
                          </td>
                          <td>
                            <h5>{item.count}</h5>
                          </td>
                          <td>
                            {/* 모달 열기 버튼 */}
                            <button
                              onClick={() => {
                                setShowModal(true);
                                setSelectedItemToUpdate({
                                  cartItemId: item.cartItemId,
                                  count: item.count,
                                  stock: item.stock,
                                });
                                console.log(showModal);
                                console.log(selectedItemToUpdate);
                              }}
                              // className="plain-btn btn"
                              className="btn btn-light mb-3"
                            >
                              수량변경
                            </button>
                            <button
                              onClick={() => {
                                const confirmed =
                                  window.confirm("선택한 아이템을 삭제할까요?");
                                if (confirmed) {
                                  deleteCartItemAndUpdateList([
                                    item.cartItemId,
                                  ]);
                                }
                              }}
                              className="btn btn-light"
                            >
                              삭제하기
                            </button>
                          </td>
                          <td>
                            <h5>
                              {(item.price * item.count).toLocaleString()}원
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
                    <td></td>
                    <td>
                      <div className="checkout_btn_inner d-flex align-items-center">
                        <button
                          className="white_button"
                          onClick={handleDeleteClick}
                          disabled={selectedItemCount === 0} // 선택된 아이템이 없을때 비활성화
                          style={{ fontSize: "12px" }}
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
                    <td></td>
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
              <div className="modal-box">
                <div className="qty-box">
                  <p>재고(Stock): {selectedItemToUpdate?.stock}</p>
                </div>
                <div>
                  <input
                    className="form-control"
                    type="number"
                    value={count}
                    onChange={handleCountChange}
                  />
                </div>
                <div className="modal-open-btn-box">
                  <button
                    className="button"
                    onClick={() =>
                      handleUpdateQuantity(
                        count || 0,
                        selectedItemToUpdate?.cartItemId || 0,
                        selectedItemToUpdate?.stock || 0
                      )
                    }
                  >
                    확인
                  </button>
                </div>
                <div className="modal-close-btn-box">
                  <button
                    className="white_button"
                    onClick={() => setShowModal(false)}
                  >
                    닫기
                  </button>
                </div>
              </div>
            </div>
          </div>
        )}
      </section>
    </>
  );
};
export default Cart;
