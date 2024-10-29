<script setup>
import "@toast-ui/editor/dist/toastui-editor.css";
import { ref } from "vue";

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

const editor = ref({
  title: "test",
  text: "<h1>hello",
  visibility: 1,
});

import Viewer from '@toast-ui/editor/dist/toastui-editor-viewer';
onMounted(() => {
  const viewer = new Viewer({
    el: document.querySelector("#viewer"),
    height: "500px",
    initialValue: "hello"
  });
  viewer.setMarkdown(editor.value.text);
});

</script>

<template>

  <Title>리프 정보</Title>

  <header>
    <BackgroundDetail
        :edit="`/leaves/${leafId}/edit`"
        :backImgPath="coverImageName"
        :username="`조현진`"
        :userid="`hyeonjin`"
        :memoirTitle="`리프 디테일 제목입니다`"
        :userUrl="`userUrl`"
    />
  </header>

  <main>

    <section class="branch-tree-detail-container">
      <h2 class="none">트리 상세 설명</h2>
      <!-- 트리 상세 설명 -->
    </section>

    <section class="leaf-page-info-container">
      <h2 class="none">도서 읽은 정보</h2>
      <BarLeafReadingPageInfo :data="{ title: '회고록', size: 28, startPage: 100, endPage: 200 }" ></BarLeafReadingPageInfo>
    </section>

    <!-- 에디터 뷰어 -->
    <section class="my-tree-list">
      <h2 class="none">에디터 뷰어</h2>
      <div id="viewer"></div>
    </section>



  </main>

</template>

<style scoped>

</style>