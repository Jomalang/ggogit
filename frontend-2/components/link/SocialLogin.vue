<script setup>
import { decodeCredential, googleTokenLogin } from "vue3-google-login";
import { useMemberJoinTmp } from "~/composables/useMemberJoinTmp.js";

const memberDetail = useMemberStore();
const config = useRuntimeConfig();
const joinInfo = useMemberJoinTmp();
const router = useRouter();

const naverState = ref(null);

// ----------------------- Oauth ----------------------- //
function generateRandomString() {
  const randomString = Math.random().toString(36).substr(2, 11);
  return randomString;
}

function openNaverLoginPopup() {
  const clientId = `${config.public.naverClientId}`;
  const redirectUri = encodeURIComponent("http://localhost:3000/member/login");
  naverState.value = generateRandomString(); // CSRF 방지용 상태 값
  const naverLoginUrl = `https://nid.naver.com/oauth2.0/authorize?response_type=code&client_id=${clientId}&redirect_uri=${redirectUri}&state=${naverState.value}`;

  // 팝업 창 열기
  window.open(naverLoginUrl, "naverLoginPopup", "width=500,height=600");
}

const naverLoginHandler = async (code) => {
  try {
    const authInfo = await $fetch(`${config.public.apiBase}/auth/oauthNaver`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        Accept: "application/json",
      },
      body: JSON.stringify({ code, state: naverState.value }),
    });
    if (authInfo.accessToken === undefined) {
      // 엑세스 토큰이 없는 경우
      joinInfo.setJoinInfo(authInfo);
      await router.push("/member/oauth/new");
      return;
    }
    // 회원인 경우
    memberDetail.setAuthWithToken(authInfo.accessToken);
    memberDetail.setLocal(
      authInfo.id,
      authInfo.name,
      authInfo.email,
      authInfo.picture,
      authInfo.role,
      authInfo.accessToken
    );
    await router.push("/home");
  } catch (error) {
    console.error("Naver 로그인 중 오류 발생:", error);
  }
};

const googleLoginHandler = async () => {
  try {
    const response = await googleTokenLogin();
    const token = response.access_token;

    const authInfo = await $fetch(`${config.public.apiBase}/auth/oauthGoogle`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        Accept: "application/json",
      },
      body: JSON.stringify({ token }),
    });
    if (authInfo.accessToken === undefined) {
      // 엑세스 토큰이 없는 경우
      joinInfo.setJoinInfo(authInfo);
      await router.push("/member/oauth/new");
      return;
    }
    // 회원인 경우
    memberDetail.setAuthWithToken(authInfo.accessToken);
    memberDetail.setLocal(
      authInfo.id,
      authInfo.name,
      authInfo.email,
      authInfo.picture,
      authInfo.role,
      authInfo.accessToken
    );
    await router.push("/home");
  } catch (error) {
    console.error("Google 로그인 중 오류 발생:", error);
  }
};

//-----------------lifecycle-----------------//
// watchEffect로 URL의 변화를 감지
watchEffect(async () => {
  const urlParams = new URLSearchParams(window.location.search);
  const code = urlParams.get("code");
  const state = urlParams.get("state");

  if (code) {
    // 부모 창에 code와 state 전달
    window.opener.postMessage({ code: code, state: state }, "*");
    // 팝업 창 닫기
    window.close();
  }
  window.addEventListener("message", function (event) {
    if (event.origin !== "http://localhost:3000") return;
    if (event.data.code) {
      const code = event.data.code;
      const state = event.data.state;
      if (state === naverState.value) {
        naverLoginHandler(code);
      }
    }
  });
});
</script>

<template>
  <div class="social-login">
    <div class="social-login__icons">
      <a @click.prevent="googleLoginHandler">
        <img src="/public/svg/google-circle.svg" alt="`구글 로그인`" />
      </a>

      <div class="social-login__icons" @click.prevent="openNaverLoginPopup">
        <img
          class="naverIcon"
          src="/public/svg/naver-circle.svg"
          alt="`네이버 로그인`"
        />
      </div>

      <a href="#">
        <div>
          <img src="/public/svg/kakao-circle.svg" alt="`카카오 로그인`" />
        </div>
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
