<script setup>
const { data } = defineProps(["data"]);
</script>

<template>
  <div class="card-tree-info-cover-frame">
    <NuxtLink :to="`/book/${data.bookId}`">
      <div class="card-tree-info-cover">
        <img
          class="card-tree-info-cover"
          :src="
            data.coverImageName
              ? useGetImageUrl(data.coverImageName, 'book')
              : useGetImageUrl(data.treeImage, 'tree')
          "
          alt="cover"
        />
      </div>
    </NuxtLink>
    <NuxtLink :to="`/tree/${data.treeId}`">
      <div class="card-tree-title-frame">
        <p v-if="data.bookTitle" class="card-book-title">
          {{ data.bookTitle }}
        </p>
        <p v-if="data.title" class="card-tree-title">{{ data.title }}</p>
      </div>
    </NuxtLink>
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
  padding-bottom: 10px;
}
.card-tree-title-frame {
  padding-left: 5px;
}

.card-book-title {
  font-size: 14px;
  font-weight: var(--medium);
  line-height: var(--line-height-main);
  letter-spacing: var(--letter-spacing-main);
  display: -webkit-box; /* Flexbox 기반 레이아웃 */
  -webkit-line-clamp: 2; /* 최대 줄 수 설정 (여기서는 2줄) */
  -webkit-box-orient: vertical; /* 박스 방향을 세로로 설정 */
  overflow: hidden; /* 넘치는 텍스트 숨김 */
  text-overflow: ellipsis; /* 잘린 부분에 '...' 표시 */
}
</style>
