// import { FC } from "react";
// import { useState, useEffect } from "react";
// import AxiosInstance from "../common/AxiosInstance";
// import { useLocation, useNavigate } from "react-router-dom";
// import Pagination from "react-js-pagination";

// interface ItemListResponseDto {
//   id: number;
//   name: string;
//   category: string;
//   thumbnailImage: string;
//   price: number;
// }

// interface PageResponseDto<T> {
//   content: T[];
//   totalPage: number;
//   page: number;
//   size: number;
//   totalCount: number;
//   start: number;
//   end: number;
//   prev: boolean;
//   next: boolean;
//   pageList: number[];
// }

// const Shop: FC = () => {
//   const staticUrl = "http://localhost:8080";
//   const navigate = useNavigate();
//   const location = useLocation();

//   const [items, setItems] = useState<ItemListResponseDto[]>([]);
//   const [pageInfo, setPageInfo] = useState<
//     PageResponseDto<ItemListResponseDto>
//   >({
//     content: [],
//     totalPage: 1,
//     page: 1,
//     size: 15,
//     totalCount: 0,
//     start: 1,
//     end: 1,
//     prev: false,
//     next: false,
//     pageList: [],
//   });

//   const [searchKeyword, setSearchKeyword] = useState<string>("");
//   const [selectedCategory, setSelectedCategory] = useState<string>("");
//   const [selectedSort, setSelectedSort] = useState<string>("");

//   const handleSearch = () => {
//     let newUrl = "/shop";

//     if (searchKeyword.trim() !== "") {
//       newUrl += `?keyword=${searchKeyword}`;
//       setSelectedCategory(""); // Reset category if keyword is present
//     } else if (selectedCategory !== "") {
//       newUrl += `?category=${selectedCategory}`;
//     }

//     if (pageInfo.page !== 1) {
//       newUrl = "/shop";
//     }

//     navigate(newUrl);
//   };

//   useEffect(() => {
//     fetchShopData();
//   }, []);

//   useEffect(() => {
//     const queryParams = new URLSearchParams(location.search);
//     const keywordParam = queryParams.get("keyword");
//     const pageParam = queryParams.get("page");

//     if (keywordParam !== null) {
//       setSearchKeyword(keywordParam);
//     }

//     if (pageParam !== null) {
//       const page = parseInt(pageParam);
//       setPageInfo((prevPageInfo) => ({
//         ...prevPageInfo,
//         page: page,
//       }));
//     }

//     fetchShopData();
//   }, [location.search]);

//   const fetchShopData = async () => {
//     try {
//       const queryParams = new URLSearchParams(location.search);
//       let url = `${staticUrl}/shop`;

//       if (queryParams.has("keyword")) {
//         url += `?keyword=${queryParams.get("keyword")}`;
//       } else if (queryParams.has("category")) {
//         url += `?category=${queryParams.get("category")}`;
//       }

//       const response = await AxiosInstance.get(url);
//       const data: PageResponseDto<ItemListResponseDto> = response.data;
//       setPageInfo(data);
//       setItems(data.content);
//     } catch (error) {
//       console.error("Error fetching shop data:", error);
//     }
//   };

//   const handleItemClick = (itemId: number) => {
//     const itemUrl = `/item/${itemId}`;
//     navigate(itemUrl);
//   };

//   const handlePageChange = (page: number) => {
//     setPageInfo((prevPageInfo) => ({
//       ...prevPageInfo,
//       page: page,
//     }));
//     const queryParams = new URLSearchParams(location.search);
//     queryParams.set("page", page.toString());
//     navigate(`${location.pathname}?${queryParams.toString()}`);
//   };

//   const handleCategoryChange = (e: React.ChangeEvent<HTMLInputElement>) => {
//     const category = e.target.value;
//     setSelectedCategory(category);
//     setSearchKeyword(""); // Reset keyword when category is selected
//     const queryParams = new URLSearchParams(location.search);
//     queryParams.set("category", category);
//     navigate(`${location.pathname}?${queryParams.toString()}`);
//   };

