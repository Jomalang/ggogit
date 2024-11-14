<script setup>
import {decodeCredential, googleTokenLogin} from "vue3-google-login";
import {useMemberJoinTmp} from "~/composables/useMemberJoinTmp.js";


const memberDetail = useMemberDetail();
const config = useRuntimeConfig();
const joinInfo = useMemberJoinTmp();
const router = useRouter();

const  naverIdLogin = ref(null);



// ----------------------- Oauth ----------------------- //
// 네이버 로그인 스크립트를 동적으로 로드하는 함수
const loadNaverScript = () => {
  return new Promise((resolve, reject) => {
    if (document.querySelector('script[src="https://static.nid.naver.com/js/naverLogin_implicit-1.0.3.js"]')) {
      resolve();
    } else {
      const script = document.createElement('script');
      script.src = 'https://static.nid.naver.com/js/naverLogin_implicit-1.0.3.js';
      script.charset = 'utf-8';
      script.defer = true;

      script.onload = () => {
        resolve();
      };

      document.head.appendChild(script);
    }
  });
};

// 네이버 로그인 초기화 함수
const naverInitHandler = () => {
  if (typeof naver_id_login === 'undefined') {
    console.error('네이버 로그인 SDK가 아직 로드되지 않았습니다.');
    return;
  }
  const callbackUrl = 'http://localhost:3000/member/login';
  const naverLogin = new naver_id_login(`${config.public.naverClientId}`, callbackUrl);
  // 상태 토큰 생성
  const state = naverLogin.getUniqState();
  naverLogin.setState(state);
  // 팝업 방식 설정
  naverLogin.setPopup();
  // 버튼 설정 (색상, 타입, 크기)
  naverLogin.setButton("white", 1, 50);
  // 네이버 로그인 초기화
  naverLogin.init_naver_id_login();
};


// 네이버 로그인 버튼 클릭 핸들러
const naverClickHandler = () => {
  const runNaverOauth = naverIdLogin.value?.firstChild;
  if (runNaverOauth) {
    runNaverOauth.click();
  } else {
    console.error("네이버 로그인 버튼을 찾을 수 없습니다.");
  }
};
//팝업창 닫기 및 access 토큰 부모에게 전달하기
const serving = async () => {
// 해시 부분에서 access_token 추출
  const hashParams = new URLSearchParams(window.location.hash.substring(1)); // # 제거 후 파싱
  const token = hashParams.get('access_token');
  const state = hashParams.get('state');
  console.log(token); // 추출된 토큰 값 확인
  console.log(state); // 추출된 상태 값 확인

// 부모 창에 토큰 전달
  if (token) {
    window.opener.postMessage({ token, state }, '*');
    window.close(); // 팝업 창 닫기
  } else {
    console.error('토큰이 존재하지 않습니다.');
  }
};
//네이버 1회용 토큰 BE로 전달
const naverTokenHandler = async (code, state) => {
  const token = code;
  const authInfo = await $fetch(`${config.public.apiBase}/auth/oauthNaver`, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
      'Accept': 'application/json'
    },
    body: JSON.stringify({token, state})
  });
  console.log('authInfo:', authInfo);
}

  const googleLoginHandler = async () => {
    try {

      const response = await googleTokenLogin();
      const token = response.access_token;

      const authInfo = await $fetch(`${config.public.apiBase}/auth/oauthGoogle`, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
          'Accept': 'application/json'
        },
        body: JSON.stringify({token})
      });
      console.log('authInfo:', authInfo);
      if (authInfo.accessToken === undefined) { // 엑세스 토큰이 없는 경우
        joinInfo.setJoinInfo(authInfo);
        await router.push("/member/oauth/new");
        return;
      }
      // 회원인 경우
      memberDetail.setAuthWithToken(authInfo.accessToken);
      memberDetail.setLocal(authInfo.id, authInfo.name, authInfo.email, authInfo.picture, authInfo.role, authInfo.accessToken);
      await router.push('/home');

    } catch (error) {
      console.error('Google 로그인 중 오류 발생:', error);
    }
  };


//-----------------lifecycle-----------------//

  onMounted(async () => {
    await loadNaverScript();
    naverInitHandler();
    // 부모 창에서 메시지 수신 처리
    window.addEventListener('message', (event) => {
      if (event.origin === 'http://localhost:3000') { // 콜백 URL의 도메인 확인
        const token = event.data.token;
        const state = event.data.state;
        naverTokenHandler(token, state); //토큰 전달
      }
    });
    //팝업창 닫기 및 access 토큰 부모에게 전달하기
    if (window.opener) {
     await serving();
    }
  });

</script>

<template>
  <div class="social-login">
    <div class="social-login__icons" id="naver-oauth">
      <a @click.prevent="googleLoginHandler">
        <img src="/public/svg/google-circle.svg" alt="`구글 로그인`"/>
      </a>

      <div @click.prevent="naverClickHandler">
        <div id="naver_id_login" ref=naverIdLogin></div>
          <img class="naverIcon" src="/public/svg/naver-circle.svg" alt="`네이버 로그인`"/>
      </div>

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

#naver_id_login{
  display: none;
}

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
