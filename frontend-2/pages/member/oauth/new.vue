<script setup>

import {useMemberJoinTmp} from "~/composables/useMemberJoinTmp.js";

const config = useRuntimeConfig();
const route = useRoute();
const key = ref(route.query.key);

// ----------------------- Method ----------------------- //
const oauthInfo = ref({
  name: '',
  email: '',
  picture: ''
});

oauthInfo.value = useMemberJoinTmp().getJoinInfo();

const joinInfo = ref({

  name: oauthInfo.value.name,
  isNameValid: true,
  nameValidateMessage: '이름을 입력해주세요.',

  email: oauthInfo.value.email,
  isEmailValid: true,
  emailValidateMessage: '이메일을 입력해주세요.',

  password: '',
  isPasswordValid: true,
  passwordValidateMessage: '비밀번호를 입력해주세요 (8~32자, 대소문자 포함, 숫자, 특스문자 1개이상 포함)',

  passwordCheck: '',
  isPasswordCheckValid: true,
  passwordCheckValidateMessage: '동일한 비밀번호를 입력해주세요.',

  nickname: '',
  isNicknameValid: true,
  nicknameValidateMessage: '닉네임을 입력해주세요.',

  introduction: '',
  isIntroductionValid: true,
  introductionValidateMessage: '한 줄 소개를 입력해주세요.',

  isPolicyAgreement: false
});


// ----------------------- API ----------------------- //
const { data: emailInfo, error: emailInfoError } = await useFetch(
    `members/join/check-email`,
    {
      method: "POST",
      baseURL: `${config.public.apiBase}`,
      headers: { "Content-Type": "application/json" },
      body: { key: key.value }
    },
);

const joinPostApi = async () => {
  try {
    const response = await $fetch(`members/oauth/join`,
        {
          method: "POST",
          baseURL: `${config.public.apiBase}`,
          headers: { "Content-Type": "application/json" },
          body: {
            name: joinInfo.value.name,
            email: joinInfo.value.email,
            password: joinInfo.value.password,
            nickname: joinInfo.value.nickname,
            introduction: joinInfo.value.introduction,
            picture: oauthInfo.value.picture
          }
        },
    );

    if (response.status !== 201) {
      alert('회원가입에 실패했습니다.');
      return;
    }

    useMemberDetail().setAuthWithToken(response.accessToken);

    alert('회원가입이 완료되었습니다.');
  } catch (error) {
    alert(error.data.message);
  }
}

watchEffect(() => {
  if (emailInfo.value) {
    joinInfo.value.email = emailInfo.value.email;
  }
});


// ----------------------- Method ----------------------- //

const inputName = (name) => {
  joinInfo.value.name = name;
}

const inputEmail = (email) => {
  joinInfo.value.email = email;
}

