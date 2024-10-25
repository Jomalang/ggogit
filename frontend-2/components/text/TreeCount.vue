<script setup>
const props = defineProps({
    num: {
        type: Number,
        default: 14
    }
})
</script>

<template>
    <div class="textbox-tree-count">
        <h2 class="textbox-tree-count__h2">트리 목록</h2>
        <p class="textbox-tree-count__p">
            <span class="textbox-tree-count__num">{{ num }}</span>그루
        </p>
    </div>
</template>

<style scoped>
.textbox-tree-count {
    display: flex;
    margin-bottom: 8px;
    flex-direction: row;
    justify-content: space-between;
    align-items: flex-end;
}

.textbox-tree-count__h2 {
    font-size: 28px;
    font-weight: var(--bold);
    line-height: var(--line-height-main);
    letter-spacing: var(--letter-spacing-main);
    color: var(--text-main);
}

.textbox-tree-count__p {
    font-size: 12px;
}

.textbox-tree-count__num {
    font-size: 18px;
    font-weight: var(--bold);
}
</style>