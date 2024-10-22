<script setup>
import HeaderSearchLink from "@/components/header/HeaderSearchLink.vue";
import TextMainTitle from "@/components/text/TextMainTitle.vue";
import BgNoTreeBook from "@/components/background/BgNoTreeBook.vue";
import LinkFullWidth from "@/components/button/LinkFullWidth.vue";
import NavigationBar from "@/components/nav/NavigationBar.vue";
</script>

<template>
  <header>
    <h1 class="none">나의 트리</h1>
    <section class="header-search-container">
      <h1 class="none">나의 트리 검색 링크</h1>
      <HeaderSearchLink />
    </section>
  </header>

  <main>
    <section class="my-tree-container">
      <h1 class="none">나의 트리 정보</h1>

      <section class="my-tree-title-container">
        <TextMainTitle :title="`나의 트리`" :size="28" />
      </section>

      <section class="book-img-container">
        <h1 class="none">트리 이미지</h1>
        <BgNoTreeBook />
      </section>
    </section>

    <section class="btn-full-width-link-container">
      <LinkFullWidth :link="`/seed/index`" :text="`트리 생성`" />
    </section>
  </main>

  <section class="nav-back-container">
    <h2 class="none">네비 뒤 공백</h2>
  </section>

  <aside class="nav-container">
    <h1 class="none">네비게이션 하단</h1>
    <NavigationBar :active="home" />
  </aside>
</template>

<style></style>
