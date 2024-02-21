import React, { useEffect } from 'react'
import { useNavigate } from 'react-router-dom';

const LogOut = () => {
    const navigate = useNavigate();

    // const logOut = () => {

    //     window.location.href = "http://localhost:8080/logout"

    // }

    useEffect(() => {
      // 컴포넌트가 마운트될 때 실행되며, access_token을 제거하고 메인 페이지로 이동합니다.
      localStorage.removeItem('access_token');
      document.cookie = "Authorization=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;";

      navigate("/");
  }, []);

    // 로컬스토리지에서 'access_token'을 제거 후 메인 페이지로 이동시킨다.
    localStorage.removeItem('access_token');
    navigate("/");

  return (
    <div>logOut
        <button onClick={LogOut}>logOut</button>
    </div>
  )
}

export default LogOut