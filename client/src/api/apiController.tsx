import axios from 'axios';

export const BASE_URL = 'http://localhost:8080/api/v1';
// export const BASE_URL = 'http://localhost:7700';

const instance = axios.create({
  baseURL: BASE_URL,
  headers: {
    'Content-Type': 'application/json',
    Authorization:
      'eyJhbGciOiJIUzUxMiJ9.eyJpZCI6IjciLCJleHAiOjE2OTcxMTA3ODN9.1FG8oW4g8iMRb4lUfaWeXkG92tadG4vUGqrPp4twlNZhhQbdGQOgpz6wn29BtjOk9x5yhIWbMzPT-blV7k3FPA',
  },
});

// Request 🧑
instance.interceptors.request.use();

// Response 🧑
instance.interceptors.response.use();

export default instance;
