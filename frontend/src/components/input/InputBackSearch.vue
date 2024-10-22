<script setup lang="ts">
import axios from "axios";
import { onUpdated, ref } from "vue";
import SearchFilterRadio from "../filter/SearchFilterRadio.vue";
//-----------------props-----------------
const props = defineProps<{
  placeholder: string;
  href: string;
  api: string;
}>();
//-----------------emit-----------------
const emit = defineEmits<{
  (event: "req", query: string): void;
  (event: "bookResult", data: any): void;
}>();
//-----------------query-----------------
let query = ref<string>("");
let filter = ref<string>("title");

const createReq = async (query: string, filter: string): Promise<void> => {
  axios
    .get(props.api, {
      params: {
        q: query,
        f: filter,
      },
    })
    .then((response) => {
      emit("bookResult", response.data);
    })
    .catch((error) => {
      console.error("Error fetching data:", error);
    });
};

//-----------------lifeCycle-----------------
onUpdated(() => {
  console.log(query.value);
  console.log(filter.value);
});
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
          v-model="query"
          autocomplete="off"
        />
        <button class="search-bar--close" type="reset">
          <img src="/public/svg/close-button.svg" alt="close-btn" />
        </button>
      </label>
      <button @click="createReq(query, filter)">
        <img src="/public/svg/lens.svg" alt="lens" />
      </button>
    </div>
  </div>
  <!-- (description, name, value, isChecked) -->
  <div class="search-filter-log">
    <label class="search-filter-log__checkbox-labal">
      <input
        class="search-filter-log__checkbox-input"
        type="radio"
        name="filterType"
        value="title"
        checked
        v-model="filter"
      />
      <span class="search-filter-log__checkbox-input-text">제목</span>
    </label>
    <label class="search-filter-log__checkbox-labal">
      <input
        class="search-filter-log__checkbox-input"
        type="radio"
        name="filterType"
        value="author"
        v-model="filter"
      />
      <span class="search-filter-log__checkbox-input-text">저자</span>
    </label>
    <label class="search-filter-log__checkbox-labal">
      <input
        class="search-filter-log__checkbox-input"
        type="radio"
        name="filterType"
        value="publisher"
        v-model="filter"
      />
      <span class="search-filter-log__checkbox-input-text">출판사</span>
    </label>
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
.search-filter-log {
  margin-top: 18px;
  display: flex;
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
