import axios from 'axios';
import * as useAuth from '@hooks/useAuth';
import { ACCESS_KEY } from '@components/common/constant';

export const BASE_URL = `${import.meta.env.VITE_APP_SERVER}/api/v1`;

const instance = axios.create({
  baseURL: BASE_URL,
  headers: {
    'Content-Type': 'application/json',
    Authorization:
      // 'eyJhbGciOiJIUzUxMiJ9.eyJpZCI6IjciLCJleHAiOjE2OTYwNzc3MTB9.qVMSbBjyL5Yl4uhsPYGTW1WGm_9hWSsR843EH4ZATPBrQKUzvFR3AIHkRvElqJ8UXBv91XoGjpSeQEVYjSQG1w',
      'eyJhbGciOiJIUzUxMiJ9.eyJpZCI6IjEiLCJleHAiOjE2OTc0NTg2NDZ9.rpccUlCOyvlRZr006QiMkJp5jGYqBhW5jCrOthxeydETPgfKBNYl6tOEbfzdBViIn4XMgBlk2DScY7m8E0Sn_Q',
  },
});

// Request 🧑
instance.interceptors.request.use(
  function (config) {
    const accessToken = useAuth.getCookie(ACCESS_KEY);
    if (accessToken) {
      config.headers[ACCESS_KEY] = accessToken;
    }
    return config;
  },
  function (error) {
    console.error(error);
    return Promise.reject(error);
  },
);

// Response 🧑
instance.interceptors.response.use();

export default instance;
