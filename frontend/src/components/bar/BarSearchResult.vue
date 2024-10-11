<script setup lang="ts">

interface Props {
  result: number;
}
const props = defineProps<Props>();
</script>

<template>
<!--==========================================
    ??개의 검색결과 출력 / A태그 추가 (result)
==========================================-->
  <div class="bar-search-result-frame">
      <div class="bar-search-result__data-frame">
          <span class="bar-search-result__num-data">{{props.result}} 개</span>
          <p class="bar-search-result__num-p">의 검색 결과</p>
      </div>
      <div class="bar-search-current-frame">
          <input
                  class="bar-search-current__detail-input"
                  type="checkbox"
                  id="bar-search-current__detail"
          />
          <label
                  class="bar-search-current__detail"
                  for="bar-search-current__detail"
          >최근 수정한 수</label>
      </div>
  </div>
</template>

<style>
/*===============================================
    bar: class="bar-search-result-frame"
===============================================*/
.bar-search-result-frame {
  display: flex;
  justify-content: space-between;
  align-items: center;
  border-radius: 8px;
  background: var(--main2--opacity40);
}
.bar-search-result__data-frame {
  padding: 9px 16px;
  display: flex;
}
.bar-search-result__num-data {
  color: var(--text-sub);
  font-size: 12px;
  font-weight: var(--bold);
  line-height: var(--line-height-main);
  letter-spacing: var(--letter-spacing-main);
}
.bar-search-result__num-p {
  color: var(--text-sub);
  font-size: 12px;
  font-weight: var(--medium);
  line-height: var(--line-height-main);
  letter-spacing: var(--letter-spacing-main);
}
.bar-search-current-frame {
  padding: 9px 16px;
  display: flex;
}

.bar-search-current__detail-input {
  display: none;
  cursor: none;
}

.bar-search-current__detail {
  padding-top: 7px;
  padding-bottom: 10px;
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

.bar-search-current__detail-content {
  width: 100%;
  display: none;
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
