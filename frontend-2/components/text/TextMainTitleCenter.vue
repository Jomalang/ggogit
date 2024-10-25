<script setup>
const props = defineProps({
    title: {
        type: String,
        default: 'Default Title'
    },
    size: {
        type: Number,
        default: 28,
        validator: (value) => [38, 28, 24, 20, 18, 16, 14].includes(value)
    }
})
</script>

<template>
    <!-- text-main-title-center(title, size) -->
    <div class="text-main-title-box">
        <h1 :class="['text-main-title--center', `text--title${props.size}`]">
            {{ props.title }}
        </h1>
    </div>
</template>

<style scoped>
.text-main-title-box {
    display: flex;
    justify-content: center;
    align-items: center;
}

.text-main-title--center {
    color: var(--main1, #323a27);
    text-align: center;
    font-weight: var(--bold, 700);
}

.text--title38 {
    font-size: 38px;
}

.text--title28 {
    font-size: 28px;
}

.text--title24 {
    font-size: 24px;
}

.text--title20 {
    font-size: 20px;
}

.text--title18 {
    font-size: 18px;
}

.text--title16 {
    font-size: 16px;
}

.text--title14 {
    font-size: 14px;
}

</style>