const inputPassword = (password) => {
  joinInfo.value.password = password;

  // 8~32자
  if (!(8 <= joinInfo.value.password.length && joinInfo.value.password.length <= 32)) {
    joinInfo.value.isPasswordValid = false;
    joinInfo.value.passwordValidateMessage = '비밀번호는 8~32자로 입력해주세요.';
    return;
  }
  joinInfo.value.passwordValidateMessage = '';

  // 대소문자 포함, 숫자
  if (!/^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)/.test(joinInfo.value.password)) {
    joinInfo.value.isPasswordValid = false;
    joinInfo.value.passwordValidateMessage = '비밀번호는 대소문자, 숫자를 포함해주세요.';
    return;
  }
  joinInfo.value.passwordValidateMessage = '';

  // 특스문자 1개이상 포함
  if (!/^(?=.*[!@#$%^&*])/.test(joinInfo.value.password)) {
    joinInfo.value.isPasswordValid = false;
    joinInfo.value.passwordValidateMessage = '비밀번호는 특수문자를 포함해주세요.';
    return;
  }
  joinInfo.value.passwordValidateMessage = '';
}

const inputPasswordCheck = (passwordCheck) => {
  joinInfo.value.passwordCheck = passwordCheck;
  if (joinInfo.value.password !== joinInfo.value.passwordCheck) {
    joinInfo.value.isPasswordCheckValid = false;
    joinInfo.value.passwordCheckValidateMessage = '비밀번호가 일치하지 않습니다.';
  } else
    joinInfo.value.isPasswordCheckValid = true;
}

const inputNickname = (nickname) => {
  joinInfo.value.nickname = nickname;
}

const inputIntroduction = (introduction) => {
  joinInfo.value.introduction = introduction;
}

const inputPolicyAgreement = (isPolicyAgreement) => {
  joinInfo.value.isPolicyAgreement = isPolicyAgreement;
}

const submitJoin = () => {

  if (joinInfo.value.name === '') {
    joinInfo.value.isNameValid = false;
    joinInfo.value.nameValidateMessage = '이름을 입력해주세요.';
  } else {
    joinInfo.value.isNameValid = true;
  }

  const emailRegExp = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
  if (!emailRegExp.test(joinInfo.value.email)) {
    joinInfo.value.isEmailValid = false;
    joinInfo.value.emailValidateMessage = '이메일 형식이 올바르지 않습니다.';
  } else {
    joinInfo.value.isEmailValid = true;
  }

  if (joinInfo.value.password === '') {
    joinInfo.value.isPasswordValid = false;
    joinInfo.value.passwordValidateMessage = '비밀번호를 입력해주세요.';
  } else {

    // 8~32자
    if (!(8 <= joinInfo.value.password.length && joinInfo.value.password.length <= 32)) {
      joinInfo.value.isPasswordValid = false;
      joinInfo.value.passwordValidateMessage = '비밀번호는 8~32자로 입력해주세요.';
    }

    // 대소문자 포함, 숫자
    if (!/^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)/.test(joinInfo.value.password)) {
      joinInfo.value.isPasswordValid = false;
      joinInfo.value.passwordValidateMessage = '비밀번호는 대소문자, 숫자를 포함해주세요.';
    }

    // 특스문자 1개이상 포함
    if (!/^(?=.*[!@#$%^&*])/.test(joinInfo.value.password)) {
      joinInfo.value.isPasswordValid = false;
      joinInfo.value.passwordValidateMessage = '비밀번호는 특수문자를 포함해주세요.';
    }

    joinInfo.value.isPasswordValid = true;
  }

  if (joinInfo.value.passwordCheck === '') {
    joinInfo.value.isPasswordCheckValid = false;
    joinInfo.value.passwordCheckValidateMessage = '비밀번호 확인을 입력해주세요.';
  } else {
    if (joinInfo.value.password !== joinInfo.value.passwordCheck) {
      joinInfo.value.isPasswordCheckValid = false;
      joinInfo.value.passwordCheckValidateMessage = '비밀번호가 일치하지 않습니다.';
    } else
    joinInfo.value.isPasswordCheckValid = true;
  }

  if (joinInfo.value.nickname === '') {
    joinInfo.value.isNicknameValid = false;
    joinInfo.value.nicknameValidateMessage = '닉네임을 입력해주세요.';
  } else {
    joinInfo.value.isNicknameValid = true;
  }

  if (joinInfo.value.introduction === '') {
    joinInfo.value.isIntroductionValid = false;
    joinInfo.value.introductionValidateMessage = '한 줄 소개를 입력해주세요.';
  } else {
    joinInfo.value.isIntroductionValid = true;
  }

  if (!joinInfo.value.isPolicyAgreement) {
    alert('이용약관에 동의해주세요.');
  }

  if (!joinInfo.value.isNameValid ||
      !joinInfo.value.isEmailValid ||
      !joinInfo.value.isPasswordValid ||
      !joinInfo.value.isPasswordCheckValid ||
      !joinInfo.value.isNicknameValid ||
      !joinInfo.value.isIntroductionValid) {
    alert('입력 정보를 확인해주세요.');
    return;
  }

  // 회원가입 API 호출
  joinPostApi();
  router.push('/member/login');
}

</script>

<template>
  <div class="login-member__join-page-container">
    <TextJoinPageInfo label="회원가입" info-text="GGoggit 기본 정보를 작성해주세요." />
    <form @submit="submitJoin">
      <div class="input-text__bar">
        <label class="input-text__label">
          <span class="input-text__label-text">* 이름</span>
          <input class="input-text__input" name="name" disabled :value="joinInfo.name" />
        </label>
        <div class="wrong-text-box"></div>
      </div>
      <div class="input-text__bar">
        <label class="input-text__label">
          <span class="input-text__label-text">* 이메일</span>
          <input class="input-text__input" name="email" disabled :value="joinInfo.email" />
        </label>
        <div class="wrong-text-box"></div>
      </div>
      <InputTextBar
          :data="{
          label: '*닉네임',
          name: 'nickname',
          placeholder: '닉네임을 입력해주세요',
          inputType: 'text',
          isValid: joinInfo.isNicknameValid,
          validateMessage: joinInfo.nicknameValidateMessage
        }"
          @inputData="inputNickname"
      />
      <InputTextBar
          :data="{
          label: '*한 줄 소개',
          name: 'introduction',
          placeholder: '한 줄 소개를 입력해주세요',
          inputType: 'text',
          isValid: joinInfo.isIntroductionValid,
          validateMessage: joinInfo.introductionValidateMessage
        }"
          @inputData="inputIntroduction"
      />
      <div class="none">
        <label>
          <input type="text" name="picture" :value="oauthInfo.picture" />
        </label>
      </div>
      <InputCheckboxJoinPolicyAgreement
          @checkEvent="inputPolicyAgreement"
          text="이용약관 동의"/>
      <ButtonSubmitBtnFullBar text="회원가입" @submit="submitJoin" />
    </form>
  </div>
</template>

<style scoped>
.login-member__join-page-container {
  width: auto;
  padding: 50px 24px 0 24px;
}
</style>

