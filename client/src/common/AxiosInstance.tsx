import axios from "axios";
import { useNavigate } from "react-router-dom";

// Axios 인스턴스 생성
const AxiosInstance = axios.create({
  baseURL: "http://localhost:8080",
  withCredentials: true,
});

// 인터셉터를 이용한 에러 핸들링 설정
AxiosInstance.interceptors.response.use(
  (response) => {
    // 응답이 성공적일 경우, 응답을 반환
    return response;
  },
  (error) => {
    // 에러가 있을 경우 상태 코드에 따라 다르게 핸들링
    const navigate = useNavigate();
    if (error.response) {
      switch (error.response.status) {
        case 401: // Unauthorized
          // 예를 들어, 로그인 페이지로 리다이렉트한다.
          navigate("/login");
          break;

        case 403: // Forbidden
          // 예를 들어, 홈페이지로 리다이렉트한다.
          navigate("/");
          break;

        // 다른 HTTP 상태 코드에 대한 처리...
      }
    }
    return Promise.reject(error);
  }
);

export default AxiosInstance;
