<script setup lang="ts">

import NavigationBar from "@/components/nav/NavigationBar.vue";
import FilterTabGet from "@/components/tab-filter/FilterTabGet.vue";
import FilterFullWidth from "@/components/button/FilterFullWidth.vue";
import TextInfo from "@/components/text/TextInfo.vue";
import TopBarBack from "@/components/top-bar/TopBarBack.vue";

</script>

<template>
  <header class="top-bar-back-container">
    <h1 class="none">씨앗 선택</h1>
    <TopBarBack title="트리 생성" link=""></TopBarBack>
  </header>

  <main>
    <section class="text-info-container">
      <h2 class="none">트리 생성 안내</h2>
      <TextInfo></TextInfo>
    </section>

    <section class="btn-select-container">
      <h2 class="none">씨앗 선택 버튼</h2>
      <FilterFullWidth link="" text="씨앗 선택"></FilterFullWidth>
    </section>

    <section class="filter-tab-container none" id="filter-bg-blur">
      <h2 class="none">씨앗 선택</h2>
      <FilterTabGet data="seeds"></FilterTabGet>
    </section>
  </main>

  <section class="nav-back-container">
    <h2 class="none">네비 바 뒤 공백</h2>
  </section>

  <aside>
    <section class="nav-container">
      <h2 class="none">네비게이션 바</h2>
      <NavigationBar active="home"></NavigationBar>
    </section>
  </aside>
</template>

<style scoped>

</style>