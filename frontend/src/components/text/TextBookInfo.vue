<script setup>
const props = defineProps({
    seed: {
        type: String,
        default: '카테고리'
    },
    title: {
        type: String,
        default: '제목'
    },
    author: {
        type: String,
        default: '저자'
    },
    translators: {
        type: Array,
        default: () => []
    },
    publisher: {
        type: String,
        default: '출판사'
    }
})
</script>

<template>
    <!-- text-book-info(seed, title, author, translators, publisher) -->
    <div class="text-book-info">
        <div class="text-book-info__frame">
            <p class="text-book-info__seed">{{ props.seed }}</p>
        </div>
        <div class="text-book-info__frame"> <!-- 여기에 category css 가 없어서 frame 으로 대체해놈 -->
            <p class="text-book-info__title">{{ props.title }}</p>
        </div>
        <div class="text-book-info__create-frame">
            <div class="text-book-info__create">
                <span class="text-book-info-create-info">{{ props.author }}</span>
                <span class="text-book-info-create-info">·</span>
            </div>
            <div v-if="props.translators && props.translators.length"
                 v-for="(translator, index) in props.translators"
                 :key="index"
                 class="text-book-info__create">
                <span class="text-book-info-create-info">{{ translator }}</span>
                <span class="text-book-info-create-info">·</span>
            </div>
            <span class="text-book-info-create-info">{{ props.publisher }}</span>
        </div>
    </div>
</template>

<style scoped>
.text-book-info {
    display: flex;
    flex-direction: column;
    justify-content: flex-start;
    height: 137px;
}
.text-book-info__frame {
    display: flex;
    background: var(--white);
}

.text-book-info__seed {
    display: inline;
    padding: 2px 8px;
    border-radius: 4px;
    font-size: 10px;
    font-weight: var(--medium);
    line-height: var(--line-height-main);
    letter-spacing: var(--letter-spacing-main);
    color: var(--white);
    background: var(--main1);
}

.text-book-info__title {
    font-size: 24px;
    font-weight: var(--bold);
    line-height: var(--line-height-main);
    letter-spacing: var(--letter-spacing-main);
    color: var(--main1);
}

.text-book-info__create {
    width: fit-content;
    display: flex;
    justify-content: left;
    gap: 2px;
}
.text-book-info__create-frame {
    display: flex;
    gap: 2px;
}

.text-book-info-create-info {
    display: flex;
    font-size: 14px;
    font-weight: var(--regular);
    line-height: var(--line-height-main);
    letter-spacing: var(--letter-spacing-main);
    color: var(--text-sub);
}
</style>