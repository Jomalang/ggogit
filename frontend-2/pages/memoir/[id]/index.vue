<script setup>
import { onMounted, ref } from "vue";

const treeInfo = ref({});
const memoir = ref({
  title: "test",
  text: "<h1>hello",
  visibility: 1,
});

const treeItem = ref({});
const memoirItem = ref({});
const leafItem = ref({});

//----------------Life Cycle----------------
import Viewer from "@toast-ui/editor";
onMounted(() => {
  const viewer = new Viewer({
    el: document.querySelector("#viewer"),
    height: "500px",
    initialValue: "hello",
  });
  viewer.setMarkdown(memoir.value.text);

  const onMounted = () => {
    function commentTabActive() {
      const commentTab = document.getElementById("comment-filter-tab-id");

      commentTab.classList.add("book-detail-comment-tab-container--active");

      document.body.classList.add("no-scroll");
      window.scrollTo({ behavior: "smooth" });
    }

    function commentTabInactive() {
      const commentTab = document.getElementById("comment-filter-tab-id");

      commentTab.classList.remove("book-detail-comment-tab-container--active");
      document.body.classList.remove("no-scroll");
    }

    document
      .getElementById("bar-comment-id")
      .addEventListener("click", commentTabActive);
    document
      .getElementById("top-bar-comment__back-icon-box-id")
      .addEventListener("click", commentTabInactive);
    document
      .getElementById("top-bar-comment__line-box")
      .addEventListener("click", commentTabInactive);

    document
      .getElementById("input-comment-input__input-id")
      .addEventListener("focus", function () {
        const submitBtn = document.getElementById(
          "input-comment-input__submit-id"
        );
        setTimeout(() => {
          submitBtn.classList.add("input-comment-input__submit--active");
        }, 100);
      });

    document
      .getElementById("input-comment-input__input-id")
      .addEventListener("focusout", function () {
        const submitBtn = document.getElementById(
          "input-comment-input__submit-id"
        );
        submitBtn.classList.remove("input-comment-input__submit--active");
      });
  };
});
</script>

<template>
  <Title>회고록</Title>
  <header>
    <UserInfoBackHeaderMemoirTitle
      :edit="`/memoir/${memoirId}/edit`"
      :backimgpath="coverImageName"
      :username="`조현진`"
      :userid="`hyeonjin`"
      :memoirtitle="`bookTitle`"
      :userurl="`userurl`"
    />
  </header>

  <main>
    <!-- 에디터 뷰어-->
    <section class="my-tree-list">
      <TextMainTitle class="memoir-title" :title="memoir.title" :size="28" />
      <h2 class="none">에디터 뷰어</h2>
      <div id="viewer"></div>
    </section>

    <!-- 작성자 -->
    <section class="bar-user-info-container">
      <h2 class="none">사용자 정보</h2>
      <BarUserInfoFollowBtn
        :userimg="`svg/comment-profile.svg`"
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
        <TextMainTitle :title="`${username}의 다른 최근 기록들`" :size="28" />
      </section>
      <section class="branch-tree-other-recode-sub-title-container">
        <TextMainTitle :title="`트리`" :size="24" />
      </section>
      <section class="branch-tree-another-record-list-container">
        <h1 class="none">트리 리스트</h1>
        <CardAnotherRecordsList :items="treeItem" />
      </section>

      <section class="branch-tree-other-recode-sub-title-container">
        <TextMainTitle :title="`회고록`" :size="24" />
      </section>

      <section class="branch-tree-another-record-list-container">
        <h1 class="none">회고록 리스트</h1>
        <section class="book-detail-other-tree-card-container">
          <CardAnotherRecordsList :items="memoirItem" />
        </section>
      </section>

      <section class="branch-tree-other-recode-sub-title-container">
        <TextMainTitle :title="`로그`" :size="24" />
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
