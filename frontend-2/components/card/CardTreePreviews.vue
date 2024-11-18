<script setup>
import { onBeforeMount, ref } from "vue";
import { defineProps } from "vue";

const props = defineProps({
  data: Object,
  default: {
    treeId: 227,
    treeTitle: "",
    treeCreatedAt: "",
    seedKorName: "",
    treeDescription: "",
    coverImageName: "",
    bookCategory: "",
    bookTitle: "",
    bookAuthor: "",
    bookTranslator: "",
    bookPublisher: "",
    bookPublishedYear: "",
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
const formatYear = (date) => {
  return new Date(date).getFullYear();
};
</script>

<template>
  <div class="card-tree-details">
    <NuxtLink class="card-tree-detail" :to="`${props.data.treeId}`">
      <img
        v-if="props.data.coverImageName !== ''"
        class="card-tree__book-cover"
        :src="
          props.data.coverImageName
            ? useGetImageUrl(props.data.coverImageName)
            : useGetImageUrl(props.data.treeImage, 'tree')
        "
        alt="도서 이미지"
      />
      <img
        v-else
        class="card-tree__book-cover-default"
        src="~/assets/png/tree-icon-white.png"
        alt="도서 기본 이미지"
      />
      <div class="card-tree-detail__box">
        <div class="card-tree-detail__tags">
          <span class="card-tree-detail__tag">{{
            props.data.seedKorName
          }}</span>
          <span class="card-tree-detail__tag">{{
            props.data.bookCategory
          }}</span>
        </div>

        <!---->
        <p class="card-tree-detail__name">{{ props.data.treeTitle }}</p>
        <p class="card-tree-detail__info">{{ props.data.bookTitle }}</p>
        <div class="card-tree-detail__info">
          <span class="card-tree-detail__info">{{
            formatYear(props.data.bookPublishedYear)
          }}</span>
          <span class="card-tree-detail__info"> &nbsp; </span>
          <span class="card-tree-detail__info">{{
            props.data.bookAuthor
          }}</span>
          <span class="card-tree-detail__info"> &nbsp; </span>
          <span class="card-tree-detail__info">{{
            props.data.bookPublisher
          }}</span>
        </div>
        <div class="card-tree-detail__info-created-date">
          {{ formatDate(props.data.treeCreatedAt) }}
        </div>
      </div>
    </NuxtLink>
  </div>
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
}

.card-tree__book-cover-default {
  height: 120px;
  width: 80px;
  object-fit: contain;
  border-radius: 4px;
  background-color: var(--main1);
}

.card-tree-detail__box {
  display: flex;
  flex-direction: column;
  width: 100%;
  line-height: var(--line-height-main);
  letter-spacing: var(--letter-spacing-main);
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
