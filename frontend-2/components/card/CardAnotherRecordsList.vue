<script setup lang="ts">
import CardAnotherRecords from "@/components/card/CardAnotherRecords.vue";
import { type CardItemProps, CardType } from "@/types/types";

const props = defineProps<{
  items: CardItemProps[];
}>();

// console.log("CardAnotherRecordsList");
// console.log(props.items);
</script>

<template>
  <div class="card-another-records-list-box">
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
