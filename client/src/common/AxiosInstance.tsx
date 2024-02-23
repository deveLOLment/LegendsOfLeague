import axios from 'axios';

const axiosInstance = axios.create({
  baseURL: 'http://15.164.169.174:8080'
});



axiosInstance.interceptors.request.use(
  (config) => {
    let token = localStorage.getItem('access_token');
    console.log(token);

    // If no token in localStorage, then try to get it from cookies
    if (!token) {
      const cookieTokenRow = document.cookie.split('; ').find(row => row.startsWith('Authorization'));
      alert(cookieTokenRow);
      // .find(row => row.startsWith('Authorization'));
      alert(cookieTokenRow);
      if (cookieTokenRow) {
        //  Authoraization=Bearer+xxxx -> Bearer xxxx 형태로 변환
        token = cookieTokenRow.split('=')[1].replace(/\+/g, ' ');
        alert(token);
        // Save token to localStorage
        localStorage.setItem('access_token', token);
      }
    }

    if (token) {
      config.headers['Authorization'] = token;
    }
    return config;
  },
  (err) => {
    return Promise.reject(err);
  },
);

export default axiosInstance;

