import axios from 'axios';

export const BASE_URL = 'http://localhost:8080/api/v1';
// export const BASE_URL = 'http://localhost:7700';

const instance = axios.create({
  baseURL: BASE_URL,
  headers: {
    'Content-Type': 'application/json',
    Authorization:
      'eyJhbGciOiJIUzUxMiJ9.eyJpZCI6IjciLCJleHAiOjE2OTU5ODA2Njl9.UHQzt9xbpykdDB-zLBTRDNWikDNMGJbmim0rtFrgyZg63fhPdb9ZwJlJxOLrnIBe_00510zuV1ocZr3aGX8CLg',
  },
});

// Request 🧑
instance.interceptors.request.use();

// Response 🧑
instance.interceptors.response.use();

export default instance;
