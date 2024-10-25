<script setup lang="ts">

</script>

<template>
  <!-- (bookmark,seed1,seed2) -->
  <div class="filter-text-btn-frame">
    <span>
      <label>
        <input class="filter-seed__text" type="text" placeholder="리프 검색하기" />
      </label>
    </span>
    <ul class="filter-category-frame">
      <li>
        <input class="filter-category__bookmark" type="checkbox" id="filter-category__bookmark"/>
        <label class="filter-category__bookmark__detail" for="filter-category__bookmark">책갈피</label>
        <ul class="filter-category__bookmark__list">
          <li>테스트1</li>
          <li>테스트2</li>
          <li>테스트3</li>
          <li>테스트4</li>
        </ul>
      </li>
      <li>
        <input class="filter-category__seed" type="checkbox" id="filter-category__seed1"/>
        <label class="filter-category__seed__detail" for="filter-category__seed1">씨앗</label>
        <ul class="filter-category__seed__list">
          <li>테스트1</li>
          <li>테스트2</li>
          <li>테스트3</li>
          <li>테스트4</li>
        </ul>
      </li>
      <li>
        <input class="filter-category__seed" type="checkbox" id="filter-category__seed2"/>
        <label class="filter-category__seed__detail" for="filter-category__seed2">씨앗</label>
        <ul class="filter-category__seed__list">
          <li>테스트1</li>
          <li>테스트2</li>
          <li>테스트3</li>
          <li>테스트4</li>
        </ul>
      </li>
      <li>
        <input class="filter-category__seed" type="checkbox" id="filter-category__seed3"/>
        <label class="filter-category__seed__detail" for="filter-category__seed3">씨앗</label>

        <ul class="filter-category__seed__list">
          <li>테스트1</li>
          <li>테스트2</li>
          <li>테스트3</li>
          <li>테스트4</li>
        </ul>
      </li>
      <li>
        <input class="filter-category__seed" type="checkbox" id="filter-category__seed4"/>
        <label class="filter-category__seed__detail" for="filter-category__seed4">씨앗</label>
        <ul class="filter-category__seed__list">
          <li>테스트1</li>
          <li>테스트2</li>
          <li>테스트3</li>
          <li>테스트4</li>
        </ul>
      </li>
    </ul>
  </div>
</template>

<style scoped>
.filter-text-btn-frame {
  display: flex;
  justify-content: space-between;
  gap: 8px;
}
.filter-seed__text:not(:focus) {
  width: 9ch;
  display: inline;
  padding: 10px 20px;
  border-radius: 8px;
  background: var(--main3);
  color: var(--main1);
  font-size: 12px;
  font-weight: var(--medium);
  line-height: var(--line-height-sub);
  letter-spacing: var(--letter-spacing-sub);
}
.filter-seed__text:focus {
  width: 9ch;
  display: inline;
  padding: 10px 20px;
  border-radius: 8px;
  background: var(--main1);
  color: var(--main3);
  font-size: 12px;
  font-weight: var(--medium);
  line-height: var(--line-height-sub);
  letter-spacing: var(--letter-spacing-sub);
}
.filter-category-frame {
  margin-top: 18px;
  display: flex;
  overflow-x: auto;
  white-space: nowrap;
  scrollbar-width: none;
  gap: 8px;
}

.filter-category__bookmark,
.filter-category__seed {
  display: none;
  cursor: none;
}

.filter-category__bookmark__detail,
.filter-category__seed__detail {
  padding: 10px 12px;
  cursor: pointer;
  border-radius: 8px;
  border: 1px solid var(--filter-unchecked);
  color: var(--text-sub);
  font-size: 12px;
  font-weight: var(--bold);
  line-height: var(--line-height-main);
  letter-spacing: var(--letter-spacing-main);
}

.filter-category__bookmark__detail::after,
.filter-category__seed__detail::after {
  width: 7px;
  height: 7px;
  content: "";
  display: inline-block;
  align-items: center;
  background-image: url("/svg/hidden-off.svg");
  background-repeat: no-repeat;
  background-position: center;
  background-size: contain;
}

.filter-category__bookmark__list,
.filter-category__seed__list {
  padding: 10px 12px;
  cursor: pointer;
  display: none;
  background-color: var(--main3);
  color: var(--text-sub);
  font-size: 12px;
  font-weight: var(--bold);
  line-height: var(--line-height-main);
  letter-spacing: var(--letter-spacing-main);
  z-index: 1;
}
.filter-category__bookmark:checked ~ .filter-category__bookmark__list,
.filter-category__seed:checked ~ .filter-category__seed__list {
  display: block;
  position: absolute;
}

.filter-attribute__bg{
  margin: 10px 20px;
  padding-top: 20px;
  background-color: var(--main2--opacity40);
  border-radius: 8px;
}

.filter-attribute__title{
  margin-left: 20px;
  margin-bottom: 8px;
  font-size: 16px;
  font-weight: var(--bold);
  line-height: var(--line-height-main);
  letter-spacing: var(--letter-spacing-main);
}
.filter-attribute__item{
  width: 100%;
  display: flex;
  justify-content: space-between;
}
</style>