//   const handleSortChange = (e: React.ChangeEvent<HTMLSelectElement>) => {
//     const sort = e.target.value;
//     setSelectedSort(sort);
//     const queryParams = new URLSearchParams(location.search);
//     queryParams.set("sort", sort);
//     navigate(`${location.pathname}?${queryParams.toString()}`);
//   };

//   return (
//     <section className="section-margin--small mb-5">
//       <div className="container">
//         <div className="row">
//           <div className="col-xl-3 col-lg-4 col-md-5">
//             <div className="sidebar-categories">
//               <div className="head">카테고리</div>
//               <ul className="main-categories">
//                 <li className="common-filter">
//                   <form action="#">
//                     <ul>
//                       <li className="filter-list">
//                         <input
//                           className="pixel-radio"
//                           type="radio"
//                           id="all"
//                           name="brand"
//                           value=""
//                           onChange={handleCategoryChange}
//                           checked={selectedCategory === ""}
//                         />
//                         <label htmlFor="all">
//                           전체<span> (3600)</span>
//                         </label>
//                       </li>
//                       <li className="filter-list">
//                         <input
//                           className="pixel-radio"
//                           type="radio"
//                           id="men"
//                           name="brand"
//                           value="CLOTHING"
//                           onChange={handleCategoryChange}
//                           checked={selectedCategory === "CLOTHING"}
//                         />
//                         <label htmlFor="men">
//                           의류<span> (3600)</span>
//                         </label>
//                       </li>
//                       <li className="filter-list">
//                         <input
//                           className="pixel-radio"
//                           type="radio"
//                           id="women"
//                           name="brand"
//                           value="ACCESSORIES"
//                           onChange={handleCategoryChange}
//                           checked={selectedCategory === "ACCESSORIES"}
//                         />
//                         <label htmlFor="women">
//                           액세서리<span> (3600)</span>
//                         </label>
//                       </li>
//                       <li className="filter-list">
//                         <input
//                           className="pixel-radio"
//                           type="radio"
//                           id="footwear"
//                           name="brand"
//                           value="STATIONERY"
//                           onChange={handleCategoryChange}
//                           checked={selectedCategory === "STATIONERY"}
//                         />
//                         <label htmlFor="footwear">
//                           문구류<span> (3600)</span>
//                         </label>
//                       </li>
//                       <li className="filter-list">
//                         <input
//                           className="pixel-radio"
//                           type="radio"
//                           id="bayItem"
//                           name="brand"
//                           value="SPORTS_OUTDOOR"
//                           onChange={handleCategoryChange}
//                           checked={selectedCategory === "SPORTS_OUTDOOR"}
//                         />
//                         <label htmlFor="bayItem">
//                           스포츠 및 야외용품<span> (3600)</span>
//                         </label>
//                       </li>
//                     </ul>
//                   </form>
//                 </li>
//               </ul>
//             </div>
//           </div>
//           <div className="col-xl-9 col-lg-8 col-md-7">
//             <div className="filter-bar d-flex flex-wrap align-items-center">
//               <div className="sorting">
//                 <select style={{ display: "block" }}>
//                   <option value="1">Default sorting</option>
//                   <option value="1">Default sorting</option>
//                   <option value="1">Default sorting</option>
//                 </select>
//                 <div className="nice-select" tabIndex={0}>
//                   {/* <span className="current">Default sorting</span>
//                   <ul className="list">
//                     <li data-value="1" className="option">
//                       Default sorting
//                     </li>
//                     <li data-value="1" className="option selected focus">
//                       Default sorting
//                     </li>
//                     <li data-value="1" className="option">
//                       Default sorting
//                     </li>
//                   </ul> */}
//                 </div>
//               </div>
//               <div className="sorting mr-auto">
//                 <select style={{ display: "none" }}>
//                   <option value="1">Show 12</option>
//                   <option value="1">Show 12</option>
//                   <option value="1">Show 12</option>
//                 </select>
//                 <div className="nice-select" tabIndex={0}>
//                   <span className="current">Show 12</span>
//                   <ul className="list">
//                     <li data-value="1" className="option">
//                       Show 12
//                     </li>
//                     <li data-value="1" className="option selected focus">
//                       Show 12
//                     </li>
//                     <li data-value="1" className="option">
//                       Show 12
//                     </li>
//                   </ul>
//                 </div>
//               </div>
//               <div>
//                 <div className="input-group filter-bar-search">
//                   <input
//                     type="text"
//                     placeholder="Search"
//                     value={searchKeyword}
//                     onChange={(e) => setSearchKeyword(e.target.value)}
//                   />
//                   <div className="input-group-append">
//                     <button type="button" onClick={handleSearch}>
//                       <i className="ti-search"></i>
//                     </button>
//                   </div>
//                 </div>
//               </div>
//             </div>
//             <section className="lattest-product-area pb-40 category-list">
//               <div className="row">
//                 {items.map((item) => (
//                   <div className="col-md-6 col-lg-4" key={item.id}>
//                     <div
//                       className="card text-center card-product"
//                       onClick={() => handleItemClick(item.id)}
//                     >
//                       <div className="card-product__img">
//                         <img
//                           className="card-img"
//                           src={item.thumbnailImage}
//                           alt={item.name}
//                         />
//                         ...
//                       </div>
//                       <div className="card-body">
//                         <p>{item.category}</p>
//                         <h4 className="card-product__title">
//                           <a href="#">{item.name}</a>
//                         </h4>
//                         <p className="card-product__price">${item.price}</p>
//                       </div>
//                     </div>
//                   </div>
//                 ))}
//               </div>
//             </section>
//             <Pagination
//               activePage={pageInfo.page}
//               itemsCountPerPage={pageInfo.size}
//               totalItemsCount={pageInfo.totalCount}
//               pageRangeDisplayed={10}
//               onChange={handlePageChange}
//               itemClass="page-item"
//               linkClass="page-link"
//               activeClass="active"
//             />
//           </div>
//         </div>
//       </div>
//     </section>
//   );
// };

