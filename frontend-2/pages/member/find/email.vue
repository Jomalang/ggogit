<script setup>
const config = useRuntimeConfig();

// ----------------------- Model ----------------------- //
const findUserInfo = ref({
  username: "",
  isUsernameValid: true,
  usernameValidateMessage: "이름을 입력해주세요.",

  email: "",
  isEmailValid: true,
  emailValidateMessage: "이메일을 입력해주세요.",

  disabled: false,
});

// ----------------------- API ----------------------- //
const sendEmailApi = async () => {
  findUserInfo.value.disabled = true;
  // API
  try {
    const response = await useAuthDataFetch(
      `${config.public.apiBase}/members/find/send-email`,
      {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          username: findUserInfo.value.username,
          email: findUserInfo.value.email,
        }),
      }
    );

    if (!response.success) {
      console.error("이메일 전송 실패");
      findUserInfo.value.disabled = false;
      return;
    }
  } catch (error) {
    findUserInfo.value.disabled = false;
    // console.log(error);
  }
};

// ----------------------- Function ----------------------- //
const inputEmail = (email) => {
  findUserInfo.value.email = email.trim();
};

const inputUsername = (username) => {
  findUserInfo.value.username = username.trim();
};

const sendEmailHandler = () => {
  // 이름 확인
  if (findUserInfo.value.username === "") {
    findUserInfo.value.isUsernameValid = false;
    findUserInfo.value.usernameValidateMessage = "이름을 입력해주세요.";
    return;
  } else {
    findUserInfo.value.isUsernameValid = true;
  }

  // 이메일 형식 확인
  const emailRegExp = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
  if (!emailRegExp.test(findUserInfo.value.email)) {
    findUserInfo.value.isEmailValid = false;
    findUserInfo.value.emailValidateMessage =
      "이메일 형식이 올바르지 않습니다.";
    return;
  } else {
    findUserInfo.value.isEmailValid = true;
  }

  // console.log("이메일 전송");
  sendEmailApi();
};
</script>

<template>
  <div class="login-member__join-page-container">
    <ButtonLoginJoinPageBackBtn/>
    <TextLoginPageInfo
      :data="{
        label: '계정 찾기',
        infoText: '비밀번호 재설정 링크를 보내드립니다.',
      }"
    />
    <form @submit.prevent="sendEmailHandler">
      <InputTextBar
        :data="{
          label: '이름',
          name: 'username',
          placeholder: '이름을 입력해주세요.',
          inputType: 'email',
          disabled: findUserInfo.disabled,
          isValid: findUserInfo.isUsernameValid,
          validateMessage: findUserInfo.usernameValidateMessage,
        }"
        @inputData="inputUsername"
      />
      <InputTextBar
        :data="{
          label: '이메일',
          name: 'email',
          placeholder: '이메일을 입력해주세요.',
          inputType: 'email',
          disabled: findUserInfo.disabled,
          isValid: findUserInfo.isEmailValid,
          validateMessage: findUserInfo.emailValidateMessage,
        }"
        @inputData="inputEmail"
      />
      <ButtonSubmitBtnFullBar
        text="이메일 전송"
        :is-submit="findUserInfo.disabled"
        @submit="sendEmailHandler"
      />
    </form>
    <TextJoinGuide
      :data="{
        infoText: '이미 계정이 있으신가요?',
        label: '로그인',
        href: '/member/login',
        isFind: false,
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
