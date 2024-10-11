<script setup>
const props = defineProps({
    text: {
        type: String,
        default: '현재 기록중인 트리가 없습니다'
    },
    boldText: {
        type: String,
        default: '트리를 검색해주세요'
    }
})
</script>

<template>
    <!-- text-info(text, boldText) -->
    <div class="textbox-no-tree">
        <p class="textbox-no-tree__text-top">
            {{ props.text }}
        </p>
        <p class="textbox-no-tree__text-bottom">
            {{ props.boldText }}
        </p>
    </div>
</template>

<style scoped>
.textbox-no-tree {
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    width: 100%;
    height: 64px;
}

.textbox-no-tree__text-top {
    color: var(--main1);
    font-size: 20px;
    font-weight: var(--medium);
    line-height: var(--line-height-main);
    letter-spacing: var(--letter-spacing-sub);
}

.textbox-no-tree__text-bottom {
    color: var(--main1);
    font-size: 20px;
    font-weight: var(--bold);
    line-height: var(--line-height-main);
    letter-spacing: var(--letter-spacing-sub);
}
</style>