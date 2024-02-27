import { useState, useEffect } from 'react';
import { useNavigate, useLocation } from 'react-router-dom';

function useAuth() {
  let navigate = useNavigate();
  let location = useLocation();
  let [isAuthenticated, setIsAuthenticated] = useState(false);

  useEffect(() => {
    // 로컬 스토리지에서 토큰을 검사합니다. 실제 구현에서는 토큰의 유효성 검증이 필요합니다.
    let token = localStorage.getItem('token');
    if (token) {
      setIsAuthenticated(true);
    } else {
      setIsAuthenticated(false);
      navigate('/login', { replace: true, state: { from: location } });
    }
  }, [navigate, location]);

  return isAuthenticated;
}

export default useAuth;
