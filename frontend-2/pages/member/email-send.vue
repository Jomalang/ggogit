<script setup lang="ts">
</script>

<template>
  <div class="login-member__join-page-container">
    <LoginJoinPageBackBtn />
    <TextLoginPageInfo
        label="회원가입"
        info-text="이메일로 회원가입을 진행합니다"
    />
    <InputTextBar
        label="이메일"
        name="email"
        placeholder="이메일을 입력해주세요."
    />
    <SubmitBtnFullBar text="회원가입" />
    <SocialLogin />
    <JoinGuide label="로그인" info-text="계정이 이미 있으신가요?" />
  </div>
</template>

<style scoped>

.login-member__join-page-container {
  width: auto;
  padding: 50px 24px 0 24px;
}
</style>