// export default Shop;

import { FC } from "react";
import { useState, useEffect } from "react";
import AxiosInstance from "../common/AxiosInstance";
import { useLocation, useNavigate } from "react-router-dom";
import Pagination from "react-js-pagination";
import { keyboard } from "@testing-library/user-event/dist/keyboard";

interface ItemListResponseDto {
  id: number;
  name: string;
  category: string;
  thumbnailImage: string;
  price: number;
}

interface PageResponseDto<T> {
  content: T[];
  totalPage: number;
  page: number;
  size: number;
  totalCount: number;
  start: number;
  end: number;
  prev: boolean;
  next: boolean;
  pageList: number[];
}

const Shop: FC = () => {
  const staticUrl = "http://localhost:8080";
  const navigate = useNavigate();
  const location = useLocation();

  const [items, setItems] = useState<ItemListResponseDto[]>([]);
  const [pageInfo, setPageInfo] = useState<
    PageResponseDto<ItemListResponseDto>
  >({
    content: [],
    totalPage: 1,
    page: 1,
    size: 15,
    totalCount: 0,
    start: 1,
    end: 1,
    prev: false,
    next: false,
    pageList: [],
  });

  const [searchKeyword, setSearchKeyword] = useState<string>("");
  const [selectedCategory, setSelectedCategory] = useState<string>("");
  const [selectedSort, setSelectedSort] = useState<string>("");
  const [selectedOrder, setSelectedOrder] = useState<string>("desc");

  useEffect(() => {
    fetchShopData();
  }, []);

  useEffect(() => {
    console.log("hello");
    const queryParams = new URLSearchParams(location.search);
    const keywordParam = queryParams.get("keyword");
    const categoryParam = queryParams.get("category");
    const pageParam = queryParams.get("page");
    const orderParam = queryParams.get("order");

    if (keywordParam !== null) {
      setSearchKeyword(keywordParam);
    }

    if (categoryParam !== null) {
      setSelectedCategory(categoryParam);
    }

    if (pageParam !== null) {
      const page = parseInt(pageParam);
      setPageInfo((prevPageInfo) => ({
        ...prevPageInfo,
        page: page,
      }));
    }

    if (orderParam === null) {
      setSelectedOrder("asc");
    }

    fetchShopData(); // Removed pageParam? from fetchShopData()
  }, [location.search]);

  const fetchShopData = async () => {
    try {
      const queryParams = new URLSearchParams(location.search);
      let url = `${staticUrl}/shop`;

      // Append query parameters based on user inputs
      if (selectedCategory !== "") {
        queryParams.set("category", selectedCategory);
      }
      if (searchKeyword.trim() !== "") {
        queryParams.set("keyword", searchKeyword);
      }
      queryParams.set("page", pageInfo.page.toString()); // Include the page parameter

      // Construct the final URL with query parameters
      url += `?${queryParams.toString()}`;

      console.log("url", url);

      const response = await AxiosInstance.get(url);
      const data: PageResponseDto<ItemListResponseDto> = response.data;
      setPageInfo(data);
      setItems(data.content);
    } catch (error) {
      console.error("Error fetching shop data:", error);
    }
  };

  const handleItemClick = (itemId: number) => {
    const itemUrl = `/item/${itemId}`;
    navigate(itemUrl);
  };

  const handlePageChange = (page: number) => {
    setPageInfo((prevPageInfo) => ({
      ...prevPageInfo,
      page: page,
    }));

    const queryParams = new URLSearchParams(location.search);
    queryParams.set("page", page.toString());
    queryParams.set("category", selectedCategory); // Include selected category
    navigate(`${location.pathname}}`);
  };

  const handleSearch = () => {
    let newUrl = "/shop";

    const queryParams = new URLSearchParams(location.search);
    if (searchKeyword.trim() !== "") {
      queryParams.set("keyword", searchKeyword);
      setSelectedCategory(""); // Reset category if keyword is present
    } else if (selectedCategory !== "") {
      queryParams.set("category", selectedCategory);
    }

    // Reset page to 1 when searching
    queryParams.set("page", "1");

    // Navigate to the new URL
    navigate(`${newUrl}?${queryParams.toString()}`);
  };

  const handleCategoryChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const category = e.target.value;
    setSelectedCategory(category);
    setSearchKeyword(""); // Reset keyword when category is selected

    // Reset page to 1 when changing category
    const queryParams = new URLSearchParams(location.search);
    queryParams.set("category", category);
    queryParams.set("page", "1"); // Reset page to 1

    // Navigate to the new URL
    navigate(`${location.pathname}?${queryParams.toString()}`);
  };

  const handleSortChange = (e: React.ChangeEvent<HTMLSelectElement>) => {
    const sort = e.target.value;
    setSelectedSort(sort);

    // Update query parameter
    const queryParams = new URLSearchParams(location.search);
    queryParams.set("sort", sort);
    navigate(`${location.pathname}?${queryParams.toString()}`);
  };

  const handleOrderChange = (e: React.ChangeEvent<HTMLSelectElement>) => {
    const order = e.target.value;
    setSelectedOrder(order);

    // Update query parameter
    const queryParams = new URLSearchParams(location.search);
    queryParams.set("order", order);
    navigate(`${location.pathname}?${queryParams.toString()}`);
  };

  return (
    <section className="section-margin--small mb-5">
      <div className="container">
        <div className="row">
          <div className="col-xl-3 col-lg-4 col-md-5">
            <div className="sidebar-categories">
              <div className="head">카테고리</div>
              <ul className="main-categories">
                <li className="common-filter">
                  <form action="#">
                    <ul>
                      <li className="filter-list">
                        <input
                          className="pixel-radio"
                          type="radio"
                          id="all"
                          name="category"
                          value=""
                          onChange={handleCategoryChange}
                          checked={selectedCategory === ""}
                        />
                        <label htmlFor="all">
                          전체<span> (3600)</span>
                        </label>
                      </li>
                      <li className="filter-list">
                        <input
                          className="pixel-radio"
                          type="radio"
                          id="men"
                          name="category"
                          value="CLOTHING"
                          onChange={handleCategoryChange}
                          checked={selectedCategory === "CLOTHING"}
                        />
                        <label htmlFor="men">
                          의류<span> (3600)</span>
                        </label>
                      </li>
                      <li className="filter-list">
                        <input
                          className="pixel-radio"
                          type="radio"
                          id="women"
                          name="category"
                          value="ACCESSORIES"
                          onChange={handleCategoryChange}
                          checked={selectedCategory === "ACCESSORIES"}
                        />
                        <label htmlFor="women">
                          액세서리<span> (3600)</span>
                        </label>
                      </li>
                      <li className="filter-list">
                        <input
                          className="pixel-radio"
                          type="radio"
                          id="footwear"
                          name="category"
                          value="STATIONERY"
                          onChange={handleCategoryChange}
                          checked={selectedCategory === "STATIONERY"}
                        />
                        <label htmlFor="footwear">
                          문구류<span> (3600)</span>
                        </label>
                      </li>
                      <li className="filter-list">
                        <input
                          className="pixel-radio"
                          type="radio"
                          id="bayItem"
                          name="category"
                          value="SPORTS_OUTDOOR"
                          onChange={handleCategoryChange}
                          checked={selectedCategory === "SPORTS_OUTDOOR"}
                        />
                        <label htmlFor="bayItem">
                          스포츠 및 야외용품<span> (3600)</span>
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
                <select
                  style={{ display: "block" }}
                  onChange={handleSortChange}
                  value={selectedSort}
                >
                  <option value="createdTime">신상품순</option>
                  <option value="price">가격순</option>
                  <option value="stock">재고순</option>
                </select>
                <div className="nice-select" tabIndex={0}>
                  {/* <span className="current">Default sorting</span>
                  <ul className="list">
                    <li data-value="1" className="option">
                      Default sorting
                    </li>
                    <li data-value="1" className="option selected focus">
                      Default sorting
                    </li>
                    <li data-value="1" className="option">
                      Default sorting
                    </li>
                  </ul> */}
                </div>
              </div>
              <div className="sorting mr-auto">
                <select
                  style={{ display: "block" }}
                  onChange={handleOrderChange}
                  value={selectedOrder}
                >
                  <option value="desc">내림차순</option>
                  <option value="asc">오름차순</option>
                </select>
              </div>
              <div>
                <div className="input-group filter-bar-search">
                  <input
                    type="text"
                    placeholder="Search"
                    value={searchKeyword}
                    onChange={(e) => setSearchKeyword(e.target.value)}
                  />
                  <div className="input-group-append">
                    <button type="button" onClick={handleSearch}>
                      <i className="ti-search"></i>
                    </button>
                  </div>
                </div>
              </div>
            </div>
            <section className="lattest-product-area pb-40 category-list">
              <div className="row">
                {items.map((item) => (
                  <div className="col-md-6 col-lg-4" key={item.id}>
                    <div
                      className="card text-center card-product"
                      onClick={() => handleItemClick(item.id)}
                    >
                      <div className="card-product__img">
                        <img
                          className="card-img"
                          src={item.thumbnailImage}
                          alt={item.name}
                        />
                        ...
                      </div>
                      <div className="card-body">
                        <p>{item.category}</p>
                        <h4 className="card-product__title">
                          <a href="#">{item.name}</a>
                        </h4>
                        <p className="card-product__price">${item.price}</p>
                      </div>
                    </div>
                  </div>
                ))}
              </div>
            </section>
            <Pagination
              activePage={pageInfo.page}
              itemsCountPerPage={pageInfo.size}
              totalItemsCount={pageInfo.totalCount}
              pageRangeDisplayed={10}
              onChange={handlePageChange}
              itemClass="page-item"
              linkClass="page-link"
              activeClass="active"
            />
          </div>
        </div>
      </div>
    </section>
  );
};

export default Shop;
