import React, { useState } from "react";
import { Link, NavLink } from "react-router-dom";

const menuItems = [
  { name: "쇼핑몰", url: "/shop" },
  { name: "LOL", url: "/lol" },
  { name: "관리자", url: "/admin" },
];

const Header: React.FC = () => {
  const [selectedItem, setSelectedItem] = useState<String | null>(null);

  const handleClick = (item: string) => {
    setSelectedItem(item);
  };

  return (
    <header
      className="header_container__2I4IR"
      id="header"
      data-app-status="false"
      data-fixed="false"
    >
      <div className="header_wrapper__3hW8A">
        <div className="header_area__33WsE">
          <div className="header_link__2fhJa header_naver__5F-Cm">
            <img
              className="img-fluid"
              src="https://legends-of-league.s3.ap-northeast-2.amazonaws.com/KakaoTalk_Photo_2024-02-28-17-56-03.png"
            ></img>
          </div>
        </div>
        <div>
          <ul
            className="menu_area__1jIEJ"
            data-fixed="false"
            data-app-status="false"
          >
            {menuItems.map((item) => (
              <li
                key={item.name}
                className="menu_item__3PJvA"
                data-selected={selectedItem === item.name}
                data-app-status="false"
              >
                <Link
                  className="menu_link__ia8Ru"
                  data-active="false"
                  to={item.url}
                  onClick={() => handleClick(item.name)}
                >
                  <span className="blind">선택됨</span>
                  <span className="menu_label__RP_su">
                    {item.name}
                    <em className="menu_badge__2x7xG">
                      <span className="blind">LIVE</span>
                    </em>
                  </span>
                </Link>
              </li>
            ))}
          </ul>
        </div>
        <div className="header_button__3XaEu">
          <div className="meta_container__35H5h">
            <div className="ticket_container__2iJfs">
              <button type="button" className="ticket_container-ticket__3wtdH">
                <span className="ticket_container-ticket__label__9E6b1">
                  라운지 티켓
                </span>
              </button>
            </div>
            <div className="notification_container__1Nvbl">
              <button
                className="notification_container-noti__3rvM8"
                type="button"
                data-not-read="true"
              >
                <span className="notification_container-noti__label__3ra5g">
                  수신함
                </span>
              </button>
            </div>
            <Link to="/login" className="meta_profile__2eIqF">
              <span className="meta_label__1Kbvf">프로필</span>
            </Link>
          </div>
          <button type="button" className="header_sports__g6e74">
            <span className="header_label__2fkL-">스포츠</span>
          </button>
        </div>
      </div>
      <div className="header_menu__23d4_ ">
        <div className="col-md-2"></div>
        <div className="col-md-5">
          <ul className="sub_menu_wrap__3Je_v">
            <li className="sub_menu_item__Q2d1m">
              <NavLink
                to="/shop"
                className={"sub_menu_link__3BySZ"}
                data-new="false"
              >
                <span className="sub_menu_label__1q_VA">홈</span>
              </NavLink>
            </li>
            <li className="sub_menu_item__Q2d1m">
              <NavLink
                to="/item/1"
                className={"sub_menu_link__3BySZ"}
                data-new="false"
              >
                <span className="sub_menu_label__1q_VA">상세 아이템</span>
              </NavLink>
            </li>
            <li className="sub_menu_item__Q2d1m">
              <NavLink
                to="/coupon/register"
                className={"sub_menu_link__3BySZ"}
                data-new="false"
              >
                <span className="sub_menu_label__1q_VA">쿠폰 등록</span>
              </NavLink>
            </li>
            <li className="sub_menu_item__Q2d1m">
              <NavLink
                to="/order-list"
                className={"sub_menu_link__3BySZ"}
                data-new="false"
              >
                <span className="sub_menu_label__1q_VA">주문 내역 조회</span>
              </NavLink>
            </li>
          </ul>
        </div>

        <div className="col-md-2"></div>
        <ul className="sub_menu_wrap__3Je_v">
          <li className="sub_menu_item__Q2d1m">
            <NavLink
              to="/carts"
              className={"sub_menu_link__3BySZ"}
              data-new="false"
            >
              <span className="sub_menu_label__1q_VA">장바구니</span>
            </NavLink>
          </li>
          <li className="sub_menu_item__Q2d1m">
            <NavLink
              to="/coupon/list"
              className={"sub_menu_link__3BySZ"}
              data-new="false"
            >
              <span className="sub_menu_label__1q_VA">쿠폰함</span>
            </NavLink>
          </li>
        </ul>
      </div>
    </header>
  );
};

export default Header;
