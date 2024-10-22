<script setup lang="ts">

import SubmitBtnFullBar from "@/components/button/SubmitBtnFullBar.vue";
import InputTextBar from "@/components/input/InputTextBar.vue";
import TextJoinPageInfo from "@/components/text/TextJoinPageInfo.vue";
</script>

<template>
    <div class="login-member__join-page-container">

        <TextJoinPageInfo
            label="비밀번호 변경"
            info-text="변경할 비밀번호를 입력해 주세요"
        />

        <InputTextBar
            label="비밀번호"
            name="password"
            placeholder="비밀번호를 입력해주세요 (8~32자, 대소문자 포함, 숫자, 특스문자 1개이상 포함)"
        />
        <InputTextBar
            label="비밀번호 확인"
            name="password"
            placeholder="확인할 비밀번호를 입력해주세요"
        />

        <SubmitBtnFullBar
            text="비밀번호 변경"
        />
    </div>
</template>

<style scoped>
@import url('@/assets/css/common/effect.css');
@import url('@/assets/css/common/reset.css');
@import url('@/assets/css/common/text.css');
@import url('@/assets/css/common/variables.css');
@import url('@/assets/css/layout.css');

.login-member__join-page-container {
    display: flex;
    flex-direction: column;
    width: auto;
    padding: 50px 24px 0 24px;
    gap: 60px
}
</style>