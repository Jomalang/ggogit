<script setup lang="ts">

import TextJoinPageInfo from "@/components/text/TextJoinPageInfo.vue";
import InputTextBar from "@/components/input/InputTextBar.vue";
import DeleteFormBtn from "@/components/input/DeleteFormBtn.vue";
import Checkbox from "@/components/input/Checkbox.vue";
import SubmitBtnFullBar from "@/components/button/SubmitBtnFullBar.vue";
import CheckboxJoinPolicyAgreement from "@/components/input/CheckboxJoinPolicyAgreement.vue";
</script>

<template>
  <div class="login-member__join-page-container">
    <TextJoinPageInfo
    label="회원가입"
    info-text="기본 정보를 작성해주세요"
    />
    <InputTextBar
        label="이름"
        name="name"
        placeholder="(필수)이름을 입력해주세요"
    />
    <InputTextBar
        label="이메일"
        name="email"
        placeholder="(필수)이메일을 입력해주세요."
    />
    <InputTextBar
        label="비밀번호"
        name="password"
        placeholder="(필수)비밀번호를 입력해주세요 (8~32자, 대소문자 포함, 숫자, 특스문자 1개이상 포함)"
    />
    <InputTextBar
        label="닉네임"
        name="nickname"
        placeholder="(필수)닉네임을 입력해주세요"
    />
    <InputTextBar
        label="한 줄 소개"
        name="introduction"
        placeholder="간단한 한 줄 소개를 작성해 주세요"
    />
    <CheckboxJoinPolicyAgreement
        label="이용약관 동의"
    />
    <SubmitBtnFullBar
        text="회원가입"
    />
  </div>
</template>

<style scoped>
@import url('/src/assets/css/common/effect.css');
@import url('/src/assets/css/common/reset.css');
@import url('/src/assets/css/common/text.css');
@import url('/src/assets/css/common/variables.css');
@import url('/src/assets/css/layout.css');

.login-member__join-page-container {
  width: auto;
  padding: 50px 24px 0 24px;
}
</style>