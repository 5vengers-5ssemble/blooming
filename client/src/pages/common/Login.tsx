import React, { useEffect } from 'react';
import { useNavigate } from 'react-router-dom';

import * as useAuth from '@hooks/useAuth';
import Loading from '@components/Animation/Loading';
import axios from 'axios';
import {
  ROLE_ADMIN,
  ROLE_ARTIST,
  ROLE_USER,
} from '@components/common/constant';

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

        const data = res.data.results;

        const accessToken = data.accessToken;
        const refreshToken = data.refreshToken;
        useAuth.setAccessToken(accessToken);
        useAuth.setRefreshToken(refreshToken);

        //role 저장
        let roleToSet = ROLE_USER;

        const roles = data.member.role;

        console.log(data.member);

        if (roles.includes(ROLE_ADMIN)) {
          roleToSet = ROLE_ADMIN;
        } else if (roles.includes(ROLE_ARTIST)) {
          roleToSet = ROLE_ARTIST;
        }
        useAuth.setRole(roleToSet);

        //id, nickname 저장
        useAuth.setNickname(data.member.nickname);
        useAuth.setRoleId(data.member.id);

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
