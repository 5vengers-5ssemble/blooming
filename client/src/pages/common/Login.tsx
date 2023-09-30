import React, { useEffect } from 'react';
import { useNavigate } from 'react-router-dom';

import axios from '@api/apiController';
import * as useAuth from '@hooks/useAuth';
import Loading from '@components/Animation/Loading';

const LoginSuccess = () => {
  const navigate = useNavigate();

  const params = new URLSearchParams(window.location.search);

  useEffect(() => {
    const auth = async () => {
      const token = {
        authToken: params.get('token'),
      };

      try {
        const res = await axios.post('/auth', token);
        useAuth.setAccessToken(res.data.accessToken);
        useAuth.setRefreshToken(res.data.refreshToken);
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
