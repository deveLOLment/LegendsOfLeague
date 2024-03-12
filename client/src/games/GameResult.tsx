import React, { useState, useEffect } from "react";
import AxiosInstance from "../common/AxiosInstance";
import "../naver/css/rate.css";
import RatePicker from "./RatePicker";

interface Props {
  gameId: number | undefined;
}

interface Player {
  playerId: number;
  playerName: string;
  position: string;
}

interface Team {
  teamInGameId: number;
  teamName: string;
  score: number | null;
  rosters: Player[];
}

interface GameResultData {
  gameId: number;
  blue: Team;
  red: Team;
}

interface RateDto {
  playerId: number;
  score: string;
}

interface RateRequestDto {
  playerScores: RateDto[];
}

interface RateResponseDto {
  playerId: number;
  score: string | null;
  average: string | null;
}

const GameResult: React.FC<Props> = ({ gameId }) => {
  const [results, setResults] = useState<GameResultData>();
  const [playerScores, setPlayerScores] = useState<{ [key: number]: string }>(
    {}
  );
  const [isVoted, setIsVoted] = useState<boolean | undefined>();
  const [playerRates, setPlayerRates] = useState<RateResponseDto[]>([]);

  useEffect(() => {
    setPlayerScores({});
    getTeamsAndRostersInGame();
  }, [gameId]);

  const getTeamsAndRostersInGame = async () => {
    const url = `/games/${gameId}`;
    console.log(url);
    await AxiosInstance.get(url)
      .then(function (response) {
        console.log("팀, 로스터 가져오기: ", response.data);
        setResults(response.data);
        getRates();
      })
      .catch(function (error) {
        console.log(error);
      });
  };

  const getRates = async () => {
    console.log("각 게임의 출전 선수 평점들 가져오기");
    const url = `/rate/${gameId}`;

    await AxiosInstance.get(url)
      .then(function (response) {
        console.log("넘어온 평점 정보: ", response);
        if (response.data === "") {
          setIsVoted(false);
        } else {
          setIsVoted(true);
          const responseData: RateResponseDto[] = response.data;
          setPlayerRates(responseData);
        }
      })
      .catch(function (error) {
        console.log(error);
      });
  };

  const handleRateChange = (playerId: number, score: string) => {
    console.log("넘어온 선수ID: ", playerId);
    console.log(playerId + "의 평점: " + score);
    setPlayerScores((prevState) => ({
      ...prevState,
      [playerId]: score,
    }));
  };

  useEffect(() => {
    console.log("선수ID 평점 저장: ", playerScores);
  }, [playerScores]);

  const handleSubmit = async () => {
    try {
      const rateRequestDto: RateRequestDto = {
        playerScores: Object.entries(playerScores).map(([playerId, score]) => ({
          playerId: parseInt(playerId),
          score: score,
        })),
      };

      // 모든 RatePicker의 평점을 서버에 전송
      const url = `/rate/${gameId}`;
      console.log("넘어온 선수와 평점들: ", playerScores);
      const response = await AxiosInstance.post(url, rateRequestDto);
      console.log("Scores submitted:", response.data);
      if (response.data === "ok") {
        getRates();
      }
    } catch (error) {
      console.error("Error submitting scores:", error);
    }
  };

  return (
    <div>
      {results && (
        <div>
          <div className="graph_area__2NbpE graph_type_line__1HRyN graph_theme_light__2sBeZ">
            <div className="graph_item__22IOC">
              <div className="graph_wrap__1Zdmw">
                <div className="graph_inner__2D1F2">
                  <strong className="graph_name__1DsHh">
                    {results.blue.teamName}
                  </strong>
                </div>
                <div className="graph_score__MMIX- graph_defeat__3PsSt">
                  {results.blue.score}
                </div>
              </div>
            </div>
            <div className="graph_item__22IOC">
              <div className="graph_wrap__1Zdmw">
                <div className="graph_inner__2D1F2">
                  <strong className="graph_name__1DsHh">
                    {results.red.teamName}
                  </strong>
                </div>
                <div className="graph_score__MMIX-">{results.red.score}</div>
              </div>
            </div>
          </div>
          {/* 로스터 및 평점 */}
          <div className="review_container__1WuSH">
            <div className="review_review_area__3rDxr review_mvp_area__3-4Xi">
              <div className="review_team_area__bL195">
                <div className="review_player_area__1YtxS">
                  <div className="review_team_name__3E9su">
                    {results.blue.teamName}
                  </div>
                  <ul>
                    {results.blue.rosters.map((player, index) => (
                      <li
                        key={player.playerId}
                        className="review_team_detail__1ciDS"
                      >
                        <div className="review_information__1867t">
                          <div
                            className="review_player_name__2_FRo"
                            style={{
                              display: "flex",
                              justifyContent: "space-between",
                            }}
                          >
                            <span className="review_name_text__dj2wk">
                              {player.playerName}
                            </span>
                            {isVoted ? (
                              <div>
                                {/* playerRates가 존재하고 playerId가 일치하는 RateDto를 찾아 average를 표시 */}
                                <span>평균: </span>
                                <span className="average">
                                  {playerRates?.find(
                                    (r) => r.playerId === player.playerId
                                  )?.average || "x"}
                                </span>
                                <span></span>
                                <span>내 점수: </span>
                                <span className="score">
                                  {playerRates?.find(
                                    (r) => r.playerId === player.playerId
                                  )?.score || "x"}
                                </span>
                              </div>
                            ) : (
                              <div>
                                <RatePicker
                                  playerId={player.playerId}
                                  onRateChange={handleRateChange}
                                />
                              </div>
                            )}
                          </div>
                        </div>
                      </li>
                    ))}
                  </ul>
                </div>
                <div className="review_player_area__1YtxS">
                  <div className="review_team_name__3E9su">
                    {results.red.teamName}
                  </div>
                  <ul>
                    {results.red.rosters.map((player, index) => (
                      <li
                        key={player.playerId}
                        className="review_team_detail__1ciDS"
                      >
                        <div className="review_information__1867t">
                          <div
                            className="review_player_name__2_FRo"
                            style={{
                              display: "flex",
                              justifyContent: "space-between",
                            }}
                          >
                            <span className="review_name_text__dj2wk">
                              {player.playerName}
                            </span>
                            {isVoted ? (
                              <div>
                                {/* playerRates가 존재하고 playerId가 일치하는 RateDto를 찾아 average를 표시 */}
                                <span>평균: </span>
                                <span className="average">
                                  {playerRates?.find(
                                    (r) => r.playerId === player.playerId
                                  )?.average || "x"}
                                </span>
                                <span></span>
                                <span>내 점수: </span>
                                <span className="score">
                                  {playerRates?.find(
                                    (r) => r.playerId === player.playerId
                                  )?.score || "x"}
                                </span>
                              </div>
                            ) : (
                              <div>
                                {/* isTimeEnd가 false일 때 보여줄 UI */}
                                <RatePicker
                                  playerId={player.playerId}
                                  onRateChange={handleRateChange}
                                />
                              </div>
                            )}
                          </div>
                        </div>
                      </li>
                    ))}
                  </ul>
                </div>
              </div>
              <button type="button" onClick={handleSubmit}>
                투표
              </button>
            </div>
          </div>
        </div>
      )}
    </div>
  );
};

export default GameResult;
