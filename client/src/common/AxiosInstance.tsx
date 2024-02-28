import axios from 'axios';

const AxiosInstance = axios.create({
  baseURL: 'http://localhost:8080',
  withCredentials: true,
});

// 인터셉터를 이용한 에러 핸들링 설정
AxiosInstance.interceptors.response.use(
  response => {
    return response;
  },
  async error => {
    try {
      if (error.response) {
        switch (error.response.status) {
          case 401:
            alert("토큰이 만료됐습니다. 재로그인해주세요");
            window.location.href = "/login";
            // Unauthorized 상태 코드 처리
            // 이 안에서 완전한 처리를 수행합니다.
            return; // 에러를 더 이상 전파하지 않고 종료합니다.
          case 403:
            // Forbidden 상태 코드 처리
            window.location.href = '/';
            break;
          default:
            // 기타 상태 코드 처리
            break;
        }
      } else if (error.request) {
        // 요청이 발생하지 않은 경우 처리
        console.error('Request error:', error.request);
      } else {
        // 요청 설정 중 오류가 발생한 경우 처리
        console.error('Error setting up the request:', error.message);
      }
    } catch (error) {
      // 예외 처리 중에 오류가 발생한 경우
      console.error('Error in error handling:', error);
    }
    // 에러를 다시 반환하지 않고 처리를 종료합니다.
    return Promise.resolve(); // 에러를 resolve하고 종료합니다.
  }
);

export default AxiosInstance;
