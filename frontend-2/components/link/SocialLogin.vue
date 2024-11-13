<script setup>
import {decodeCredential, googleTokenLogin} from "vue3-google-login";
import {useMemberJoinTmp} from "~/composables/useMemberJoinTmp.js";

const memberDetail = useMemberDetail();
const config = useRuntimeConfig();
const joinInfo = useMemberJoinTmp();
const router = useRouter();

const googleLoginHandler = async () => {
  try {

    const response = await googleTokenLogin();
    const token = response.access_token;

    const authInfo = await $fetch(`${config.public.apiBase}/auth/authentication` , {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
        'Accept': 'application/json'
      },
      body: JSON.stringify({token})
    });
    if (!authInfo.accessToken) { // 엑세스 토큰이 없는 경우
      joinInfo.setJoinInfo(authInfo);
      await router.push("/member/oauth/new");
      return;
    }

    // 회원인 경우
    memberDetail.setAuthWithToken(authInfo.accessToken);
    memberDetail.setAuth(authInfo);
    await router.push('/home');

  } catch (error) {
    console.error('Google 로그인 중 오류 발생:', error);
  }
};

</script>

<template>
  <div class="social-login">
    <div class="social-login__icons">
      <a @click.prevent="googleLoginHandler">
        <img src="/public/svg/google-circle.svg" alt="`구글 로그인`"/>
      </a>
      <a href="#"
      >
        <div>
          <img src="/public/svg/naver-circle.svg" alt="`네이버 로그인`"/></div
        >
      </a>
      <a href="#"
      >
        <div>
          <img src="/public/svg/kakao-circle.svg" alt="`카카오 로그인`"/></div
        >
      </a>
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
