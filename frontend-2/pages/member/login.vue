<script setup lang="ts">
</script>

<template>
  <div class="login-member__join-page-container">
    <LoginJoinPageBackBtn />
    <TextLoginPageInfo />
    <InputTextBar
        label="이메일"
        name="email"
        placeholder="이메일을 입력해주세요."
    />
    <InputTextBar
        label="비밀번호"
        name="password"
        placeholder="비밀번호를 입력해주세요."
    />
    <SubmitBtnFullBar text="로그인" />
    <SocialLogin />
    <JoinGuide />
  </div>
</template>

<style scoped>

.login-member__join-page-container {
  width: auto;
  padding: 50px 24px 0 24px;
}
</style>
