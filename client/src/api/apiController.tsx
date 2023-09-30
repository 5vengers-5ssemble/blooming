import axios from 'axios';

export const BASE_URL = 'http://localhost:8080/api/v1';
// export const BASE_URL = 'http://localhost:7700';

const instance = axios.create({
  baseURL: BASE_URL,
  headers: {
    'Content-Type': 'application/json',
    Authorization:
      'eyJhbGciOiJIUzUxMiJ9.eyJpZCI6IjciLCJleHAiOjE2OTYwMzk0NTl9.W8ug0UxnC4x_jYnCjwO_wRKL2K92z-HfsD_soDOpdsO6I7bAz8rSecQrNje78mdqXTJA97e6BixKWgKKdD-tGQ',
  },
});

// Request 🧑
instance.interceptors.request.use();

// Response 🧑
instance.interceptors.response.use();

export default instance;
