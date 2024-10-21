<script setup lang="ts">

import TopBarBack from "@/components/top-bar/TopBarBack.vue";
import TagSearchOrRegisterBar from "@/components/input/TagSearchOrRegisterBar.vue";
import TagSelectedList from "@/components/tag/TagSelectedList.vue";
import TopBarTagInfo from "@/components/top-bar/TopBarTagInfo.vue";
import TagListBox from "@/components/tag/TagListBox.vue";
</script>

<template>
  <header>
    <h1 class="none">태그 이름</h1>
    <TopBarBack title="태그 이름" link=""></TopBarBack>
  </header>
  <main>
    <section>
      <h2 class="none">태그 검색</h2>
      <TagSearchOrRegisterBar tag="null"></TagSearchOrRegisterBar>
    </section>

    <section>
      <h2 class="none">태그 선택 태그</h2>
      <TagSelectedList tags="null"></TagSelectedList>
    </section>

    <section>
      <h2 class="none">태그 기능 알림</h2>
      <TopBarTagInfo text="옵션 선택 또는 생성"></TopBarTagInfo>
    </section>

    <section>
      <h2 class="none">태그 리스트</h2>
      <TagListBox tags="null"></TagListBox>
    </section>
  </main>
</template>

<style scoped>

</style>