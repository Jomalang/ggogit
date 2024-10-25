<script setup>
const props = defineProps({
    text: {
        type: String,
        default: '코딩 테스트 합격자 되기 자바스크립트 편의 영감 기록을 남기는 이유는 다음과 같습니다. 영감 기록을 작성하면 책에서 배운 내용을 정리하고 이해도를 높일 수 있습니다.'
    }
})
</script>

<template>
    <!-- recent-tree-ex(text) -->
    <div class="textbox-recent-tree-ex">
        <h2 class="textbox-recent-tree-ex__h2">트리 설명</h2>
        <p class="textbox-recent-tree-ex__p">
            {{ text }}
        </p>
    </div>
</template>

<style scoped>
.textbox-recent-tree-ex__h2 {
    color: var(--main1);
    font-size: 18px;
    font-weight: var(--semi-bold);
    line-height: var(--line-height-main);
    margin-bottom: 4px;
}

.textbox-recent-tree-ex__p {
    margin-bottom: 30px;
    font-size: 14px;
    line-height: var(--line-height-main);
    color: var(--text-sub);
}
</style>