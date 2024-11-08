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
    bookPublishedYear: ""
  },
});
</script>

<template>
  <div class="card-leaf-details">
    <NuxtLink class="card-leaf-detail" :to="`${props.data.leafId}`">
      <div  class="card-leaf-detail">
      <img
        v-if="props.data.coverImageName !== undefined"
        class="card-leaf__book-cover"
        :src="`/png/${props.data.coverImageName}`"
        alt="도서 이미지"
      />
      <img
        v-else
        class="card-leaf__book-cover"
        src="/svg/leaf-icon--white.svg"
        alt="도서 기본 이미지"
      />
      <div class="card-leaf-detail__box">
        <div class="card-leaf-detail__tags">
          <span class="card-leaf-detail__tag">{{ props.data.seedKorName }}</span>
          <span class="card-leaf-detail__tag">{{ props.data.bookCategory }}</span>
        </div>

        <!---->
        <p class="card-leaf-detail__name">{{ props.data.leafTitle }}</p>
        <p class="card-leaf-detail__info">{{ props.data.bookTitle }}</p>
        <div class="card-leaf-detail__info">
          <span class="card-leaf-detail__info">{{props.data.bookPublishedYear}}</span>
          <span class="card-leaf-detail__info"> &nbsp; </span>
          <span class="card-leaf-detail__info">{{ props.data.bookAuthor }}</span>
          <span class="card-leaf-detail__info"> &nbsp; </span>
          <span class="card-leaf-detail__info">{{ props.data.bookPublisher }}</span>
        </div>
        <div class="card-leaf-detail__info-created-date">
          {{ props.data.treeCreatedAt }}
        </div>
      </div>
      </div>
    </NuxtLink>
  </div>
</template>

<style scoped>
.card-leaf-details {
  width: 100%;
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
}

.card-leaf-detail {
  width: 100%;
  display: flex;
  flex-direction: row;
  gap: 10px;
}

.card-leaf__book-cover {
  height: 120px;
  width: 80px;
  border-radius: 4px;
  background-color: var(--main1);
}

.card-leaf-detail__box {
  display: flex;
  flex-direction: column;
  width: 100%;
  line-height: var(--line-height-main);
  letter-spacing: var(--letter-spacing-main);
}

.card-leaf-detail__tags {
  line-height: var(--line-height-sub);
}

.card-leaf-detail__tag {
  color: var(--text-sub);
  font-size: 10px;
  margin-right: 2px;
}
.card-leaf-detail__slot {
  display: flex;
  justify-content: space-between;
  align-items: center;
}
.card-leaf-detail__name {
  font-size: 18px;
  font-weight: var(--bold);
  display: flex;
  align-items: center;
  justify-content: space-between;
}

.card-leaf-detail__info {
  font-size: 14px;
  font-weight: var(--regular);
  color: var(--text-sub);
}

.card-leaf-detail__info-created-date {
  font-size: 14px;
  font-weight: var(--regular);
  color: var(--text-sub);
  display: flex;
  flex-direction: column-reverse;
  align-items: flex-end;
  flex-grow: 1;
}
</style>
