<script setup>

import { ref } from "vue";
import "@toast-ui/editor/dist/toastui-editor.css";
import Viewer from '@toast-ui/editor/dist/toastui-editor-viewer';
import CardTreeInfoCover from "~/components/card/CardTreeInfoCover.vue";

const coverImageName = ref("background-image.png");
const myProfile = ref("~/assets/jpg/leaf-profile.jpg");
const config = useRuntimeConfig();
const route = useRoute();
const leafId = route.params.id;

// ---------------------- Model ---------------------- //
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

const member = reactive({
  id: 1,
  nickName: '닉네임',
  userName: '유저이름',
  email: '이메일',
  backImgName: '배경이미지',
  profileImgName: '프로필이미지',
});

let info = reactive({});

//카드 아이템
const treeItems = ref([]);
const memoirItems = ref([]);
const leafItems = ref([]);

// ----------------------  API ---------------------- //
const { data: infoData, error: infoError } = await useAuthFetch(() => `trees/leaves/${leafId}/info`, {
  baseURL: config.public.apiBase,
});

const { data: leafDetailData, error: leafDetailDataError } = await useAuthFetch(() => `leaves/${leafId}`, {
  baseURL: config.public.apiBase,
});

const { data: leafMemberInfo, error: leafMemberInfoError } = await useAuthFetch(() => `leaves/${leafId}/member`, {
  baseURL: config.public.apiBase,
});

const { data: leafCardData, error: leafCardError }
    = await useAuthFetch(() => `/members/${member.id}/leaves/book/cards`, {
  method: "GET",
  baseURL: `${config.public.apiBase}`,
});

const { data: treeCardData, error: treeCardError }
    = await useAuthFetch(() => `trees/members/${member.id}/trees/book/cards`, {
  method: "GET",
  baseURL: `${config.public.apiBase}`,
});

const { data: memoirCardData, error: memoirCardError }
    = await useAuthFetch(() => `memoirs/members/${member.id}/memoirs/book/cards`, {
  method: "GET",
  baseURL: `${config.public.apiBase}`,
});

if (leafCardData.value) {
  // console.log(leafCardData.value);
  leafItems.value = [...leafCardData.value.items];
}

if (treeCardData.value) {
  // console.log(treeCardData.value);
  treeItems.value = [...treeCardData.value.treeBookCardResponse];
}

if (memoirCardData.value) {
  // console.log(memoirCardData.value);
  memoirItems.value = [...memoirCardData.value.memoirBookCardDtoResponse];
}

watchEffect(() => {
  if (infoData.value) {
    // console.log('infoData.value 데이터 확인', infoData.value);
    Object.assign(info, infoData.value);
  }

  if (leafDetailData.value) {
    // console.log('leafDetailData.value', leafDetailData.value);
    editor.value.title = leafDetailData.value.leafTitle;
    editor.value.text = leafDetailData.value.leafContent;
    leafPageInfo.startPage = leafDetailData.value.startPage;
    leafPageInfo.endPage = leafDetailData.value.endPage;
  }

  if (leafMemberInfo.value) {
    member.id = leafMemberInfo.value.id;
    member.nickName = leafMemberInfo.value.nickName;
    member.userName = leafMemberInfo.value.userName;
    member.email = leafMemberInfo.value.email;
    member.backImgName = leafMemberInfo.value.backImgName;
    member.profileImgName = leafMemberInfo.value.profileImgName;
  }
});
// ---------------------- LifeCycle -------------------- //

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
        :edit="`/leaf/etc/${leafId}/edit`"
        :backImgPath="member.backImgName"
        :username="member.nickName"
        :userid="member.email"
        :memoirTitle="leafDetailData.leafTitle"
        :userUrl="`/member/${member.id}`"
    />
  </header>

  <main>

    <section class="user-tree-info__container">
      <h2 class="none">트리 정보</h2>
      <CardTreeInfoCover :data="info">트리 정보</CardTreeInfoCover>
    </section>
    <section class="branch-tree-detail-container">
      <h2 class="none">트리 상세 설명</h2>
      <CardEtcHiddenInfo :data ="info" >트리 상세 설명</CardEtcHiddenInfo>
    </section>

    <section class="leaf-page-info-container">
      <h2 class="none">제목</h2>
      <text-main-title :data="{ title: '내용', size: 28 }"></text-main-title>
    </section>

    <!-- 에디터 뷰어 -->
    <section class="editor-show-container">
      <h2 class="none">에디터 뷰어</h2>
      <div id="viewer"></div>
    </section>

    <!-- 팔로우 -->
    <section class="follow-container">
      <BarUserInfoFollowBtn
          :followId="member.id"
          :userImg="member.profileImgName"
          :username="member.userName"
          :userid="member.email"
      />
    </section>

    <!-- 댓글 -->

    <section class="user-another-records">
      <h2 class="none">작성자 다른 기록들</h2>
      <!-- 컴포넌트 -->
      <section class="user-another-records-title-container">
        <TextMainTitle
            :data="{ title: `${member.nickName}의 다른 최근 기록들`, size: 28 }"
        />
      </section>
      <section
          v-if="treeItems.length > 0"
          class="branch-tree-other-recode-sub-title-container"
      >
        <TextMainTitle :data="{ title: `🌲 트리`, size: 24 }" />
      </section>
      <section class="branch-tree-another-record-list-container">
        <h1 class="none">트리 리스트</h1>
        <CardAnotherRecordsList :items="treeItems" :sideScrollType="`tree`" />
      </section>

      <section
          v-if="memoirItems.length > 0"
          class="branch-tree-other-recode-sub-title-container"
      >
        <TextMainTitle :data="{ title: `📖 회고록`, size: 24 }" />
      </section>

      <section class="branch-tree-another-record-list-container">
        <h1 class="none">회고록 리스트</h1>
        <section class="book-detail-other-tree-card-container">
          <CardAnotherRecordsList :items="memoirItems" :sideScrollType="`memoir`" />
        </section>
      </section>

      <section
          v-if="leafItems.length > 0"
          class="branch-tree-other-recode-sub-title-container"
      >
        <TextMainTitle :data="{ title: `🌿 리프`, size: 24 }" />
      </section>

      <section class="branch-tree-another-record-list-container">
        <h1 class="none">리프 리스트</h1>
        <CardAnotherRecordsList :items="leafItems" :sideScrollType="`leaf`" />
      </section>
    </section>

  </main>

  <Footer :noticeText="`개발 중입니다.`" />

  <section class="nav-back-container">
    <h2 class="none">네비바 뒤 공백</h2>
  </section>

  <aside>
    <section class="nav-container">
      <h2 class="none">네비게이션</h2>
      <!-- 트리 생성 언더바  -->
      <NavNavigationBar :active="'home'" />
    </section>
  </aside>

</template>

<style scoped>
.editor-show-container {
  margin: 10px 16px 100px 16px;
}

.follow-container {
  margin: 20px 16px 40px 16px;
}
</style>