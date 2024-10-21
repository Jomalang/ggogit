

<script setup lang="ts">

import TopBarBack from "@/components/top-bar/TopBarBack.vue";
import LogPath from "@/components/bar/LogPath.vue";
import TextMainTitleRight from "@/components/text/TextMainTitleRight.vue";
import BtnShortAGreen from "@/components/button/BtnShortAGreen.vue";
import BranchStateMain from "@/components/bot-bar/BranchStateMain.vue";
import {Branch, TextBtnGreenProps, TextMainTitleRightProps} from "@/types/types";

import { ref } from 'vue';
import {useRoute} from "vue-router";

// leaf_id 가져오기
const route = useRoute();
const leafId = route.query.leaf_id as string;

const branch = ref<Branch>({
  branch: "브랜치 이름",
  log: 0,
  like: 0,
  view: 0,
  date: "2024-10-19",
});

setTimeout(() => {
  branch.value = {
    branch: "브랜치 이름",
    log: 10,
    like: 10,
    view: 10,
    date: "2024-10-19",
  };
}, 5_000);

const title = ref<TextMainTitleRightProps>({
  title: "2024년 10월 19일",
  size: 24,
});

const btn = ref<TextBtnGreenProps>({
  text: "리프 생성",
  link: "/app/leaf/create",
});

</script>

<template>
  <header class="log-list-header-container">
    <h1 class="none">리프 목록</h1>
    <section>
      <h1 class="none">뒤로가기 상단바</h1>
      <TopBarBack title="브랜치" link=""></TopBarBack>
    </section>

    <section>
      <h1 class="none">현재 포커싱 리프 정보</h1>
      <section class="log-path-container">
        <h1 class="none">리프 경로</h1>
        <LogPath tree="트리이름" branch="브랜치 이름" leaf="리프 이름"></LogPath>
      </section>

      <section class="log-list-date-title-container">
        <h1 class="none">리프 날짜</h1>
        <TextMainTitleRight :title="title.title" :size="title.size"></TextMainTitleRight>
      </section>
    </section>
  </header>

  <main>

    <section class="log-list-container">
      <h1 class="none">리프 리스트</h1>
      <!-- 리프 리스트 -->
    </section>

  </main>

  <aside class="log-list-bot-bar-container">
    <h1 class="none">브랜치 정보 알림 하단 바</h1>
    <section class="log-list-bot-btn-container">
      <h1 class="none">리프 생성 버튼</h1>
      <BtnShortAGreen :btn="btn"></BtnShortAGreen>
    </section>

    <section class="log-list-bot-bar-info-container">
      <h1 class="none">브랜치 정보 하단 바</h1>
      <BranchStateMain :branch="branch"></BranchStateMain>
    </section>
  </aside>

</template>

<style scoped>
.log-list-header-container {
  width: 100%;
  position: fixed;
  top: 0;
  z-index: 10;
}

.log-path-container {
  width: auto;
  padding: 0 24px 0 24px;
  background-color: var(--main2, #e5eddb);
}

.log-list-date-title-container {
  margin: 14px 24px 0 24px;
  position: absolute;
  right: 0;
  z-index: 20;
}

.log-list-container {
  margin: 600px 0;
  width: 100%;
}

.log-list-bot-bar-container {
  width: 100%;
  position: fixed;
  bottom: 0;
  z-index: 10;
}

.log-list-bot-bar-info-container {
  z-index: 10;
}

.log-list-bot-btn-container {
  margin: 0 24px 16px 24px;
  display: flex;
  justify-content: flex-end;
  align-items: center;
}

</style>