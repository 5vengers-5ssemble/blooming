import axios from 'axios';

export const BASE_URL = 'http://localhost:8080/api/v1';
// export const BASE_URL = 'http://localhost:7700';

const instance = axios.create({
  baseURL: BASE_URL,
  headers: {
    'Content-Type': 'application/json',
    Authorization:
      'eyJhbGciOiJIUzUxMiJ9.eyJpZCI6IjciLCJleHAiOjE2OTU5NzMxMTB9.FPiSIUgKeXoeEuTWCyO1YlsxTqeJLEekq7MohhncdTx_Vso3s7LTCV_6VSFvJtufqeEZ6RiJm-8a-2BWBLgq4Q',
  },
});

// Request 🧑
instance.interceptors.request.use();

// Response 🧑
instance.interceptors.response.use();

export default instance;
