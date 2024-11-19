<script setup>
const config = useRuntimeConfig();
const router = useRouter();
// ----------------------- Store ----------------------- //
const memberDetail = useMemberStore();
const navStore = useNavStore();
const backStore = useBackStore();
backStore.initStackAndCookie();

// ----------------------- Model ----------------------- //
const loginInfo = ref({
  email: "",
  isEmailValid: true,
  emailValidateMessage: "이메일을 입력해주세요.",

  password: "",
  isPasswordValid: true,
  passwordValidateMessage: "비밀번호를 입력해주세요.",
});
// ----------------------- API ----------------------- //

const loginApi = async () => {
  // API
  try {
    // { "email": "gksxorb147@naver.com", "password": "mypassword" } < 테스트 데이터
    const response = await $fetch(`${config.public.apiBase}/members/login`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        email: loginInfo.value.email,
        password: loginInfo.value.password,
      }),
    });

    // console.log(response);
    memberDetail.setAuthWithToken(response.accessToken);
    //현재 URL에서 returnUrl 값을 가져오기
    // console.log('리턴 URL 확인', router.currentRoute.value.query.retrunUrl);
    const returnUrl = router.currentRoute.value.query.retrunUrl || "/home";
    // console.log("로그인 성공");
    // 로그인 성공시 returnUrl로 이동
    router.push(returnUrl);
  } catch (error) {
    alert("다시 로그인 해주세요.");
  }
};

// ----------------------- Method ----------------------- //
const inputEmail = (email) => {
  loginInfo.value.email = email.trim();
};

const inputPassword = (password) => {
  loginInfo.value.password = password.trim();
};

const submitHandler = () => {
  // 이메일 형식 확인
  const emailRegExp = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
  if (!emailRegExp.test(loginInfo.value.email)) {
    loginInfo.value.isEmailValid = false;
    loginInfo.value.emailValidateMessage = "이메일 형식이 올바르지 않습니다.";
  } else {
    loginInfo.value.isEmailValid = true;
  }

  // 비밀번호 입력 확인
  if (loginInfo.value.password.length === 0) {
    loginInfo.value.isPasswordValid = false;
    loginInfo.value.passwordValidateMessage = "비밀번호를 입력해주세요.";
  } else {
    loginInfo.value.isPasswordValid = true;
  }

  if (!(loginInfo.value.isEmailValid && loginInfo.value.isPasswordValid)) {
    return; // 유효성 검사 실패
  }

  loginApi();
};
</script>

<template>
  <div class="login-member__join-page-container">
    <ButtonLoginJoinPageBackBtn :data="{ link: '/' }" />
    <TextLoginPageInfo
      :data="{
        label: '로그인',
        infoText: '이메일로 로그인을 진행합니다.',
      }"
    />
    <form @submit="submitHandler">
      <InputTextBar
        :data="{
          label: '이메일',
          name: 'email',
          placeholder: '이메일을 입력해주세요.',
          inputType: 'email',
          isValid: loginInfo.isEmailValid,
          validateMessage: loginInfo.emailValidateMessage,
        }"
        @inputData="inputEmail"
      />
      <InputTextBar
        :data="{
          label: '비밀번호',
          name: 'password',
          placeholder: '비밀번호를 입력해주세요.',
          inputType: 'password',
          isValid: loginInfo.isPasswordValid,
          validateMessage: loginInfo.passwordValidateMessage,
        }"
        @inputData="inputPassword"
      />
      <ButtonSubmitBtnFullBar text="로그인" @submit="submitHandler" />
    </form>
    <LinkSocialLogin />
    <TextJoinGuide
      :data="{
        infoText: '계정이 없으신가요?',
        label: '회원가입',
        href: '/member/join/email',
        isFind: true,
      }"
    />
  </div>
</template>

<style scoped>
.login-member__join-page-container {
  width: auto;
  padding: 50px 24px 0 24px;
}
</style>
