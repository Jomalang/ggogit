<script setup lang="ts">
import SnsCardTree from "@/components/card/SnsCardTree.vue";
import { CardType, type SnsCardTreeProps } from "@/types/types";

const props = defineProps<{
  list: SnsCardTreeProps[];
}>();
</script>

<template>
  <div v-if="props.list.length >= 1" class="sns-card-tree-list-box">
    <ul class="sns-card-tree__list">
      <li
        class="sns-card-tree__item"
        v-for="(item, index) in props.list"
        :key="index"
      >
        <SnsCardTree :data="item" />
      </li>
    </ul>
  </div>
  <div v-else>
    <TextMainTitle
      class="book-detail-my-tree-no-tree-container"
      :data="{
        title: '아직 기록이 없어요! 첫 기록을 남겨보세요.',
        size: 18,
      }"
    />
  </div>
</template>

<style scoped>
.sns-card-tree__item {
  background-color: var(--white, #ffffff);
  width: 290px;
  padding: 16px;
  margin-left: 10px;
  border-radius: 16px;
  box-shadow: 4px 4px 8px rgba(0, 0, 0, 0.25);
}

.sns-card-tree-list-box {
  display: flex;
  overflow-x: auto;
  padding-bottom: 10px;
  margin: 0 24px 0 24px;
  list-style: none;
}

.sns-card-tree-list-box::-webkit-scrollbar {
  display: none;
}

.sns-card-tree__list {
  display: flex;
}

.sns-card-tree__list li:last-child {
  margin-right: 60px; /* 마지막 아이템의 오른쪽에 공백 추가 */
}
</style>
