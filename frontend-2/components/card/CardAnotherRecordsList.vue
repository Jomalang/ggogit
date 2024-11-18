<script setup lang="ts">
import CardAnotherRecords from "@/components/card/CardAnotherRecords.vue";
import { type CardItemProps, CardType } from "@/types/types";

const props = defineProps<{
  items: CardItemProps[];
  sideScrollType: String;
}>();

// 스크롤 이벤트 핸들러
let scrollContainer: HTMLElement | null = null;
onMounted(() => {
  scrollContainer = document.querySelector(`.card-another-records-list-box.${props.sideScrollType}`);
});

const scroll = (e: MouseEvent) => {
  e.preventDefault(); // 기본 스크롤 동작 방지
  const scrollAmount = e.deltaY || -e.wheelDelta || 0;
  const adjustedScrollAmount = scrollAmount * 1; // 스크롤 속도 조절
  scrollContainer.scrollLeft += adjustedScrollAmount;
};
</script>

<template>
  <div class="card-another-records-list-box"
       :class="props.sideScrollType"
       @wheel="scroll">
    <ul class="card-another-records__list">
      <li
        class="card-another-records__item"
        v-for="(item, index) in props.items"
        :key="index"
      >
        <CardAnotherRecords :item="item" />
      </li>
    </ul>
  </div>
</template>

<style scoped>
.card-another-records-list-box {
  display: flex;
  overflow-x: auto;
  padding-bottom: 10px;
  margin: 0;
  list-style: none;
}

.card-another-records-list-box::-webkit-scrollbar {
  display: none;
}

.card-another-records__list {
  display: flex;
}

.card-another-records__list li:last-child {
  margin-right: 60px; /* 마지막 아이템의 오른쪽에 공백 추가 */
}
.card-another-records__list-empty-space {
  height: 160px;
}
</style>