import axios from "axios";
import React, { useEffect } from "react";
import { useNavigate, useParams, useSearchParams } from "react-router-dom";
import AxiosInstance from "../common/AxiosInstance";

const KakaoPayApprove = () => {
  const [searchParams] = useSearchParams();
  const navigate = useNavigate();
  const pgToken = searchParams.get("pg_token");

  useEffect(() => {
    const fetchPayUrl = async () => {
      try {
        const tid = localStorage.getItem("tid");
        const purchaseId = localStorage.getItem("purchaseId");
        console.log(tid);
        console.log(pgToken);
        const url =
          "http://localhost:8080/purchase/approve?pg_token=" +
          pgToken +
          "&tid=" +
          tid +
          "&purchaseId=" +
          purchaseId;
        const response = await AxiosInstance.get(url);

        navigate("/purchase/success");
      } catch (error) {}
    };

    fetchPayUrl();
  }, []);

  return <div>KakaoPayApprove</div>;
};

export default KakaoPayApprove;
