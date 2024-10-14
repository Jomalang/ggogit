<script setup lang="ts"></script>

<template>
  <div class="top-bar__tag-info-box" th:fragment="top-bar-tag-info">
    <p class="top-bar__tag-info-text">옵션 선택 또는 생성</p>
  </div>
</template>

<style>
/* ===========================
        option or create
  =============================  */
.top-bar__tag-info-box {
  padding: 10px;
  display: flex;
  flex-direction: row;
  gap: 10px;
  row-gap: 0;
  align-items: center;
  border-top: solid 1px #d9d9d9;
  border-bottom: solid 1px #d9d9d9;
  justify-content: flex-start;
  flex-shrink: 0;
}
.top-bar__tag-info-text {
  color: var(--main1);
  text-align: left;
  font-size: 10px;
  line-height: var(--line-height-main);
  letter-spacing: var(--letter-spacing-main);
  font-weight: var(--regular);
}
</style>
