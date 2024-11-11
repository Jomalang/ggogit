<script setup>
import { reactive, ref } from "vue";
import router from "#app/plugins/router.js";
const config = useRuntimeConfig();
//---------------variable----------------
const apiUrl = `${config.public.apiBase}/books`;
const keyword = ref("");
const books = ref([]);
const page = ref(1);
const totalPage = ref(0);
const totalCount = ref(0);
const limit = ref(10);
const searchLoading = ref(false);
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

const dropListHandler = () => {
  console.log("dropListHandler");
};

const searchLoadingPage = (isLoading) => {
  console.log(`searchLoadingPage=${isLoading}`);
  searchLoading.value = isLoading;
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

  <section v-if="searchLoading" class="search-blur-container">
    <h2 class="none">검색 블러 컨테이너</h2>
    <div class="blur-bg">
      <div class="info-box">
        <p>도서를 검색중입니다    <span class="dot">·</span><span class="dot">·</span><span class="dot">·</span></p>
      </div>
    </div>
  </section>

  <header class="reg-book-search-container">
    <h1 class="none">도서 검색</h1>
    <section>
      <h2 class="none">도서 검색 창</h2>
      <InputBackSearch
        :placeholder="`검색할 도서를 입력해주세요.`"
        :href="`./seed/index`"
        :api="apiUrl"
        :page="page"
        @dropListEvent="dropListHandler"
        @bookResult="handleBookResult"
        @req="handleKeyword"
        @page="handlePage"
        @totalCount="handleTotalCount"
        @totalPage="handleTotalPage"
        @loading="searchLoadingPage"
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

    <section v-else>
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
  height: 100%;
  flex-direction: column;
  gap: 20px;
  overflow-y: auto;
}

/* /home/tree/search/list.html */
.tree-card-list {
  display: flex;
  flex-direction: column;
  gap: 20px;
  margin-left: 24px;
  margin-right: 24px;
}

.tree-card-list__main {
  display: flex;
  flex-direction: column;
  gap: 20px;
  margin-top: 24px;
  margin-left: 24px;
  margin-right: 24px;
  align-items: center;
}

.tree-card-list::after {
  content: " ";
  display: block;
  width: 100%;
  height: 50px;
}

.blur-bg {
  z-index: 100;
  position: absolute;
  height: 100vh;
  width: 100vw;
  background-color: rgba(0, 0, 0, 0.6);

  .info-box {
    position: absolute;
    top: 300px;
    left: 50%;
    transform: translate(-50%, -50%);
    width: 250px;
    height: 100px;
    background-color: var(--main2);
    border-radius: 20px;
    display: flex;
    justify-content: center;
    align-items: center;

    p {
      font-size: 18px;
      font-weight: bold;
      text-align: center;

      .dot:nth-child(1) { --i: 0; }
      .dot:nth-child(2) { --i: 1; }
      .dot:nth-child(3) { --i: 2; }

      .dot {
        display: inline-block;
        animation: dot-blink 1s infinite;
        animation-delay: calc(0.1s * var(--i));
      }
    }
  }
}

@keyframes dot-blink {
  0% { transform: translateY(0); }
  25% { transform: translateY(-10px); }
  50% { transform: translateY(0); }
  75% { transform: translateY(10px); }
  100% { transform: translateY(0); }
}

</style>
