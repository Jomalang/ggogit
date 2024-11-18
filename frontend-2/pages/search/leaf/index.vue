<script setup>
import { reactive, ref } from "vue";
import InputBackSearchTree from "~/components/input/InputBackSearchTree.vue";
import { debounce } from "lodash";

//---------------variable----------------
const query = ref("");
const leaves = ref([]);
const page = ref(0);
const totalPage = ref(0);
const totalCount = ref(0);
const scrollContainer = ref(null);
const config = useRuntimeConfig();
const apiUrl = config.public.apiBase + "/leaves/search";
let filterName = ref("최근 수정한 순");
let sort = ref(0);
//-------------handler----------------

const handleTreeResult = (data) => {
  leaves.value = data || [];

  // console.log(data);
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
const handleFilterName = (filter) => {
  filterName.value = filter;
  // console.log(`filterName=${filterName.value}`);
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
  if(leaves.value.length === 0) return;
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
    <h1 class="none">리프 검색</h1>
    <section>
      <h2 class="none">리프 검색 창</h2>
      <InputBackSearchNavLeaf
        :placeholder="`검색할 리프를 입력해주세요.`"
        :href="`./seed/index`"
        :api="apiUrl"
        :page="page"
        :sort="sort"
        @result="handleTreeResult"
        @req="handleKeyword"
        @page="handlePage"
        @totalCount="handleTotalCount"
        @totalPage="handleTotalPage"
        @filterName="handleFilterName"
      />
    </section>

    <section>
      <h2 class="none">검색 결과 개수 및 최근 수정한 순서</h2>
      <TopBarSearchResultNumAndFilter :num="totalCount" :filterName="filterName"/>
    </section>
  </header>

  <main>
    <section v-if="query === ''">
      <div class="text-info-container">
        <h2 class="none">검색 시작 안내</h2>
        <TextInfo
            :text="''"
            :boldText="'현재 검색중인 리프가 없습니다 :)'"
        />
      </div>
    </section>

    <section class="tree-card-list" v-else-if="totalCount >= 1">
      <h3 class="none">리프 검색 결과</h3>
      <div class="scroll-container" ref="scrollContainer">
        <div v-for="leaf in leaves" :key="leaf.treeId">
          <CardLeafPreviews :data="leaf" />
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
.reg-book-search-container {
  display: flex;
  flex-direction: column;
  gap: 10px;
  padding: 30px 26px 24px 24px;
}
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
</style>
