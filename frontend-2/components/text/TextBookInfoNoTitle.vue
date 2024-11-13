<script setup>
import { defineProps } from "vue";
const { data } = defineProps(["data"]);
function seedConverter(seedId) {
  switch (seedId) {
    case 1:
      return "도서";
    case 2:
      return "생각";
    case 3:
      return "문장";
    case 4:
      return "공부";
    case 5:
      return "영상";
    default:
      return "알 수 없는 유형";
  }
}
let seed = seedConverter(data.seed);
</script>

<template>
  <!-- text-book-info--no-title(authors,translators, publisher, page,seed) -->
  <div class="text-book-info--no-title">
    <div class="text-book-info--no-title__creators">
      <div class="text-book-info--no-title__creators">
        <span class="text-book-info-create-info">{{ data.authors }} </span>
        <span class="text-book-info-create-info"> | </span>
      </div>
      <div
        v-if="data.translators"
        v-for="translator of data.translators"
        class="text-book-info--no-title__creators"
      >
        <span class="text-book-info-create-info">{{ translator }}</span>
        <span class="text-book-info-create-info"> | </span>
      </div>
      <span class="text-book-info-create-info">{{ data.publisher }}</span>
    </div>
    <div>
      <span class="text-book-info--no-title__page">총 페이지 수:</span>
      <span class="text-book-info--no-title__page">{{ data.page }}</span>
    </div>
    <div>
      <p class="text-book-info--no-title__seed">{{ seed }}</p>
    </div>
  </div>
</template>

<style scoped>
.text-book-info--no-title {
  width: 100%;
  display: flex;
  flex-direction: column;
  gap: 4px;
}

.text-book-info--no-title__creators {
  display: flex;
}

.text-book-info--no-title__page {
  font-size: 14px;
  font-weight: var(--regular);
  color: var(--text-sub);
  line-height: var(--line-height-main);
  letter-spacing: var(--letter-spacing-main);
}

.text-book-info--no-title__seed {
  display: inline-block;
  padding: 2px 8px;
  font-size: 10px;
  font-weight: var(--medium);
  color: var(--white);
  line-height: var(--line-height-main);
  letter-spacing: var(--letter-spacing-main);
  border-radius: 4px;
  background: var(--main1);
}

.text-book-info-create-info {
  font-size: 14px;
  line-height: var(--line-height-main);
  letter-spacing: var(--letter-spacing-main);
  font-weight: var(--semi-bold);
}
</style>
