import React from 'react';
import { Navigate, Outlet } from 'react-router-dom';
import * as useAuth from '@hooks/useAuth';
import { ACCESS_KEY } from '@components/common/constant';

export default function PrivateRoute() {
  // 이곳에 인증을 검증할 방법을 적으면 된다
  const isLogin = useAuth.getCookie(ACCESS_KEY);
  //console.log(isLogin);
  if (isLogin) {
    // 인증이 반드시 필요한 페이지
    return <Outlet />;
  }
  // 미로그인 유저가 이동할 곳
  return <Navigate replace to="/" />;
}
