<script setup>
import { ref } from "vue";
import { useRouter} from "vue-router";
import useBackNavigation from "~/composables/useBackNavigation.js";

//-----------------props-----------------
const props = defineProps({
  placeholder: "",
  href: "",
  api: "",
  page: 1,
});
//-----------------emit-----------------
const emit = defineEmits([
  "req",
  "bookResult",
  "page",
  "totalCount",
  "totalPage",
  "dropListEvent",
  "loading",
]);

//-----------------ref-----------------
const bookResult = ref([]);
const query = ref("");
const filter = ref("title");
const page = ref(1);
const totalCount = ref(0);
const totalPage = ref(0);
const selectedPage = ref("/tree/book/search");
const router = useRouter();

watch(page, () => {
  if (page.value > 1) {
    createReq(query.value, filter.value, page.value);
    // console.log("new page");
  }
});

const createReq = async (query, filter, currentPage) => {
  try {
    emit("loading", true); // Emit loading event
    const response = await useAuthDataFetch(props.api, {
      method: "GET",
      params: {
        q: query,
        f: filter,
        p: currentPage,
      },
    });
    if (response !== undefined) {
      if (currentPage === 1) {
        bookResult.value = response.books;
      } else {
        bookResult.value = [...bookResult.value, ...response.books];
      }
      page.value = currentPage;
      totalCount.value = response.totalCount;
      totalPage.value = response.totalPage;
    } else {
      bookResult.value = [];
      totalCount.value = 0;
      totalPage.value = 1;
    }
    emit("bookResult", bookResult.value);
    emit("req", query);
    emit("page", page.value);
    emit("totalCount", totalCount.value);
    emit("totalPage", totalPage.value);
  } catch (error) {
    if (error.response && error.response.status === 400) {
      alert("검색어를 두 글자 이상 입력해 주세요.");
    } else {
      alert("오류가 발생했습니다. 다시 시도해 주세요.");
    }
  } finally {
    emit("loading", false); // Emit loading event
  }
};

const dropListHandler = () => {
  query.value = "";
  emit("dropListEvent");
};

const navigateToPage = () => {
  console.log(selectedPage.value);
  router.push(selectedPage.value);
};

//-----------------lifeCycle-----------------
onUpdated(() => {
  if (page.value < props.page) {
    page.value = props.page;
  }
});
</script>

<template>
  <div class="search__form">
    <div>
      <div @click.prevent="useGoBack()">
        <img src="~/assets/svg/back.svg" alt="back button" />
      </div>
    </div>
    <div class="search-bar">
      <label class="search-bar--label">
        <input
          class="search-bar--input"
          type="text"
          :placeholder="props.placeholder"
          v-model="query"
          autocomplete="off"
          @keyup.enter="createReq(query, filter, 1)"
        />
        <button @click="dropListHandler" class="search-bar--close" type="reset">
          <img src="~/assets/svg/close-button.svg" alt="close-btn" />
        </button>
      </label>
      <button @click="createReq(query, filter, 1)">
        <img src="~/assets/svg/lens.svg" alt="lens" />
      </button>
    </div>
  </div>

  <div class="search-filter-frame">
    <div class="search-filter-log">
      <label>
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
      <label>
        <input
          class="search-filter-log__checkbox-input"
          type="radio"
          name="filterType"
          value="author"
          v-model="filter"
        />
        <span class="search-filter-log__checkbox-input-text">저자</span>
      </label>
      <label>
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
    <div class="search-filter-log">
      <select class="search-filter-log__checkbox-input-select" v-model="selectedPage" @change="navigateToPage">
        <option class="search-filter-log__checkbox-input-select" value="/tree/book/search">도서</option>
        <option class="search-filter-log__checkbox-input-text" value="/tree/search">트리</option>
        <option class="search-filter-log__checkbox-input-text" value="/leaf/search">리프</option>
      </select>
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
.search-filter-log__checkbox-input-select {
  font-family: "Pretendard", serif;
  font-size: 12px;
  font-weight: var(--medium, 500);
  border-radius: 8px;
  border: none;
  background-color: var(--main1, #323a27);
  color: var(--white, #ffffff);
  padding: 12px 12px;
  cursor: pointer;
  user-select: none;
  flex-shrink: 0;
}
.search-filter-frame {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.search-filter-log__checkbox-input:checked
  + .search-filter-log__checkbox-input-text {
  background-color: var(--main1, #323a27);
  color: var(--white, #ffffff);
}

</style>
