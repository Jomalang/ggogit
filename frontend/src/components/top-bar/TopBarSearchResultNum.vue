<script setup lang="ts">
const props = defineProps<{
  num: Number;
}>();
</script>

<template>
  <!-- topbar-search-result-num(num)-->
  <div class="top-bar-search-result-num">
    <p class="top-bar-search-result-number">
      {{ num }}
    </p>
    <span class="top-bar-search-result-number">개</span>
    <p class="top-bar-search-result-text">의 검색 결과</p>
  </div>
</template>

<style>
/* =========================
    topbar-search-result-num
          검색 결과 개수
  ========================== */

.top-bar-search-result-num {
  box-sizing: border-box;
  background-color: var(--main3);
  border-radius: 8px;
  padding: 8px 16px 8px 16px;
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: flex-start;
  align-self: stretch;
  height: 34px;
}

.top-bar-search-result-number {
  color: var(--text-sub, #767676);
  text-align: center;
  font-size: 12px;
  line-height: var(--line-height-main);
  letter-spacing: var(--letter-spacing-main);
  font-weight: var(--bold);
  display: flex;
  align-items: center;
  justify-content: center;
}

.top-bar-search-result-text {
  color: var(--text-sub, #767676);
  text-align: center;
  font-size: 12px;
  line-height: var(--line-height-main);
  letter-spacing: var(--letter-spacing-main);
  font-weight: var(--medium);
  display: flex;
  align-items: center;
  justify-content: center;
}
</style>
