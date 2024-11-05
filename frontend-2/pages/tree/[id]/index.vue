<script setup >
import { ref,watchEffect } from "vue";

import axios from "axios";
import { useRoute } from "vue-router";
import CardHiddenInfo from "~/components/card/CardHiddenInfo.vue";
import CardTreeInfoCover from "~/components/card/CardTreeInfoCover.vue";
import CardBranchList from "~/components/card/CardBranchList.vue";
import TextMainTitle from "~/components/text/TextMainTitle.vue";
import FilterTreeLeafCard from "~/components/filter/FilterTreeLeafCard.vue";
import NavigationBar from "~/components/nav/NavigationBar.vue";
import InputBackSearch from "~/components/input/InputBackSearch.vue";

// -----------DOM 객체---------------------------------------

const treeId = Number(useRoute().params.id);
let filterQuery = 10;
let sortQuery = 1;
let filterName = ref('최근 수정 순');


const queryParam = reactive({
  filter: 10,
  sort: 1,
  bookMark: "",
  page: 0,
});

let info = reactive({});
let branch = reactive({
  items: [],
  totalCount: 0
});
const mergedBranch = ref({
  items: []
});

const config = useRuntimeConfig();

const isActive = ref(false);
let totalCnt = 0;

const openPopup = () => {
  const filterBack1 = document.getElementById("filter-bg");
  const filterBack2 = document.getElementById("filter-bg-blur");
  const filterTab = document.getElementById("filter-tab");

  filterBack1.classList.remove('none');
  filterBack2.classList.remove('none');

  setTimeout(() => {
    filterTab.classList.add('up');
  }, 30)
};

const closePopup = () => {
  const filterBack1 = document.getElementById("filter-bg");
  const filterBack2 = document.getElementById("filter-bg-blur");
  const filterTab = document.getElementById("filter-tab");
  filterTab.classList.remove('up');
  filterBack1.classList.add('none');
  filterBack2.classList.add('none');

  queryParam.filter = filterQuery;
  queryParam.sort = sortQuery;
  queryParam.page = 0;
}

const { data: infoData, error: infoError } = await useFetch(() => `trees/${treeId}/info`, {
  baseURL: config.public.apiBase,
});

const { data: branchData, error: branchError, refresh } = await useFetch(() => `trees/${treeId}/branches`, {
  baseURL: config.public.apiBase,
  params: queryParam,
});


const bookMarkHandler = (bookMark) => {
  queryParam.bookMark = bookMark;
  queryParam.page = 0;
};

const sortHandler = (e) => {
  sortQuery = e;
}

const filterNameHandler = (e) => {
  filterQuery = e;
  switch (e) {
    case 10:
      filterName.value = "최근 수정 순";
      break;
    case 11:
      filterName.value = "제목 순";
      break;
    case 12:
      filterName.value = "리프 수 순";
      break;
    case 13:
      filterName.value = "조회 수 순";
      break;
    case 14:
      filterName.value = "좋아요 수 순";
      break;
  }
}

const loadMore = () => {
  queryParam.page += 1;
};

watchEffect(() => {
  if (infoData.value) {
    Object.assign(info, infoData.value);
  }

  if (branchData.value) {
    // branch 객체 자체를 재할당하지 않고 속성만 업데이트
    branch.items = [...branchData.value.items];
    branch.totalCount = branchData.value.totalCount;

    // queryParam.page가 0이면 리스트 초기화, 그렇지 않으면 병합
    if (queryParam.page === 0) {
      mergedBranch.value.items = [...branch.items]; // 새 리스트로 덮어쓰기
    } else {
      mergedBranch.value.items.push(...branch.items); // 기존 리스트에 병합
    }

    // 중복 제거 (Set 사용)
    mergedBranch.value.items = [...new Set(mergedBranch.value.items.map(item => item.id))].map(id =>
        mergedBranch.value.items.find(item => item.id === id)
    );
  }
});

</script>


<!------------------------------------View---------------------------------------->

<template>
  <header>
    <h1 class="none">사용자 트리 세부정보</h1>
    <section class="reg-book-search-container">
      <h2 class="none">트리 검색</h2>

      <InputSearchWithBackBtn
        placeholder="검색할 트리를 입력해주세요"
        href="javascript:history.back()"
        api=""
        >트리 검색 상단 바</InputSearchWithBackBtn>
    </section>
  </header>
  <main>
    <section class="user-tree-info__container">
      <h2 class="none">트리 정보</h2>
        <CardTreeInfoCover :data="info">트리 정보</CardTreeInfoCover>
    </section>
    <section class="branch-tree-detail-container">
      <h2 class="none">트리 상세 설명</h2>

      <CardHiddenInfo :data ="info" >트리 상세 설명</CardHiddenInfo>
    </section>

    <section class="branch-list__container">
      <div>
      <TextMainTitleListCount
        :title="'브랜치 목록'"
        :number='branch.totalCount'
      >브랜치 목록</TextMainTitleListCount>
      </div>
      <section class="branch-filter-container">
        <h3 class="none">브랜치 필터</h3>

        <div>
        <FilterTreeLeafCard
            @popup="openPopup"
            @bookMark="bookMarkHandler"
            :filterName="filterName"
        ></FilterTreeLeafCard>
        </div>
      </section>

      <section class="branch-filter-result-list__container">
        <h3 class="none">브랜치 리스트</h3>
        <div id="card-branch__list-frame">
          <div>
          <CardBranchList
              :items="mergedBranch.items"
              :totalCnt="branch.totalCount"
              @loadMore="loadMore"
          ></CardBranchList>
          </div>
        </div>
      </section>
    </section>
    <section>
      <div  @click.prevent="closePopup" class="filter-tab-container none" id="filter-bg"></div>
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
          <h1 class="filter-tab__header--title">정렬 선택</h1>
        </div>

        <div class="filter-list-frame">
        <div class="filter-attribute__bg">
          <h2 class="filter-attribute__title">정렬 기준</h2>
            <ul id="filter-tab__list1">
              <li @click="filterNameHandler( 10)" class="filter-tab__item">
                <label class="filter-tab__item-label">
                  <input
                    class="filter-tab__item-radio"
                    type="radio"
                    name="filter"
                    value="10"
                    checked
                  />
                  <span class="filter-tab__item--label-text">최근 수정</span>
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
              <li @click="filterNameHandler(12)" class="filter-tab__item">
                <label class="filter-tab__item-label">
                  <input
                    class="filter-tab__item-radio"
                    type="radio"
                    name="filter"
                    value="12"
                  />
                  <span class="filter-tab__item--label-text">리프 수</span>
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
  </main>

  <section class="nav-back-container">
    <h2 class="none">네비바 뒤 공백</h2>
  </section>
  <aside>
    <section class="nav-container">
      <h2 class="none">네비게이션</h2>
      <div>
      <NavigationBar :active = "'home'" ></NavigationBar>
      </div>
    </section>
  </aside>
</template>

<style scoped>

.filter-tab-container--30 {
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
.filter-list-frame{
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
.filter-attribute__title{
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

.filter-tab__list{
  margin-bottom: 250px;
}

</style>
