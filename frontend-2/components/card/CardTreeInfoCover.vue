<script setup>


const {data} = defineProps(['data']);

const img = ref(false)

onMounted(async () => {
  await console.log(data.coverImageName);
  if(data.coverImageName){
    img.value = true;
  }
});
</script>

<template>
  <div class="card-tree-info-cover-frame">
    <div class="card-tree-info-cover">
      <img v-if="img" class="card-tree-info-cover" :src="data.coverImageName" alt="cover">
      <img v-else class="card-tree-info-cover" src="/public/svg/tree-icon--white.svg" alt="cover">
    </div>
    <div class="card-tree-title-frame">
      <p class="card-tree-title">{{ data.title }}</p>
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