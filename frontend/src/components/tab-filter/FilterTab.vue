<script setup lang="ts">
// const aa: FilterTabProps = {
//   filterName: '도서필터',
//   filterItems: [
//     {
//       name: '필터정보1',
//       value: 100,
//       description: '후후후'
//     },
//     {
//       name: '필터정보1',
//       value: 100,
//       description: '후후후'
//     },
//     {
//       name: '필터정보1',
//       value: 100,
//       description: '후후후'
//     }
//   ]
// };

import {FilterTabProps} from "@/types/types";

const props = defineProps<{
  data:FilterTabProps
}>();

</script>

<template>
  <div
      id="filter-tab"
      class="filter-tab__box"
  >
    <header class="filter-tab__header">
      <h1 class="filter-tab__header--title">{{data.filterName}}</h1>
      <button
          id="filter-tab-close-btn"
          class="filter-tab__btn--back"
          type="button"
      >
        <img src="/public/svg/tab-back.svg" alt="뒤로가기 버튼" />
      </button>
    </header>

    <ul class="filter-tab__list">
      <li v-for="item in data.filterItems" class="filter-tab__item">
        <label class="filter-tab__item-label">
          <input
              class="filter-tab__item-radio"
              type="radio"
              :name="item.name"
              :value="item.value"
              checked
          />
          <span
              class="filter-tab__item--label-text"
          >{{item.description}}</span
          >
          <div class="filter-tab__icon-box">
            <img
                class="filter-tab__icon-img"
                src="/public/svg/tab-check-btn.svg"
                alt="필터 버튼"
            />
          </div>
        </label>
      </li>
    </ul>
  </div>
</template>

<style scoped>
.filter-tab__box {
  background-color: var(--white, #ffffff);
  margin-top: 52px;
  border-radius: 18px;
  height: 100%;
  transition-property: height;
  transform: translateY(100%); /* 리스트가 아래로 숨겨짐 */
  transition: transform 0.3s ease-in-out;
}
.filter-tab__box--30 {
  background-color: var(--white, #ffffff);
  border-radius: 18px;
  border: 1px solid var(--main2--opacity40);
  height: 100%;
  transition-property: height;
  transform: translateY(100%); /* 리스트가 아래로 숨겨짐 */
  transition: transform 0.3s ease-in-out;
}

.filter-tab__box.up, .filter-tab__box--30.up {
  transform: translateY(0); /* 리스트가 위로 슬라이드됨 */
}

.filter-tab__header {
  width: auto;
  display: flex;
  justify-content: flex-start;
  align-items: center;
  margin: 0 24px;
  position: relative;
}

.filter-tab__btn--back {
  background-color: var(--white, #ffffff);
  padding: 10px;
  border: none;
  margin-left: auto;
  margin-top: 5px;
  margin-bottom: 5px;
}

.filter-tab__header--title {
  position: absolute;
  top: 50%;
  left: 50%;
  font-size: 20px;
  transform: translate(-50%, -50%);
  color: var(--main1, #323a27);
  white-space: nowrap;
  font-weight: var(--bold);
  line-height: var(--line-height-main);
  letter-spacing: var(--letter-spacing-main);
}

.filter-tab__item {
  margin: 12px 24px;
  display: flex;
  justify-content: flex-start;
  align-items: center;
}

.filter-tab__item-label {
  padding: 5px 0;
  display: flex;
  width: 100%;
  justify-content: space-between;
  align-items: center;
}

.filter-tab__item-radio {
  display: none;
}

.filter-tab__item--label-text {
  font-size: 16px;
  color: var(--text-sub, #767676);
  font-weight: var(--medium, 500);
  line-height: var(--line-height-main);
  letter-spacing: var(--letter-spacing-main);
}

.filter-tab__item--label-text--active {
  font-size: 16px;
  color: var(--main1, #323a27);
  font-weight: var(--bold);
  line-height: var(--line-height-main);
  letter-spacing: var(--letter-spacing-main);
}

.filter-tab__item--radio:checked + .filter-tab__item--label-text {
  font-size: 16px;
  color: var(--main1, #323a27);
  font-weight: var(--bold);
  line-height: var(--line-height-main);
  letter-spacing: var(--letter-spacing-main);
}

.filter-tab__icon-box {
  height: 36px;
  width: 36px;
}

.filter-tab__icon-img {
  display: none;
}

.filter-tab__item-radio:checked ~ .filter-tab__icon-box .filter-tab__icon-img {
  display: block;
}
</style>