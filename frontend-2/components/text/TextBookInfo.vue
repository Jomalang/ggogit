<script setup>
import { defineProps } from 'vue';

// const textBookInfoProps = {
//   title: data.title,
//   authors: data.authors,
//   translators: translators,
//   publisher: data.publisher,
//   page: data.page,
//   seed: data.seed,
// };

const { data } = defineProps(['data']);
// seedConverter 함수 정의
function seedConverter(seedId) {
  switch (seedId) {
    case 1:
      return '도서';
    case 2:
      return '생각';
    case 3:
      return '문장';
    case 4:
      return '공부';
    case 5:
      return '영상';
    default:
      return '알 수 없는 유형';
  }
};
let seed = (() => {
  if (typeof data.seed === 'number' && data.seed > 0) {
    return seedConverter(data.seed);
  } else if (typeof data.seed === 'string') {
    return data.seed;
  }
  return '알 수 없는 유형';
})();
</script>

<template>
  <div class="text-book-info">
    <div class="text-book-info__frame">
      <p class="text-book-info__seed">{{ seed }}</p>
    </div>
    <div class="text-book-info__frame">
      <p class="text-book-info__title">{{ data.title }}</p>
    </div>
    <div class="text-book-info__create-frame">
      <div class="text-book-info__create">
        <span class="text-book-info-create-info">{{ data.authors }}</span>
        <span class="text-book-info-create-info">·</span>
      </div>
      <div v-if="data.translators && data.translators.length"
           v-for="(translator, index) in data.translators"
           :key="index"
           class="text-book-info__create">
        <span class="text-book-info-create-info">{{ data.translators }}</span>
        <span class="text-book-info-create-info">·</span>
      </div>
      <span class="text-book-info-create-info">{{ data.publisher }}</span>
    </div>
  </div>
</template>

<style scoped>
.text-book-info {
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
}

.text-book-info__frame {
  display: flex;
}

.text-book-info__seed {
  display: inline;
  padding: 2px 8px;
  border-radius: 4px;
  font-size: 10px;
  font-weight: var(--medium);
  line-height: var(--line-height-main);
  letter-spacing: var(--letter-spacing-main);
  color: var(--white);
  background: var(--main1);
}

.text-book-info__title {
  font-size: 24px;
  font-weight: var(--bold);
  line-height: var(--line-height-main);
  letter-spacing: var(--letter-spacing-main);
  color: var(--main1);
}

.text-book-info__create {
  width: fit-content;
  display: flex;
  justify-content: left;
  gap: 2px;
}
.text-book-info__create-frame {
  display: flex;
  gap: 2px;
}

.text-book-info-create-info {
  display: flex;
  font-size: 14px;
  font-weight: var(--regular);
  line-height: var(--line-height-main);
  letter-spacing: var(--letter-spacing-main);
  color: var(--text-sub);
}
</style>
