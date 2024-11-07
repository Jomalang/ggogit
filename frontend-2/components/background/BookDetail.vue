<script setup>
const { data } = defineProps(["data"]);
const config = useRuntimeConfig();

const backImageUrl = useGetImageUrl(data.imageFile, "book");
/*
imageFile: String,
src: String,
*/

const backgroundImage = computed(() => {
  return {
    backgroundImage: `linear-gradient(rgba(0, 0, 0, 0.4), rgba(0, 0, 0, 0.4)), url('')`,
  };
});
</script>

<template>
  <div id="bg-book-detail-box-id" class="bg-book-detail-box">
    <img class="bg-book-detail__image" :src="backImageUrl" alt="도서 이미지" />
    <div
      id="bg-book-detail__front-img-box-id"
      class="bg-book-detail__front-img-box"
    >
      <img
        class="bg-book-detail__front-img"
        :src="backImageUrl"
        alt="도서 이미지"
      />
    </div>
    <div class="user-tree-user-info-container" :style="backgroundImage"></div>
  </div>
</template>

<style scoped>
.bg-book-detail-box {
  width: 100%;
  height: 404px;
  position: relative;
  overflow: hidden;
}

.bg-book-detail__image {
  width: 100%;
  object-fit: cover;
  object-position: top;
  filter: blur(10px) brightness(0.8);
  transform: scale(1.5);
}

.bg-book-detail__front-img-box {
  position: absolute;
  width: 170px;
  left: 50%;
  bottom: 50px;
  transform: translateX(-50%);
  transition: all 0.5s ease;
}

.bg-book-detail__front-img-box--comment-active {
  position: absolute;
  width: 120px;
  left: 50%;
  top: 40px;
  bottom: auto;
  transform: translateX(-50%);
}

.bg-book-detail__front-img {
  width: 100%;
  box-shadow: 4px 4px 8px rgba(0, 0, 0, 0.25);
}
</style>
