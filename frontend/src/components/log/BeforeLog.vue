<script setup lang="ts">

import {BeforeLeafItemProps} from "@/types/types";

const props = defineProps<{
  beforeLeafData: BeforeLeafItemProps;
}>();

setTimeout((): void => {
  const element: HTMLElement | null = document.querySelector('.before-log-box');
  if (element) {
    element.classList.add('before-log-box--show');
  } else {
    console.error('Element with class "before-log-box" not found.');
  }

}, 500); // 0.5초 뒤에 이벤트를 등록한다.

</script>

<template>
  <!-- 인자 (leafInfo) -->
  <div class="before-log-box">
    <div class="before-log__branch">
      <div class="before-log__line"></div>
      <div class="before-log__circle"></div>
    </div>
    <div class="before-log__text-box">
      <p class="before-log__text">이전 로그</p>
      <p class="before-log__title">{{ beforeLeafData.title }}</p>
      <p class="before-log__date">{{ beforeLeafData.date }}</p>
      <ul class="before-log__tags">
        <li class="before-log__tag"
            v-for="tag in beforeLeafData.tags"
            :key="tag.id"
        >{{ tag.name }}
        </li>
      </ul>
    </div>
  </div>
</template>

<style scoped>
  .before-log-box {
    width: 100%;
    transform: translateY(-100%);
    transition: transform 0.5s ease;
  }

  .before-log-box--show {
    transform: translateY(0);
  }

  .before-log__branch {
    width: 12px;
    display: flex;
    justify-content: center;
    align-items: center;
    flex-direction: column;
  }

  .before-log__line {
    width: 2px;
    height: 20px;
    background: var(--main1, #323a27);
  }

  .before-log__circle {
    width: 12px;
    height: 12px;
    margin-top: 4px;
    border-radius: 100px;
    background-color: var(--warning, #ba0c0c);
  }

  .before-log__text {
    font-weight: var(--bold, 700);
    font-size: 20px;
    color: var(--main1, #323a27);
    line-height: var(--line-height-main);
    letter-spacing: var(--letter-spacing-main);
  }

  .before-log__title {
    font-size: 16px;
    color: var(--text-sub, #767676);
    font-weight: var(--bold, 700);
    line-height: var(--line-height-main);
    letter-spacing: var(--letter-spacing-main);
  }

  .before-log__date {
    font-size: 14px;
    color: var(--text-sub, #767676);
    font-weight: var(--regular, 400);
    line-height: var(--line-height-main);
    letter-spacing: var(--letter-spacing-main);
  }

  .before-log__tags {
    display: flex;
    flex-direction: row;
    gap: 4px;
    margin-top: 2px;
  }

  .before-log__tag {
    display: inline-block;
    border-radius: 4px;
    padding: 2px 8px;
    background-color: var(--main1, #323a27);
    font-size: 14px;
    font-weight: var(--medium, 500);
    line-height: var(--line-height-main);
    letter-spacing: var(--letter-spacing-main);
    color: var(--white, #ffffff);
  }
</style>