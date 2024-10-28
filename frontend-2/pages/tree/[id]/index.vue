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


const treeId = Number(useRoute().params.id);

const queryParam = reactive({
  filter: 10,
  sort: 1,
  bookMark: "",
  p: 0,
});

let info = reactive({});
let branch = reactive({
  items: [],
  totalCnt: 0,
  totalPage: 0
});

const config = useRuntimeConfig();

const { data: infoData, error: infoError } = useFetch(() => `trees/${treeId}/info`, {
  baseURL: config.public.apiBase,
});

const { data: branchData, error: branchError } = useFetch(() => `trees/${treeId}/branches`, {
  baseURL: config.public.apiBase,
  params: {
    f: queryParam.filter,
    s: queryParam.sort,
    b: queryParam.bookMark,
    p: queryParam.p,
  },
});

let filterName = "최근 수정 순";

watchEffect(() => {
  if (infoData.value) {
    info = infoData.value;
  }
  if(branchData.value) {
    branch = branchData.value;
  }
});

</script>


<!------------------------------------View---------------------------------------->

<template>
  <header>
    <h1 class="none">사용자 트리 세부정보</h1>
    <section class="reg-book-search-container">
      <h2 class="none">트리 검색</h2>

      <InputBackSearch
        placeholder="검색할 트리를 입력해주세요"
        href="javascript:history.back()"
        api=""
        >트리 검색 상단 바</InputBackSearch>
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
        :number='branch.totalCnt'
      >브랜치 목록</TextMainTitleListCount>
      </div>
<!--      <h2-->
<!--        th:replace="~{fragments/text :: text-main-title__listCount(title='브랜치 목록', number=${#lists.size(leafList)})}"-->
<!--      ></h2>-->
      <section class="branch-filter-container">
        <h3 class="none">브랜치 필터</h3>

        <div>
        <FilterTreeLeafCard :filterName="filterName"></FilterTreeLeafCard>
        </div>
<!--        <div th:replace="~{fragments/filter :: filter-tree-leaf__card()}"></div>-->
      </section>

      <section class="branch-filter-result-list__container">
        <h3 class="none">브랜치 리스트</h3>
        <div id="card-branch__list-frame">
          <div>
          <CardBranchList :items="branch.items.content"></CardBranchList>
          </div>
<!--          <div-->
<!--            th:replace="~{fragments/card :: card-branch__list(${leafList})}"-->
<!--          ></div>-->
        </div>
      </section>
    </section>
    <section class="filter-tab-container--30 none" id="filter-bg-blur">
      <h2 class="none">정렬 선택</h2>
      <div id="filter-tab" class="filter-tab__box--30">
        <div class="filter-tab__header">
          <button
            id="filter-tab-close-btn"
            class="filter-tab__btn--back"
            type="button"
          >
            <img src="/public/svg/tab-back.svg" alt="뒤로가기 버튼" />
          </button>
          <h1 class="filter-tab__header--title">정렬 선택</h1>
        </div>

        <div class="filter-attribute__bg">
          <h2 class="filter-attribute__title">정렬 기준</h2>
          <ul class="filter-tab__list" id="filter-tab__list1">
            <li class="filter-tab__item">
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
            <li class="filter-tab__item">
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
            <li class="filter-tab__item">
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
            <li class="filter-tab__item">
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
          </ul>
        </div>
        <div class="filter-attribute__bg">
          <h2 class="filter-attribute__title">정렬 순서</h2>
          <ul class="filter-tab__list" id="filter-tab__list2">
            <li class="filter-tab__item">
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
            <li class="filter-tab__item">
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
<!--      <div th:replace="~{fragments/nav :: navigation-bar(active='home')}"></div>-->
    </section>
  </aside>
</template>

<style scoped></style>
