<script setup>
import { useMemberJoinTmp } from "~/composables/useMemberJoinTmp.js";

const config = useRuntimeConfig();
const route = useRoute();
const router = useRouter();
const key = ref(route.query.key);

// ----------------------- Method ----------------------- //
const oauthInfo = ref({
  name: "",
  email: "",
  picture: "",
});

oauthInfo.value = useMemberJoinTmp().getJoinInfo();

const joinInfo = ref({
  name: oauthInfo.value.name,
  isNameValid: true,
  nameValidateMessage: "이름을 입력해주세요.",

  email: oauthInfo.value.email,
  isEmailValid: true,
  emailValidateMessage: "이메일을 입력해주세요.",

  nickname: "",
  isNicknameValid: true,
  nicknameValidateMessage: "닉네임을 입력해주세요.",

  introduction: "",
  isIntroductionValid: true,
  introductionValidateMessage: "한 줄 소개를 입력해주세요.",

  isPolicyAgreement: false,
});

// ----------------------- API ----------------------- //
const { data: emailInfo, error: emailInfoError } = await useAuthFetch(
  `members/join/check-email`,
  {
    method: "POST",
    baseURL: `${config.public.apiBase}`,
    headers: { "Content-Type": "application/json" },
    body: { key: key.value },
  }
);

const joinPostApi = async () => {
  try {
    const response = await useAuthDataFetch(`members/oauth/join`, {
      method: "POST",
      baseURL: `${config.public.apiBase}`,
      headers: { "Content-Type": "application/json" },
      body: {
        name: joinInfo.value.name,
        email: joinInfo.value.email,
        password: joinInfo.value.password,
        nickname: joinInfo.value.nickname,
        introduction: joinInfo.value.introduction,
        picture: oauthInfo.value.picture,
      },
    });

    if (response.message !== "로그인 성공") {
      alert("회원가입에 실패했습니다.");
      return;
    }

    // console.log(response.accessToken);
    useMemberStore().setAuthWithToken(response.accessToken);

    alert("회원가입이 완료되었습니다.");
    router.push("/home");
  } catch (error) {
    alert(error.data.message);
  }
};

watchEffect(() => {
  if (emailInfo.value) {
    joinInfo.value.email = emailInfo.value.email;
  }
});

// ----------------------- Method ----------------------- //

const inputNickname = (nickname) => {
  joinInfo.value.nickname = nickname;
};

const inputIntroduction = (introduction) => {
  joinInfo.value.introduction = introduction;
};

const inputPolicyAgreement = (isPolicyAgreement) => {
  joinInfo.value.isPolicyAgreement = isPolicyAgreement;
};

const submitJoin = () => {
  if (joinInfo.value.nickname === "") {
    joinInfo.value.isNicknameValid = false;
    joinInfo.value.nicknameValidateMessage = "닉네임을 입력해주세요.";
  } else {
    joinInfo.value.isNicknameValid = true;
  }

  if (joinInfo.value.introduction === "") {
    joinInfo.value.isIntroductionValid = false;
    joinInfo.value.introductionValidateMessage = "한 줄 소개를 입력해주세요.";
  } else {
    joinInfo.value.isIntroductionValid = true;
  }

  if (!joinInfo.value.isPolicyAgreement) {
    alert("이용약관에 동의해주세요.");
  }

  if (!joinInfo.value.isNicknameValid || !joinInfo.value.isIntroductionValid) {
    alert("입력 정보를 확인해주세요.");
    return;
  }

  // 회원가입 API 호출
  joinPostApi();
  router.push("/member/home");
};
</script>

<template>
  <div class="login-member__join-page-container">
    <TextJoinPageInfo
      label="회원가입"
      info-text="GGoggit 기본 정보를 작성해주세요."
    />
    <form @submit="submitJoin">
      <div class="input-text__bar">
        <label class="input-text__label">
          <span class="input-text__label-text">* 이름</span>
          <input
            class="input-text__input"
            name="name"
            disabled
            :value="joinInfo.name"
          />
        </label>
        <div class="wrong-text-box"></div>
      </div>
      <div class="input-text__bar">
        <label class="input-text__label">
          <span class="input-text__label-text">* 이메일</span>
          <input
            class="input-text__input"
            name="email"
            disabled
            :value="joinInfo.email"
          />
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
          validateMessage: joinInfo.nicknameValidateMessage,
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
          validateMessage: joinInfo.introductionValidateMessage,
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
        text="이용약관 동의"
      />
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
