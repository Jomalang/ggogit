<script setup>
const props = defineProps({
    infoText: {
        type: String,
        default: '계정이 없으신가요?'
    },
    label: {
        type: String,
        default: '회원가입'
    },
    href: {
        type: String,
        required: true
    }
})
</script>

<template>
    <!-- join-guide(infoText, label, href) -->
    <div class="join-guide">
        <p class="text--title14">{{ infoText }}</p>
        <a :href="href">
            <p class="text--title16">{{ label }}</p>
        </a>
    </div>
</template>

<style scoped>
.join-guide {
    display: flex;
    justify-content: flex-end;
    align-items: center;
    text-align: center;
    gap: 4px;
}

.text--title14 {
    font-size: 14px;
}

.text--title16 {
    font-size: 16px;
}
</style>