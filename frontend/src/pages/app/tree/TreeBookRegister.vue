<script setup lang="ts">

import TopBarBack from "@/components/top-bar/TopBarBack.vue";
import TextMainTitle from "@/components/text/TextMainTitle.vue";
import DefaultData from "@/components/input/DefaultData.vue";
import BookInputImg from "@/components/input/BookInputImg.vue";
import TextBox from "@/components/input/TextBox.vue";
import InputSelectBox from "@/components/input/InputSelectBox.vue";
import FilterTab from "@/components/tab-filter/FilterTab.vue";
import TextareaBox from "@/components/input/TextareaBox.vue";
import InputVisibility from "@/components/input/InputVisibility.vue";
import SubmitBtnFullBar from "@/components/button/SubmitBtnFullBar.vue";
import NavigationBar from "@/components/nav/NavigationBar.vue";
import TextNumberBox from "@/components/input/TextNumberBox.vue";
</script>

<template>
  <header>
    <h1 class="none">도서 트리 생성 페이지</h1>
    <section class="tob-bar-back-container">
      <h1 class="none">트리 생성 상단 바</h1>
      <TopBarBack title="트리 생성" link=""></TopBarBack>
    </section>
  </header>

  <main>
    <section class="book-tree-input-form-container">
      <h1 class="none">도서 정보 입력</h1>

      <section class="select-title-container">
        <TextMainTitle title="도서 직접 입력" size="28"></TextMainTitle>
      </section>

      <form
          id="book-tree-input-form-id"
          class="book-tree-input-form"
          action="/tree/book/reg?auto=false"
          method="post"
          enctype="multipart/form-data"
      >
        <section>
          <h1 class="none">씨앗 카테고리</h1>
          <DefaultData name="seedCategoryType" values=""></DefaultData>
        </section>

        <section class="book-tree-input-form__photo-container">
          <h1 class="none">도서 이미지 입력</h1>
          <bookInputImg></bookInputImg>
        </section>

        <section class="input-form__input-container">
          <h1 class="none">도서 이름 입력</h1>
          <TextBox label="*도서 이름" name="bookTitle" placeholder="도서 이름을 입력해주세요"></TextBox>
        </section>

        <section class="input-form__input-container">
          <h1 class="none">지은이 입력</h1>
          <TextBox label="*지은이" name="author" placeholder="지은이를 입력해주세요"></TextBox>
        </section>

        <section class="input-form__input-container">
          <h1 class="none">출판사 입력</h1>
          <TextBox label="*출판일" name="publishDate" placeholder="출판일을 입력해주세요"></TextBox>
        </section>

        <section class="input-form__input-container">
          <h1 class="none">총페이지 입력</h1>
          <TextNumberBox label="*총페이지" name="totalPage" min="1" placeholder="총페이지를 입력해주세요"></TextNumberBox>
        </section>

        <section class="input-form__input-container">
          <h1 class="none">카테고리 선택</h1>
          <InputSelectBox id="filter-tab-btn" label="*카테고리 선택"></InputSelectBox>
          <section id="filter-bg-blur" class="filter-tab-container none">
            <h1 class="none">카테고리 언더바 필터</h1>
            <!-- (label='*카테고리', name='bookCategoryType', values=${categories}) -->
            <FilterTab data=""></FilterTab>
          </section>
        </section>

        <section class="input-form__input-container">
          <h1 class="none">트리이름 입력</h1>
          <TextBox label="*트리 이름" name="treeTitle" placeholder="트리 이름을 입력해주세요"></TextBox>
        </section>

        <section class="book-tree-input-form__large-input-container">
          <h1 class="none">설명글 작성</h1>
          <TextareaBox label="*트리 설명" name="description" placeholder="트리를 설명할 글을 작성해 주세요"></TextareaBox>
        </section>

        <section class="input-form__input-container">
          <h1 class="none">공개성 선택</h1>
          <InputVisibility name="visibility"></InputVisibility>
        </section>

        <section class="book-tree-submit-container">
          <h1 class="none">트리 생성 버튼</h1>
          <SubmitBtnFullBar text="트리 생성" id="submit-btn"></SubmitBtnFullBar>
        </section>
        <section class="none">
          <label><input name="bookCategoryId" value="1"></label> <!--TODO: 도서 카테고리 개발예정 -->
          <label><input name="memberId" value="1"></label><!--TODO: session storage or cookies or JWT 확인 필요 -->
        </section>

      </form>
    </section>
  </main>

  <aside class="nav-container">
    <h1 class="none">네비게이션 하단</h1>
    <NavigationBar active="home"></NavigationBar>
  </aside>
</template>

<style scoped>

</style>