<script setup>
import { ref } from "vue";
import { value } from "lodash/seq.js";
import {useRouter} from "#vue-router";
//-----------------props-----------------
const props = defineProps({
  placeholder: "",
  href: "",
  api: "",
  sort: 0,
  page: 1,
});
//-----------------emit-----------------
const emit = defineEmits(["req", "result", "page", "totalCount", "totalPage"]);
//-----------------ref-----------------
const result = ref([]);
const query = ref("");
const page = ref(1);
const totalCount = ref(0);
const totalPage = ref(0);
const filter = ref("title");
const selectedPage = ref("/tree/search");
const router = useRouter();
//-----------------watcher-----------------
// page 값이 변경될 때 요청
watch(page, () => {
  if (page.value > 0) {
    createReq(props.sort, page.value, filter.value, false); // props.sort 사용
    // console.log("new page");
  }
});

// sort 값이 변경될 때 요청
watch(
  () => props.sort,
  (newSort) => {
    // console.log(`sort changed to ${newSort}`);

    // 페이지를 초기화하고 새로운 정렬 기준으로 요청
    page.value = 0;

    // 새로운 sort 값으로 fetch 요청
    createReq(newSort, page.value, filter.value, false);
  }
);

//-----------------methods-----------------
const createReq = async (sort, currentPage, filter, isChange) => {
  let queryContent = "";
  if (isChange) {
    queryContent = query.value;
  }
  try {
    const response = await useAuthDataFetch(props.api, {
      method: "GET",
      params: {
        query: queryContent,
        sort: sort,
        page: currentPage,
        filter: filter,
      },
    });

    if (response !== undefined) {
      // 페이지가 0이면 항상 result를 빈 배열로 초기화
      if (currentPage === 0) {
        result.value = [];
      }

      // console.log(response.content);

      // 새로운 데이터를 추가
      result.value = [...result.value, ...response.content];

      // 중복 제거
      result.value = [
        ...new Set(result.value.map((value) => value.treeId)),
      ].map((treeId) => result.value.find((value) => value.treeId === treeId));

      // 페이지 정보 업데이트
      page.value = currentPage;
      totalCount.value = response.totalElements;
      totalPage.value = response.totalPages;
    } else {
      // 응답이 없을 경우 빈 배열로 초기화
      result.value = [];
      totalCount.value = 0;
      totalPage.value = 1;
    }

    // 부모에게 결과 전달
    emit("result", result.value);
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
  }
};

const navigateToPage = () => {
  console.log(selectedPage.value);
  router.push(selectedPage.value);
};


const dropQueryHandler = () => {
  query.value = "";
};

//-----------------lifeCycle-----------------
onUpdated(() => {
  if (page.value < props.page) {
    page.value = props.page;
  }
});

const goBack = () => {
  useBackNavigation().popPageFromStack();
};
const { getLastPage } = useBackNavigation();
const lastPage = computed(() => getLastPage());
</script>

<template>
  <!-- input-back-search(placeholder, href, method, name) -->
  <div class="search__form">
    <div>
      <NuxtLink :to="lastPage" @click="goBack()">
        <img src="/public/svg/back.svg" alt="back button" />
      </NuxtLink>
    </div>
    <div class="search-bar">
      <label class="search-bar--label">
        <input
          class="search-bar--input"
          type="text"
          :placeholder="props.placeholder"
          v-model="query"
          autocomplete="off"
          @keyup.enter="createReq(sort, 0, filter, true)"
        />
        <button
          @click="dropQueryHandler"
          class="search-bar--close"
          type="reset"
        >
          <img src="/public/svg/close-button.svg" alt="close-btn" />
        </button>
      </label>
      <button @click="createReq(sort, 0, filter, true)">
        <img src="/public/svg/lens.svg" alt="lens" />
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
      <option class="search-filter-log__checkbox-input-text" value="/tree/book/search">도서</option>
      <option class="search-filter-log__checkbox-input-select" value="/tree/search">트리</option>
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

.search-bar--label{
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
.search-filter-frame {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.search-filter-log {
  margin-top: 18px;
  display: flex;
  white-space: nowrap;
  scrollbar-width: none;
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
