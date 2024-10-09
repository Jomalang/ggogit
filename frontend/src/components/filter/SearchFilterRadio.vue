<script setup lang="ts">

</script>

<template>
  <!-- (description, name, value, isChecked) -->
  <div class="search-filter-log">
    <label class="search-filter-log__checkbox-labal">
      <input
        class="search-filter-log__checkbox-input"
        type="radio"
        th:name="${name}"
        th:value="${value}"
        th:checked="${isChecked}"
      />
      <span class="search-filter-log__checkbox-input-text" th:text="${description}">리프 전체 검색</span>
    </label>
  </div>
</template>

<style scoped>
.search-filter {
  display: flex;
  justify-content: flex-start;
  gap: 10px;
}

.search-filter-log__checkbox-input {
  display: none;
}

.search-filter-log__checkbox-input-text {
  font-family: "Pretendard", serif;
  font-size: 12px;
  font-weight: var(--medium, 500);
  color: var(--text-sub, #767676);
  border-radius: 8px;
  background-color: #f7f7f7;
  padding: 12px 20px;
  cursor: pointer;
  user-select: none;
  flex-shrink: 0;
}

.search-filter-log__checkbox-input:checked
+ .search-filter-log__checkbox-input-text {
  background-color: var(--main1, #323a27);
  color: var(--white, #ffffff);
}

</style>