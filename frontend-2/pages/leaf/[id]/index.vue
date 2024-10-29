<script setup>

import UserInfoBackHeaderMemoirTitle from "~/components/background/UserInfoBackHeaderMemoirTitle.vue";

const leafId = ref(1);
const coverImageName = ref("background-image.png");

let cardHiddenInfo = reactive({
  hiddenText: '자세히',
  authors: '작가 이름',
  translators: '번역가 이름',
  publisher: '출판사 이름',
  page: 100, // 총 페이지 수
  seed: 1,
  treeDescription: '트리 설명글',
  readPage: 50, // 읽은 페이지 수
  progress: 50, // 읽은 페이지 수 / 총 페이지 수
  fullPage: 100, // 총 페이지 수
  leaf: 100, // 리프 수
  like: 100, // 리프 수
  view: 100 // 리프 수
});

</script>

<template>

  <Title>리프 정보</Title>

  <header>
    <UserInfoBackHeaderMemoirTitle
        :edit="`/leaves/${leafId}/edit`"
        :backimgpath="coverImageName"
        :username="`조현진`"
        :userid="`hyeonjin`"
        :memoirtitle="`bookTitle`"
        :userurl="`userurl`"
    />
  </header>

  <main>
    <section class="my-tree-list">
      <h2 class="none">리프 제목</h2>
      <TextMainTitle class="memoir-title" :data="{ title: '리프 제목', size: 28 }" />
    </section>

    <section class="branch-tree-detail-container">
      <h2 class="none">트리 상세 설명</h2>
      <!-- 트리 상세 설명 -->
    </section>

    <!-- 에디터 뷰어-->
    <section class="my-tree-list">
      <h2 class="none">에디터 뷰어</h2>
      <div id="viewer"></div>
    </section>

  </main>

</template>

<style scoped>

</style>