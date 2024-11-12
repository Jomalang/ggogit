<script setup>
import {decodeCredential} from "vue3-google-login";

const memberDetail = useMemberDetail();
const config = useRuntimeConfig();

const googleLoginHandler = async (response) => {
  try {
    const googleUser = decodeCredential(response.credential); // JWT 토큰 디코딩
    console.log('Google User:', googleUser);

    // 리소스 서버에 전송 후 memberDetail 발급받기 (예: API 호출)
    // 예시: await axios.post('/api/login', { token: response.credential });

    // 이후 memberDetail을 이용한 상태 유지 필요
    memberDetail.value = googleUser; // 예시로 사용자 정보를 저장
  } catch (error) {
    console.error('Google 로그인 중 오류 발생:', error);
  }
};

</script>

<template>
  <div class="social-login">
    <div class="social-login__icons">
      <GoogleLogin :callback="googleLoginHandler">
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
