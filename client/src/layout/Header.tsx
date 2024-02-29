import React from "react";
import { Link, NavLink } from "react-router-dom";

const Header: React.FC = () => {
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
            <li
              className="menu_item__3PJvA"
              data-selected="true"
              data-app-status="false"
            >
              <Link className="menu_link__ia8Ru" data-active="false" to="#">
                <span className="blind">선택됨</span>
                <span className="menu_label__RP_su">
                  쇼핑몰
                  <em className="menu_badge__2x7xG">
                    <span className="blind">LIVE</span>
                  </em>
                </span>
              </Link>
            </li>
            <li
              className="menu_item__3PJvA"
              data-selected="false"
              data-app-status="false"
            >
              <Link className="menu_link__ia8Ru" data-active="false" to="#">
                <span className="menu_label__RP_su">
                  PUBG
                  <em className="menu_badge__2x7xG">
                    <span className="blind">LIVE</span>
                  </em>
                </span>
              </Link>
            </li>
            <li
              className="menu_item__3PJvA"
              data-selected="false"
              data-app-status="false"
            >
              <Link
                className="menu_link__ia8Ru"
                data-active="false"
                to="/esports/Valorant/home"
              >
                <span className="menu_label__RP_su">
                  발로란트
                  <em className="menu_badge__2x7xG">
                    <span className="blind">LIVE</span>
                  </em>
                </span>
              </Link>
            </li>
            <li
              className="menu_item__3PJvA"
              data-selected="false"
              data-app-status="false"
            >
              <Link className="menu_link__ia8Ru" data-active="false" to="#">
                <span className="menu_label__RP_su">일반</span>
              </Link>
            </li>
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
        <div className="col-md-4"></div>
        <div className="col-md-3">
          <ul className="sub_menu_wrap__3Je_v">
            <li className="sub_menu_item__Q2d1m">
              <NavLink
                to="/shop"
                className={"sub_menu_link__3BySZ"}
                data-new="false"
              >
                <span className="sub_menu_label__1q_VA">상품목록</span>
              </NavLink>
            </li>
            <li className="sub_menu_item__Q2d1m">
              <NavLink
                to="/item/1"
                className={"sub_menu_link__3BySZ"}
                data-new="false"
              >
                <span className="sub_menu_label__1q_VA">아무거나</span>
              </NavLink>
            </li>
          </ul>
        </div>

        <div className="col-md-3"></div>
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
        </ul>
      </div>
    </header>
  );
};

export default Header;
