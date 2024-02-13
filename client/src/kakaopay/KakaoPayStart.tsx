import React from "react";
import { useNavigate } from "react-router-dom";

const KakaoPayStart = () => {
  const navigate = useNavigate();

  const goToKakaoPayRay = () => {
    navigate("/purchase/kakaoPay/ready");
  };
  return (
    <div>
      <button onClick={goToKakaoPayRay}>카카오 페이 버튼</button>
    </div>
  );
};

export default KakaoPayStart;
