import React from 'react';
import { useNavigate } from 'react-router-dom';
import axiosInstance from '../common/AxiosInstance';

const LogOut = () => {
  const navigate = useNavigate();

  const logout = () => {
    axiosInstance.post('/logout')
      .then(() => {
        alert("로그아웃 성공")
        // 성공적으로 로그아웃 되었을 때 루트 페이지로 이동
        navigate('/');
      })
      .catch(() => {
        // 이 부분은 axiosInstance의 인터셉터에서 처리할 수 있으므로 비어있어도 됩니다.
      });
  };

  return (
    <button onClick={logout}>Logout</button>
  );
};

export default LogOut;
