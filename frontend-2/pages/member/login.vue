<script setup>
import { jwtDecode } from "jwt-decode";
import { errorMessages } from "vue/compiler-sfc";

const config = useRuntimeConfig();

const memberDetail = useMemberDetail();
const memberRequest = ref({
  email: "",
  password: "",
});

const loginHandler = async () => {
  try {
    const response = await $fetch("/members/login", {
      method: "POST",
      baseURL: `${config.public.apiBase}`,
      body: memberRequest.value,
    });
    const memberInfo = jwtDecode(response.accessToken);
    console.log(memberInfo);
    memberDetail.setAuth(memberInfo);
    const returnURL = route.query.returnURL || "/";
    return useRouter().push(returnURL);
  } catch (error) {
    alert(error.data?.message);
  }
};
</script>

<template>
  <div class="login-member__join-page-container">
    <ButtonLoginJoinPageBackBtn />
    <TextLoginPageInfo />
    <div class="input-text__bar">
      <label class="input-text__label">
        <span class="input-text__label-text">이메일</span>
        <input
          class="input-text__input"
          placeholder="이메일을 입력해주세요"
          autocomplete="off"
          v-model="memberRequest.email"
        />
      </label>
    </div>
    <div class="input-text__bar">
      <label class="input-text__label">
        <span class="input-text__label-text">비밀번호</span>
        <input
          class="input-text__input"
          placeholder="비밀번호를 입력해주세요"
          autocomplete="off"
          type="password"
          v-model="memberRequest.password"
        />
      </label>
    </div>
    <div class="btn-full-bar">
      <button
        id="book-tree-input-form-id"
        class="btn-full-bar__btn"
        @click.prevent="loginHandler"
      >
        로그인
      </button>
    </div>
    <LinkSocialLogin />
    <TextJoinGuide :href="`/member/email-send`" />
  </div>
</template>

<style scoped>
.login-member__join-page-container {
  width: auto;
  padding: 50px 24px 0 24px;
}
.btn-full-bar {
  width: 100%;
  height: 56px;
  display: flex;
  justify-content: center;
  align-items: center;
  padding: 30px 0 30px 0;
}

.btn-full-bar__btn {
  color: var(--white, #ffffff);
  background-color: var(--main1, #323a27);
  height: 56px;
  width: 100%;
  border-radius: 20px;
  box-shadow: var(--shadow-basic);
  font-size: 18px;
  font-weight: var(--bold, 700);
}
.input-text__label {
  display: flex;
  gap: 8px;
  flex-direction: column;
  width: 100%;
  position: relative;
  padding: 30px 0 0 0;
}

.input-text__label-text {
  font-weight: var(--semi-bold);
  font-size: 18px;
}

.input-text__input {
  width: auto;
  height: 56px;
  display: flex;
  border: none;
  border-radius: 12px;
  font-size: 16px;
  font-weight: var(--regular);
  color: var(--text-sub);
  padding-left: 16px;
  background-color: var(--main3, #e5eddb);
}

.input-text__input--warning {
  outline: 2px solid var(--warning);
}

.input-text__input:focus {
  outline: 2px solid var(--filter-checked);
}

.input-text__input:not(:placeholder-shown) {
  color: var(--text-main);
  outline: 2px solid var(--main1, #323a27);
}

.input-text__input-wrong {
  margin: 12px 0 0 12px;
  color: var(--warning, #ba0c0c);
}
.input-text__input:read-only {
  background-color: var(--main2, #e5eddb);
  outline: 3px solid var(--gray);
}
</style>
