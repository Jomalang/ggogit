<script setup lang="ts">
import TextLoginPageInfo from "@/components/text/TextLoginPageInfo.vue";
import LoginJoinPageBackBtn from "@/components/button/LoginJoinPageBackBtn.vue";
import SocialLogin from "@/components/link/SocialLogin.vue";
import SubmitBtnFullBar from "@/components/button/SubmitBtnFullBar.vue";
import JoinGuide from "@/components/text/JoinGuide.vue";
import InputTextBar from "@/components/input/InputTextBar.vue";
</script>

<template>
  <div class="login-member__join-page-container">
    <LoginJoinPageBackBtn/>
    <TextLoginPageInfo/>
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
    <SubmitBtnFullBar
        text="로그인"
    />
    <SocialLogin/>
    <JoinGuide/>
  </div>
</template>

<style scoped>
@import url('/src/assets/css/common/effect.css');
@import url('/src/assets/css/common/reset.css');
@import url('/src/assets/css/common/text.css');
@import url('/src/assets/css/common/variables.css');

.login-member__join-page-container {
  width: auto;
  padding: 50px 24px 0 24px;
}
</style>