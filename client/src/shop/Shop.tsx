import { FC } from "react";
import { useRef, useReducer, useEffect, useCallback } from "react";
import AxiosInstance from "../common/AxiosInstance";
import { useLocation, useNavigate } from "react-router-dom";
import Pagination from "react-js-pagination";
import "../aroma-master/vendors/themify-icons/themify-icons.css";
import { truncateTitle } from "../common/utils";
import BlogBanner from "../layout/BlogBanner";

interface ItemListResponseDto {
  id: number;
  name: string;
  category: string;
  thumbnailImage: string;
  price: number;
}

interface PageResponseDto<T> {
  content: ItemListResponseDto[];
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

const initialState = {
  keyword: "",
  category: "",
  sort: "",
  page: 1,
  order: "desc",
  items: [],
  pageInfo: {
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
  },
};

interface State {
  keyword: string;
  category: string;
  page: number;
  order: string;
  sort: string;
  items: ItemListResponseDto[];
  pageInfo: PageResponseDto<ItemListResponseDto>;
}

type Action =
  | { type: "SET_KEYWORD"; payload: string }
  | { type: "SET_CATEGORY"; payload: string }
  | { type: "SET_PAGE"; payload: number }
  | { type: "SET_SORT"; payload: string }
  | { type: "SET_ORDER"; payload: string }
  | { type: "SET_ITEMS"; payload: ItemListResponseDto[] }
  | { type: "SET_PAGE_INFO"; payload: PageResponseDto<ItemListResponseDto> };

function reducer(state: State, action: Action): State {
  switch (action.type) {
    case "SET_KEYWORD":
      return { ...state, keyword: action.payload };
    case "SET_CATEGORY":
      return { ...state, category: action.payload };
    case "SET_PAGE":
      return { ...state, page: action.payload };
    case "SET_ORDER":
      return { ...state, order: action.payload };
    case "SET_SORT":
      return { ...state, sort: action.payload };
    case "SET_ITEMS":
      return { ...state, items: action.payload };
    case "SET_PAGE_INFO":
      return { ...state, pageInfo: action.payload };
    default:
      throw new Error();
  }
}

const Shop: FC = () => {
  const staticUrl = "http://localhost:8080";
  const navigate = useNavigate();
  const location = useLocation();

  const [state, dispatch] = useReducer<React.Reducer<State, Action>>(
    reducer,
    initialState
  );

  const fetchShopData = useCallback(async () => {
    const params = new URLSearchParams({
      keyword: state.keyword,
      category: state.category,
      page: state.page.toString(),
      order: state.order,
    });

    const response = await AxiosInstance.get(
      `${staticUrl}/shop?${params.toString()}`
    );

    const data: PageResponseDto<ItemListResponseDto> = response.data;

    dispatch({ type: "SET_ITEMS", payload: data.content });
    dispatch({ type: "SET_PAGE_INFO", payload: data });
  }, [state.keyword, state.category, state.page, state.order]);

  useEffect(() => {
    const queryParams = new URLSearchParams(location.search);
    const keywordParam = queryParams.get("keyword");
    const categoryParam = queryParams.get("category");
    const pageParam = queryParams.get("page");
    const orderParam = queryParams.get("order");
    const sortParam = queryParams.get("sort");

    if (keywordParam !== null) {
      dispatch({ type: "SET_KEYWORD", payload: keywordParam });
    }

    if (categoryParam !== null) {
      dispatch({ type: "SET_CATEGORY", payload: categoryParam });
    }

    if (pageParam !== null) {
      dispatch({ type: "SET_PAGE", payload: parseInt(pageParam) });
    }

    if (orderParam !== null) {
      dispatch({ type: "SET_ORDER", payload: orderParam });
    }

    if (sortParam !== null) {
      dispatch({ type: "SET_SORT", payload: sortParam });
    }

    fetchShopData();
  }, [location.search, fetchShopData]);

  const handleItemClick = (itemId: number) => {
    const itemUrl = `/item/${itemId}`;
    navigate(itemUrl);
  };

  const handlePageChange = (page: number) => {
    dispatch({ type: "SET_PAGE", payload: page });

    const queryParams = new URLSearchParams(location.search);
    queryParams.set("page", page.toString());
    queryParams.set("category", state.category);

    navigate(`${location.pathname}?${queryParams.toString()}`);
  };

  const inputRef = useRef<HTMLInputElement>(null);

  const handleSearch = () => {
    const keyword = inputRef.current ? inputRef.current.value : "";

    dispatch({ type: "SET_KEYWORD", payload: keyword });

    const queryParams = new URLSearchParams(location.search);
    if (keyword.trim() !== "") {
      queryParams.set("keyword", keyword.trim());
      dispatch({ type: "SET_CATEGORY", payload: "" });
    }

    queryParams.set("page", "1");

    navigate(`${location.pathname}?${queryParams.toString()}`);
  };

  const handleCategoryChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const category = e.target.value;

    dispatch({ type: "SET_CATEGORY", payload: category });
    dispatch({ type: "SET_KEYWORD", payload: "" }); //reset

    const queryParams = new URLSearchParams(location.search);
    queryParams.set("category", category);
    queryParams.set("keyword", "");
    queryParams.set("page", "1"); // Reset page to 1

    // Navigate to the new URL
    navigate(`${location.pathname}?${queryParams.toString()}`);
  };

