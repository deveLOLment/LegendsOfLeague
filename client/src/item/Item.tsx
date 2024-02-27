import axios from "axios";
import React, { useState, useEffect, MouseEvent, useRef } from "react";
import { useNavigate } from "react-router-dom";
import { TypePredicateKind } from "typescript";
import AxiosInstance from "../common/AxiosInstance";

const Item = () => {
  const navigate = useNavigate();
  const [quantity, setQuantity] = useState<number>(1);
  const quantityInputRef = useRef<HTMLInputElement>(null);

  const increaseQuantity = () => {
    setQuantity((prevQuantity) => prevQuantity + 1);
  };

  const decreaseQuantity = () => {
    if (quantity > 1) {
      setQuantity((prevQuantity) => prevQuantity - 1);
    }
  };

  const addToCart = () => {
    const url = "http://localhost:8080/cart/add";
  };

  return (
    <div className="product_image_area">
      <div className="container">
        <div className="row s_product_inner">
          <div className="col-lg-6">
            <div className="owl-carousel owl-theme s_Product_carousel owl-loaded owl-drag">
              {/* Your carousel items here */}
            </div>
          </div>
          <div className="col-lg-5 offset-lg-1">
            <div className="s_product_text">
              <h3>Faded SkyBlu Denim Jeans</h3>
              <h2>$149.99</h2>
              <ul className="list">
                <li>
                  <a className="active" href="#">
                    <span>Category</span> : Household
                  </a>
                </li>
                <li>
                  <a href="#">
                    <span>Availibility</span> : In Stock
                  </a>
                </li>
              </ul>
              <p>
                Mill Oil is an innovative oil filled radiator with the most
                modern technology. If you are looking for something that can
                make your interior look awesome, and at the same time give you
                the pleasant warm feeling during the winter.
              </p>
              <div className="product_count">
                <label htmlFor="qty">Quantity:</label>
                <button
                  onClick={increaseQuantity}
                  className="increase items-count"
                  type="button"
                >
                  <i className="ti-angle-left"></i>
                </button>
                <input
                  ref={quantityInputRef}
                  type="text"
                  name="qty"
                  id="sst"
                  size={2}
                  maxLength={12}
                  value={quantity}
                  title="Quantity:"
                  className="input-text qty"
                />
                <button
                  onClick={decreaseQuantity}
                  className="reduced items-count"
                  type="button"
                >
                  <i className="ti-angle-right"></i>
                </button>
                <br />
                <a onClick={addToCart} className="button primary-btn" href="#">
                  Add to Cart
                </a>
              </div>
            </div>
          </div>
        </div>
      </div>
      <section className="product_description_area">
        <div className="container">
          <ul className="nav nav-tabs" id="myTab" role="tablist">
            <li className="nav-item">
              <a
                className="nav-link"
                id="home-tab"
                data-toggle="tab"
                href="#home"
                role="tab"
                aria-controls="home"
                aria-selected="true"
              >
                Description
              </a>
            </li>
            <li className="nav-item">
              <a
                className="nav-link"
                id="profile-tab"
                data-toggle="tab"
                href="#profile"
                role="tab"
                aria-controls="profile"
                aria-selected="false"
              >
                Specification
              </a>
            </li>
            <li className="nav-item">
              <a
                className="nav-link"
                id="contact-tab"
                data-toggle="tab"
                href="#contact"
                role="tab"
                aria-controls="contact"
                aria-selected="false"
              >
                Comments
              </a>
            </li>
            <li className="nav-item">
              <a
                className="nav-link active"
                id="review-tab"
                data-toggle="tab"
                href="#review"
                role="tab"
                aria-controls="review"
                aria-selected="false"
              >
                Reviews
              </a>
            </li>
          </ul>
          <div className="tab-content" id="myTabContent">
            <div
              className="tab-pane fade"
              id="home"
              role="tabpanel"
              aria-labelledby="home-tab"
            >
              <p>Description content goes here.</p>
            </div>
            <div
              className="tab-pane fade"
              id="profile"
              role="tabpanel"
              aria-labelledby="profile-tab"
            >
              <div className="table-responsive">
                <table className="table">
                  <tbody>
                    <tr>
                      <td>
                        <h5>Width</h5>
                      </td>
                      <td>
                        <h5>128mm</h5>
                      </td>
                    </tr>
                    {/* Specification content rows */}
                  </tbody>
                </table>
              </div>
            </div>
            <div
              className="tab-pane fade"
              id="contact"
              role="tabpanel"
              aria-labelledby="contact-tab"
            >
              {/* Comments content goes here */}
            </div>
            <div
              className="tab-pane fade show active"
              id="review"
              role="tabpanel"
              aria-labelledby="review-tab"
            >
              {/* Reviews content goes here */}
            </div>
          </div>
        </div>
      </section>
    </div>
  );
};
export default Item;
