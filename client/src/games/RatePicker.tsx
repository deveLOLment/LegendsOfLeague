import React, { useState, useEffect } from "react";
import "./RatePicker.css";

interface Props {
  playerId: number;
  onRateChange: (playerId: number, score: string) => void;
}

const RatePicker: React.FC<Props> = ({ playerId, onRateChange }) => {
  const [selectedScore, setSelectedScore] = useState(""); // 선택된 점수를 저장할 상태
  const [isEditMode, setIsEditMode] = useState(true); // 수정 모드 상태
  const [isButtonVisible, setIsButtonVisible] = useState(true); // 수정 버튼의 가시성 상태

  useEffect(() => {
    // setIsEditMode(false);
    setIsButtonVisible(false);
    onRateChange(playerId, selectedScore);
  }, [selectedScore]);

  const handleScoreChange = (e: {
    target: { value: React.SetStateAction<string> };
  }) => {
    const score = e.target.value;
    setSelectedScore(score); // 선택된 점수를 상태에 업데이트
    setIsEditMode((prevMode) => !prevMode);
  };

  const handleEditButtonClick = () => {
    setIsEditMode(true); // 수정 모드 활성화
  };

  return (
    <div style={{ display: "flex", justifyContent: "space-between" }}>
      {/* 옵션 선택 메뉴 */}
      {isEditMode ? (
        <>
          <select value={selectedScore} onChange={handleScoreChange}>
            <option value="">Select a score</option>
            <option value="1">1</option>
            <option value="2">2</option>
            <option value="3">3</option>
            <option value="4">4</option>
            <option value="5">5</option>
            {/* 추가적인 옵션들 */}
          </select>
        </>
      ) : (
        <>
          <div>
            <span>{selectedScore}</span>
            <button className="edit-button" onClick={handleEditButtonClick}>
              Edit
            </button>
          </div>
        </>
      )}
    </div>
  );
};

export default RatePicker;
