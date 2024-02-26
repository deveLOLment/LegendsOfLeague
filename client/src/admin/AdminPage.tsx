import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';

export const AdminPage = () => {
    const [data, setData] = useState("");
    const navigate = useNavigate();

    const fetchAdminPage = async () => {
        try {
            const response = await fetch('http://localhost:8080/admin', {
                method: 'GET',
                headers: {
                    'Content-Type': 'application/json',
                    // 이곳에 필요한 헤더를 추가할 수 있습니다.
                },
                credentials: 'include', // 여기에 추가
                });

            if (!response.ok) {
                if(response.status === 401){
                  alert("catch 401");
                  alert("토큰이 만료되었습니다. 재로그인 해주세요")
                  navigate("/login");
                  return;
                  // throw new Error('Network response was not ok');
                }else if(response.status === 403){
                  alert("catch 403");
                  navigate("/");
                  return;
                }
            }
            const responseData = await response;
            console.log(responseData);
            // setData(responseData);
          } catch (error) {
          // alert(response.status);
          alert(error);
           console.log(error);
            console.error('Error fetching data:', error);
            navigate("/login");
        } 
    };

    return (
        <div>
            adminPage
            <button onClick={fetchAdminPage}>관리자 페이지</button>
        </div>
    );
};

export default AdminPage;
