<script setup lang="ts">

import {CardReactNumbersProps} from "@/types/types";

const data: CardReactNumbersProps = {
  leafCount: 100,
  likeCount: 100,
  viewCount: 100,
};

const props = defineProps<{
  data: CardReactNumbersProps;
}>();

function modifyCount(count) {
  return 999 < count ? '999+' : String(count);
}

</script>

<template>
  <div class="card-reading-progress-status__list">
    <p class="card-reading-progress-status__leaf">
      리프
      <span class="card-reading-progress-status__num">{{ modifyCount(data.leafCount) }}</span>
    </p>
    <p class="card-reading-progress-status__like">
      좋아요
      <span class="card-reading-progress-status__num">{{ modifyCount(data.likeCount) }}</span>
    </p>
    <p class="card-reading-progress-status__views">
      조회수
      <span class="card-reading-progress-status__num">{{ modifyCount(data.viewCount) }}</span>
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