import React from 'react'

import { useNavigate } from 'react-router-dom';




const googleLoginReady = () => {
  const onGoogleLogin = () => {
    window.location.href =
      "https://15.164.169.174.nip.io/oauth2/authorization/google";

// alert(document.cookie);

// 쿠키에서 토큰 꺼내오기

// const token = document.cookie
//     .split('; ')
//     .find(row => row.startsWith('Authorization'))
//     .split("=")[1]
//     .replace(/\+/g, ' ');
// const token = document.cookie
//     .split('; ')
//     .find(row => row.startsWith('Authorization'))
//     ?.split('=')[1]?.replace(/\+/g, ' ')
//     ?? '기본값';

// alert(token);

// // 토큰 저장
// localStorage.setItem("access_token", token);

// // 토큰 만료 시간 설정
// const expirationTime = Date.now() + 3600 * 1000; // 현재 시간에 1시간을 더함

// // 토큰 만료 시간 저장

// localStorage.setItem('tokenExpiration', expirationTime.toString());


    }

  return (

    <div>googleLoginReady

        <button onClick={onGoogleLogin}>googleLogin</button>

    </div>

  )

}



export default googleLoginReady
