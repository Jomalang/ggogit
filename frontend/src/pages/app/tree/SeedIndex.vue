<script setup lang="ts">

import NavigationBar from "@/components/nav/NavigationBar.vue";
import FilterTabGet from "@/components/tab-filter/FilterTabGet.vue";
import FilterFullWidth from "@/components/button/FilterFullWidth.vue";
import TextInfo from "@/components/text/TextInfo.vue";
import TopBarBack from "@/components/top-bar/TopBarBack.vue";
import {SeedFilterProps, SeedFilterTabProps} from "@/types/types";
import {onMounted, reactive} from "vue";
import axios from "axios";

// -------------------------- Model -------------------------- //

const seeds = reactive<SeedFilterTabProps>({});
// -------------------------- Life Cycle -------------------------- //
onMounted(() => {
  fetchData();
});

// -------------------------- API -------------------------- //

const fetchData = async () => {
  try {
    const response = await axios.get('http://localhost:8080/api/v1/seeds');
    console.log(response.data);
    seeds.filterName = "씨앗 선택";
    seeds.filterItems = response.data.items;

  } catch (error) {
    console.error('Error fetching data:', error);
  }
};

// -------------------------- Event Function -------------------------- //

const filterTabUpHandler = () => {
  const filterTab = document.querySelector('.filter-tab-container')
  filterTab.classList.remove("none");
  const filterTabBox = document.querySelector('.filter-tab__box');
  setTimeout(() => filterTabBox.classList.add("up"), 10);
}

const filterTabDownHandler = () => {
  const filterTabBox = document.querySelector('.filter-tab__box');
  filterTabBox.classList.remove("up");
  const filterTab = document.querySelector('.filter-tab-container')
  setTimeout(() => filterTab.classList.add("none"), 300);
}

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
      <FilterFullWidth text="씨앗 선택" @click="filterTabUpHandler"></FilterFullWidth>
    </section>

    <section class="filter-tab-container none" @click="filterTabDownHandler">
      <h2 class="none">씨앗 선택</h2>
      <FilterTabGet :data="seeds" @backButtonClick="filterTabDownHandler"></FilterTabGet>
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