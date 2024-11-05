<script setup>

import { ref } from "vue";
import "@toast-ui/editor/dist/toastui-editor.css";
import Viewer from '@toast-ui/editor/dist/toastui-editor-viewer';
import CardHiddenInfo from "~/components/card/CardHiddenInfo.vue";
import CardTreeInfoCover from "~/components/card/CardTreeInfoCover.vue";


const coverImageName = ref("background-image.png");
const myProfile = ref("/jpg/leaf-profile.jpg");
const config = useRuntimeConfig();
const route = useRoute();
const leafId = route.params.id;

// ---------------------- Model ----------------------
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

const leafPageInfo = reactive({
  title: '내용',
  size: 28,
  startPage: 100,
  endPage: 200
});

const leafMember = reactive({
  id: 1,
  nickName: '닉네임',
  userName: '유저이름',
  email: '이메일',
  backImgName: '배경이미지',
  profileImgName: '프로필이미지',
});

let info = reactive({});

// ----------------------  API ----------------------
const { data: infoData, error: infoError } = await useFetch(() => `trees/leaves/${leafId}/info`, {
  baseURL: config.public.apiBase,
});

const { data: leafDetailData, error: leafDetailDataError } = await useFetch(() => `leaves/${leafId}`, {
  baseURL: config.public.apiBase,
});

const { data: leafMemberInfo, error: leafMemberInfoError } = await useFetch(() => `leaves/${leafId}/member`, {
  baseURL: config.public.apiBase,
});

watchEffect(() => {
  if (infoData.value) {
    console.log('infoData.value', infoData.value);
    Object.assign(info, infoData.value);
  }

  if (leafDetailData.value) {
    console.log('leafDetailData.value', leafDetailData.value);
    editor.value.title = leafDetailData.value.leafTitle;
    editor.value.text = leafDetailData.value.leafContent;
    leafPageInfo.startPage = leafDetailData.value.startPage;
    leafPageInfo.endPage = leafDetailData.value.endPage;
  }

  if (leafMemberInfo.value) {
    leafMember.id = leafMemberInfo.value.id;
    leafMember.nickName = leafMemberInfo.value.nickName;
    leafMember.userName = leafMemberInfo.value.userName;
    leafMember.email = leafMemberInfo.value.email;
    leafMember.backImgName = leafMemberInfo.value.backImgName;
    leafMember.profileImgName = leafMemberInfo.value.profileImgName;
  }
});
// ---------------------- LifeCycle ----------------------

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
        :username="leafMember.nickName"
        :userid="leafMember.email"
        :memoirTitle="leafDetailData.leafTitle"
        :userUrl="`/member/${leafMember.id}`"
    />
  </header>

  <main>
    <section class="user-tree-info__container">
      <h2 class="none">트리 정보</h2>
      <CardTreeInfoCover :data="info">트리 정보</CardTreeInfoCover>
    </section>
    <section class="branch-tree-detail-container">
      <h2 class="none">트리 상세 설명</h2>
      <CardHiddenInfo :data ="info" >트리 상세 설명</CardHiddenInfo>
    </section>

    <section class="leaf-page-info-container">
      <h2 class="none">도서 읽은 정보</h2>
      <BarLeafReadingPageInfo :data="leafPageInfo" ></BarLeafReadingPageInfo>
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