<!-- TextBookInfo.vue -->
<script setup lang="ts">
import { defineProps } from 'vue';

const props = defineProps({
  seed: {
    type: Number,
    default: 0
  },
  title: {
    type: String,
    default: '제목'
  },
  author: {
    type: String,
    default: '저자'
  },
  translators: {
    type: Array as () => string[],
    default: () => []
  },
  publisher: {
    type: [String, null],
    default: '출판사'
  }
});

interface  DataItem {
  title: string;
  author: string;
  translators: string[];
  publisher: string;
  seed: string;
}
const data = ref<DataItem>({
  title: '',
  author: '',
  translators: '',
  publisher: '',
  seed: ''
});

function updateData(props) {
  data.title = props.title;
  data.author = props.author;
  data.translators = props.translators;
  data.publisher = props.publisher;
  data.seed = seedConverter(props.seed);
  console.log('data: ',data);
}

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
}

onMounted(async () => {
  await updateData(props);

});
</script>

<template>
  <div class="text-book-info">
    <div class="text-book-info__frame">
      <p class="text-book-info__seed">{{ data.seed }}</p>
    </div>
    <div class="text-book-info__frame">
      <p class="text-book-info__title">{{ data.title }}</p>
    </div>
    <div class="text-book-info__create-frame">
      <div class="text-book-info__create">
        <span class="text-book-info-create-info">{{ data.author }}</span>
        <span class="text-book-info-create-info">·</span>
      </div>
      <div v-if="data.translators && data.translators.length"
           v-for="(translator, index) in data.translators"
           :key="index"
           class="text-book-info__create">
        <span class="text-book-info-create-info">{{ data.translator }}</span>
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
    height: 137px;
}
.text-book-info__frame {
    display: flex;
    background: var(--white);
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