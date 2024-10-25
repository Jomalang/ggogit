<script setup>
import InputBackSearch from "@/components/input/InputBackSearch.vue";
import TopbarSearchResultNum from "@/components/top-bar/TopbarSearchResultNum.vue";
import NavigationBar from "@/components/nav/NavigationBar.vue";
import TextInfo from "@/components/text/TextInfo.vue";
import CardBookPreviews from "@/components/card/CardBookPreviews.vue";
import BtnShortAGreen from "@/components/button/BtnShortAGreen.vue";

import { reactive, ref } from "vue";

//---------------variable----------------
const apiUrl = `${import.meta.env.VITE_API_BASE_URL}/api/v1/books`;
let keyword = ref("");
let size = ref(0);
const books = ref([]);
const page = ref(1);
//-------------handler----------------

const handleBookResult = (data) => {
  books.value = data.books || [];
  page.value = data.page || 1;
  size.value = books.value.length || 0;

  console.log(data.books);
  console.log(books.value);
  console.log(`length=${books.value.length}`);
};

const handleKeyword = (query) => {
  keyword.value = query;
  console.log(`keyword=${keyword.value}`);
};
</script>

<template>
  <header class="reg-book-search-container">
    <h1 class="none">도서 검색</h1>
    <section>
      <h2 class="none">도서 검색 창</h2>
      <InputBackSearch
        :placeholder="`검색할 도서를 입력해주세요.`"
        :href="`./seed/index`"
        :api="apiUrl"
        @bookResult="handleBookResult"
        @req="handleKeyword"
      />
    </section>

    <section>
      <h2 class="none">검색 결과 개수 및 최근 수정한 순서</h2>
      <TopbarSearchResultNum :num="size" />
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

    <section class="tree-card-list" v-else-if="size >= 1">
      <h3 class="none">도서 검색 결과</h3>
      <div v-for="book in books" :key="book.id">
        <CardBookPreviews :data="book" />
      </div>
    </section>

    <section v-else="size === 0">
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
    <BtnShortAGreen
      :href="`/tree/book/reg?auto=false`"
      :text="`도서 직접 등록하기`"
    />
  </section>

  <div class="nav-back-container">
    <h2 class="none">네비게이션 뒤 공백</h2>
  </div>

  <aside class="nav-container">
    <NavigationBar active="home" />
  </aside>
</template>

<style scoped></style>
