import axios from 'axios';

export const BASE_URL = import.meta.env.VITE_APP_SERVER;

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
