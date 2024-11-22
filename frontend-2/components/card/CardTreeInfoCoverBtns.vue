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
    <div class="card-tree-title-frame">
      <div class="transparent-btn-frame">
        <div v-if="data.seedId === 1">
          <NuxtLink :to="`/tree/${data.treeId}/book/edit`" class="top-bar__transparent-setting-btn"></NuxtLink>
        </div>
        <div v-else>
          <NuxtLink :to="`/tree/${data.treeId}/etc/edit`" class="top-bar__transparent-setting-btn"></NuxtLink>
        </div>
        <div @click="emit('isDelete')" class="top-bar__transparent-delete-btn"></div>
      </div>
      <p v-if="data.bookTitle" class="card-book-title">
        {{ data.bookTitle }}
      </p>
      <p v-if="data.title" class="card-tree-title">{{ data.title }}</p>
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
  padding-bottom: 10px;
}
.card-tree-title-frame {
  width: 100%;
  height: 140%;
  padding-left: 5px;
}

.card-book-title {
  font-size: 14px;
  font-weight: var(--medium);
  line-height: var(--line-height-main);
  letter-spacing: var(--letter-spacing-main);
}

.transparent-btn-frame{
  margin-bottom: 14px;
  display: flex;
  align-items: center;
  justify-content: flex-end;
  gap: 15px;
}
.top-bar__transparent-setting-btn {
  width: 25px;
  height: 25px;
  display: flex;
  background: transparent url("~/assets/png/edit_document.png") no-repeat center;
  background-size: contain;
  align-items: center;
}
.top-bar__transparent-delete-btn {
  width: 25px;
  height: 25px;
  display: flex;
  background: transparent url("~/assets/png/delete--gray.png") no-repeat center;
  background-size: contain;
  padding: 0;
  border: none;
  cursor: pointer;
}
</style>
