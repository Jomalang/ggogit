<script setup>
const config = useRuntimeConfig();
const router = useRouter();
const route = useRoute();
const key = ref(route.query.key);

console.log(key.value);

// ----------------------- Model ----------------------- //

const resetInfo = ref({
  email: "",

  password: "",
  isPasswordValid: true,
  passwordValidateMessage: "비밀번호를 입력해주세요.",

  passwordCheck: "",
  isPasswordCheckValid: true,
  passwordCheckValidateMessage: "비밀번호를 다시 입력해주세요.",
});

// ----------------------- Method ----------------------- //
const { data: emailInfo, error: emailInfoError } = await useAuthFetch(
  `members/password/check-email`,
  {
    method: "POST",
    baseURL: `${config.public.apiBase}`,
    headers: { "Content-Type": "application/json" },
    body: { key: key.value },
  }
);

const passwordResetApi = async () => {
  try {
    const response = await useAuthDataFetch(`members/password-reset`, {
      baseURL: `${config.public.apiBase}`,
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: {
        email: resetInfo.value.email,
        token: key.value,
        newPassword: resetInfo.value.password,
      },
    });

    if (!response) {
      alert("비밀번호 변경 오류");
    }

    console.log("비밀번호 수정 완료");
    router.push("/member/login");
  } catch (error) {}
};

watchEffect(() => {
  if (emailInfo.value) {
    resetInfo.value.email = emailInfo.value.email;
    console.log("");
  }
});

// ----------------------- Method ----------------------- //

const submitPasswordReset = () => {
  if (resetInfo.value.password === "") {
    resetInfo.value.isPasswordValid = false;
    resetInfo.value.passwordValidateMessage = "비밀번호를 입력해주세요.";
    return;
  }

  // 8~32자
  if (
    !(
      8 <= resetInfo.value.password.length &&
      resetInfo.value.password.length <= 32
    )
  ) {
    resetInfo.value.isPasswordValid = false;
    resetInfo.value.passwordValidateMessage =
      "비밀번호는 8~32자로 입력해주세요.";
    return;
  }

  // 대소문자 포함, 숫자
  if (!/^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)/.test(resetInfo.value.password)) {
    resetInfo.value.isPasswordValid = false;
    resetInfo.value.passwordValidateMessage =
      "비밀번호는 대소문자, 숫자를 포함해주세요.";
    return;
  }

  // 특스문자 1개이상 포함
  if (!/^(?=.*[!@#$%^&*])/.test(resetInfo.value.password)) {
    resetInfo.value.isPasswordValid = false;
    resetInfo.value.passwordValidateMessage =
      "비밀번호는 특수문자를 포함해주세요.";
    return;
  }

  resetInfo.value.isPasswordValid = true;

  if (resetInfo.value.passwordCheck === "") {
    resetInfo.value.isPasswordCheckValid = false;
    resetInfo.value.passwordCheckValidateMessage =
      "비밀번호 확인을 입력해주세요.";
    return;
  }

  if (resetInfo.value.password !== resetInfo.value.passwordCheck) {
    resetInfo.value.isPasswordCheckValid = false;
    resetInfo.value.passwordCheckValidateMessage =
      "비밀번호가 일치하지 않습니다.";
    return;
  } else {
    resetInfo.value.isPasswordCheckValid = true;
  }

  // 회원가입 API 호출
  passwordResetApi();
  // router.push('/member/login');
};

const inputPassword = (password) => {
  resetInfo.value.password = password.trim();

  if (resetInfo.value.password === "") {
    resetInfo.value.isPasswordValid = false;
    resetInfo.value.passwordValidateMessage = "비밀번호를 입력해주세요.";
    return;
  }

  // 8~32자
  if (
    !(
      8 <= resetInfo.value.password.length &&
      resetInfo.value.password.length <= 32
    )
  ) {
    resetInfo.value.isPasswordValid = false;
    resetInfo.value.passwordValidateMessage =
      "비밀번호는 8~32자로 입력해주세요.";
    return;
  }

  // 대소문자 포함, 숫자
  if (!/^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)/.test(resetInfo.value.password)) {
    resetInfo.value.isPasswordValid = false;
    resetInfo.value.passwordValidateMessage =
      "비밀번호는 대소문자, 숫자를 포함해주세요.";
    return;
  }

  // 특스문자 1개이상 포함
  if (!/^(?=.*[!@#$%^&*])/.test(resetInfo.value.password)) {
    resetInfo.value.isPasswordValid = false;
    resetInfo.value.passwordValidateMessage =
      "비밀번호는 특수문자를 포함해주세요.";
    return;
  }

  resetInfo.value.isPasswordValid = true;
};

const inputPasswordCheck = (passwordCheck) => {
  resetInfo.value.passwordCheck = passwordCheck;
  if (resetInfo.value.password !== resetInfo.value.passwordCheck) {
    resetInfo.value.isPasswordCheckValid = false;
    resetInfo.value.passwordCheckValidateMessage =
      "비밀번호가 일치하지 않습니다.";
  } else resetInfo.value.isPasswordCheckValid = true;
};
</script>

<template>
  <div class="login-member__join-page-container">
    <TextJoinPageInfo
      label="비밀번호 변경"
      info-text="변경할 비밀번호를 입력해 주세요"
    />
    <form>
      <div class="input-text__bar">
        <label class="input-text__label">
          <span class="input-text__label-text">* 이메일</span>
          <input
            class="input-text__input"
            name="email"
            disabled
            :value="resetInfo.email"
          />
        </label>
        <div class="wrong-text-box"></div>
      </div>
      <InputTextBar
        :data="{
          label: '*비밀번호',
          name: 'password',
          placeholder: '비밀번호 입력',
          inputType: 'password',
          isValid: resetInfo.isPasswordValid,
          validateMessage: resetInfo.passwordValidateMessage,
        }"
        @inputData="inputPassword"
      />
      <InputTextBar
        :data="{
          label: '*비밀번호 확인',
          name: 'passwordCheck',
          placeholder: '동일한 비밀번호를 입력해주세요',
          inputType: 'password',
          isValid: resetInfo.isPasswordCheckValid,
          validateMessage: resetInfo.passwordCheckValidateMessage,
        }"
        @inputData="inputPasswordCheck"
      />
      <ButtonSubmitBtnFullBar
        text="비밀번호 변경"
        @submit="submitPasswordReset"
      />
    </form>
  </div>
</template>

<style scoped>
.login-member__join-page-container {
  display: flex;
  flex-direction: column;
  width: auto;
  padding: 50px 24px 0 24px;
  gap: 60px;
}
</style>
