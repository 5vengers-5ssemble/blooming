import react from '@vitejs/plugin-react-swc';
import { defineConfig } from 'vite';
import svgr from 'vite-plugin-svgr';
import tsconfigPaths from 'vite-tsconfig-paths';

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [react(), svgr(), tsconfigPaths()],
  optimizeDeps: {
    exclude: ['js-big-decimal'],
  },
  define: { _global: {} },
  resolve: {
    // https://github.com/aws-amplify/amplify-js/issues/9639
    alias: {
      './runtimeConfig': './runtimeConfig.browser',
    },
  },
  build: {
    sourcemap: true,
    commonjsOptions: {
      include: [/node_modules/],
      extensions: ['.js', '.cjs'],
      strictRequires: true,
      transformMixedEsModules: true,
    },
  },
});