  const handleSortChange = (e: React.ChangeEvent<HTMLSelectElement>) => {
    const sort = e.target.value;

    dispatch({ type: "SET_SORT", payload: sort });

    // Update query parameter
    const queryParams = new URLSearchParams(location.search);
    queryParams.set("sort", sort);
    navigate(`${location.pathname}?${queryParams.toString()}`);
  };

  const handleOrderChange = (e: React.ChangeEvent<HTMLSelectElement>) => {
    const order = e.target.value;

    dispatch({ type: "SET_ORDER", payload: order });

    // Update query parameter
    const queryParams = new URLSearchParams(location.search);
    queryParams.set("order", order);
    navigate(`${location.pathname}?${queryParams.toString()}`);
  };

  return (
    <>
      <BlogBanner title="Shop Category"></BlogBanner>
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
                            checked={state.category === ""}
                          />
                          <label htmlFor="all">전체</label>
                        </li>
                        <li className="filter-list">
                          <input
                            className="pixel-radio"
                            type="radio"
                            id="men"
                            name="category"
                            value="CLOTHING"
                            onChange={handleCategoryChange}
                            checked={state.category === "CLOTHING"}
                          />
                          <label htmlFor="men">의류</label>
                        </li>
                        <li className="filter-list">
                          <input
                            className="pixel-radio"
                            type="radio"
                            id="women"
                            name="category"
                            value="ACCESSORIES"
                            onChange={handleCategoryChange}
                            checked={state.category === "ACCESSORIES"}
                          />
                          <label htmlFor="women">액세서리</label>
                        </li>
                        <li className="filter-list">
                          <input
                            className="pixel-radio"
                            type="radio"
                            id="footwear"
                            name="category"
                            value="STATIONERY"
                            onChange={handleCategoryChange}
                            checked={state.category === "STATIONERY"}
                          />
                          <label htmlFor="footwear">문구류</label>
                        </li>
                        <li className="filter-list">
                          <input
                            className="pixel-radio"
                            type="radio"
                            id="bayItem"
                            name="category"
                            value="SPORTS_OUTDOOR"
                            onChange={handleCategoryChange}
                            checked={state.category === "SPORTS_OUTDOOR"}
                          />
                          <label htmlFor="bayItem">스포츠 및 야외용품</label>
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
                    value={state.sort}
                  >
                    <option value="createdTime">신상품순</option>
                    <option value="price">가격순</option>
                    <option value="stock">재고순</option>
                  </select>
                </div>
                <div className="sorting mr-auto">
                  <select
                    style={{ display: "block" }}
                    onChange={handleOrderChange}
                    value={state.order}
                  >
                    <option value="desc">내림차순</option>
                    <option value="asc">오름차순</option>
                  </select>
                </div>
                <div>
                  <div className="input-group filter-bar-search">
                    <input type="text" placeholder="Search" ref={inputRef} />
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
                  {state.items &&
                    state.items.map((item: ItemListResponseDto) => (
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
                          </div>
                          <div className="card-body">
                            <p>{item.category}</p>
                            <h6 className="card-product__title">
                              <a>{truncateTitle(item.name)}</a>
                            </h6>
                          </div>
                        </div>
                      </div>
                    ))}
                </div>
              </section>
              <Pagination
                activePage={state.pageInfo.page}
                itemsCountPerPage={state.pageInfo.size}
                totalItemsCount={state.pageInfo.totalCount}
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
    </>
  );
};
export default Shop;
