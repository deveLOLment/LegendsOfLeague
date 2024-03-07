import axios from 'axios';
import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
// import './LoginPage.css';

function LoginPage2() {
const navigate = useNavigate();
const [username, setUsername] = useState('');
const [password, setPassword] = useState('');

const handleLogin = async () => {
    try {
        const url = 'http://localhost:8080/login';

        const response = await axios.post(url, {
            username : username,
            password : password,
        },{
          withCredentials:true
        });

        console.log("응답결과:" + response);

        // const token = response.headers.authorization;

        // console.log(token);

        alert(response.status);

        // localStorage.setItem("access_token", token);

        // // 토큰 만료 시간 설정
        // const expirationTime = Date.now() + 3600 * 1000; // 현재 시간에 1시간을 더함

        // // 토큰 만료 시간 저장
        // localStorage.setItem('tokenExpiration', expirationTime.toString());



        // if(response.status === 401){
        //     alert("로그인 실패 401");
        // }



        navigate("/");

    } catch (e) {
        // 에러 처리
      console.log(e);
      alert("아이디나 비밀번호가 올바르지 않습니다.");
      }
};

return (
    <div className="login-box">
        <h2>Login</h2>
        <form>
            <div className="user-box">
                <input type="text" name="username" required={true} value={username} onChange={(e) => setUsername(e.target.value)} />
                <label>Username</label>
            </div>
            <div className="user-box">
                <input type="password" name="password" required={true} value={password} onChange={(e) => setPassword(e.target.value)} />
                <label>Password</label>
            </div>
            <a  onClick={handleLogin}>
                <span></span>
                <span></span>
                <span></span>
                <span></span>
                Submit
            </a>
        </form>
    </div>
);
}

export default LoginPage2;
