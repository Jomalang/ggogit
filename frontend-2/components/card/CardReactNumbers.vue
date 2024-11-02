<script setup>
import { defineProps } from "vue";

const props = defineProps({
  leaf: 0,
  like: 0,
  view: 0,
});

function modifyCount(count) {
  return 999 < count ? "999+" : String(count);
}
</script>

<template>
  <div class="card-reading-progress-status__list">
    <p class="card-reading-progress-status__leaf">
      리프
      <span class="card-reading-progress-status__num">{{
        modifyCount(props.leaf)
      }}</span>
    </p>
    <p class="card-reading-progress-status__like">
      좋아요
      <span class="card-reading-progress-status__num">{{
        modifyCount(props.like)
      }}</span>
    </p>
    <p class="card-reading-progress-status__views">
      조회수
      <span class="card-reading-progress-status__num">{{
        modifyCount(props.view)
      }}</span>
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
