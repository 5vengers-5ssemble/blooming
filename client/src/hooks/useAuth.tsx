import { ACCESS_KEY } from '@components/common/constant';
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

const deleteCookie = (key: string) => {
  const date = new Date('2020-01-01').toUTCString();
  document.cookie = `${key}=; expires=${date}; path=/;`;
  window.location.reload();
};

export { getCookie, setAccessToken, setRefreshToken, deleteCookie };
