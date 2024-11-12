import { defineNuxtPlugin } from "#app";
import vue3GoogleLogin from "vue3-google-login";

export default defineNuxtPlugin((nuxtApp) => {
  nuxtApp.vueApp.use(vue3GoogleLogin, {
    clientId:
      "56165295668-cmnhabamv9b6bpsbcc0mbtrpqhiqtu36.apps.googleusercontent.com",
  });
});
