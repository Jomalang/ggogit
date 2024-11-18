<script setup>
import { onMounted, reactive } from "vue";

// -------------------------- Model -------------------------- //

const seeds = reactive({});
const config = useRuntimeConfig();

// -------------------------- API -------------------------- //

const { data } = await useAuthFetch("seeds", {
  method: "GET",
  baseURL: `${config.public.apiBase}`,
});
console.log(data);
seeds.filterName = "씨앗 선택";
seeds.filterItems = data.value.items;

// -------------------------- Event Function -------------------------- //

const filterTabUpHandler = () => {
  const filterTab = document.querySelector(".filter-tab-container");
  const background = document.querySelector(".filter-bg");
  filterTab.classList.remove("none");
  background.classList.remove("none");
  const filterTabBox = document.querySelector(".filter-tab__box");
  setTimeout(() => filterTabBox.classList.add("up"), 10);
};

const filterTabDownHandler = () => {
  const filterTabBox = document.querySelector(".filter-tab__box");
  filterTabBox.classList.remove("up");
  const filterTab = document.querySelector(".filter-tab-container");
  const background = document.querySelector(".filter-bg");
  setTimeout(() => filterTab.classList.add("none"), 300);
  setTimeout(() => {
    background.classList.add("none");
  }, 300);
};
</script>

<template>
  <header class="top-bar-back-container">
    <h1 class="none">씨앗 선택</h1>
    <TopBarBack :title="`트리 생성`"></TopBarBack>
  </header>

  <main>
    <section class="text-info-container">
      <h2 class="none">트리 생성 안내</h2>
      <TextInfo
        :text="`트리를 생성하기 위해`"
        :boldText="`씨앗을 선택해주세요`"
      ></TextInfo>
    </section>

    <section class="btn-select-container">
      <h2 class="none">씨앗 선택 버튼</h2>
      <ButtonFilterFullWidth
        :text="`씨앗 선택`"
        @click="filterTabUpHandler"
      ></ButtonFilterFullWidth>
    </section>

    <div class="filter-bg none"></div>
    <section class="filter-tab-container none" @click="filterTabDownHandler">
      <h2 class="none">씨앗 선택</h2>
      <TabFilterTabGet
        :data="seeds"
        @backButtonClick="filterTabDownHandler"
      ></TabFilterTabGet>
    </section>
  </main>

  <section class="nav-back-container">
    <h2 class="none">네비 바 뒤 공백</h2>
  </section>

  <aside>
    <section class="nav-container">
      <h2 class="none">네비게이션 바</h2>
      <NavNavigationBar active="home"></NavNavigationBar>
    </section>
  </aside>
</template>

<style scoped>
.filter-bg {
  position: absolute;
  left: 0;
  top: 0;
  background-color: rgba(0, 0, 0, 0.6);
  width: 200%;
  height: 200%;
  transform: translate(-50%, -50%);
  z-index: 1;
}
</style>
