<script setup lang="ts">

import TopBarBack from "@/components/top-bar/TopBarBack.vue";
import TextMainTitle from "@/components/text/TextMainTitle.vue";
import TextBox from "@/components/input/TextBox.vue";
import InputSelectBox from "@/components/input/InputSelectBox.vue";
import TextareaBox from "@/components/input/TextareaBox.vue";
import InputVisibility from "@/components/input/InputVisibility.vue";
import SubmitBtnFullBar from "@/components/button/SubmitBtnFullBar.vue";
import NavigationBar from "@/components/nav/NavigationBar.vue";
import LInkOneImageDetail from "@/components/link/LInkOneImageDetail.vue";
import TextBookInfo from "@/components/text/TextBookInfo.vue";
import TreeInfoCard from "@/components/card/TreeInfoCard.vue";

</script>

<template>
  <header>
    <h1 class="none">도서 선택 후 트리 생성</h1>

    <section>
      <h2 class="none">트리 생성</h2>
      <TopBarBack title="트리 생성" link=""></TopBarBack>
    </section>
  </header>

  <main >
    <h2 class="none">선택한 도서</h2>
    <section class="tree-book-reg-search-book__title-container">
      <h3 class="none">도서 TEXT 컨테이너</h3>
      <TextMainTitle title="선택한 도서" size="28"></TextMainTitle>
    </section>
    <section>
      <section class="tree-reg-cover-info__container">
        <h3 class="none">도서 커버 및 도서 정보</h3>
        <section class="tree-reg-cover__container">
          <h4 class="none">도서 커버</h4>
          <LInkOneImageDetail href="" src=""></LInkOneImageDetail>
        </section>
        <section class="tree-reg-book-info__container">
          <h4 class="none">도서 정보</h4>
          <!-- <div th:replace="~{fragments/text :: text-book-info(*{category},*{title},*{author},null,*{publisher})}"></div>-->
          <TextBookInfo seed="" title="" author="" translators="" publisher=""></TextBookInfo>
        </section>
      </section>
      <section class="tree-info-card__container">
        <h3 class="none">선택 도서 기본 정보</h3>
        <TreeInfoCard data="null"></TreeInfoCard>
      </section>
    </section>

    <form action="/tree/book/reg?auto=true" name="form"  method="POST" enctype="multipart/form-data">

      <section class="register__input-container">
        <h3 class="none">트리 이름 입력</h3>
        <TextBox label="*트리 이름" name="treeTitle" placeholder="트리 이름을 입력해 주세요."></TextBox>
      </section>

      <section class="register__input-container">
        <h3 class="none">카테고리 선택</h3>
        <!--todo: 도서 카테고리-->
        <InputSelectBox id="category" label="*카테고리" name="category" placeholder="카테고리를 선택해 주세요."></InputSelectBox>
      </section>

      <section class="register__input-container">
        <h3 class="none">트리 설명글</h3>
        <TextareaBox label="*설명글" name="description" placeholder="트리를 설명하는 글을 작성해 주세요"></TextareaBox>
      </section>

      <section class="register__input-container">
        <h3 class="none">공개성 선택</h3>
        <InputVisibility name="visibility"></InputVisibility>
      </section>

      <section class="register__input-container--last">
        <h3 class="none">트리 생성 버튼</h3>
        <SubmitBtnFullBar text="꼬깃 시작하기"></SubmitBtnFullBar>
      </section>

<!-- 데이터 바인딩 -->
<!--      <section class="none">-->
<!--        <label><input name="bookId" th:value="${book.id}"></label>-->
<!--        <label><input name="bookCategoryId" value="1"></label> &lt;!&ndash;todo: 도서 카테고리 개발예정 &ndash;&gt;-->
<!--        <label><input name="bookTitle" th:value="${book.title}"></label>-->
<!--        <label><input name="imageFile" th:value="${book.imageFile}"></label>-->
<!--        <label><input name="author" th:value="${book.author}"></label>-->
<!--        <label><input name="publisher" th:value="${book.publisher}"></label>-->
<!--        <label><input name="totalPage" th:value="${book.totalPage}"></label>-->
<!--        <label><input name="seedId" value="1"></label>-->
<!--        <label><input name="memberId" value="14"></label> &lt;!&ndash;todo: session storage or cookies or JWT 확인 필요 &ndash;&gt;-->
<!--      </section>-->
    </form>
  </main>

  <aside>
    <section>
      <h2 class="none">네비게이션 바</h2>
      <NavigationBar active="home"></NavigationBar>
    </section>
  </aside>

</template>

<style scoped>

</style>