<script setup lang="ts">
import axios from "axios";
import { ref } from "vue";
import SearchFilterRadio from "../filter/SearchFilterRadio.vue";

const query = ref<string>("");

const props = defineProps<{
  placeholder: string;
  href: string;
  api: string;
}>();

const emit = defineEmits<{
  (event: "query", query: string): void;
  (event: "data", data: any): void;
}>();

const EmitQuery = async (): Promise<void> => {
  try {
    const response = await axios.get(props.api, {
      params: {
        query: query.value,
      },
    });

    console.log(response.data);

    emit("query", query.value);
    emit("data", response.data);
  } catch (error) {
    console.error("Error fetching data:", error);
  }
};
</script>

<template>
  <!-- input-back-search(placeholder, href, method, name) -->
  <div class="search__form">
    <div>
      <a :href="href">
        <img src="/public/svg/back.svg" alt="back button" />
      </a>
    </div>
    <div class="search-bar">
      <label class="search-bar--label">
        <input
          class="search-bar--input"
          type="text"
          :placeholder="props.placeholder"
          :v-modle="query"
          autocomplete="off"
        />
        <button class="search-bar--close" type="reset">
          <img src="/public/svg/close-button.svg" alt="close-btn" />
        </button>
      </label>
      <button @click="EmitQuery()">
        <img src="/public/svg/lens.svg" alt="lens" />
      </button>
    </div>
  </div>
  <!-- (description, name, value, isChecked) -->
  <div class="filter-category-frame">
    <div class="search-filter-log">
      <label class="search-filter-log__checkbox-labal">
        <input
          class="search-filter-log__checkbox-input"
          type="radio"
          name="filterType"
          value="title"
        />
        <span class="search-filter-log__checkbox-input-text">제목</span>
      </label>
      <label class="search-filter-log__checkbox-labal">
        <input
          class="search-filter-log__checkbox-input"
          type="radio"
          name="filterType"
          value="author"
        />
        <span class="search-filter-log__checkbox-input-text">저자</span>
      </label>
      <label class="search-filter-log__checkbox-labal">
        <input
          class="search-filter-log__checkbox-input"
          type="radio"
          name="filterType"
          value="publisher"
        />
        <span class="search-filter-log__checkbox-input-text">출판사</span>
      </label>
    </div>
  </div>
</template>

<style scoped>
/* =================================
      input-back-search, input-search 
      검색버튼 뒤로가기버튼 있음. 없음.
   ===================================
 */
a {
  text-decoration: none;
}

button {
  border: none;
  background-color: transparent;
  cursor: pointer;
}

.search__form {
  display: flex;
  align-items: center;
}

.search-bar {
  background: var(--main2);
  border-radius: 20px;
  padding: 8px 16px 8px 16px;
  display: flex;
  align-items: center;
  justify-content: space-between;
  flex-grow: 1;
}

.search-bar--label {
  display: flex;
  flex-grow: 1;
  font-size: 14px;
}

.search-bar--input {
  width: 100%;
  font-weight: var(--semi-bold);
  font-size: 14px;
  background-color: var(--main2);
  border: none;
}

.search-bar--input:focus {
  outline: none;
}

/*  */
.search-bar--close {
  display: none;
}

.search-bar--input:not(:placeholder-shown) {
  font-weight: var(--semi-bold);
  font-size: 14px;
  background-color: var(--main2);
}

/*  */
.search-bar--input:not(:placeholder-shown) + .search-bar--close {
  display: inline;
  height: 18px;
}

/*  */
.search-bar-img {
  height: 18px;
}
/* 필터 */
.filter-category-frame {
  margin-top: 18px;
  display: flex;
  overflow-x: auto;
  white-space: nowrap;
  scrollbar-width: none;
  gap: 10px;
}
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
