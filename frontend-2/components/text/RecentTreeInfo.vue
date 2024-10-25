<script setup>
const props = defineProps({
    tree: {
        type: Object,
        required: true,
        default: () => ({
            title: '나의 첫번째 트리',
            bookCategory: '시/에세이',
            bookTitle: '무정형의 삶',
            bookAuthor: '김민철',
            bookPublisher: '위즈덤 하우스',
            createdAt: new Date()
        })
    }
})

const formatDate = (date) => {
    if (!(date instanceof Date)) {
        date = new Date(date)
    }
    return date.toISOString().split('T')[0]
}
</script>

<template>
    <!-- recent-tree-info(tree) -->
    <div class="textbox-recent-tree-info">
        <div class="textbox-recent-tree-info__a">
            <h2 class="textbox-recent-tree-info__tree-title">
                {{ tree.title }}
            </h2>
            <div class="textbox-recent-tree-info__tag tag">
                {{ tree.bookCategory }}
            </div>
            <p class="textbox-recent-tree-info__book-title">
                {{ tree.bookTitle }}
            </p>
            <div class="textbox-recent-tree-info__items">
                <p class="textbox-recent-tree-info__author">{{ tree.bookAuthor }}</p>
                <p class="textbox-recent-tree-info__publisher">{{ tree.bookPublisher }}</p>
            </div>
            <p class="textbox-recent-tree-info__date">
                {{ formatDate(tree.createdAt) }}
            </p>
        </div>
    </div>
</template>

<style scoped>

</style>