<script setup lang="ts">
const props = defineProps<{
  title: string;
}>();
</script>

<template>
  <!--top-bar-title(title)-->
  <div class="top-bar--green">
    <div class="top-bar__frame">
      <a class="tob-bar__back-link" href="prevPage">
        <img
          src="/public/svg/back-arrow-icon.svg"
          alt="top-bar--backarrow.svg"
        />
      </a>
      <p class="tob-bar__title">{{ title }}</p>
      <a class="top-bar__success-link" href="prevPage">
        <div class="top-bar__text" type="submit">완료</div>
      </a>
    </div>
  </div>
</template>

<style>
/* ========================================== /
   FRAGMENT: 뒤로가기 상단바 -- 오른쪽 버튼없음, 있음.
/ ========================================== */

.top-bar--green {
  background: var(--main2, #e5eddb);
  display: flex;
  flex-direction: row;
  align-items: flex-end;
  height: 76px;
  position: relative;
  border-bottom: 1px solid var(--gray);
  padding: 0 24px 0 24px;
}

.top-bar--white {
  background-color: var(--white);
  display: flex;
  flex-direction: row;
  align-items: flex-end;
  height: 76px;
  position: relative;
  justify-content: space-between;
  border-bottom: 1px solid var(--gray);
  padding: 0 24px;
}

.top-bar__frame {
  width: 100%;
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 14px;
  position: relative;
}

.tob-bar__title {
  color: var(--main1, #323a27);
  font-size: 20px;
  line-height: var(--line-height-main);
  letter-spacing: var(--letter-spacing-main);
  font-weight: var(--bold);
}

.top-bar__title {
  color: var(--main1, #323a27);
  font-size: 20px;
  line-height: var(--line-height-main);
  letter-spacing: var(--letter-spacing-main);
  font-weight: var(--bold);
  order: 1;
  position: absolute;
  left: 50%;
  transform: translateX(-50%);
}

.top-bar__success-link {
  order: 2;
}

.top-bar__text {
  color: var(--main1, #323a27);
  font-size: 16px;
  letter-spacing: var(--letter-spacing-main);
  font-weight: var(--bold);
  align-self: flex-end;
  background: transparent;
  border: none;
  padding: 0;
  width: 41.49px;
  order: 2;
}

.tree-input-text--select__tag {
  display: flex;
  flex: 0 0 auto;
  align-items: center;
  height: 30px;
  padding-left: 20px;
  padding-right: 20px;
  border-radius: 12px;
  background-color: var(--main1);
  font-size: 14px;
  font-weight: var(--semi-bold);
  color: var(--text-icon);
}

.tree-input-text--select__tag-button-name {
  align-self: center;
  border: none;
  background-color: var(--main1);
  font-size: 14px;
  font-weight: var(--semi-bold);
  color: var(--text-icon);
}

.tree-input-text--select__tag-button-cancle {
  align-self: center;
  border: none;
  background-color: var(--main1);
  font-size: 14px;
  font-weight: var(--semi-bold);
  color: var(--text-icon);
}
</style>
