<script setup>
import { reactive, ref } from "vue";
import InputBackSearchTree from "~/components/input/InputBackSearchTree.vue";
import { debounce } from "lodash";

//---------------variable----------------
const query = ref("");
const trees = ref([]);
const page = ref(0);
const totalPage = ref(0);
const totalCount = ref(0);
const scrollContainer = ref(null);
const config = useRuntimeConfig();
const apiUrl = config.public.apiBase + "/trees/search";
let filterName = "최근 수정한 순";
let sort = ref(0);
//-------------handler----------------

const handleTreeResult = (data) => {
  trees.value = data || [];
  // console.log(`length=${trees.value.length}`);
};

const handleKeyword = (words) => {
  query.value = words;
  // console.log(`query=${query.value}`);
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

const handleScroll = debounce(() => {
  const container = scrollContainer.value;
  const { scrollTop, clientHeight, scrollHeight } = container;

  if (scrollTop + clientHeight >= scrollHeight) {
    // console.log("scroll end");

    // 최대 페이지까지만 증가
    if (parseInt(page.value) < parseInt(totalPage.value)) {
      page.value = parseInt(page.value) + 1;
      // console.log(`page=${page.value}`);
    }
  }
}, 300); // 디바운스 적용으로 스크롤 이벤트 과도한 호출 방지
const sortHandler = debounce(() => {
  if (trees.value.length === 0) return;
  page.value = 0;
  if (sort.value === 0) sort.value = 1;
  else sort.value = 0;
});

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
    <h1 class="none">트리 검색</h1>
    <section>
      <h2 class="none">트리 검색 창</h2>
      <InputBackSearchTree
        :placeholder="`검색할 트리를 입력해주세요.`"
        :href="`./seed/index`"
        :api="apiUrl"
        :page="page"
        :sort="sort"
        @result="handleTreeResult"
        @req="handleKeyword"
        @page="handlePage"
        @totalCount="handleTotalCount"
        @totalPage="handleTotalPage"
      />
    </section>

    <section>
      <h2 class="none">검색 결과 개수 및 최근 수정한 순서</h2>
      <TopBarSearchResultNumAndFilter
        :num="totalCount"
        :filterName="filterName"
        @sort="sortHandler"
      />
    </section>
  </header>

  <main>
    <section v-if="query === ''">
      <div class="text-info-container">
        <h2 class="none">검색 시작 안내</h2>
        <TextInfo
          :text="'현재 검색중인 트리가 없습니다'"
          :boldText="'트리를 검색하거나 직접 등록해주세요'"
        />
      </div>
    </section>

    <section class="tree-card-list" v-else-if="totalCount >= 1">
      <h3 class="none">트리 검색 결과</h3>
      <div class="scroll-container" ref="scrollContainer">
        <div v-for="tree in trees" :key="tree.treeId">
          <CardTreePreviews :data="tree" />
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
  <aside class="nav-container">
    <NavNavigationBar active="home" />
  </aside>
</template>

<style scoped>
.scroll-container {
  display: flex;
  margin-bottom: 10px;
  margin-left: 24px;
  margin-right: 26px;
  flex-direction: column;
  gap: 20px;
  overflow-y: auto;
  height: 630px;
}
</style>
