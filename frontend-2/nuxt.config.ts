// https://nuxt.com/docs/api/configuration/nuxt-config
export default defineNuxtConfig({
  runtimeConfig: {
    public: {
      apiBase: "http://localhost:8080/api/v1",
    },
  },
  //모바일 접속
  devServer: {
    host: "0.0.0.0",
    port: 3000,
  },
  compatibilityDate: "2024-04-03",
  devtools: { enabled: true },
  nitro: {
    routeRules: {
      // toast-ui editor 가 SSR 을 지원하지 않아 reload시 에러가 나는것을 방지
      "book/category": {
        ssr: false, // "/editor/toast" 경로에 대해서는 SSR을 비활성화
      },
      "tree/book/new": {
        ssr: false, // "/editor/toast" 경로에 대해서는 SSR을 비활성화
      },
      "leaf/book/new": {
        ssr: false, // "/editor/toast" 경로에 대해서는 SSR을 비활성화
      },
      "memoir/:id/edit": {
        ssr: false, // "/editor/toast" 경로에 대해서는 SSR을 비활성화
      },
      "memoir/:id/reg": {
        ssr: false, // "/editor/toast" 경로에 대해서는 SSR을 비활성화
      },
      "memoir/:id": {
        ssr: false, // "/editor/toast" 경로에 대해서는 SSR을 비활성화
      },
      "leaf/:id": {
        ssr: false, // "/editor/toast" 경로에 대해서는 SSR을 비활성화
      },
    },
  },
});
