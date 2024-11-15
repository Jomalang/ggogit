<script setup>
import { ref } from "vue";

const coverImageName = ref("/png/book-example.png");
const commentCount = ref("/jpg/leaf-profile.jpg");
const config = useRuntimeConfig();
const bookId = useRoute().params.id;

//=== model ========================================

const book = ref({});
const myTreeCards = ref([]);
const bookTreeCards = ref([]);
const bookMemoirCards = ref([]);
const bookleafCards = ref([]);

//=== fetch ========================================
const {
  data: bookData,
  status: bookStatus,
  error: bookError,
} = await useAuthFetch(`books/${bookId}`, {
  method: "GET",
  baseURL: `${config.public.apiBase}`,
});

if (bookData.value) {
  book.value = bookData.value;
}

//TODO: MembmerId는 상태관리 추가되면 가져와야 함 (현재는 임시로 1로 설정)
const memberId = 1;
const {
  data: treeCardsData,
  status: treeCardsStatus,
  error: treeCardsError,
} = await useAuthFetch(
  `trees/members/${memberId}/books/${bookId}/trees/cards`,
  {
    method: "GET",
    baseURL: `${config.public.apiBase}`,
  }
);

if (treeCardsData.value) {
  myTreeCards.value = [...treeCardsData.value.treeBookCardResponse];
}

if (treeCardsError.value === "noContent") {
  myTreeCards.value = [];
}

//=== cards fetch ========================================
const {
  data: bookleafCradsData,
  error: bookleafCradsError,
  status: bookleafCradsStatus,
} = await useAuthFetch(`books/${bookId}/leaves/cards`, {
  method: "GET",
  baseURL: `${config.public.apiBase}`,
});

if (bookleafCradsData.value) {
  bookleafCards.value = [...bookleafCradsData.value.items];
}

const {
  data: bookMemoirCradsData,
  error: bookMemoirCradsError,
  status: bookMemoirCradsStatus,
} = await useAuthFetch(`memoirs/books/${bookId}/memoirs/cards`, {
  method: "GET",
  baseURL: `${config.public.apiBase}`,
});

if (bookMemoirCradsData.value) {
  bookMemoirCards.value = [...bookMemoirCradsData.value.items];
}

const {
  data: bookTreeCradsData,
  error: bookTreeCradsError,
  status: bookTreeCradsStatus,
} = await useAuthFetch(`trees/books/${bookId}/trees/cards`, {
  method: "GET",
  baseURL: `${config.public.apiBase}`,
});

if (bookTreeCradsData.value) {
  bookTreeCards.value = [...bookTreeCradsData.value.items];
}
</script>

<template>
  <main>
    <section class="book-detail-bg-container">
      <h1 class="none">도서 배경 이미지</h1>

      <section class="book-detail-top-bar-container">
        <h1 class="none">뒤로가기 상단바</h1>
        <TopBarClearBack :link="`#`" />
      </section>

      <BackgroundBookDetail
        :data="{
          imageFile: bookData.imageFile,
          backImgPath: bookData.imageFile,
        }"
      />
      <section class="book-detail-like-bar-container">
        <h1 class="none">좋아요 및 공유</h1>
        <BarLikeShare
          :likeLink="`javascript:history.back()`"
          :shareLink="`javascript:history.back()`"
        />
      </section>
    </section>

    <section class="book-detail-book-info-container">
      <h1 class="none">도서 제목 및 저자 정보</h1>
      <TextBookInfo
        :data="{
          category: {
            name: bookData.bookCategoryName,
            id: bookData.bookCategoryId,
          },
          title: bookData.title,
          author: bookData.author,
          translators: bookData.translators,
          publisher: bookData.publisher,
        }"
      />
    </section>

    <section class="book-detail-book-info-container">
      <CardTreeInfoCard
        :data="{
          date: bookData.publishDate,
          pageCount: bookData.totalPage,
          isbn: bookData.isbn,
        }"
      />
      <h1 class="none">도서 기본 정보</h1>
    </section>

    <section class="book-detail-comment-container">
      <h1 class="none">댓글</h1>
      <BarComment :commentCount="1" :profileImg="commentCount" />
      <section
        id="comment-filter-tab-id"
        class="book-detail-comment-tab-container book-detail-comment-tab-container--active none"
      >
        <h1 class="none">댓글 탭</h1>
        <!-- TODO: 추후에 데이터 바인딩하면 주석 풀 것 -->
        <!-- <TabComment /> -->
      </section>
    </section>

    <section class="book-detail-my-tree-container">
      <h1 class="none">도서의 나의 트리 정보</h1>
      <section class="book-detail-my-tree-title-container">
        <TextMainTitle :data="{ title: '이 책의 나의 트리', size: 28 }" />
      </section>

      <section class="book-detail-my-tree-list-container">
        <!-- 이 부분은 추후에 어플리케이션이 사용자 정보를 상태 유지 가능할때 기능 추가 할 예정 -->
        <h1 class="none">나의 트리 리스트</h1>
        <section class="book-detail-my-tree-card-container">
          <CardTreeList v-if="myTreeCards.length >= 1" :data="myTreeCards" />
          <TextMainTitle
            class="book-detail-my-tree-no-tree-container"
            v-else
            :data="{ title: '아직 트리가 없어요!', size: 20 }"
          />
        </section>
      </section>
    </section>

    <section class="book-detail-other-recode-container">
      <h1 class="none">도서의 다른 기록 보기</h1>
      <section class="book-detail-other-recode-title-container">
        <TextMainTitle
          :data="{ title: '이 도서의 다른 기록 보기', size: 28 }"
        />
      </section>

      <section class="book-detail-other-recode-sub-title-container">
        <TextMainTitle :data="{ title: '🌲 트리', size: 24 }" />
      </section>

      <section class="book-detail-other-tree-list-container">
        <h1 class="none">트리 리스트</h1>
        <section class="book-detail-other-tree-card-container">
          <CardSnsCardTreeList :list="bookTreeCards" />
        </section>
      </section>

      <section class="book-detail-other-recode-sub-title-container">
        <TextMainTitle :data="{ title: '📖 회고록', size: 24 }" />
      </section>

      <section class="book-detail-other-tree-list-container">
        <h1 class="none">회고록 리스트</h1>
        <section class="book-detail-other-tree-card-container">
          <CardSnsCardTreeList :list="bookMemoirCards" />
        </section>
      </section>

      <section class="book-detail-other-recode-sub-title-container">
        <TextMainTitle :data="{ title: '🌿 리프', size: 24 }" />
      </section>

      <section class="book-detail-other-tree-list-container">
        <h1 class="none">리프 리스트</h1>
        <section class="book-detail-other-tree-card-container">
          <CardSnsCardTreeList :list="bookleafCards" />
        </section>
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
      <section class="short-btn-container">
        <h4 class="none">트리 생성 버튼</h4>
        <ButtonBtnShortAGreen
          :link="`/tree/book/auto/${bookId}/new`"
          :text="`트리
        생성`"
        />
      </section>

      <NavNavigationBar :active="'home'" />
    </section>
  </aside>
</template>

<style scoped></style>
