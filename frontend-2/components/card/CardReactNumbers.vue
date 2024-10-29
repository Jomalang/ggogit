<script setup lang="ts">
// const cardReactNumbersProps = {
//   leaf: data.treeLeafCnt,
//   like: data.treeLikeCnt,
//   view: data.treeViewCnt,
// };
import { defineProps } from 'vue';

const {data} = defineProps(['data']);

function modifyCount(count) {
  return 999 < count ? '999+' : String(count);
}

</script>

<template>
  <div class="card-reading-progress-status__list">
    <p class="card-reading-progress-status__leaf">
      리프
      <span class="card-reading-progress-status__num">{{ modifyCount(data.leaf) }}</span>
    </p>
    <p class="card-reading-progress-status__like">
      좋아요
      <span class="card-reading-progress-status__num">{{ modifyCount(data.like) }}</span>
    </p>
    <p class="card-reading-progress-status__views">
      조회수
      <span class="card-reading-progress-status__num">{{ modifyCount(data.view) }}</span>
    </p>
  </div>
</template>

<style scoped>
.card-reading-progress-status__list {
  font-size: 14px;
  line-height: var(--line-height-main);
  color: var(--text-sub);
  transform: translate(0, -8.8px);
}

.card-reading-progress-status__num {
  font-weight: var(--bold);
}
</style>