<script setup>
const props = defineProps({
  href: String,
  src: String,
});
</script>

<template>
  <!--link__one-imgae-detail(src,href)-->
  <div>
    <NuxtLink class="link-cover-img-anker" :to="href">
      <img class="link-cover-img" :src="useGetImageUrl(src)" alt="표지" />
      <p class="link-cover-img-p">자세히 보기</p>
    </NuxtLink>
  </div>
</template>

<style>
.link-cover-img {
  box-shadow: var(--shadow-basic);
}

.link-cover-img-anker {
  display: flex;
  flex-direction: column;
  gap: 10px;
  width: 126px;
  background: transparent;
}

.link-cover-img-p {
  display: flex;
  justify-content: center;
}
</style>
