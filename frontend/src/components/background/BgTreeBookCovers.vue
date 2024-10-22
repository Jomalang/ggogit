<script setup>
const props = defineProps({
  trees: Array,
});
</script>

<template>
  <div class="tree-book-bg">
    <ul class="tree-book-bg__list">
      <li
        class="mid__item"
        v-for="(tree, index) in trees"
        :key="index"
        :class="index"
      >
        <a :href="`/tree/detail/${tree.treeId}`">
          <img
            class="mid__img"
            :src="tree.coverImageName"
            alt="도서 예시 이미지"
          />
        </a>
      </li>
      <li v-if="trees.length === 1" class="mid__item 1">
        <a href="/seed/index">
          <img
            class="mid__img"
            src="/public/png/tree-book-blank.png"
            alt="도서 예시 이미지"
          />
        </a>
      </li>
      <li v-if="trees.length <= 2" class="mid__item 2">
        <a href="/seed/index">
          <img
            class="mid__img"
            src="/public/png/tree-book-blank.png"
            alt="도서 예시 이미지"
          />
        </a>
      </li>
      <li
        class="mid__item"
        v-for="(tree, index) in trees"
        :key="index"
        :class="index"
      >
        <a :href="`/tree/detail/${tree.treeId}`">
          <img
            class="mid__img"
            :src="tree.coverImageName"
            alt="도서 예시 이미지"
          />
        </a>
      </li>
      <li v-if="trees.length === 1" class="mid__item 1">
        <a href="/seed/index">
          <img
            class="mid__img"
            src="/public/png/tree-book-blank.png"
            alt="도서 예시 이미지"
          />
        </a>
      </li>
      <li v-if="trees.length <= 2" class="mid__item 2">
        <a href="/seed/index">
          <img
            class="mid__img"
            src="/public/png/tree-book-blank.png"
            alt="도서 예시 이미지"
          />
        </a>
      </li>
      <li
        class="mid__item"
        v-for="(tree, index) in trees"
        :key="index"
        :class="index"
      >
        <a :href="`/tree/detail/${tree.treeId}`">
          <img
            class="mid__img"
            :src="tree.coverImageName"
            alt="도서 예시 이미지"
          />
        </a>
      </li>
      <li v-if="trees.length === 1" class="mid__item 1">
        <a href="/seed/index">
          <img
            class="mid__img"
            src="/public/png/tree-book-blank.png"
            alt="도서 예시 이미지"
          />
        </a>
      </li>
      <li v-if="trees.length <= 2" class="mid__item 2">
        <a href="/seed/index">
          <img
            class="mid__img"
            src="/public/png/tree-book-blank.png"
            alt="도서 예시 이미지"
          />
        </a>
      </li>
    </ul>
  </div>
</template>

<style scoped>
.tree-book-bg {
  /* 캐러셀 위해 90vw로 고정 */
  display: flex;
  overflow: hidden;
  width: 90vw;
  height: 50vw;
}

.tree-book-bg__list {
  z-index: 1;
  display: flex;
  align-items: last baseline;
  justify-content: space-between;
  padding: 0;
  margin: 0;
  gap: 10vw;
  /*캐러셀의 부드러운 이동을 위한 최적화*/
  will-change: transform;
}
.tree-book-bg__list--transition {
  transition: all, 0.6s, ease-out;
}
.mid__item {
}

.mid__img {
  width: 20vw;
  transition: width 0.2s linear;
}

.center__img {
  width: 30vw;
}
</style>
