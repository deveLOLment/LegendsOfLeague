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

const Games = () => {
  const [games, setGames] = useState<GameScheduleData[]>([]);
  const [selectedGameId, setSelectedGameId] = useState<number>(1); // 선택된 경기 상태 추가
  const plugins = [new Arrow()];

  useEffect(() => {
    getGames();
  }, []);

  const getGames = async () => {
    try {
      const url = "http://localhost:8080/games/schedule";
      const response = await AxiosInstance.get(url);
      const fetchedGames = response.data;
      setGames(fetchedGames);
    } catch (error) {
      console.error("Error fetching games:", error);
    }
  };

  const handleGameClick = (gameId: number) => {
    setSelectedGameId(gameId);
  };

  return (
    <div className="layout_container__wBrag">
      <div className="layout_content__2OO80">
        <div className="community_container__FmFdx">
          <div className="community_contents__1d9Is">
            <div className="gametalk_section__1JlKB">
              <div className="gametalk_game__1O8-m">
                <div className="game_area__3c2yW">
                  <span className="game_gradient__2Yrhv game_gradient_left__10O7T"></span>
                  <div className="game_box__2B_5o">
                    <div className="game_list__2CE7m">
                      <div>
                        <Flicking circular={true} plugins={plugins}>
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
                          <ViewportSlot>
                            <span className="flicking-arrow-prev is-circle"></span>
                            <span className="flicking-arrow-next is-circle"></span>
                          </ViewportSlot>
                        </Flicking>
                      </div>
                    </div>
                  </div>
                </div>
                <>
                  <GameResult gameId={selectedGameId} />
                  <ReviewComment gameId={selectedGameId} />
                </>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Games;
