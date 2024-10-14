<script setup lang="ts">

import {CardProgressBarProps} from "@/types/types";

// const data: CardProgressBarProps = {
//   progress: 50,
//   readPage: 50,
//   fullPage: 100,
// };

const props = defineProps<{
  data: CardProgressBarProps;
}>();

function progressStyle(progress: number) {
  return `width: ${progress}%;`;
}

</script>

<template>
  <div class="card-reading-progress__header">
    <div class="card-reading-progress__title">
      <h2>독서 진행률</h2>
      <div>
        <span class="card-reading-progress__num">{{ data.progress }}</span>
        <span class="card-reading-progress__sep">%</span>
      </div>
    </div>
    <div class="card-reading-progress-content">
      <div class="card-reading-progress__bar-container">
        <div class="card-reading-progress__bar" :style="progressStyle(data.progress)"></div>
      </div>
      <div class="card-reading-progress__page-num">
        <span class="card-progress-page__current">{{ data.readPage }}</span>
        <span class="card-progress-page__sep">/</span>
        <span class="card-progress-page__total"> {{ data.fullPage }} </span>
      </div>
    </div>
  </div>
</template>

<style scoped>
.card-reading-progress {
  background-color: var(--main2);
  border-radius: 8px;
  padding: 16px;
  line-height: var(--line-height-main);
  letter-spacing: var(--letter-spacing-main);
  height: 143px;
  box-shadow: var(--shadow-basic);
}
.card-reading-progress-no-background {
  border-radius: 8px;
  line-height: var(--line-height-main);
  letter-spacing: var(--letter-spacing-main);
}

.card-reading-progress__title {
  display: flex;
  justify-content: space-between;
  color: var(--main1);
  font-size: 16px;
  font-weight: var(--semi-bold);
}

.card-reading-progress-content {
  display: flex;
  flex-wrap: wrap;
  flex-direction: row;
  justify-content: end;
}

.card-reading-progress__bar-container {
  border-radius: 100px;
  background-color: var(--gray);
  height: 4px;
  width: 100%;
  margin-bottom: 4px;
  margin-top: 4px;
}

.card-reading-progress__bar {
  border-radius: 100px;
  background-color: var(--main1);
  width: 80%;
  height: 4px;
  content-visibility: hidden;
}

.card-reading-progress__num {
  font-size: 16px;
  font-weight: var(--semi-bold);
  color: var(--main1);
}

.card-reading-progress__sep {
  font-size: 14px;
  color: var(--main1);
}

.card-progress-page__num {
  font-size: 12px;
  font-weight: var(--semi-bold);
  color: var(--main1);
}

.card-reading-progress-status__list {
  font-size: 14px;
  line-height: var(--line-height-main);
  color: var(--text-sub);
  transform: translate(0px, -8.8px);
}

.card-reading-progress-status__num {
  font-weight: var(--bold);
}
</style>