import {
  LIVE_ID,
  LIVE_NICKNAME,
  LIVE_TITLE,
  SESSION_ID,
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

const setLiveId = (id: number) => {
  document.cookie = `${LIVE_ID}=${id}; max-age=604800; path=/; secure; samesite=none`;
};
const setLiveSessionId = (id: string) => {
  document.cookie = `${SESSION_ID}=${id}; max-age=604800; path=/; secure; samesite=none`;
};

const setLiveTitle = (title: string) => {
  document.cookie = `${LIVE_TITLE}=${title}; max-age=604800; path=/; secure; samesite=none`;
};

const setLiveNickName = (name: string) => {
  document.cookie = `${LIVE_NICKNAME}=${name}; max-age=604800; path=/; secure; samesite=none`;
};

const deleteCookie = (key: string) => {
  const date = new Date('2020-01-01').toUTCString();
  document.cookie = `${key}=; expires=${date}; path=/;`;
  window.location.reload();
};

export {
  getCookie,
  setLiveId,
  setLiveSessionId,
  setLiveTitle,
  setLiveNickName,
  deleteCookie,
};
