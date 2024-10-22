<script setup>
import InputBackSearch from "@/components/input/InputBackSearch.vue";
import TopbarSearchResultNum from "@/components/top-bar/TopbarSearchResultNum.vue";
import NavigationBar from "@/components/nav/NavigationBar.vue";
import { ref } from "vue";
const apiUrl = `${import.meta.env.VITE_API_BASE_URL}/api/v1/books`;

//-------------handler----------------
const handleBookResult = (data) => {
  console.log(data);
};
</script>

<template>
  <header class="reg-book-search-container">
    <h1 class="none">나의 트리</h1>
    <section>
      <h2 class="none">트리 검색</h2>
      <!-- 컴포넌트 start -->
      <InputBackSearch
        :placeholder="`검색할 트리를 입력해주세요.`"
        :href="`./seed/index`"
        :api="apiUrl"
        @bookResult="handleBookResult"
      />
    </section>

    <section>
      <h2 class="none">검색 결과 개수 및 최근 수정한 순서</h2>
      <TopbarSearchResultNum :size="1" />
    </section>
  </header>

  <main>
    <h2 class="none">트리 검색 결과</h2>
    <section class="tree-card-list">
      <h3 class="none">트리 검색 리스트</h3>
      <div th:each="tree : *{values}">
        <div
          th:replace="~{fragments/card::card-tree-details(tree = ${tree})}"
        ></div>
      </div>
    </section>
    <section>
      <h3 class="none">검색 결과 없음</h3>
      <div
        class="margin-bottom500"
        th:if="*{size} eq 0"
        th:insert="~{fragments/text::text-info(text = '검색 결과가 없습니다.', boldText='다시 검색해 주세요' )}"
      ></div>
    </section>
  </main>

  <div class="nav-back-container">
    <h2 class="none">네비게이션 뒤 공백</h2>
  </div>

  <aside class="nav-container">
    <NavigationBar :active="home" />
  </aside>
</template>

<style scoped></style>
