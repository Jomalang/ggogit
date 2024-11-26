<script setup>
import { decodeCredential, googleTokenLogin } from "vue3-google-login";
import { useMemberJoinTmp } from "~/composables/useMemberJoinTmp.js";
import * as url from "node:url";

const memberDetail = useMemberStore();
const config = useRuntimeConfig();
const joinInfo = useMemberJoinTmp();
const router = useRouter();
const redirectUri = encodeURIComponent("http://ggogit.taecobug.io:3006/member/login");

const state = ref(null);

// ----------------------- Oauth ----------------------- //
function generateRandomString() {
  const randomString = Math.random().toString(36).substr(2, 11);
  return randomString;
}

function openNaverLoginPopup() {
  const clientId = `${config.public.naverClientId}`;
  state.value = generateRandomString(); // CSRF 방지용 상태 값
  const naverLoginUrl = `https://nid.naver.com/oauth2.0/authorize?response_type=code&client_id=${clientId}&redirect_uri=${redirectUri}&state=${state.value}`;

  // 팝업 창 열기
  window.open(naverLoginUrl, "naverLoginPopup", "width=500,height=600");
}
function openKakaoLoginPopup() {
  const clientId = `${config.public.kakaoClientId}`;
  state.value = generateRandomString(); // CSRF 방지용 상태 값
  const kakaoLoginUrl = `https://kauth.kakao.com/oauth/authorize?response_type=code&client_id=${clientId}&redirect_uri=${redirectUri}&state=${state.value}`;

  // 팝업 창 열기
  window.open(kakaoLoginUrl, "kakaoLoginPopup", "width=500,height=600");
}

const naverLoginHandler = async (code) => {
  try {
    const authInfo = await $fetch(`${config.public.apiBase}/auth/oauthNaver`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        Accept: "application/json",
      },
      body: JSON.stringify({ code, state: state.value }),
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

const kakoLoginHandler = async (code) => {
  try {
    const authInfo = await $fetch(`${config.public.apiBase}/auth/oauthKakao`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        Accept: "application/json",
      },
      body: JSON.stringify({ code, state: state.value }),
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
    console.error("Kako 로그인 중 오류 발생:", error);
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
watchEffect(() => {
  //현재 창이 팝업창인지 확인
  if (window.opener) {
    const urlObj = new URL(window.location.href);
    const code = urlObj.searchParams.get("code");
    const receivedState = urlObj.searchParams.get("state");
    // 부모창에 code와 state 전달
    window.opener.postMessage({ code, receivedState }, "*");
    // 데이터 전달 후 팝업 닫기
    window.close();
  }
  // 팝업창이 아닌 경우
  window.addEventListener('message', (event) => {
    if(event.data.receivedState !== state.value) return;
    if(event.data.code) {
      if (event.source.name === 'naverLoginPopup') {
        naverLoginHandler(event.data.code);
      } else if (event.source.name === "kakaoLoginPopup") {
        kakoLoginHandler(event.data.code);
      }
    }
  });
});
</script>

<template>
  <div class="social-login">
    <div class="social-login__icons">
      <a @click.prevent="googleLoginHandler">
        <img src="~/assets/png/google-circle.png" alt="`구글 로그인`" />
      </a>

      <div class="social-login__icons">
        <a @click.prevent="openNaverLoginPopup">
          <img
            class="naverIcon"
            src="~/assets/png/naver-circle.png"
            alt="`네이버 로그인`"
          />
        </a>
      </div>
      <div class="social-login__icons">
        <a @click.prevent="openKakaoLoginPopup">
          <img src="~/assets/png/kakao-circle.png" alt="`카카오 로그인`" />
        </a>
      </div>
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
