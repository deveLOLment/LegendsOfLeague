import React, { useState } from 'react';
import axiosInstance from '../common/AxiosInstance';


export const AdminPage = () => {
    const[data,setData] = useState<String>(""); 

  const fetchAdminPage = async () => {
    axiosInstance.get('http://localhost:8080/admin')
      .then(response => {
        setData(response.data);
      })
      .catch(error => {
        console.error('Error fetching data:', error);
      });
  };

  return (
    <div>adminPage
      <button onClick={fetchAdminPage}>관리자 페이지</button>
    </div>
  );
};

export default AdminPage;