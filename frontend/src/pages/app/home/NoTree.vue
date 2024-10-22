<script setup></script>

<template>
  <header>
    <h1 class="none">나의 트리</h1>
    <section class="header-search-container">
      <h1 class="none">나의 트리 검색 링크</h1>
      <div th:replace="~{fragments/header :: header-search-link}"></div>
    </section>
  </header>

  <main>
    <section class="my-tree-container">
      <h1 class="none">나의 트리 정보</h1>

      <section class="my-tree-title-container">
        <h1
          th:replace="~{fragments/text :: text-main-title('나의 트리', 28)}"
        ></h1>
      </section>

      <section class="book-img-container">
        <h1 class="none">트리 이미지</h1>
        <div th:replace="~{fragments/background :: bg-no-tree-book}"></div>
      </section>
    </section>

    <section class="btn-full-width-link-container">
      <h1 class="none">트리 생성 버튼</h1>
      <div
        th:replace="~{fragments/button :: link-full-width(link='/seed/index', text='트리 생성')}"
      ></div>
    </section>
  </main>

  <section class="nav-back-container">
    <h2 class="none">네비 뒤 공백</h2>
  </section>

  <aside class="nav-container">
    <h1 class="none">네비게이션 하단</h1>
    <nav th:replace="~{fragments/nav :: navigation-bar(active='home')}"></nav>
  </aside>
</template>

<style></style>
