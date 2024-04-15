import { fileURLToPath, URL } from 'node:url';
import { defineConfig } from 'vite';
import vue from '@vitejs/plugin-vue';
import fs from 'fs';

export default defineConfig({
  plugins: [vue()],
  resolve: {
    alias: {
      '@': fileURLToPath(new URL('./src', import.meta.url)),
    },
  },
  server: {
    proxy: {
      '/api': {
        target: 'https://localhost:8888',
        changeOrigin: true,
        rewrite: path => path.replace(/^\/api/, ''),
      },
    },
    // https: {
    //   key: fs.readFileSync('/Users/duck/Desktop/Keys/localhost-key.pem'),
    //   cert: fs.readFileSync('/Users/duck/Desktop/Keys/localhost.pem')
    // }
  }
});
