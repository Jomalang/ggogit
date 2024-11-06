<script setup>
import { defineProps } from "vue";

const props = defineProps({
  data: Object,
  default: {
    treeId: "",
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

const formatDateYear = (date) => {
  return new Date(date).getFullYear().toString();
};

const formatDate = (date) => {
  const options = {
    year: "2-digit", // '23' 형식으로 출력
    month: "2-digit", // '10' 형식으로 출력
    day: "2-digit" // '01' 형식으로 출력
  };

  // '2023. 10. 01.' 형식으로 반환된 문자열을 '-'로 대체하고, 공백을 제거
  return new Date(date).toLocaleDateString('ko-KR', options)
      .replace(/\./g, '-') // '.'을 '-'로 변경
      .replace(/ /g, '')   // 공백 제거
      .slice(0, -1);       // 마지막에 붙는 '-' 제거
};

</script>

<template>
  <div class="card-tree-details">
    <NuxtLink class="card-tree-detail" :to="`${data.treeId}`">
      <img
        v-if="data.coverImageName !== undefined"
        class="card-tree__book-cover"
        :src="data.coverImageName"
        alt="도서 이미지"
      />
      <img
        v-else
        class="card-tree__book-cover"
        src="/svg/tree-icon--white.svg"
        alt="도서 기본 이미지"
      />
      <div class="card-tree-detail__box">
        <div class="card-tree-detail__tags">
          <span class="card-tree-detail__tag">{{ data.seedKorName }}</span>
          <span class="card-tree-detail__tag">{{ data.bookCategory }}</span>
        </div>

        <!---->
        <p class="card-tree-detail__name">{{ data.treeTitle }}</p>
        <p class="card-tree-detail__info">{{ data.bookTitle }}</p>
        <div class="card-tree-detail__info">
          <span class="card-tree-detail__info">{{ formatDateYear(data.bookPublishedYear) }}</span>
          <span class="card-tree-detail__info"> &nbsp;  </span>
          <span class="card-tree-detail__info">{{ data.bookAuthor }}</span>
          <span class="card-tree-detail__info">  &nbsp; </span>
          <span class="card-tree-detail__info">{{ data.bookPublisher }}</span>
        </div>
        <div class="card-tree-detail__info-created-date">
          {{ formatDate(data.treeCreatedAt) }}
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
