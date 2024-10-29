<script setup>
import { onMounted, ref } from "vue";
import "@toast-ui/editor/dist/toastui-editor.css";
import Viewer from "@toast-ui/editor/dist/toastui-editor-viewer";
import UserInfoBackHeaderMemoirTitle from "~/components/background/UserInfoBackHeaderMemoirTitle.vue";
import NavigationBar from "~/components/nav/NavigationBar.vue";
import Comment from "~/components/bar/Comment.vue";

//----------------variable----------------
let isOnwer = ref(false);

//----------------Model----------------
let tree = reactive({});

let memoir = reactive({
  id: 0,
  title: "",
  text: "",
  visibility: true,
});

let book = reactive({
  id: 1,
  publishDate: "2007",
  totalPage: 759,
  bookCategoryId: 4,
  author: "J.K. 롤링",
  isbn: "978-3-16-148410-0",
  publisher: "블룸즈버리",
  title: "해리 포터와 죽음의 성물",
  imageFile: "harry_potter_cover.jpg",
  createTime: "24-10-01",
  updateTime: "24-10-01",
});

//카드 아이템
const treeItem = reactive({});
const memoirItem = reactive({});
const leafItem = reactive({});

//----------------Life Cycle----------------
onBeforeMount(async () => {
  const { data, error } = await useFetch(
    `/api/v1/memoir/${useRoute().params.id}`,
    {
      method: "GET",
      baseURL: import.meta.env.VITE_API_BASE_URL,
    }
  );

  if (data.value) {
    memoir = data.value.memoirDto;
    book = data.value.bookDto;
    isOnwer.value = data.value.owner;
  } else {
    console.error("회고록 조회 실패 : ", error.value);
    alert(error.value.data.message);
    //TODO: 이전 페이지 기억했다가 리다이렉션 시키기
    await navigateTo("/home");
  }
});

onMounted(() => {
  const viewer = new Viewer({
    el: document.querySelector("#viewer"),
    height: "500px",
    initialValue: "hello",
  });

  console.log(memoir.text);
  viewer.setMarkdown(memoir.text);

  // function commentTabActive() {
  //   const commentTab = document.getElementById("comment-filter-tab-id");

  //   commentTab.classList.add("book-detail-comment-tab-container--active");

  //   document.body.classList.add("no-scroll");
  //   window.scrollTo({ behavior: "smooth" });
  // }

  // function commentTabInactive() {
  //   const commentTab = document.getElementById("comment-filter-tab-id");

  //   commentTab.classList.remove("book-detail-comment-tab-container--active");
  //   document.body.classList.remove("no-scroll");
  // }

  // document
  //   .getElementById("bar-comment-id")
  //   .addEventListener("click", commentTabActive);
  // document
  //   .getElementById("top-bar-comment__back-icon-box-id")
  //   .addEventListener("click", commentTabInactive);
  // document
  //   .getElementById("top-bar-comment__line-box")
  //   .addEventListener("click", commentTabInactive);

  // document
  //   .getElementById("input-comment-input__input-id")
  //   .addEventListener("focus", function () {
  //     const submitBtn = document.getElementById(
  //       "input-comment-input__submit-id"
  //     );
  //     setTimeout(() => {
  //       submitBtn.classList.add("input-comment-input__submit--active");
  //     }, 100);
  //   });

  // document
  //   .getElementById("input-comment-input__input-id")
  //   .addEventListener("focusout", function () {
  //     const submitBtn = document.getElementById(
  //       "input-comment-input__submit-id"
  //     );
  //     submitBtn.classList.remove("input-comment-input__submit--active");
  //   });
});
</script>

<template>
  <Title>회고록</Title>
  <header>
    <UserInfoBackHeaderMemoirTitle
      :edit="`/memoir/${memoir.id}/edit`"
      :backimgpath="coverImageName"
      :username="`조현진`"
      :userid="`hyeonjin`"
      :memoirTitle="`bookTitle`"
      :userUrl="`userUrl`"
    />
  </header>

  <main>
    <section class="my-tree-list">
      <TextMainTitle :data="{ title: '회고록', size: 28 }" />
      <h2 class="none">에디터 뷰어</h2>

      <!-- 에디터 뷰어-->
      <div id="viewer"></div>
    </section>

    <!-- 작성자 -->
    <section class="bar-user-info-container">
      <h2 class="none">사용자 정보</h2>
      <BarUserInfoFollowBtn
        :userImg="`svg/comment-profile.svg`"
        :username="`조현진`"
        :userid="`hyeonjin`"
      />
    </section>

    <!-- 댓글 -->
    <section class="tree-index-comment-container">
      <h1 class="none">댓글</h1>
      <Comment :profileImg="`/svg/comment-profile.svg`" commentCount="199" />
      <section
        id="comment-filter-tab-id"
        class="book-detail-comment-tab-container"
      >
        <h1 class="none">댓글 탭</h1>
        <TabComment />
      </section>
    </section>

    <section class="user-another-records">
      <h2 class="none">작성자 다른 기록들</h2>
      <!-- 컴포넌트 -->
      <section class="user-another-records-title-container">
        <TextMainTitle :data="{ title: `${username}의 다른 최근 기록들`, size: 28 }" />
      </section>
      <section class="branch-tree-other-recode-sub-title-container">
        <TextMainTitle :data="{ title: `트리`, size: 24 }" />
      </section>
      <section class="branch-tree-another-record-list-container">
        <h1 class="none">트리 리스트</h1>
        <CardAnotherRecordsList :items="treeItem" />
      </section>

      <section class="branch-tree-other-recode-sub-title-container">
        <TextMainTitle :data="{ title: `회고록`, size: 24 }" />
      </section>

      <section class="branch-tree-another-record-list-container">
        <h1 class="none">회고록 리스트</h1>
        <section class="book-detail-other-tree-card-container">
          <CardAnotherRecordsList :items="memoirItem" />
        </section>
      </section>

      <section class="branch-tree-other-recode-sub-title-container">
        <TextMainTitle :data="{ title: `로그`, size: 24 }" />
      </section>

      <section class="branch-tree-another-record-list-container">
        <h1 class="none">리프 리스트</h1>
        <CardAnotherRecordsList :items="leafItem" />
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
      <NavigationBar :active="'home'" />
    </section>
  </aside>
</template>

<style scoped>
/*회고록 idnex페이지*/
.memoir-title {
  padding-top: 30px;
}
</style>
