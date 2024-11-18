<script setup>
import axios from "axios";
import { useRouter } from 'vue-router';
import {onMounted, reactive, watch} from "vue";
import useTreeFormData from "~/composables/useTreeFormData.js";

// -------------------------- Model -------------------------- //
const treeFormData = useTreeFormData().treeFormData;
const config = useRuntimeConfig();
const queryParam = reactive({
  name: ''
});

const bookCategories = reactive({
  items: []
});

const router = useRouter();

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

    const response = await $fetch(`book-categories`, {
      method: 'GET',
      baseURL: `${config.public.apiBase}`,
      headers: {
        'Content-Type': 'application/json',
        'Accept': 'application/json'
      },
      params: {
        query: queryParam.name
      }
    });

    bookCategories.items = response.bookCategories;

  } catch (error) {
    console.error('Error fetching data:', error);
  }
};

// -------------------------- Event Function -------------------------- //
const goBack = () => {
  router.back();
}

const chooseBookCategory = (category) => {
    treeFormData.value.bookCategoryId = category.id;
    treeFormData.value.bookCategoryName = category.name;
    treeFormData.value.bookCategorySelected = true;
    // console.log('Selected book category:', category);
    router.push('/tree/book/new');
};

</script>

<template>
  <header>
    <h1 class="none">도서 카테고리 이름</h1>
    <TopBarBack title="도서 카테고리"></TopBarBack>
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
      <TagBookCategoryListBox :categories="bookCategories.items" @choose="chooseBookCategory" ></TagBookCategoryListBox>
    </section>
  </main>
</template>

<style scoped>
.tree-input-text__form {
  display: flex;
  justify-content: center;
}
.tree-input-text__subject {
  display: flex;
  flex-direction: column;
  flex-grow: 1;
  font-size: 16px;
  font-weight: var(--semi-bold);
}
.tree-input-text__rectangle {
  display: flex;
  flex-grow: 1;
  height: 56px;
  border: none;
  font-size: 16px;
  font-weight: var(--regular);
  color: var(--text-sub);
  background-color: var(--main3);
  padding-left: 16px;
}
.tree-input-text__rectangle:focus {
  outline: none;
}

.tree-input-text__rectangle__re-enter {
  display: block;
  height: 56px;
  border-color: var(--warning);
  font-size: 16px;
  font-weight: var(--regular);
  color: var(--warning);
  background-color: var(--main3);
  padding-left: 16px;
}
</style>