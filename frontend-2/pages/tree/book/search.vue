<script setup>
import { reactive, ref } from "vue";

//---------------variable----------------
const apiUrl = `${import.meta.env.VITE_API_BASE_URL}books`;
const keyword = ref("");
const books = ref([]);
const page = ref(1);
const totalPage = ref(0);
const totalCount = ref(0);
const limit = ref(10);
const scrollContainer = ref(null);
//-------------handler----------------

const handleBookResult = (data) => {
  books.value = data || [];
  // console.log(`length=${books.value.length}`);
};

const handleKeyword = (query) => {
  keyword.value = query;
  // console.log(`keyword=${keyword.value}`);
};

const handlePage = (p) => {
  page.value = p;
  // console.log(`page=${page.value}`);
};

const handleTotalPage = (totalP) => {
  totalPage.value = totalP;
  // console.log(`totalPages=${totalPage.value}`);
};

const handleTotalCount = (totalC) => {
  totalCount.value = totalC;
  // console.log(`totalCount=${totalCount.value}`);
};

const handleScroll = () => {
  const container = scrollContainer.value;
  const { scrollTop, clientHeight, scrollHeight } = container;
  if (scrollTop + clientHeight >= scrollHeight) {
    // console.log("scroll end");
    //최대 페이지까지만 증가
    if (parseInt(page.value) < parseInt(totalPage.value)) {
      page.value = parseInt(page.value) + 1;
      // console.log(`page=${page.value}`);
    }
  }
};

//-------------life cycle----------------
onMounted(() => {
  watch(
    [scrollContainer],
    () => {
      if (scrollContainer.value) {
        scrollContainer.value.addEventListener("scroll", handleScroll, {
          passive: false,
        });
      }
    },
    { immediate: true }
  );
});
</script>

<template>
  <header class="reg-book-search-container">
    <h1 class="none">도서 검색</h1>
    <section>
      <h2 class="none">도서 검색 창</h2>
      <InputBackSearch
        :placeholder="`검색할 도서를 입력해주세요.`"
        :href="`/tree/seed`"
        :api="apiUrl"
        :page="page"
        @bookResult="handleBookResult"
        @req="handleKeyword"
        @page="handlePage"
        @totalCount="handleTotalCount"
        @totalPage="handleTotalPage"
      />
    </section>

    <section>
      <h2 class="none">검색 결과 개수 및 최근 수정한 순서</h2>
      <TopBarSearchResultNum :num="totalCount" />
    </section>
  </header>

  <main>
    <section v-if="keyword === ''">
      <div class="text-info-container">
        <h2 class="none">검색 시작 안내</h2>
        <TextInfo
          :text="'현재 검색중인 도서가 없습니다'"
          :boldText="'도서를 검색하거나 직접 등록해주세요'"
        />
      </div>
    </section>

    <section class="tree-card-list" v-else-if="totalCount >= 1">
      <h3 class="none">도서 검색 결과</h3>
      <div class="scroll-container" ref="scrollContainer">
        <div v-for="book in books" :key="book.id">
          <CardBookPreviews :data="book" />
        </div>
      </div>
    </section>

    <section v-else="totalCount === 0">
      <h3 class="none">검색 결과 없음</h3>
      <div class="text-info-container">
        <TextInfo
          :text="'검색 결과가 없습니다.'"
          :boldText="'다시 검색하거나 직접 등록해주세요'"
        />
      </div>
    </section>
  </main>

  <section class="btn-select-container--right">
    <h2 class="none">도서 직접 등록 버튼</h2>
    <!-- TODO: href변경하기 -->
    <ButtonBtnShortAGreen
      :link="`/tree/book/new`"
      :text="`도서 직접 등록하기`"
    />
  </section>

  <div class="nav-back-container">
    <h2 class="none">네비게이션 뒤 공백</h2>
  </div>

  <aside class="nav-container">
    <NavNavigationBar active="home" />
  </aside>
</template>

<style scoped>
.scroll-container {
  display: flex;
  flex-direction: column;
  gap: 20px;
  overflow-y: auto;
  height: 480px;
}
</style>
