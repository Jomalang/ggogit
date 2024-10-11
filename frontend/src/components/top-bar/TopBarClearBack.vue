<script setup lang="ts">
const props = defineProps<{
  link: string;
}>();
</script>

<template>
  <!-- clear-back-->
  <div id="top-bar-clear-back-id" class="top-bar-clear-back-box">
    <div class="top-bar-clear-back__left-box">
      <a :href="link">
        <div class="top-bar-clear-back__icon-box">
          <img src="/public/svg/back--white.svg" alt="" />
        </div>
      </a>
      <a :href="link">
        <div class="top-bar-clear-back__icon-box">
          <img src="/public/svg/home--white.svg" alt="" />
        </div>
      </a>
    </div>
    <div class="top-bar-clear-back__right-box">
      <a :href="link">
        <div class="top-bar-clear-back__icon-box">
          <img src="/public/svg/search--white.svg" alt="" />
        </div>
      </a>
    </div>
  </div>
</template>

<style>
/*  ========================================== /
     FRAGMENT: 투명 뒤로가기 상단 바
/   ========================================== */
.top-bar-clear-back-box {
  width: 100%;
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.top-bar-clear-back-box--none {
  display: none;
}

.top-bar-clear-back__left-box {
  display: flex;
  align-items: center;
  margin-left: 24px;
  gap: 8px;
}

.top-bar-clear-back__right-box {
  margin-right: 24px;
}

.top-bar-clear-back__icon-box {
  width: 36px;
  height: 36px;
  display: flex;
  justify-content: center;
  align-items: center;
}
</style>
