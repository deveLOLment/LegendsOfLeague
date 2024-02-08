import axios from "axios";
import React, { useEffect } from "react";
import { useNavigate, useParams, useSearchParams } from "react-router-dom";

const KakaoPayApprove = () => {
  const [searchParams] = useSearchParams();
  const navigate = useNavigate();
  const pgToken = searchParams.get("pg_token");

  console.log(pgToken);
  useEffect(() => {
    const fetchPayUrl = async () => {
      try {
        const tid = localStorage.getItem("tid");
        console.log(tid);
        console.log(pgToken);
        const url =
          "http://localhost:8080/purchase/approve?pg_token=" +
          pgToken +
          "&tid=" +
          tid;
        console.log(url);
        const response = await axios.get(url);
        localStorage.removeItem("tid");

        navigate("/purchase/success");
      } catch (error) {}
    };

    fetchPayUrl();
  }, []);

  return <div>KakaoPayApprove</div>;
};

export default KakaoPayApprove;
