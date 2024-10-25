<script setup lang="ts"></script>

<template>
  <header>
    <h1 class="none">도서 세부 정보</h1>
    <section class="book-detail-top-bar-container">
      <h1 class="none">뒤로가기 상단바</h1>
      <TopBarClearBack :link="`javascript:history.back()`" />
    </section>
  </header>

  <main>
    <section class="book-detail-bg-container">
      <h1 class="none">도서 배경 이미지</h1>
      <BookDetail :imgSrc="`/img/book/detail/book.jpg`" />
      <section class="book-detail-like-bar-container">
        <h1 class="none">좋아요 및 공유</h1>
        <LikeShare
          :likeLink="`javascript:history.back()`"
          :shareLink="`javascript:history.back()`"
        />
      </section>
    </section>

    <section class="book-detail-book-info-container">
      <h1 class="none">도서 제목 및 저자 정보</h1>
      <TextBookInfo
        :seed="`시/에세이`"
        :title="`무정형의 삶`"
        :author="`올라프`"
        :translators="null"
        :publisher="`위즈덤하우스`"
      />
    </section>

    <section class="book-detail-book-info-container">
      <TreeInfoCard :data="{ date: '2024-10-25', pageCount: 200 }" />
      <h1 class="none">도서 기본 정보</h1>
    </section>

    <section class="book-detail-comment-container">
      <h1 class="none">댓글</h1>
      <Comment :commentCount="1" profileImg="null" />
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
        <TextMainTitle :title="`나의 트리`" :size="28" />
      </section>

      <section class="book-detail-my-tree-list-container">
        <h1 class="none">나의 트리 리스트</h1>
        <section class="book-detail-my-tree-card-container">
          <CardTreeList :list="null" />
        </section>
      </section>
    </section>

    <section class="book-detail-other-recode-container">
      <h1 class="none">도서의 다른 기록 보기</h1>
      <section class="book-detail-other-recode-title-container">
        <TextMainTitle :title="`다른 기록 보기`" :size="28" />
      </section>

      <section class="book-detail-other-recode-sub-title-container">
        <TextMainTitle :title="`트리 리스트`" :size="24" />
      </section>

      <section class="book-detail-other-tree-list-container">
        <h1 class="none">트리 리스트</h1>
        <section class="book-detail-other-tree-card-container">
          <SnsCardTreeList :list="`type1`" />
        </section>
      </section>

      <section class="book-detail-other-recode-sub-title-container">
        <TextMainTitle :title="`회고록 리스트`" :size="24" />
      </section>

      <section class="book-detail-other-tree-list-container">
        <h1 class="none">회고록 리스트</h1>
        <section class="book-detail-other-tree-card-container">
          <SnsCardTreeList :list="`type2`" />
        </section>
      </section>

      <section class="book-detail-other-recode-sub-title-container">
        <TextMainTitle :title="`리프 리스트`" :size="24" />
      </section>

      <section class="book-detail-other-tree-list-container">
        <h1 class="none">리프 리스트</h1>
        <section class="book-detail-other-tree-card-container">
          <SnsCardTreeList :list="`type3`" />
        </section>
      </section>
    </section>
  </main>

  <aside class="book-detail-bot-aside-container">
    <h1 class="none">트리 생성 하단 바</h1>
    <div th:replace="fragments/bot-bar :: create-tree"></div>
  </aside>

  <footer th:replace="~{fragments/footer :: footer}"></footer>
</template>

<style scoped></style>
