<script setup lang="ts">
import { onMounted, reactive } from "vue";

import axios from "axios";
import { useRoute } from "vue-router";
import type {BranchInfoProps} from "~/types/types";
import CardHiddenInfo from "~/components/card/CardHiddenInfo.vue";
import CardTreeInfoCover from "~/components/card/CardTreeInfoCover.vue";
import CardBranchList from "~/components/card/CardBranchList.vue";
import TextMainTitle from "~/components/text/TextMainTitle.vue";
import FilterTreeLeafCard from "~/components/filter/FilterTreeLeafCard.vue";
import NavigationBar from "~/components/nav/NavigationBar.vue";
import InputBackSearch from "~/components/input/InputBackSearch.vue";


const treeId: number = Number(useRoute().params.id);

export interface BranchInfoPage {
  items: BranchInfoProps[];
  total: number;
  page: number;
  size: number;
}

const dataLoaded = ref(false);
const data = reactive<{
  info: {
    coverImageName?: string;
    title?: string;
    bookTitle?: string;
    bookAuthor?: string;
    bookTranslator?: string;
    bookPublisher?: string;
    bookTotalPage?: number;
    seedId?: number;
    description?: string;
    readingPage?: number;
    treeLeafCnt?: number;
    treeLikeCnt?: number;
    treeViewCnt?: number;
  };
  list: BranchInfoPage;
}>({
  info: {
    coverImageName: '',
    title: '',
    bookTitle: '',
    bookAuthor: '',
    bookTranslator: '',
    bookPublisher: '',
    bookTotalPage: 0,
    seedId: 0,
    description: '',
    readingPage: 0,
    treeLeafCnt: 0,
    treeLikeCnt: 0,
    treeViewCnt: 0,
  },
  list:{
    items: [],
    total: 0,
    page: 0,
    size: 0,
  }
});

const queryParam = reactive({
  filter: 10,
  sort: 1,
  bookMark: "",
  p: 0,
});

const fetchInfo = async () => {
  try {
    const response = await axios.get(`http://localhost:8080/api/v1/trees/1/info`);
    // (`http://localhost:8080/api/v1/trees/${treeId}/branches?f=${queryParam.filter}&s=${queryParam.sort}&b=${queryParam.bookMark}&p=${queryParam.p}`);
    Object.assign(data.info, response.data);
    dataLoaded.value = true;
    console.log('dataLoaded:', dataLoaded.value);
    return data.info;
  } catch (error) {
    console.error("Error fetching data:", error);
  }
};

const fetchList = async () => {
  try {
    const response = await axios.get<BranchInfoProps[]>(
      `http://localhost:8080/api/v1/trees/1/branches`,{
          params: {
            f: queryParam.filter,
            s: queryParam.sort,
            b: queryParam.bookMark,
            p: queryParam.p
          }
        }
    );
    // (`http://localhost:8080/api/v1/trees/${treeId}/branches?f=${queryParam.filter}&s=${queryParam.sort}&b=${queryParam.bookMark}&p=${queryParam.p}`);
    Object.assign(data.list, response.data);
    return data.list;
  } catch (error) {
    console.error("Error fetching data:", error);
  }
};

onMounted(async () => {
  await fetchInfo();
  await fetchList();
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
      <div>
        <CardTreeInfoCover
        :data="{
            src: data.info.coverImageName ,
            treeTitle: data.info.title,
            bookTitle: data.info.bookTitle
        }"
        >트리 정보</CardTreeInfoCover>
      </div>
<!--        th:replace="~{fragments/card :: card-tree-info-cover(src=${data.info.coverImageName},treetitle=${data.info.title},booktitle=${data.info.bookTitle})}"-->
    </section>
    <section class="branch-tree-detail-container">
<!--      th:with="rPage=${data.info.readingPage ?: 0}, totalPage=${data.info.bookTotalPage ?: 1}"-->
      <h2 class="none">트리 상세 설명</h2>
      <div v-if="dataLoaded">
      <CardHiddenInfo
        :item="{
          hiddenText: '자세히',
          authors: data.info.bookAuthor,
          translators: data.info.bookTranslator,
          publisher: data.info.bookPublisher,
          page: data.info.bookTotalPage,
          seedId: data.info.seedId,
          treeDescription:
            data.info.description  ?
            data.info.description : '입력된 설명이 없습니다.',
          readPage: data.info.readingPage,
          progress:
            data.info.readingPage && data.info.bookTotalPage
              ? parseFloat(
                  (
                    (data.info.readingPage * 100.0) /
                    data.info.bookTotalPage
                  ).toFixed(1)
                )
              : 0,
          fullPage: data.info.bookTotalPage,
          leaf: data.info.treeLeafCnt,
          like: data.info.treeLikeCnt ,
          view: data.info.treeViewCnt
        }"
      ></CardHiddenInfo>
      </div>
      <div v-else>
        <p>로딩중...</p>
      </div>
    </section>
<!--      <div-->
<!--        th:replace="~{fragments/card :: card-hidden-info(-->
<!--        hiddentext='자세히',-->
<!--        authors=${data.info.bookAuthor},-->
<!--        translators=${data.info.bookTranslator},-->
<!--        publisher=${data.info.bookPublisher},-->
<!--        page=${totalPage},-->
<!--        seed=${data.info.seedId},-->
<!--        treedescription=${data.info.description},-->
<!--        readpage=${rPage},-->
<!--        progress=${#numbers.formatDecimal((rPage * 100.0 / totalPage), 1, 1)},-->
<!--        fullpage=${data.info.bookTotalPage},-->
<!--        leaf=${data.info.treeLeafCnt},-->
<!--        like=${data.info.treeLikeCnt},-->
<!--        view=${data.info.treeViewCnt}-->
<!--    )}"-->
<!--      ></div>-->

    <section class="branch-list__container">
      <div>
      <TextMainTitle
        :title="'브랜치 목록'"
        :number="data.list.items.length"
        >브랜치 목록</TextMainTitle>
      </div>
<!--      <h2-->
<!--        th:replace="~{fragments/text :: text-main-title__listCount(title='브랜치 목록', number=${#lists.size(leafList)})}"-->
<!--      ></h2>-->
      <section class="branch-filter-container">
        <h3 class="none">브랜치 필터</h3>

        <div>
        <FilterTreeLeafCard></FilterTreeLeafCard>
        </div>
<!--        <div th:replace="~{fragments/filter :: filter-tree-leaf__card()}"></div>-->
      </section>

      <section class="branch-filter-result-list__container">
        <h3 class="none">브랜치 리스트</h3>
        <div id="card-branch__list-frame">
          <div>
          <CardBranchList :items="data.list.items"></CardBranchList>
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
