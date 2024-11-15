<script setup>
import { onMounted, ref } from "vue";
import "@toast-ui/editor/dist/toastui-editor.css";
import Viewer from "@toast-ui/editor/dist/toastui-editor-viewer";

//----------------variable----------------
let isOnwer = ref(false);
const config = useRuntimeConfig();

//----------------Model----------------
let tree = ref({
  id: 1,
  memberId: 1,
  seedId: 1,
  bookId: 1,
  title: "토마토 나무",
  description: "토마토 나무의 성장 과정을 나타내는 나무입니다.",
  visibility: true,
  createdAt: "2024-10-01T10:00",
  updatedAt: "2024-10-01T10:00",
});

let member = ref({
  id: 1,
  nickName: "nickname1",
  userName: "user1",
  email: "user1@example.com",
  backImgName: "",
  profileImgName: "",
});

let memoir = ref({
  id: 0,
  title: "",
  text: "",
  visibility: true,
});

let book = ref({
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

//fetch
const { data, error } = await useAuthFetch(`/memoirs/${useRoute().params.id}`, {
  method: "GET",
  baseURL: `${config.public.apiBase}`,
});

if (data.value) {
  memoir.value = data.value.memoirDto;
  book.value = data.value.bookDto;
  member.value = data.value.memberDto;
  tree.value = data.value.treeDto;
  isOnwer.value = data.value.owner;
} else {
  console.error("회고록 조회 실패 : ");
  //이전페이지로 이동
  useRouter().push("/home");
}

//카드 아이템
const treeItems = ref([]);
const memoirItems = ref([]);
const leafItems = ref([]);

//fetch
const { data: leafCardData, error: leafCardError } = await useAuthFetch(
  `/members/${member.value.id}/leaves/book/cards`,
  {
    method: "GET",
    baseURL: `${config.public.apiBase}`,
  }
);

const { data: treeCardData, error: treeCardError } = await useAuthFetch(
  `trees/members/${member.value.id}/trees/book/cards`,
  {
    method: "GET",
    baseURL: `${config.public.apiBase}`,
  }
);

const { data: memoirCardData, error: memoirCardError } = await useAuthFetch(
  `memoirs/members/${member.value.id}/memoirs/book/cards`,
  {
    method: "GET",
    baseURL: `${config.public.apiBase}`,
  }
);

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

//-----------function----------------

//----------------Life Cycle----------------

onMounted(() => {
  //viewer 렌더링
  const viewer = new Viewer({
    el: document.querySelector("#viewer"),
    height: "500px",
    initialValue: "hello",
  });

  viewer.setMarkdown(memoir.value.text);

  // function commentTabActive() {
  //   const commentTab = document.querySelector(".comment-filter-tab-id");

  //   commentTab.classList.add("book-detail-comment-tab-container--active");

  //   document.body.classList.add("no-scroll");
  //   window.scrollTo({ behavior: "smooth" });
  // }

  // function commentTabInactive() {
  //   const commentTab = document.querySelector(".comment-filter-tab-id");

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
  //   .querySelector(".top-bar-comment__line-box")
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
  <!-- TODO:본인 회고록인 경우에만 수정,삭제 버튼이 보이게 하기 -->
  <Title>회고록</Title>
  <header>
    <BackgroundUserInfoBackHeaderMemoirTitle
      :edit="`/memoir/${memoir.id}/edit`"
      :delete="`/memoirs/${memoir.id}`"
      :backImgPath="member.backImgName"
      :userName="member.nickName"
      :userId="member.email"
      :bookTitle="book.title"
      :userUrl="member.email"
    />
  </header>

  <main>
    <section class="my-tree-list memoir-title">
      <TextMainTitle :data="{ title: memoir.title, size: 28 }" />
      <h2 class="none">에디터 뷰어</h2>

      <!-- 에디터 뷰어-->
      <div id="viewer"></div>
    </section>

    <!-- 작성자 -->
    <section class="bar-user-info-container">
      <h2 class="none">사용자 정보</h2>
      <BarUserInfoFollowBtn
        :userimg="member.profileImgName"
        :username="member.nickName"
        :userid="member.email"
        :follow-id="member.id"
      />
    </section>

    <!-- 댓글 -->
    <!-- <section class="tree-index-comment-container">
      <h1 class="none">댓글</h1>
      <BarComment
        :profileImg="`/svg/comment-profile.svg`"
        :commentCount="199"
      />
      <section
        id="comment-filter-tab-id"
        class="book-detail-comment-tab-container"
      >
        <h1 class="none">댓글 탭</h1>
        <TabComment />
      </section>
    </section> -->

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
        <CardAnotherRecordsList :items="treeItems" :sideScrollType="`tree`"  />
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
        <CardAnotherRecordsList :items="leafItems" :sideScrollType="`laef`" />
      </section>
    </section>
  </main>

  <footer>
    <Footer :noticeText="`개발 중입니다.`" />
  </footer>

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
/*회고록 idnex페이지*/
.memoir-title {
  padding-top: 30px;
}
</style>
