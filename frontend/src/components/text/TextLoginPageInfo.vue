<script setup>
const props = defineProps({
    label: {
        type: String,
        default: '로그인'
    },
    infoText: {
        type: String,
        default: '이메일과 비밀번호를 입력해주세요.'
    }
})
</script>

<template>
    <!-- text-login__page-info(label, infoText) -->
    <div class="text-login__page-info">
        <p class="text--title28">{{ label }}</p>
        <p class="text--explain">{{ infoText }}</p>
    </div>
</template>

<style scoped>
.text-login__page-info {
    display: flex;
    flex-direction: column;
    gap: 4px;
    padding-top: 30px;
}

.text--title28 {
    font-size: 28px;
}

.text--explain {

}
</style>