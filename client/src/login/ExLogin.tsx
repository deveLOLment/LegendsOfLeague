import React from 'react';
import { useNavigate } from 'react-router-dom';
import axiosInstance from '../common/AxiosInstance';

const ExLogin = () => {
  const navigate = useNavigate(); // useHistory 훅 사용

  const handleLogin = async () => {
    // AxiosInstance를 사용하여 요청을 보냅니다.
    const response = await axiosInstance.get('/ex', {
      // 로그인에 필요한 데이터를 전송할 수 있습니다.
      // username: 'yourUsername',
      // password: 'yourPassword',
    });

    // 응답이 성공하면 처리할 로직을 작성합니다.
    // console.log(response.data);
    
    // 예를 들어, 로그인 후 특정 페이지로 이동할 수 있습니다.
    // navigate("/");
  };

  return (
    <div>
      <h2>Login Page</h2>
      <button onClick={handleLogin}>Login</button>
    </div>
  );
};

export default ExLogin;
