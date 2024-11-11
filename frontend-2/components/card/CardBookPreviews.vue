<script setup>
import { onBeforeMount, ref } from "vue";
import { defineProps } from "vue";

const props = defineProps({
  data: Object,
  default: {
    id: 22,
    publishDate: "2007-07-21",
    totalPage: 759,
    author: "J.K. 롤링",
    isbn: "978-3-16-148410-0",
    publisher: "블룸즈버리",
    title: "해리 포터와 죽음의 성물",
    imageFile: "/png/book-example.png",
    createTime: "24-10-01",
    updateTime: "24-10-01",
    bookCategoryId: 4,
    bookCategoryName: "판타지",
    link: "/tree/book/22",
  },
});
</script>

<template>
  <div class="card-tree-details">
    <NuxtLink class="card-tree-detail" :to="`/tree/book/auto/${props.data.id}/new`">
      <img
        v-if="props.data.imageFile !== undefined"
        class="card-tree__book-cover"
        :src="useGetImageUrl(props.data.imageFile)"
        alt="도서 이미지"
      />
      <img
        v-else
        class="card-tree__book-cover"
        src="/svg/tree-icon--white.svg"
        alt="도서 기본 이미지"
      />
      <div class="card-tree-detail__box">
        <!--반복문으로 넣어야 할듯..-->
        <div class="card-tree-detail__tags">
          <span class="card-tree-detail__tag">{{
            props.data.bookCategoryName
          }}</span>
        </div>

        <!---->
        <p class="card-tree-detail__name ellipsis">{{ props.data.title }}</p>
        <div class="card-tree-detail__info">
          <span class="card-tree-detail__info">{{
            props.data.publishDate
          }}</span>
          <span class="card-tree-detail__info"> / </span>
          <span class="card-tree-detail__info">{{ props.data.author }}</span>
          <span class="card-tree-detail__info"> / </span>
          <span class="card-tree-detail__info">{{ props.data.publisher }}</span>
        </div>
        <div class="card-tree-detail__info-created-date">
          {{ props.data.createTime }}
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

.ellipsis {
  display: -webkit-box;       /* Flexbox를 사용하여 요소가 줄바꿈되도록 설정 */
  -webkit-line-clamp: 2;      /* 표시할 줄 수 설정 (여기서는 3줄) */
  -webkit-box-orient: vertical;
  overflow: hidden;           /* 넘친 텍스트를 숨김 */
  text-overflow: ellipsis;    /* 넘친 부분에 ... 추가 */
}

</style>
