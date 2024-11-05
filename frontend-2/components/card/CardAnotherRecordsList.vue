<script setup lang="ts">
import CardAnotherRecords from "@/components/card/CardAnotherRecords.vue";
import { type CardItemProps, CardType } from "@/types/types";

const props = defineProps<{
  items: CardItemProps[];
}>();
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

<style scoped></style>
