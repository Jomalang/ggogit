<script setup>
import { HttpStatusCode } from "axios";

const config = useRuntimeConfig();

// ----------------------- Model ----------------------- //
const joinEmail = ref({
  email: "",
  isEmailValid: true,
  emailValidateMessage: "이메일을 입력해주세요.",
  disabled: false,
});

// ----------------------- API ----------------------- //
const sendEmailApi = async () => {
  alert("이메일 전송 성공");
  joinEmail.value.disabled = true;

  // API
  try {
    const response = await $fetch(
      `${config.public.apiBase}/members/join/send-email`,
      {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          email: joinEmail.value.email,
        }),
      }
    );

    if (!response.success) {
      console.error("이메일 전송 실패");
      return;
    }
  } catch (error) {
    // console.log(error);
  }
};

// ----------------------- Function ----------------------- //
const inputEmail = (email) => {
  joinEmail.value.email = email.trim();
};

const sendEmailHandler = () => {
  // 이메일 형식 확인
  const emailRegExp = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
  if (!emailRegExp.test(joinEmail.value.email)) {
    joinEmail.value.isEmailValid = false;
    joinEmail.value.emailValidateMessage = "이메일 형식이 올바르지 않습니다.";
    return;
  } else {
    joinEmail.value.isEmailValid = true;
  }

  // console.log("이메일 전송");
  sendEmailApi();
};
</script>

<template>
  <div class="login-member__join-page-container">
    <ButtonLoginJoinPageBackBtn :data="{ link: '/member/login' }" />
    <TextLoginPageInfo
      :data="{
        label: '회원가입',
        infoText: '이메일로 회원가입을 진행합니다',
      }"
    />
    <form @submit.prevent="sendEmailHandler">
      <InputTextBar
        :data="{
          label: '이메일',
          name: 'email',
          placeholder: '이메일을 입력해주세요.',
          inputType: 'email',
          disabled: joinEmail.disabled,
          isValid: joinEmail.isEmailValid,
          validateMessage: joinEmail.emailValidateMessage,
        }"
        @inputData="inputEmail"
      />
      <ButtonSubmitBtnFullBar
        text="회원가입"
        :is-submit="joinEmail.disabled"
        @submit="sendEmailHandler"
      />
    </form>
    <LinkSocialLogin />
    <TextJoinGuide
      :data="{
        infoText: '이미 계정이 있으신가요?',
        label: '로그인',
        href: '/member/login',
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