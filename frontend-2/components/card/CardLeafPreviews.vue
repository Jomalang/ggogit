<script setup>
import { onBeforeMount, ref } from "vue";
import { defineProps } from "vue";

let path = ref("");

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
function modifyCount(count) {
  return count > 999 ? "999+" : String(count);
}

onBeforeMount(() => {
  if (props.data.seedKorName === "도서") {
    path.value = `/leaf/book/${props.data.leafId}`;
  } else {
    path.value = `/leaf/etx/${props.data.leafId}`;
  }
});
</script>

<template>
  <div class="leaf-list-frame">

    <NuxtLink :to="path">
      <div class="card-leaf-detail">
        <img
          v-if="props.data.coverImageName !== undefined"
          class="card-leaf__book-cover"
          :src="
            props.data.coverImageName
              ? useGetImageUrl(props.data.coverImageName)
              : useGetImageUrl(props.data.treeImage, 'tree')
          "
          alt="도서 이미지"
        />
        <img
          v-else
          class="card-leaf__book-cover"
          src="~/assets/png/leaf-icon--white.png"
          alt="도서 기본 이미지"
        />
        <div class="card-leaf-detail__box">
          <div class="card-leaf-detail__tags">
            <span class="card-leaf-detail__tag">{{
              props.data.seedKorName
            }}</span>
            <span class="card-leaf-detail__tag">{{
              props.data.bookCategory
            }}</span>
          </div>
          <!---->
          <p class="card-leaf-detail__name">{{ props.data.treeTitle }}</p>
          <p class="card-leaf-detail__info">{{ props.data.bookTitle }}</p>
          <div class="card-leaf-detail__info">
            <span class="card-leaf-detail__info">{{
              formatYear(props.data.bookPublishedYear)
            }}</span>
            <span class="card-leaf-detail__info"> &nbsp; </span>
            <span class="card-leaf-detail__info">{{
              props.data.bookAuthor
            }}</span>
            <span class="card-leaf-detail__info"> &nbsp; </span>
            <span class="card-leaf-detail__info">{{
              props.data.bookPublisher
            }}</span>
          </div>
        </div>
      </div>
      <div class="card-leaf-detail__box">
        <div class="card-leaf-detail-content">
          <span class="card-leaf-detail__name">
            {{ props.data.leafTitle }}
          </span>
          <span class="card-leaf-detail__info-created-date">
            {{ formatDate(props.data.leafUpdatedAt) }}
          </span>
        </div>
        <p>{{ props.data.leafContent }}</p>
        <div class="card-leaf-detail__count">
          <span class="card-leaf-detail__info-bold">{{
            modifyCount(props.data.likeCount)
          }}</span>
          <span class="card-leaf-detail__info">좋아요 수</span>
          <span class="card-leaf-detail__info-bold">{{
            modifyCount(props.data.viewCount)
          }}</span>
          <span class="card-leaf-detail__info">조회 수</span>
        </div>
      </div>
    </NuxtLink>
  </div>
</template>

<style scoped>
.leaf-list-frame {
  padding-bottom: 10px;
  border-bottom: 1px solid var(--main1--opacity10);
}
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

.card-leaf-detail-content {
  width: 100%;
  margin-top: 8px;
  margin-bottom: 6px;
  display: flex;
  justify-content: space-between;
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
.card-leaf-detail__count {
  margin-right: 8px;
  display: flex;
  justify-content: end;
  gap: 10px;
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

.card-leaf-detail__info-bold {
  font-size: 14px;
  font-weight: var(--bold);
  color: var(--text-sub);
}

.card-leaf-detail__info-created-date {
  margin-right: 8px;
  font-size: 14px;
  font-weight: var(--regular);
  color: var(--text-sub);
  display: flex;
  flex-direction: column-reverse;
  align-items: flex-end;
}
</style>
