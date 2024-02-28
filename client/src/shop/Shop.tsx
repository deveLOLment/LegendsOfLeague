import React from "react";
import { FC } from "react";

const Shop: FC = () => {
  return (
    <section className="section-margin--small mb-5">
      <div className="container">
        <div className="row">
          <div className="col-xl-3 col-lg-4 col-md-5">
            <div className="sidebar-categories">
              <div className="head">Browse Categories</div>
              <ul className="main-categories">
                <li className="common-filter">
                  <form action="#">
                    <ul>
                      <li className="filter-list">
                        <input
                          className="pixel-radio"
                          type="radio"
                          id="men"
                          name="brand"
                        />
                        <label htmlFor="men">
                          Men<span> (3600)</span>
                        </label>
                      </li>
                      <li className="filter-list">
                        <input
                          className="pixel-radio"
                          type="radio"
                          id="women"
                          name="brand"
                        />
                        <label htmlFor="women">
                          Women<span> (3600)</span>
                        </label>
                      </li>
                      <li className="filter-list">
                        <input
                          className="pixel-radio"
                          type="radio"
                          id="accessories"
                          name="brand"
                        />
                        <label htmlFor="accessories">
                          Accessories<span> (3600)</span>
                        </label>
                      </li>
                      <li className="filter-list">
                        <input
                          className="pixel-radio"
                          type="radio"
                          id="footwear"
                          name="brand"
                        />
                        <label htmlFor="footwear">
                          Footwear<span> (3600)</span>
                        </label>
                      </li>
                      <li className="filter-list">
                        <input
                          className="pixel-radio"
                          type="radio"
                          id="bayItem"
                          name="brand"
                        />
                        <label htmlFor="bayItem">
                          Bay item<span> (3600)</span>
                        </label>
                      </li>
                      <li className="filter-list">
                        <input
                          className="pixel-radio"
                          type="radio"
                          id="electronics"
                          name="brand"
                        />
                        <label htmlFor="electronics">
                          Electronics<span> (3600)</span>
                        </label>
                      </li>
                      <li className="filter-list">
                        <input
                          className="pixel-radio"
                          type="radio"
                          id="food"
                          name="brand"
                        />
                        <label htmlFor="food">
                          Food<span> (3600)</span>
                        </label>
                      </li>
                    </ul>
                  </form>
                </li>
              </ul>
            </div>
          </div>
          <div className="col-xl-9 col-lg-8 col-md-7">
            <div className="filter-bar d-flex flex-wrap align-items-center">
              <div className="sorting">
                <select style={{ display: "none" }}>
                  <option value="1">Default sorting</option>
                  <option value="2">Option 1</option>
                  <option value="3">Option 2</option>
                </select>
                <div className="nice-select" tabIndex={0}>
                  <span className="current">Default sorting</span>
                  <ul className="list">
                    <li data-value="1" className="option selected focus">
                      Default sorting
                    </li>
                    <li data-value="2" className="option">
                      Option 1
                    </li>
                    <li data-value="3" className="option">
                      Option 2
                    </li>
                  </ul>
                </div>
              </div>
              <div className="sorting mr-auto">
                <select style={{ display: "none" }}>
                  <option value="1">Show 12</option>
                  <option value="1">Show 12</option>
                  <option value="1">Show 12</option>
                </select>
                <div className="nice-select" tabIndex={0}>
                  <span className="current">Show 12</span>
                  <ul className="list">
                    <li data-value="1" className="option selected focus">
                      Show 12
                    </li>
                    <li data-value="1" className="option">
                      Show 12
                    </li>
                    <li data-value="1" className="option">
                      Show 12
                    </li>
                  </ul>
                </div>
              </div>
              <div>
                <div className="input-group filter-bar-search">
                  <input type="text" placeholder="Search" />

                  <div className="input-group-append">
                    <button type="button">
                      <i className="ti-search"></i>
                    </button>
                  </div>
                </div>
              </div>
            </div>
            <section className="lattest-product-area pb-40 category-list">
              <div className="row">
                <div className="col-md-6 col-lg-4">
                  <div className="card text-center card-product">
                    <div className="card-product__img">
                      <img
                        className="card-img"
                        src="./img/product/product1.png"
                        alt=""
                      />

                      <ul className="card-product__imgOverlay">
                        <li>
                          <button>
                            <i className="ti-search"></i>
                          </button>
                        </li>
                        <li>
                          <button>
                            <i className="ti-shopping-cart"></i>
                          </button>
                        </li>
                        <li>
                          <button>
                            <i className="ti-heart"></i>
                          </button>
                        </li>
                      </ul>
                    </div>
                    <div className="card-body">
                      <p>Accessories</p>
                      <h4 className="card-product__title">
                        <a href="#">Quartz Belt Watch</a>
                      </h4>
                      <p className="card-product__price">$150.00</p>
                    </div>
                  </div>
                </div>
                <div className="col-md-6 col-lg-4">
                  <div className="card text-center card-product">
                    <div className="card-product__img">
                      <img
                        className="card-img"
                        src="img/product/product2.png"
                        alt=""
                      />
                      <ul className="card-product__imgOverlay">
                        <li>
                          <button>
                            <i className="ti-search"></i>
                          </button>
                        </li>
                        <li>
                          <button>
                            <i className="ti-shopping-cart"></i>
                          </button>
                        </li>
                        <li>
                          <button>
                            <i className="ti-heart"></i>
                          </button>
                        </li>
                      </ul>
                    </div>
                    <div className="card-body">
                      <p>Beauty</p>
                      <h4 className="card-product__title">
                        <a href="#">Women Freshwash</a>
                      </h4>
                      <p className="card-product__price">$150.00</p>
                    </div>
                  </div>
                </div>
                <div className="col-md-6 col-lg-4">
                  <div className="card text-center card-product">
                    <div className="card-product__img">
                      <img
                        className="card-img"
                        src="img/product/product3.png"
                        alt=""
                      />
                      <ul className="card-product__imgOverlay">
                        <li>
                          <button>
                            <i className="ti-search"></i>
                          </button>
                        </li>
                        <li>
                          <button>
                            <i className="ti-shopping-cart"></i>
                          </button>
                        </li>
                        <li>
                          <button>
                            <i className="ti-heart"></i>
                          </button>
                        </li>
                      </ul>
                    </div>
                    <div className="card-body">
                      <p>Decor</p>
                      <h4 className="card-product__title">
                        <a href="#">Room Flash Light</a>
                      </h4>
                      <p className="card-product__price">$150.00</p>
                    </div>
                  </div>
                </div>
                <div className="col-md-6 col-lg-4">
                  <div className="card text-center card-product">
                    <div className="card-product__img">
                      <img
                        className="card-img"
                        src="img/product/product4.png"
                        alt=""
                      />
                      <ul className="card-product__imgOverlay">
                        <li>
                          <button>
                            <i className="ti-search"></i>
                          </button>
                        </li>
                        <li>
                          <button>
                            <i className="ti-shopping-cart"></i>
                          </button>
                        </li>
                        <li>
                          <button>
                            <i className="ti-heart"></i>
                          </button>
                        </li>
                      </ul>
                    </div>
                    <div className="card-body">
                      <p>Decor</p>
                      <h4 className="card-product__title">
                        <a href="#">Room Flash Light</a>
                      </h4>
                      <p className="card-product__price">$150.00</p>
                    </div>
                  </div>
                </div>
                <div className="col-md-6 col-lg-4">
                  <div className="card text-center card-product">
                    <div className="card-product__img">
                      <img
                        className="card-img"
                        src="img/product/product5.png"
                        alt=""
                      />
                      <ul className="card-product__imgOverlay">
                        <li>
                          <button>
                            <i className="ti-search"></i>
                          </button>
                        </li>
                        <li>
                          <button>
                            <i className="ti-shopping-cart"></i>
                          </button>
                        </li>
                        <li>
                          <button>
                            <i className="ti-heart"></i>
                          </button>
                        </li>
                      </ul>
                    </div>
                    <div className="card-body">
                      <p>Accessories</p>
                      <h4 className="card-product__title">
                        <a href="#">Man Office Bag</a>
                      </h4>
                      <p className="card-product__price">$150.00</p>
                    </div>
                  </div>
                </div>
                <div className="col-md-6 col-lg-4">
                  <div className="card text-center card-product">
                    <div className="card-product__img">
                      <img
                        className="card-img"
                        src="img/product/product6.png"
                        alt=""
                      />
                      <ul className="card-product__imgOverlay">
                        <li>
                          <button>
                            <i className="ti-search"></i>
                          </button>
                        </li>
                        <li>
                          <button>
                            <i className="ti-shopping-cart"></i>
                          </button>
                        </li>
                        <li>
                          <button>
                            <i className="ti-heart"></i>
                          </button>
                        </li>
                      </ul>
                    </div>
                    <div className="card-body">
                      <p>Kids Toy</p>
                      <h4 className="card-product__title">
                        <a href="#">Charging Car</a>
                      </h4>
                      <p className="card-product__price">$150.00</p>
                    </div>
                  </div>
                </div>
                <div className="col-md-6 col-lg-4">
                  <div className="card text-center card-product">
                    <div className="card-product__img">
                      <img
                        className="card-img"
                        src="img/product/product7.png"
                        alt=""
                      />
                      <ul className="card-product__imgOverlay">
                        <li>
                          <button>
                            <i className="ti-search"></i>
                          </button>
                        </li>
                        <li>
                          <button>
                            <i className="ti-shopping-cart"></i>
                          </button>
                        </li>
                        <li>
                          <button>
                            <i className="ti-heart"></i>
                          </button>
                        </li>
                      </ul>
                    </div>
                    <div className="card-body">
                      <p>Accessories</p>
                      <h4 className="card-product__title">
                        <a href="#">Blutooth Speaker</a>
                      </h4>
                      <p className="card-product__price">$150.00</p>
                    </div>
                  </div>
                </div>
                <div className="col-md-6 col-lg-4">
                  <div className="card text-center card-product">
                    <div className="card-product__img">
                      <img
                        className="card-img"
                        src="img/product/product8.png"
                        alt=""
                      />
                      <ul className="card-product__imgOverlay">
                        <li>
                          <button>
                            <i className="ti-search"></i>
                          </button>
                        </li>
                        <li>
                          <button>
                            <i className="ti-shopping-cart"></i>
                          </button>
                        </li>
                        <li>
                          <button>
                            <i className="ti-heart"></i>
                          </button>
                        </li>
                      </ul>
                    </div>
                    <div className="card-body">
                      <p>Kids Toy</p>
                      <h4 className="card-product__title">
                        <a href="#">Charging Car</a>
                      </h4>
                      <p className="card-product__price">$150.00</p>
                    </div>
                  </div>
                </div>
                <div className="col-md-6 col-lg-4">
                  <div className="card text-center card-product">
                    <div className="card-product__img">
                      <img
                        className="card-img"
                        src="img/product/product1.png"
                        alt=""
                      />
                      <ul className="card-product__imgOverlay">
                        <li>
                          <button>
                            <i className="ti-search"></i>
                          </button>
                        </li>
                        <li>
                          <button>
                            <i className="ti-shopping-cart"></i>
                          </button>
                        </li>
                        <li>
                          <button>
                            <i className="ti-heart"></i>
                          </button>
                        </li>
                      </ul>
                    </div>
                    <div className="card-body">
                      <p>Accessories</p>
                      <h4 className="card-product__title">
                        <a href="#">Quartz Belt Watch</a>
                      </h4>
                      <p className="card-product__price">$150.00</p>
                    </div>
                  </div>
                </div>
              </div>
            </section>
          </div>
        </div>
      </div>
    </section>
  );
};

export default Shop;
