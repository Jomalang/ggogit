<script setup>
defineProps({
    authors: {
        type: Array,
        required: true
    },
    translators: {
        type: Array,
        default: () => []
    },
    publisher: {
        type: String,
        required: true
    },
    page: {
        type: [Number, String],
        required: true
    },
    seed: {
        type: String,
        required: true
    }
})
</script>

<template>
    <!-- text-book-info--no-title(authors,translators, publisher, page,seed) -->
    <div class="text-book-info--no-title">
        <div class="text-book-info--no-title__creators">
            <div v-for="(author, index) in authors" :key="'author-' + index" class="text-book-info--no-title__creators">
                <span class="text-book-info-create-info">{{ author }}</span>
                <span class="text-book-info-create-info">|</span>
            </div>
            <div v-if="translators" v-for="(translator, index) in translators" :key="'translator-' + index" class="text-book-info--no-title__creators">
                <span class="text-book-info-create-info">{{ translator }}</span>
                <span class="text-book-info-create-info">|</span>
            </div>
            <span class="text-book-info-create-info">{{ publisher }}</span>
        </div>
        <div>
            <span class="text-book-info--no-title__page">총 페이지 수:</span>
            <span class="text-book-info--no-title__page">{{ page }}</span>
        </div>
        <div>
            <p class="text-book-info--no-title__seed">{{ seed }}</p>
        </div>
    </div>
</template>

<style scoped>
.text-book-info--no-title {
    width: 100%;
    display: flex;
    flex-direction: column;
    gap: 4px;
}

.text-book-info--no-title__creators {
    display: flex;
}

.text-book-info--no-title__page {
    font-size: 14px;
    font-weight: var(--regular);
    color: var(--text-sub);
    line-height: var(--line-height-main);
    letter-spacing: var(--letter-spacing-main);
}

.text-book-info--no-title__seed {
    display: inline-block;
    padding: 2px 8px;
    font-size: 10px;
    font-weight: var(--medium);
    color: var(--white);
    line-height: var(--line-height-main);
    letter-spacing: var(--letter-spacing-main);
    border-radius: 4px;
    background: var(--main1);
}
</style>