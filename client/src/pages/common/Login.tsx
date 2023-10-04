import React, { useEffect } from 'react';
import { useNavigate } from 'react-router-dom';

import * as useAuth from '@hooks/useAuth';
import Loading from '@components/Animation/Loading';
import axios from 'axios';

const LoginSuccess = () => {
  const navigate = useNavigate();

  const params = new URLSearchParams(window.location.search);

  useEffect(() => {
    const auth = async () => {
      const token = {
        authToken: params.get('token'),
      };

      try {
        const res = await axios.post(
          `${import.meta.env.VITE_APP_SERVER}/api/v1/auth`,
          token,
        );
        const accessToken = res.data.results.accessToken;
        const refreshToken = res.data.results.refreshToken;
        useAuth.setAccessToken(accessToken);
        useAuth.setRefreshToken(refreshToken);

        console.log(accessToken, refreshToken);
        navigate('/');
      } catch (err) {
        alert('로그인에 실패했습니다. 잠시 후 다시 시도해주세요');
      }
    };

    auth();
  }, []);

  return (
    <>
      <Loading />
    </>
  );
};

export default LoginSuccess;
