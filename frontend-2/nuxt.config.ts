// https://nuxt.com/docs/api/configuration/nuxt-config

export default defineNuxtConfig({
  runtimeConfig: {
    public: {
      apiBase: "http://192.168.0.69:8080/api/v1",
      naverClientId: "Y82lIjIMLGmIfTM3LzMr",
      kakaoClientId: "abafc6aa7f933101c7a24f0d63557024",
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
      leaf: {
        ssr: false, // 화면에 따른 window 객체의 값으로 인해 SSR을 비활성화
      },
      "leaf/book/new": {
        ssr: false, // "/editor/toast" 경로에 대해서는 SSR을 비활성화
      },
      "leaf/book/:id": {
        ssr: false, // "/editor/toast" 경로에 대해서는 SSR을 비활성화
      },
      "leaf/book/:id/edit": {
        ssr: false, // "/editor/toast" 경로에 대해서는 SSR을 비활성화
      },
      "leaf/book/:id/new": {
        ssr: false, // "/editor/toast" 경로에 대해서는 SSR을 비활성화
      },
      "leaf/etc/new": {
        ssr: false, // "/editor/toast" 경로에 대해서는 SSR을 비활성화
      },
      "leaf/etc/:id": {
        ssr: false, // "/editor/toast" 경로에 대해서는 SSR을 비활성화
      },
      "leaf/etc/:id/edit": {
        ssr: false, // "/editor/toast" 경로에 대해서는 SSR을 비활성화
      },
      "leaf/etc/:id/new": {
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
      "member/login": {
        ssr: false,
      },
      "member/email-send": {
        ssr: false,
      },
    },
  },
  modules: ["@pinia/nuxt"],
  router: {
    middleware: ["checkTreeFormData"],
  },
});
