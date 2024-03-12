import { useState, useEffect, Component } from "react";
import AxiosInstance from "../common/AxiosInstance";
import "../naver/css/common.css";
import "@egjs/react-flicking/dist/flicking.css";
import Flicking, { ViewportSlot, find } from "@egjs/react-flicking";
import GameResult from "./GameResult";
import { Arrow } from "@egjs/flicking-plugins";
import "@egjs/flicking-plugins/dist/arrow.css";
import ReviewComment from "./ReviewComment";

interface GameScheduleData {
  gameId: number;
  dateTime: string;
  blue: {
    teamInGameId: number;
    teamName: string;
  };
  red: {
    teamInGameId: number;
    teamName: string;
  };
}

interface ReviewCommentResponse {
  reviewCommentId: number;
}

const Games = () => {
  const [games, setGames] = useState<GameScheduleData[]>([]);
  const [selectedGameId, setSelectedGameId] = useState<number | undefined>(); // 선택된 경기 상태 추가
  const [isActive, setIsActive] = useState<boolean | undefined>();
  const plugins = [new Arrow()];

  useEffect(() => {
    getGames();
  }, []);

  const getGames = async () => {
    try {
      const url = "/games/schedule";
      const response = await AxiosInstance.get(url);
      const fetchedGames = response.data;
      setGames(fetchedGames);
      const now = new Date();
      console.log("지금 시간: ", now);

      for (let i = 0; i < fetchedGames.length - 1; i++) {
        const currentGame = fetchedGames[i];
        const nextGame = fetchedGames[i + 1];

        const currentGameDateTime = new Date(currentGame.dateTime);
        const nextGameDateTime = new Date(nextGame.dateTime);

        console.log("현재 게임 시작 시간: ", currentGameDateTime);
        console.log("다음 게임 시작 시간: ", nextGameDateTime);

        if (
          currentGameDateTime <= now ||
          (currentGameDateTime <= now && now < nextGameDateTime)
        ) {
          setIsActive(true);
          setSelectedGameId(currentGame.gameId);
          break;
        } else {
          setIsActive(false);
        }
      }

      for (const game of fetchedGames) {
        const gameDateTime = new Date(game.dateTime);
        console.log("게임 시작 시간: ", gameDateTime);
        if (gameDateTime >= now) {
          setSelectedGameId(game.gameId); // 다가오는 게임의 id를 설정
          break; // 다가오는 게임을 찾았으므로 더 이상 반복할 필요가 없음
        }
      }
    } catch (error) {
      console.error("Error fetching games:", error);
    }
  };

  const handleGameClick = (gameId: number) => {
    setSelectedGameId(gameId);
  };

  return (
    <div className="layout_container__wBrag">
      <div className="layout_wrapper__1fOTj">
        <div className="layout_content__2OO80">
          <div className="community_container__FmFdx">
            <div className="community_contents__1d9Is">
              <div className="gametalk_section__1JlKB">
                <div className="gametalk_game__1O8-m">
                  <div className="game_area__3c2yW">
                    {/* <button
            className="game_button__2M05t game_button_previous__3q-NT"
            type="button"
          >
            <span className="blind">이전으로 이동</span>
          </button> */}
                    <span className="game_gradient__2Yrhv game_gradient_left__10O7T"></span>
                    <div className="game_box__2B_5o">
                      <div className="game_list__2CE7m">
                        <div>
                          <Flicking circular={true} plugins={plugins}>
                            {/* <div className="eg-flick-viewport">
                    <div className="eg-flick-camera"> */}
                            {games.map((game) => (
                              <div
                                className="game_item__2K7Iq eg-flick-panel"
                                key={game.gameId}
                                onClick={() => handleGameClick(game.gameId)}
                              >
                                <button
                                  id={game.gameId.toString()}
                                  className="game_tab__1LSEL"
                                  aria-pressed="false"
                                  type="button"
                                >
                                  <span className="game_inner__3QjWD">
                                    <span className="game_name__2YSHc">
                                      {game.blue.teamName}
                                    </span>
                                    vs
                                    <span className="game_name__2YSHc">
                                      {game.red.teamName}
                                    </span>
                                  </span>
                                </button>
                              </div>
                            ))}
                            {/* </div>
                  </div> */}
                            <ViewportSlot>
                              <span className="flicking-arrow-prev is-circle"></span>
                              <span className="flicking-arrow-next is-circle"></span>
                            </ViewportSlot>
                          </Flicking>
                        </div>
                      </div>
                    </div>
                    {/* <button
            type="button"
            className="game_button__2M05t game_button_next__G2_dh"
          >
            <span className="blind">다음으로 이동</span>
          </button> */}
                  </div>
                  <>
                    <GameResult gameId={selectedGameId} isActive={isActive} />
                    <ReviewComment
                      gameId={selectedGameId}
                      isActive={isActive}
                    />
                  </>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Games;
