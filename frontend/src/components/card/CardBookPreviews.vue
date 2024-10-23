<script setup lang="ts">
import { CardBookPreviewsProps } from "@/types/types";
import { onBeforeMount, ref } from "vue";
import { defineProps } from "vue";

const props = defineProps<{
  data: CardBookPreviewsProps;
}>();
</script>

<template>
  <div class="card-tree-details">
    <a class="card-tree-detail" :href="props.data.link">
      <img
        v-if="props.data.imageFile !== undefined"
        class="card-tree__book-cover"
        :src="props.data.imageFile"
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
          <span class="card-tree-detail__tag">{{ props.data.category }}</span>
        </div>

        <!---->
        <p class="card-tree-detail__name">{{ props.data.title }}</p>
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
    </a>
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
