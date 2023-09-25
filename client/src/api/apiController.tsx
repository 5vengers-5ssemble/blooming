import axios from 'axios';

// export const BASE_URL = 'http://localhost:8080';
export const BASE_URL = 'http://localhost:7700';

const instance = axios.create({
  baseURL: BASE_URL,
  headers: {
    'Content-Type': 'application/json',
  },
});

// Request 🧑
instance.interceptors.request.use();

// Response 🧑
instance.interceptors.response.use();

export default instance;
