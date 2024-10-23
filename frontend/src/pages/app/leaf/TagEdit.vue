<script setup lang="ts">

import TagSearchOrRegisterBar from "@/components/input/TagSearchOrRegisterBar.vue";
import TopBarTagEdit from "@/components/top-bar/TopBarTagEdit.vue";
import TopBarTagInfo from "@/components/top-bar/TopBarTagInfo.vue";
import DefaultData from "@/components/input/DefaultData.vue";
import DeleteFormBtn from "@/components/input/DeleteFormBtn.vue";

</script>

<template>
  <header>
    <h1 class="none">태그 수정 페이지</h1>

    <form action="/tag/edit" method="post">
      <section>
        <h2 class="none">뒤로가기 및 완료 바</h2>
        <TopBarTagEdit tag="" member-id=""></TopBarTagEdit>
      </section>

      <section class="none">
        <h2>태그 아이디</h2>
        <label><input type="text" name="id"/></label>
      </section>

      <section>
        <h2 class="none">태그 입력</h2>
        <TagSearchOrRegisterBar tag="tag"></TagSearchOrRegisterBar>
      </section>
    </form>
  </header>

  <main>
    <section>
      <h2 class="none">태그 기능 알림</h2>
      <TopBarTagInfo></TopBarTagInfo>
    </section>

    <form id="tag-delete-form-id" action="/tag/delete" method="post">
      <section>
        <h1 class="none">삭제 데이터</h1>
        <DefaultData name="" values=""></DefaultData>
      </section>

      <section>
        <h1 class="none">삭제 버튼</h1>
        <DeleteFormBtn></DeleteFormBtn>
      </section>
    </form>
  </main>

</template>

<style scoped>

</style>