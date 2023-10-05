import {
  ACCESS_KEY,
  MEMBER_ID,
  ROLE,
  ROLE_ID,
} from '@components/common/constant';
import { Cookies } from 'react-cookie';

const cookies = new Cookies();

const getCookie = (key: string) => {
  if (cookies.get(key)) {
    return cookies.get(key);
  } else {
    return null;
  }
};

const setAccessToken = (token: string) => {
  document.cookie = `${ACCESS_KEY}=${token}; max-age=604800; path=/; secure; samesite=none`;
};

const setRefreshToken = (token: string) => {
  document.cookie = `Refresh=${token}; max-age=604800; path=/; secure; samesite=none`;
};

const setRole = (role: string) => {
  document.cookie = `${ROLE}=${role}; max-age=604800; path=/; secure; samesite=none`;
};

const setNickname = (nickname: string) => {
  document.cookie = `Nickname=${nickname}; max-age=604800; path=/; secure; samesite=none`;
};

const setRoleId = (id: number) => {
  document.cookie = `${ROLE_ID}=${id}; max-age=604800; path=/; secure; samesite=none`;
};

const setMemberId = (id: number) => {
  document.cookie = `${MEMBER_ID}=${id}; max-age=604800; path=/; secure; samesite=none`;
};

const deleteCookie = (key: string) => {
  const date = new Date('2020-01-01').toUTCString();
  document.cookie = `${key}=; expires=${date}; path=/;`;
  window.location.reload();
};

const deleteAllCookies = () => {
  const allCookies = cookies.getAll();
  Object.keys(allCookies).forEach((cookieName: string) => {
    const date = new Date('2020-01-01').toUTCString();
    document.cookie = `${cookieName}=; expires=${date}; path=/;`;
  });
  window.location.reload();
};

export {
  getCookie,
  setAccessToken,
  setRefreshToken,
  setRole,
  setNickname,
  setMemberId,
  setRoleId,
  deleteCookie,
  deleteAllCookies,
};
