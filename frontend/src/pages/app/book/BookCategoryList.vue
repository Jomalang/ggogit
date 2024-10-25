<script setup lang="ts">

import TopBarBack from "@/components/top-bar/TopBarBack.vue";
import TopBarTagInfo from "@/components/top-bar/TopBarTagInfo.vue";
import axios from "axios";
import {onMounted, reactive, watch} from "vue";
import BookCategoryListBox from "@/components/tag/BookCategoryListBox.vue";


// -------------------------- Model -------------------------- //
const queryParam = reactive({
  name: ''
});

const bookCategories = reactive({
  items: []
});

watch(
    queryParam,
    (newVal) => {
      fetchData();
    }
);

// -------------------------- Life Cycle -------------------------- //
onMounted(() => {
  fetchData();
});

// -------------------------- API -------------------------- //

const fetchData = async () => {
  try {
    const response = await axios.get(`http://localhost:8080/api/v1/book-categories?query=${queryParam.name}`);
    bookCategories.items = response.data.bookCategories;

  } catch (error) {
    console.error('Error fetching data:', error);
  }
};

// -------------------------- Event Function -------------------------- //


</script>

<template>
  <header>
    <h1 class="none">도서 카테고리 이름</h1>
    <TopBarBack title="도서 카테고리" link="/tree/book/reg"></TopBarBack>
  </header>
  <main>
    <section>
      <h2 class="none">도서 카테고리 검색</h2>
      <div class="tree-input-text__form">
        <label class="tree-input-text__subject">
          <input
              id="tree-input-text__rectangle-id"
              name="name"
              class="tree-input-text__rectangle"
              placeholder="도서 카테고리를 입력하세요."
              v-model="queryParam.name"
          />
        </label>
      </div>
    </section>

    <section>
      <h2 class="none">도서 카테고리 기능 알림</h2>
      <TopBarTagInfo text="도서 카테고리"></TopBarTagInfo>
    </section>

    <section>
      <h2 class="none">도서 카테고리 리스트</h2>
      <BookCategoryListBox :tags="bookCategories.items"></BookCategoryListBox>
    </section>
  </main>
</template>

<style scoped>

</style>