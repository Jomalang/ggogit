<script setup>
import "@toast-ui/editor/dist/toastui-editor.css";
import { ref } from "vue";

const leafId = ref(1);
const coverImageName = ref("background-image.png");
const myProfile = ref("/jpg/leaf-profile.jpg");

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
    <section class="editor-show-container">
      <h2 class="none">에디터 뷰어</h2>
      <div id="viewer"></div>
    </section>

    <!-- 팔로우 -->
    <section class="follow-container">
      <BarUserInfoFollowBtn
          :followId="1"
          :userImg="myProfile"
          :username="`사용자 이름`"
          :userid="`@gksxorb147`"
      />
    </section>

    <!-- 댓글 -->
    <section class="book-detail-comment-container">
      <h1 class="none">댓글</h1>
      <BarComment :commentCount="1" :profileImg="myProfile" />
      <section
          id="comment-filter-tab-id"
          class="book-detail-comment-tab-container book-detail-comment-tab-container--active none"
      >
        <h1 class="none">댓글 탭</h1>
        <!-- TODO: 추후에 데이터 바인딩하면 주석 풀 것 -->
        <!-- <TabComment /> -->
      </section>
    </section>

  </main>

</template>

<style scoped>
.editor-show-container {
  margin: 10px 16px 100px 16px;
}

.follow-container {
  margin: 20px 16px 40px 16px;
}
</style>