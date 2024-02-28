import React, { useState } from 'react';
import axiosInstance from '../common/AxiosInstance';

export const AdminPage = () => {
    const [data, setData] = useState("");

    const fetchAdminPage = async () => {
        try {
            const response = await axiosInstance.get('/admin');
            if (response && response.data) {
                setData(response.data);
            }
        } catch (error) {
            alert("개별예외처리")
            console.error('Error fetching data:', error);
        } 
    };

    return (
        <div>
            <h2>Admin Page</h2>
            <button onClick={fetchAdminPage}>Fetch Admin Data</button>
            {data && <div>{data}</div>}
        </div>
    );
};

export default AdminPage;
