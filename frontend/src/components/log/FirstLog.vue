<script setup lang="ts">
  setTimeout((): void => {
    const element: HTMLElement | null = document.querySelector('.first-log-box');
    if (element) {
      element.classList.add('first-log-box--show');
    } else {
      console.error('Element with class "first-log-box" not found.');
    }
  }, 500); // 0.5초 뒤에 이벤트를 등록한다.
</script>

<template>
  <div class="first-log-box">
    <div class="first-log-box__line"></div>
    <div class="first-log-box__circle"></div>
  </div>
</template>

<style scoped>

  .first-log-box {
    display: flex;
    flex-direction: column;
    align-items: center;
    width: 100%;
    transform: translateY(-100%);
    transition: transform 0.5s ease;
  }

  .first-log-box--show {
    transform: translateY(0);
  }

  .first-log-box__line {
    width: 8px;
    height: 40px;
    background: var(--main1, #323a27);
  }

  .first-log-box__circle {
    width: 30px;
    height: 30px;
    border-radius: 50%;
    margin-top: 4px;
    background: var(--warning, #ba0c0c);
  }
</style>