<script setup lang="ts">

import TopBarBack from "@/components/top-bar/TopBarBack.vue";
import TextMainTitle from "@/components/text/TextMainTitle.vue";
import BookInputImg from "@/components/input/BookInputImg.vue";
import TextBox from "@/components/input/TextBox.vue";
import TextareaBox from "@/components/input/TextareaBox.vue";
import InputVisibility from "@/components/input/InputVisibility.vue";
import SubmitBtnFullBar from "@/components/button/SubmitBtnFullBar.vue";
import NavigationBar from "@/components/nav/NavigationBar.vue";
</script>

<template>
  <header>
    <h1 class="none">기타 트리 생성 페이지</h1>
    <section class="tob-bar-back-container">
      <h1 class="none">트리 생성 상단 바</h1>
      <TopBarBack title="트리 생성" link=""></TopBarBack>
    </section>
  </header>

  <main>
    <section class="book-tree-input-form-container">
      <h1 class="none">기타 정보 입력</h1>

      <section class="select-title-container">
        <TextMainTitle title="트리 이름" size="28"></TextMainTitle>
      </section>

      <form id="book-tree-input-form-id" class="book-tree-input-form" action="/tree/etc/reg"
            method="POST" enctype="multipart/form-data">

        <section class="book-tree-input-form__photo-container">
          <h1 class="none">기타 이미지 입력</h1>
          <BookInputImg></BookInputImg>
        </section>

        <section class="input-form__input-container">
          <h1 class="none">트리이름 입력</h1>
          <TextBox label="*트리 이름" name="treeTitle" placeholder="트리 이름을 입력해주세요"></TextBox>
        </section>

        <section class="book-tree-input-form__large-input-container">
          <h1 class="none">설명글 작성</h1>
          <TextareaBox label="*트리설명" placeholder="트리를 설명할 글을 작성해 주세요" name="description"></TextareaBox>
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
          <label><input name="memberId" value="14"></label>
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