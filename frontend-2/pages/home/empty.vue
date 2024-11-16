<script setup>
const memberDetail = useMemberStore();
const { _nickname: username } = storeToRefs(memberDetail);
</script>

<template>
  <header>
    <h1 class="none">나의 트리</h1>
    <section class="header-search-container">
      <h1 class="none">나의 트리 검색 링크</h1>
      <HeaderSearchLink />
    </section>
  </header>

  <main>
    <section class="my-tree-container">
      <h1 class="none">나의 트리 정보</h1>

      <section class="my-tree-title-container">
        <TextMainTitle :data="{ title: `${username}님의 트리`, size: 28 }" />
      </section>

      <section class="book-img-container">
        <h1 class="none">트리 이미지</h1>
        <BackgroundBgNoTreeBook />
      </section>
    </section>

    <section class="margin-bottom160">
      <TextInfo
        text="현재 기록중인 트리가 없습니다"
        boldText="트리를 생성해주세요"
      />
    </section>
  </main>

  <footer>
    <Footer :noticeText="`개발중입니다.`" />
  </footer>


  <aside class="nav-container">
    <section class="short-btn-container">
      <h4 class="none">트리 생성 버튼</h4>
      <ButtonBtnShortAGreen :link="`/tree/seed`" :text="`트리 생성`" />
    </section>
    <h1 class="none">네비게이션 하단</h1>
    <NavNavigationBar :active="home" />
  </aside>
</template>

<style scoped></style>
