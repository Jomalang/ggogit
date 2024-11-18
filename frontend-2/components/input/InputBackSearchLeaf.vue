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
const emit = defineEmits([
  "req",
  "result",
  "page",
  "totalCount",
  "totalPage",
  "filterName",
]);
//-----------------filter-----------------
let filterQuery = 10;
let sortQuery = 0;
let searchQuery = "title";

//-----------------ref-----------------
const result = ref([]);
const query = ref("");
const page = ref(1);
const totalCount = ref(0);
const totalPage = ref(0);
const filterName = ref("최근 수정 순");
const searchFilterName = ref("제목 검색");
const filter = ref(10);
const searchFilter = ref("title");
const sort = ref(0);
const selectedPage = ref("/leaf/search");
const router = useRouter();
//-----------------watcher-----------------
// page 값이 변경될 때 요청
watch(page, () => {
  if (page.value > 0) {
    createReq(props.sort, page.value, searchFilter.value, filterQuery, false); // props.sort 사용
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
    createReq(newSort, page.value, searchFilter.value, filterQuery, false);
  }
);

//-----------------methods-----------------
const createReq = async (sort, currentPage, searchFilter, filter, isChange) => {
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
        searchFilter: searchFilter,
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
        ...new Set(result.value.map((value) => value.leafId)),
      ].map((leafId) => result.value.find((value) => value.leafId === leafId));

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

    // console.log(result.value);
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

const openPopup = () => {
  const filterBack1 = document.getElementById("filter-bg");
  const filterBack2 = document.getElementById("filter-bg-blur");
  const filterTab = document.getElementById("filter-tab");

  filterBack1.classList.remove("none");
  filterBack2.classList.remove("none");

  setTimeout(() => {
    filterTab.classList.add("up");
  }, 30);
};

const closePopup = () => {
  const filterBack1 = document.getElementById("filter-bg");
  const filterBack2 = document.getElementById("filter-bg-blur");
  const filterTab = document.getElementById("filter-tab");
  filterTab.classList.remove("up");
  filterBack1.classList.add("none");
  filterBack2.classList.add("none");

  searchFilter.value = searchQuery;
  sort.value = sortQuery;
  page.value = 0;

  if (query.value !== "") {
    createReq(sort.value, page.value, searchFilter.value, filterQuery, false);
  }
  emit("filterName", filterName.value);
};

const searchFilterHandler = (e) => {
  searchQuery = e;
  switch (e) {
    case "title":
      searchFilterName.value = "제목 검색";
      break;
    case "content":
      searchFilterName.value = "내용 검색";
      break;
    case "all":
      searchFilterName.value = "제목 + 내용 검색";
      break;
  }
};

const filterNameHandler = (e) => {
  filterQuery = e;
  switch (e) {
    case 10:
      filterName.value = "최근 수정 순";
      break;
    case 11:
      filterName.value = "제목 순";
      break;
    case 13:
      filterName.value = "조회 수 순";
      break;
    case 14:
      filterName.value = "좋아요 수 순";
      break;
  }
};
const sortHandler = (e) => {
  sortQuery = e;
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
          @keyup.enter="createReq(sort, 0, searchFilter, filter, true)"
        />
        <button
          @click="dropQueryHandler"
          class="search-bar--close"
          type="reset"
        >
          <img src="/public/svg/close-button.svg" alt="close-btn" />
        </button>
      </label>
      <button @click="createReq(sort, 0, searchFilter, filter, true)">
        <img src="/public/svg/lens.svg" alt="lens" />
      </button>
    </div>
  </div>

  <div class="search-filter-frame">
    <div class="search-filter-log">
      <span
        @click.prevent="openPopup"
        class="search-filter-log__checkbox-input-img"
      >
        <img class="" src="/svg/sort-white.svg" />
      </span>
      <span
        @click.prevent="openPopup"
        class="search-filter-log__checkbox-input-text"
      >
        <p>{{ searchFilterName }}</p>
      </span>
    </div>


    <div class="search-filter-log">
      <select class="search-filter-log__checkbox-input-select" v-model="selectedPage" @change="navigateToPage">
        <option class="search-filter-log__checkbox-input-text" value="/tree/book/search">도서</option>
        <option class="search-filter-log__checkbox-input-text" value="/tree/search">트리</option>
        <option class="search-filter-log__checkbox-input-select" value="/leaf/search">리프</option>
      </select>
    </div>
  </div>
  <section>
    <div
      @click.prevent="closePopup"
      class="filter-tab-container none"
      id="filter-bg"
    ></div>
    <div class="filter-tab-container--30 none" id="filter-bg-blur">
      <h2 class="none">정렬 선택</h2>
      <div id="filter-tab" class="filter-tab__box--30">
        <div class="filter-tab__header">
          <button
            @click.prevent="closePopup"
            id="filter-tab-close-btn"
            class="filter-tab__btn--back"
            type="button"
          >
            <img src="/public/svg/tab-back.svg" alt="뒤로가기 버튼" />
          </button>
          <h1 class="filter-tab__header--title">필터 선택</h1>
        </div>

        <div class="filter-list-frame">
          <div class="filter-attribute__bg">
            <h2 class="filter-attribute__title">검색 기준</h2>
            <ul id="filter-tab__list1" class="filter-tab__list1">
              <li
                @click="searchFilterHandler('title')"
                class="filter-tab__item"
              >
                <label class="filter-tab__item-label">
                  <input
                    class="filter-tab__item-radio"
                    type="radio"
                    name="searchFilter"
                    value="10"
                    checked
                  />
                  <span class="filter-tab__item--label-text">제목 검색</span>
                  <div class="filter-tab__icon-box">
                    <img
                      class="filter-tab__icon-img"
                      src="/svg/tab-check-btn.svg"
                      alt="필터 버튼"
                    />
                  </div>
                </label>
              </li>
              <li
                @click="searchFilterHandler('content')"
                class="filter-tab__item"
              >
                <label class="filter-tab__item-label">
                  <input
                    class="filter-tab__item-radio"
                    type="radio"
                    name="searchFilter"
                    value="11"
                  />
                  <span class="filter-tab__item--label-text">내용 검색</span>
                  <div class="filter-tab__icon-box">
                    <img
                      class="filter-tab__icon-img"
                      src="/svg/tab-check-btn.svg"
                      alt="필터 버튼"
                    />
                  </div>
                </label>
              </li>
              <li @click="searchFilterHandler('all')" class="filter-tab__item">
                <label class="filter-tab__item-label">
                  <input
                    class="filter-tab__item-radio"
                    type="radio"
                    name="searchFilter"
                    value="12"
                  />
                  <span class="filter-tab__item--label-text"
                    >제목 + 내용 검색</span
                  >
                  <div class="filter-tab__icon-box">
                    <img
                      class="filter-tab__icon-img"
                      src="/svg/tab-check-btn.svg"
                      alt="필터 버튼"
                    />
                  </div>
                </label>
              </li>
            </ul>
            <h2 class="filter-attribute__title">정렬 기준</h2>
            <ul id="filter-tab__list1" class="filter-tab__list1">
              <li @click="filterNameHandler(10)" class="filter-tab__item">
                <label class="filter-tab__item-label">
                  <input
                    class="filter-tab__item-radio"
                    type="radio"
                    name="filter"
                    value="10"
                    checked
                  />
                  <span class="filter-tab__item--label-text"> 최근 수정 </span>
                  <div class="filter-tab__icon-box">
                    <img
                      class="filter-tab__icon-img"
                      src="/svg/tab-check-btn.svg"
                      alt="필터 버튼"
                    />
                  </div>
                </label>
              </li>
              <li @click="filterNameHandler(11)" class="filter-tab__item">
                <label class="filter-tab__item-label">
                  <input
                    class="filter-tab__item-radio"
                    type="radio"
                    name="filter"
                    value="11"
                  />
                  <span class="filter-tab__item--label-text">제목</span>
                  <div class="filter-tab__icon-box">
                    <img
                      class="filter-tab__icon-img"
                      src="/svg/tab-check-btn.svg"
                      alt="필터 버튼"
                    />
                  </div>
                </label>
              </li>
              <li @click="filterNameHandler(13)" class="filter-tab__item">
                <label class="filter-tab__item-label">
                  <input
                    class="filter-tab__item-radio"
                    type="radio"
                    name="filter"
                    value="13"
                  />
                  <span class="filter-tab__item--label-text">조회 수</span>
                  <div class="filter-tab__icon-box">
                    <img
                      class="filter-tab__icon-img"
                      src="/svg/tab-check-btn.svg"
                      alt="필터 버튼"
                    />
                  </div>
                </label>
              </li>
              <li @click="filterNameHandler(14)" class="filter-tab__item">
                <label class="filter-tab__item-label">
                  <input
                    class="filter-tab__item-radio"
                    type="radio"
                    name="filter"
                    value="13"
                  />
                  <span class="filter-tab__item--label-text">좋아요 수</span>
                  <div class="filter-tab__icon-box">
                    <img
                      class="filter-tab__icon-img"
                      src="/svg/tab-check-btn.svg"
                      alt="필터 버튼"
                    />
                  </div>
                </label>
              </li>
            </ul>
            <div class="filter-attribute__bg">
              <h2 class="filter-attribute__title">정렬 순서</h2>
              <ul class="filter-tab__list" id="filter-tab__list2">
                <li @click="sortHandler(1)" class="filter-tab__item">
                  <label class="filter-tab__item-label">
                    <input
                      class="filter-tab__item-radio"
                      type="radio"
                      name="sort"
                      value="1"
                      checked
                    />
                    <span class="filter-tab__item--label-text">내림차순</span>
                    <div class="filter-tab__icon-box">
                      <img
                        class="filter-tab__icon-img"
                        src="/svg/tab-check-btn.svg"
                        alt="필터 버튼"
                      />
                    </div>
                  </label>
                </li>
                <li @click="sortHandler(0)" class="filter-tab__item">
                  <label class="filter-tab__item-label">
                    <input
                      class="filter-tab__item-radio"
                      type="radio"
                      name="sort"
                      value="0"
                    />
                    <span class="filter-tab__item--label-text">오름차순</span>
                    <div class="filter-tab__icon-box">
                      <img
                        class="filter-tab__icon-img"
                        src="/svg/tab-check-btn.svg"
                        alt="필터 버튼"
                      />
                    </div>
                  </label>
                </li>
              </ul>
            </div>
          </div>
        </div>
      </div>
    </div>
  </section>
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
.search-filter-frame {
  margin-top: 18px;
  margin-bottom: 14px;
  display: flex;
  justify-content: space-between;
  align-items: center;
}
.search-filter-frame-search {
  display: flex;
  gap: 10px;
  align-items: center;
}

.search-filter-log {
  display: flex;
  white-space: nowrap;
  scrollbar-width: none;
  gap: 10px;
}

.search-filter-log__checkbox-input-img {
  border-radius: 8px;
  background-color: var(--main1, #323a27);
  padding: 9px 9px;
  cursor: pointer;
  user-select: none;
  flex-shrink: 0;
  align-items: center;
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
.filter-tab-container--30 {
  max-width: var(--max-width-1);
  margin: 0 auto;
  top: 30%;
  right: 0;
  bottom: 0;
  left: 0;
  z-index: 10;
  position: fixed;
  aspect-ratio: 5 / 8;
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

.filter-tab__box--30.up {
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
.filter-list-frame {
  height: 100%;
  overflow: auto;
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
  margin: 6px 24px;
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
.filter-attribute__title {
  margin-left: 20px;
  margin-bottom: 8px;
  font-size: 16px;
  font-weight: var(--bold);
  line-height: var(--line-height-main);
  letter-spacing: var(--letter-spacing-main);
}
.filter-tab__icon-img {
  display: none;
}

.filter-tab__item-radio:checked ~ .filter-tab__icon-box .filter-tab__icon-img {
  display: block;
}

.filter-tab__list {
  padding-right: 10px;
  padding-left: 10px;
  margin-bottom: 250px;
}
.filter-tab__list1 {
  padding-right: 10px;
  padding-left: 10px;
  border-bottom: solid 1px var(--main2--opacity40);
  margin-bottom: 20px;
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
</style>
