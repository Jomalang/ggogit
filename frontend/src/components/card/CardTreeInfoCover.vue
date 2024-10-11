<script setup lang="ts" xmlns:th="http://www.w3.org/1999/xhtml">

interface CardTreeInfoCoverProps {
  src: string;
  treeTitle: string;
  bookTitle: string;
}

const data: CardTreeInfoCoverProps = {
  src: null,
  treeTitle: 'Tree Title',
  bookTitle: 'Book Title'
}

const props = defineProps<{
  data: CardTreeInfoCoverProps
}>();

</script>

<template>
  <!-- (src,treetitle,booktitle) -->
  <div class="card-tree-info-cover-frame">
    <div class="card-tree-info-cover">
      <img v-if="data.src" class="card-tree-info-cover" :src="data.src" alt="cover">
      <img v-else class="card-tree-info-cover" src="/public/svg/tree-icon--white.svg" alt="cover">
    </div>
    <div class="card-tree-title-frame">
      <p class="card-tree-title">{{ data.treeTitle }}</p>
      <p class="card-book-title">{{ data.bookTitle }}</p>
    </div>
  </div>
</template>

<style scoped>
.card-tree-info-cover-frame {
  display: flex;
  padding-left: 64px;
  height: 70px;
  align-items: flex-end;
  position: relative;
}

.card-tree-info-cover {
  width: 60px;
  height: 90px;
  background-color: var(--main1);
  border-radius: 8px;
  position: absolute;
  top: 0;
  left: 0;
}

.card-tree-title {
  font-size: 16px;
  font-weight: var(--bold);
  line-height: var(--line-height-main);
  letter-spacing: var(--letter-spacing-main);
}
.card-tree-title-frame {
  padding-left : 5px;
}

.card-book-title {
  font-size: 14px;
  font-weight: var(--medium);
  line-height: var(--line-height-main);
  letter-spacing: var(--letter-spacing-main);
}
</style>