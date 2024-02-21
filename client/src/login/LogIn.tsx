import axios from 'axios';
import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';

function LoginPage() {
    const navigate = useNavigate();
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');

  const handleLogin = async () => {
    try {
        const url = 'http://localhost:8080/login';

    const response = await axios.post(url, {
        username : username,
        password : password,
    });

    const token = response.headers.authorization;
    
    console.log(token);

    localStorage.setItem("access_token", token);

    navigate("/");

    }catch (e){

    }
  };

  return (
    <div>
      <h1>로그인 페이지</h1>
      <input
        type="text"
        placeholder="사용자 이름"
        value={username}
        onChange={(e) => setUsername(e.target.value)}
      />
      <input
        type="password"
        placeholder="비밀번호"
        value={password}
        onChange={(e) => setPassword(e.target.value)}
      />
      <button onClick={handleLogin}>로그인</button>
    </div>
  );
}

export default LoginPage;