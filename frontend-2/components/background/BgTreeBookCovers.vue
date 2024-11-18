<script setup>
const props = defineProps({
  treeInfoList: {
    type: Array,
    required: true,
  },
});
const treeInfoList = ref(props.treeInfoList);
watchEffect(() => {
  treeInfoList.value = props.treeInfoList;
});
</script>

<template>
  <div class="tree-book-bg">
    <ul class="tree-book-bg__list">
      <li
        :class="`mid__item ${index}`"
        v-for="(tree, index) in treeInfoList"
        :key="index"
      >
        <a :href="`/tree/detail/${tree.treeId}`">
          <img
            class="mid__img"
            :src="useGetImageUrl(tree.coverImageName)"
            alt="도서 예시 이미지"
          />
        </a>
      </li>
      <li v-if="treeInfoList.length === 1" class="mid__item 1">
        <NuxtLink :to="`/tree/seed`">
          <img
            class="mid__img"
            src="~/assets/png/tree-book-blank.png"
            alt="도서 예시 이미지"
          />
        </NuxtLink>
      </li>
      <li v-if="treeInfoList.length <= 2" class="mid__item 2">
        <NuxtLink :to="`/tree/seed`">
          <img
            class="mid__img"
            src="~/assets/png/tree-book-blank.png"
            alt="도서 예시 이미지"
          />
        </NuxtLink>
      </li>
      <li
        :class="`mid__item ${index}`"
        v-for="(tree, index) in treeInfoList"
        :key="index"
      >
        <a :href="`/tree/detail/${tree.treeId}`">
          <img
            class="mid__img"
            :src="useGetImageUrl(tree.coverImageName)"
            alt="도서 예시 이미지"
          />
        </a>
      </li>
      <li v-if="treeInfoList.length === 1" class="mid__item 1">
        <NuxtLink :to="`/tree/seed`">
          <img
            class="mid__img"
            src="~/assets/png/tree-book-blank.png"
            alt="도서 예시 이미지"
          />
        </NuxtLink>
      </li>
      <li v-if="treeInfoList.length <= 2" class="mid__item 2">
        <NuxtLink :to="`/tree/seed`">
          <img
            class="mid__img"
            src="~/assets/png/tree-book-blank.png"
            alt="도서 예시 이미지"
          />
        </NuxtLink>
      </li>
      <li
        :class="`mid__item ${index}`"
        v-for="(tree, index) in treeInfoList"
        :key="index"
      >
        <a :href="`/tree/detail/${tree.treeId}`">
          <img
            class="mid__img"
            :src="useGetImageUrl(tree.coverImageName)"
            alt="도서 예시 이미지"
          />
        </a>
      </li>
      <li v-if="treeInfoList.length === 1" class="mid__item 1">
        <NuxtLink :to="`/tree/seed`">
          <img
            class="mid__img"
            src="~/assets/png/tree-book-blank.png"
            alt="도서 예시 이미지"
          />
        </NuxtLink>
      </li>
      <li v-if="treeInfoList.length <= 2" class="mid__item 2">
        <NuxtLink :to="`/tree/seed`">
          <img
            class="mid__img"
            src="~/assets/png/tree-book-blank.png"
            alt="도서 예시 이미지"
          />
        </NuxtLink>
      </li>
    </ul>
  </div>
</template>

<style scoped>
.tree-book-bg {
  /* 캐러셀 위해 90vw로 고정 */
  display: flex;
  overflow: hidden;
  width: 100%;
}

.tree-book-bg__list {
  --bg-10: calc(100% / 10);
  z-index: 1;
  display: flex;
  align-items: last baseline;
  justify-content: space-between;
  padding: 0;
  margin: 0;
  gap: var(--bg-10);
  /*캐러셀의 부드러운 이동을 위한 최적화*/
  will-change: transform;
}
.tree-book-bg__list--transition {
  transition: all, 0.6s, ease-out;
}

.mid__img {
  width: var(--mid-width);
  transition: width 0.2s linear;
}

.center__img {
  width: var(--center-width);
}
</style>
