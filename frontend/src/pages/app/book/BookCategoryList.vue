<script setup lang="ts">

import TopBarBack from "@/components/top-bar/TopBarBack.vue";
import TagSelectedList from "@/components/tag/TagSelectedList.vue";
import TopBarTagInfo from "@/components/top-bar/TopBarTagInfo.vue";
import TagListBox from "@/components/tag/TagListBox.vue";

</script>

<template>
  <header>
    <h1 class="none">도서 카테고리 이름</h1>
    <TopBarBack title="도서 카테고리" link=""></TopBarBack>
  </header>
  <main>
    <section>
      <h2 class="none">도서 카테고리 검색</h2>
      <div class="tree-input-text__form">
        <label class="tree-input-text__subject">
          <input
              v-if="tag"
              id="tree-input-text__rectangle-id"
              name="name"
              class="tree-input-text__rectangle"
              placeholder="도서 카테고리를 입력하세요."
              :value="tag.name"
          />
          <input
              v-else
              id="tree-input-text__rectangle-id"
              name="name"
              class="tree-input-text__rectangle"
              placeholder="도서 카테고리를 입력하세요."
          />
        </label>
      </div>
    </section>

    <section>
      <h2 class="none">도서 카테고리 선택</h2>
      <TagSelectedList tags="null"></TagSelectedList>
    </section>

    <section>
      <h2 class="none">도서 카테고리 기능 알림</h2>
      <TopBarTagInfo text="리스트"></TopBarTagInfo>
    </section>

    <section>
      <h2 class="none">도서 카테고리 리스트</h2>
      <TagListBox tags="null"></TagListBox>
    </section>
  </main>
</template>

<style scoped>

</style>