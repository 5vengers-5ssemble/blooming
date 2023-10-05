import axios from 'axios';
import * as useAuth from '@hooks/useAuth';
import { ACCESS_KEY, REFRESH_KEY } from '@components/common/constant';

export const BASE_URL = `${import.meta.env.VITE_APP_SERVER}/api/v1`;

const instance = axios.create({
  baseURL: BASE_URL,
  headers: {
    'Content-Type': 'application/json',
  },
});

// Request 🧑
instance.interceptors.request.use(
  function (config) {
    const accessToken = useAuth.getCookie(ACCESS_KEY);
    if (accessToken) {
      config.headers[ACCESS_KEY] = `Bearer ${accessToken}`;
      // config.headers[ACCESS_KEY] = `Bearer ${accessToken}`;
    }
    return config;
  },
  function (error) {
    console.error(error);
    return Promise.reject(error);
  },
);

// Response 🧑
instance.interceptors.response.use(
  async (response) => {
    return response;
  },
  async (error) => {
    const originalRequest = error.config;
    // 401 에러면 refresh token 보내기
    if (error?.response?.data?.status === 401) {
      // console.log('access-token 만료됐어');
      try {
        // console.log('refresh-token 보낼게!');
        const response = await axios.post(`${BASE_URL}/auth/refresh`, {
          refreshToken: useAuth.getCookie(REFRESH_KEY),
        });

        // console.log('이전 access : ', getAccessToken());
        // console.log('이전 refresh : ', getRefreshToken());

        // **응답 헤더에서 Access Token과 Refresh Token 추출
        const accessToken = response.data.results.accessToken;
        const refreshToken = response.data.results.refreshToken;
        // console.log('이후 access : ', accessToken);
        // console.log('이후 refresh : ', refreshToken);
        // **access token 을 다시 setting 하고 origin request 를 재요청
        useAuth.setAccessToken(accessToken);
        useAuth.setRefreshToken(refreshToken);
        originalRequest.headers[ACCESS_KEY] = `Bearer ${accessToken}`;

        // **새로운 토큰 발급 확인
        // console.log(accessToken, refreshToken);

        return axios(originalRequest);
      } catch (error) {
        // **만약 refreshToken 보내도 error 가 뜨면 login 화면으로 보내기 -> redirect
        //!login 이동
        window.location.href = '/'; // 로그인화면으로 보내기
        useAuth.deleteAllCookies();
      }

      return Promise.reject(error);
    }
  },
);

export default instance;
