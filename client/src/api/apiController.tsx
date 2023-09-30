import axios from 'axios';
import * as useAuth from '@hooks/useAuth';

export const BASE_URL = 'http://localhost:8080/api/v1';
// export const BASE_URL = 'http://localhost:7700';

const instance = axios.create({
  baseURL: BASE_URL,
  headers: {
    'Content-Type': 'application/json',
    Authorization:
      'eyJhbGciOiJIUzUxMiJ9.eyJpZCI6IjciLCJleHAiOjE2OTYwNzc3MTB9.qVMSbBjyL5Yl4uhsPYGTW1WGm_9hWSsR843EH4ZATPBrQKUzvFR3AIHkRvElqJ8UXBv91XoGjpSeQEVYjSQG1w',
  },
});

// Request 🧑
instance.interceptors.request.use(
  function (config) {
    const accessToken = useAuth.getCookie('Authorization');
    if (accessToken) {
      config.headers['Authorization'] = accessToken;
    }
    return config;
  },
  function (error) {
    return Promise.reject(error);
  },
);

// Response 🧑
instance.interceptors.response.use();

export default instance;
