<script setup>
import { GoogleLogin } from "vue3-google-login";

const memberDetail = useMemberDetail();
const config = useRuntimeConfig();

const googleLoginHandler = async (googleUser) => {
  console.log(googleUser);
  //리소스 서버에 전송 후 memberDetail 발급받아야 함.

  //이후 memberDetail이용한 상태 유지 필요
};
</script>

<template>
  <div class="social-login">
    <div class="social-login__icons">
      <GoogleLogin @success="googleLoginHandler">
        <img src="/public/svg/google-circle.svg" alt="`구글 로그인`" />
      </GoogleLogin>
      <a href="#"
        ><div>
          <img src="/public/svg/naver-circle.svg" alt="`네이버 로그인`" /></div
      ></a>
      <a href="#"
        ><div>
          <img src="/public/svg/kakao-circle.svg" alt="`카카오 로그인`" /></div
      ></a>
    </div>
  </div>
</template>

<style scoped>
/*===============================================
    FRAGMENT: 소셜 로그인
===============================================*/
.social-login {
  width: 100%;
  display: flex;
  justify-content: center;
  padding: 30px 0 0 0;
}

.social-login__icons {
  display: flex;
  justify-content: center;
  gap: 48px;
}
</style>
