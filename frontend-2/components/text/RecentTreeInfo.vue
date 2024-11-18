<script setup>
const props = defineProps({
  tree: {
    type: Object,
    required: true,
    default: () => ({
      treeId: 1,
      title: "나의 첫번째 트리",
      bookCategory: "시/에세이",
      bookTitle: "무정형의 삶",
      bookAuthor: "김민철",
      bookPublisher: "위즈덤 하우스",
      createdAt: new Date(),
    }),
  },
});

const formatDate = (date) => {
  if (!(date instanceof Date)) {
    date = new Date(date);
  }
  return date.toISOString().split("T")[0];
};
</script>

<template>
  <!-- recent-tree-info(tree) -->
  <div class="textbox-recent-tree-info">
    <NuxtLink class="textbox-recent-tree-info__a" :to="`tree/${tree.treeId}`">
      <h2 class="textbox-recent-tree-info__tree-title">
        {{ tree.title }}
      </h2>
      <div class="textbox-recent-tree-info__tag tag">
        {{ tree.bookCategory || tree.seedKorName }}
      </div>
      <p v-if="tree.bookTitle" class="textbox-recent-tree-info__book-title">
        {{ tree.bookTitle }}
      </p>
      <div v-if="tree.bookAuthor" class="textbox-recent-tree-info__items">
        <p class="textbox-recent-tree-info__author">{{ tree.bookAuthor }}</p>
        <p class="textbox-recent-tree-info__publisher">
          {{ tree.bookPublisher }}
        </p>
      </div>
      <p class="textbox-recent-tree-info__date">
        {{ formatDate(tree.createdAt) }}
      </p>
    </NuxtLink>
  </div>
</template>

<style scoped>
/*  ==========================================
    FRAGMENT: 최근 트리 정보
    ========================================== */

.textbox-recent-tree-info {
  padding: 24px;
}

.textbox-recent-tree-info__a {
  display: flex;
  flex-direction: column;
  align-items: center;
  color: var(--text-sub);
  text-decoration: none;
}

.tag {
  font-size: 15px;
  color: var(--white);
  background-color: var(--main1);
  border-radius: 4px;
  text-align: center;
  padding: 2px 8px;
  border: none;
  line-height: var(--line-height-main);
}

.textbox-recent-tree-info__tag {
  order: 0;
  margin-bottom: 4px;
}

.textbox-recent-tree-info__tree-title {
  order: 1;
  margin-bottom: 4px;
  font-size: 20px;
  font-weight: var(--semi-bold);
  color: var(--main1);
  line-height: var(--line-height-main);
  letter-spacing: var(--letter-spacing-main);
}

.textbox-recent-tree-info__book-title {
  order: 2;
  font-size: 16px;
  line-height: var(--line-height-main);
  margin-bottom: 4px;
  text-overflow: ellipsis;
  overflow: hidden;
}

.textbox-recent-tree-info__items {
  color: var(--text-sub);
  display: flex;
  order: 3;
}

.textbox-recent-tree-info__author {
  line-height: var(--line-height-main);
  font-size: 14px;
  margin-bottom: 4px;
}

.textbox-recent-tree-info__author::after {
  content: " · ";
  font-size: 14px;
  padding-left: 3px;
  padding-right: 3px;
}

.textbox-recent-tree-info__publisher {
  line-height: var(--line-height-main);
  font-size: 14px;
  margin-bottom: 4px;
}

.textbox-recent-tree-info__date {
  order: 4;
  line-height: var(--line-height-main);
  font-size: 14px;
  font-weight: var(--semi-bold);
}

.book-tree-explanation-item {
  transition: all, 0.3s, linear;
}
</style>
