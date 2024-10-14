<script setup lang="ts">
const props = defineProps<{
  href: string;
  src: string;
}>();
</script>

<template>
  <!--link__one-imgae-detail(src,href)-->
  <div>
    <a class="link-cover-img-anker" :href="href">
      <img class="link-cover-img" :src="'/uploads/image/' + src" alt="표지" />
      <p class="link-cover-img-p">자세히 보기</p>
    </a>
  </div>
</template>

<style>
.link-cover-img {
  box-shadow: var(--shadow-basic);
}

.link-cover-img-anker {
  display: flex;
  flex-direction: column;
  gap: 4px;
  width: 126px;
  background: transparent;
}

.link-cover-img-p {
  display: flex;
  justify-content: center;
}
</style>
