import React from "react";
import { Link } from "react-router-dom";

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
          <Link className="header_link__2fhJa header_naver__5F-Cm" to="#">
            <span className="blind">NAVER</span>
          </Link>
          <Link className="header_link__2fhJa header_esports__1m-Zr" to="#">
            <span className="blind">e스포츠</span>
          </Link>
          <div className="header_sub__3dG0w header_chzzk__2OvHG">
            <Link to="#" className="header_link__2fhJa" target="_blank">
              <span className="blind">치지직 Beta</span>
            </Link>
          </div>
          <div className="header_sub__3dG0w header_games__29Hij">
            <Link to="#" className="header_link__2fhJa" target="_blank">
              <span className="blind">GAME</span>
            </Link>
          </div>
          <div className="header_sub__3dG0w header_pcgame__3-Dkm">
            <Link to="#" className="header_link__2fhJa" target="_blank">
              <span className="blind">PC게임</span>
            </Link>
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
                  LoL
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
            <Link to="#" className="meta_profile__2eIqF">
              <span className="meta_label__1Kbvf">프로필</span>
            </Link>
          </div>
          <button type="button" className="header_sports__g6e74">
            <span className="header_label__2fkL-">스포츠</span>
          </button>
        </div>
      </div>
      <div className="header_menu__23d4_">
        <ul className="sub_menu_wrap__3Je_v">
          <li className="sub_menu_item__Q2d1m">
            <Link
              to="#"
              className="sub_menu_link__3BySZ"
              data-active="true"
              data-new="false"
            >
              <span className="sub_menu_label__1q_VA">홈</span>
            </Link>
          </li>
          <li className="sub_menu_item__Q2d1m">
            <Link
              to="#"
              className="sub_menu_link__3BySZ"
              data-active="false"
              data-new="false"
            >
              <span className="sub_menu_label__1q_VA">커뮤니티</span>
            </Link>
          </li>
          <li className="sub_menu_item__Q2d1m">
            <Link
              to="#"
              className="sub_menu_link__3BySZ"
              data-active="false"
              data-new="false"
            >
              <span className="sub_menu_label__1q_VA">경기</span>
            </Link>
          </li>
          <li className="sub_menu_item__Q2d1m">
            <Link
              to="#"
              className="sub_menu_link__3BySZ"
              data-active="false"
              data-new="false"
            >
              <span className="sub_menu_label__1q_VA">뉴스</span>
            </Link>
          </li>
          <li className="sub_menu_item__Q2d1m">
            <Link
              to="#"
              className="sub_menu_link__3BySZ"
              data-active="false"
              data-new="false"
            >
              <span className="sub_menu_label__1q_VA">일정</span>
            </Link>
          </li>
          <li className="sub_menu_item__Q2d1m">
            <Link
              to="#"
              className="sub_menu_link__3BySZ"
              data-active="false"
              data-new="false"
            >
              <span className="sub_menu_label__1q_VA">영상</span>
            </Link>
          </li>
          <li className="sub_menu_item__Q2d1m">
            <Link
              to="#"
              className="sub_menu_link__3BySZ"
              data-active="false"
              data-new="false"
            >
              <span className="sub_menu_label__1q_VA">순위</span>
            </Link>
          </li>
          <li className="sub_menu_item__Q2d1m">
            <Link
              to="#"
              className="sub_menu_link__3BySZ"
              data-active="false"
              data-new="false"
            >
              <span className="sub_menu_label__1q_VA">승부예측</span>
            </Link>
          </li>
        </ul>
      </div>
    </header>
  );
};

export default Header;
