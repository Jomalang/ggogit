<script setup>
const props = defineProps({
    title: {
        type: String,
        default: 'Default Title'
    },
    number: {
        type: Number,
        default: 0
    }
})
</script>

<template>
    <div class="text-main-title-side-text__frame">
        <span class="text-main-title text--title24">
          {{ props.title }}
        </span>
        <span class="text-main-side-text" id="branch-count">
          {{ props.number }} 개
        </span>
    </div>
</template>

<style scoped>
.text-main-title-side-text__frame{
    display: flex;
    justify-content: space-between;
    align-items: end;
}

.text-main-side-text{
    color: var(--main1, #323a27);
    font-weight: var(--bold, 700);
    font-size: 16px;
}

.text--title24 {
    font-size: 24px;
}

.text-main-title {
    color: var(--main1, #323a27);
    font-weight: var(--bold, 700);
}
</style>