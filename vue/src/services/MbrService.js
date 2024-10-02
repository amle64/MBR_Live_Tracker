import axios from 'axios';

const http = axios.create({
  baseURL: import.meta.env.VITE_REMOTE_API
});

export default {
  uploadMbr(file,authToken) {
    const formData = new FormData();
    formData.append("file",file);

    return http.post('/mbrs/upload',formData, {
      headers: {
        Authorization: `Bearer ${authToken}`,
        'Content-Type': 'multipart/form-data'
      }
    });
  },

  
  
};
