<script setup>
import { computed } from "vue";

const props = defineProps({
  tree: {
    type: Object,
    required: true,
  },
});

const formatDate = (date) => {
  const options = {
    year: "2-digit", // '24' 형식으로 출력
    month: "2-digit", // '10' 형식으로 출력
    day: "2-digit", // '01' 형식으로 출력
    hour: "2-digit",
    hour12: false, // 시간 출력 (24시간제)
    minute: "2-digit", // 분 출력
    timeZone: "Asia/Seoul", // 한국 시간대
  };
  return new Date(date).toLocaleString("ko-KR", options);
};


const tree = ref(props.tree);
</script>

<template>
  <!-- th:fragment="card-tree-details(tree)" -->

  <NuxtLink class="card-tree-detail" :to="`tree/${tree.treeId}`">
    <div class="card-tree__img-frame">
      <img
        class="card-tree__book-cover"
        alt="treeCover"
        :src="
          tree.coverImageName
            ? useGetImageUrl(tree.coverImageName, 'book')
            : useGetImageUrl(tree.treeImage, 'tree')
        "
      />
    </div>

    <div class="card-tree-detail__box">
      <!--반복문으로 넣어야 할듯..-->
      <div v-if="tree.bookCategory" class="card-tree-detail__tags">
        <span class="card-tree-detail__tag">{{ tree.bookCategory }}</span>
      </div>
      <!---->
      <p class="card-tree-detail__name">{{ tree.title }}</p>

      <div v-if="tree.bookTitle" class="card-tree-detail__slot">
        <p class="card-tree-detail__explanation">{{ tree.bookTitle }}</p>
        <div
          v-if="tree.bookComplete"
          class="card-tree-detail__complete-icon"
        ></div>
      </div>
      <div v-if="tree.bookPublishedYear" class="card-tree-detail__info">
        <span class="card-tree-detail__info">{{ tree.bookPublishedYear }}</span>
        <span class="card-tree-detail__info"> / </span>
        <span class="card-tree-detail__info">{{ tree.bookAuthor }}</span>
        <span class="card-tree-detail__info"> / </span>
        <span v-if="tree.bookTranslator" class="card-tree-detail__info">{{
          tree.bookTranslator
        }}</span>
        <span class="card-tree-detail__info" v-if="tree.bookTranslator">/</span>
        <span class="card-tree-detail__info">{{ tree.bookPublisher }}</span>
      </div>
      <span class="card-tree-detail__info-created-date">{{
        formatDate(tree.leafCreatedAt)
      }}</span>
    </div>
  </NuxtLink>
</template>

<style scoped>
.card-tree-details {
  width: 100%;
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
}

.card-tree-detail {
  width: 100%;
  display: flex;
  flex-direction: row;
  gap: 10px;
}

.card-tree__book-cover {
  height: 120px;
  width: 80px;
  border-radius: 4px;
  background-color: var(--main1);

  object-fit: cover;
}

.card-tree-detail__box {
  display: flex;
  flex-direction: column;
  width: 100%;
  line-height: var(--line-height-main);
  letter-spacing: var(--letter-spacing-main);
  overflow: hidden;
  text-overflow: ellipsis;
}

.card-tree-detail__tags {
  line-height: var(--line-height-sub);
}

.card-tree-detail__tag {
  color: var(--text-sub);
  font-size: 10px;
  margin-right: 2px;
}
.card-tree-detail__slot {
  display: flex;
  justify-content: space-between;
  align-items: center;
}
.card-tree-detail__name {
  font-size: 18px;
  font-weight: var(--bold);
  display: flex;
  align-items: center;
  justify-content: space-between;
}
.card-tree-detail__complete-icon {
  display: inline-flex;
  width: 20px;
  height: 20px;
  background-color: var(--main1);
  mask-image: url("/svg/card-tree-details-complete.svg");
  mask-size: contain;
  mask-repeat: no-repeat;
  mask-position: center;
}

.card-tree-detail__explanation {
  font-size: 14px;
  font-weight: var(--regular);
  color: var(--text-sub);
  overflow: hidden;
  white-space: nowrap;
  text-overflow: ellipsis;
}

.card-tree-detail__info {
  font-size: 14px;
  font-weight: var(--regular);
  color: var(--text-sub);
}

.card-tree-detail__info-created-date {
  font-size: 14px;
  font-weight: var(--regular);
  color: var(--text-sub);
  display: flex;
  flex-direction: column-reverse;
  align-items: flex-end;
  flex-grow: 1;
}
</style>
