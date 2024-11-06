<script setup lang="ts">
const props = defineProps<{
  num: Number;
  filterName: String;
}>();

const emit = defineEmits(['sort']);

</script>

<template>
  <!-- topbar-search-result-num(num)-->
  <div class="top-bar-search-frame">
    <span class="top-bar-search-result-num-tree">
      <p class="top-bar-search-result-number">
        {{ num }}
      </p>
      <span class="top-bar-search-result-number">개</span>
      <p class="top-bar-search-result-text">의 검색 결과</p>
    </span>
    <span @click="emit('sort')" class="filter-tree-leaf__card-sort" id="filter-tree-leaf__card-sort">
      <input class="bar-search-current__detail-input" type="checkbox" id="bar-search-current__detail"/>
      <label class="bar-search-current__detail" for="bar-search-current__detail">{{filterName}}</label>
    </span>
  </div>
</template>

<style>
/* =========================
    topbar-search-result-num
          검색 결과 개수
  ========================== */
.top-bar-search-frame{
  display: flex;
  box-sizing: border-box;
  background-color: var(--main3);
  border-radius: 8px;
  justify-content: space-between;
  align-items: center;
  align-self: stretch;
  height: 34px;
}
.top-bar-search-result-num-tree {
  padding: 8px 16px 8px 16px;
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: flex-start;
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


.filter-tree-leaf__card-sort {
  display: flex;
  align-items: end;
}

.bar-search-current__detail-input {
  display: none;
  cursor: none;
}

.bar-search-current__detail {
  padding: 7px 8px 10px;
  margin-left: auto;
  content: "자세히";
  font-size: 10px;
  font-weight: var(--bold, 700);
  cursor: pointer;
}

.bar-search-current__detail::after {
  width: 7px;
  height: 7px;
  content: "";
  display: inline-block;
  align-items: center;
  background-image: url("/svg/hidden-on.svg");
  background-repeat: no-repeat;
  background-position: center;
  background-size: contain;
}


.bar-search-current__detail-input:checked + .bar-search-current__detail::after {
  width: 7px;
  height: 7px;
  content: "";
  align-items: center;
  display: inline-block;
  background-image: url("/svg/hidden-off.svg");
  background-position: center;
  background-size: contain;
}

.bar-search-current__detail-input:checked
~ .bar-search-current__detail-content {
  width: 100%;
  display: flex;
  flex-direction: column;
}
</style>
