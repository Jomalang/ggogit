<script setup>
const { data } = defineProps(["data"]);
</script>

<template>
  <div class="leaf-page-info-box">
    <h1 class="text-main-title" :class="`text--title${data.size}`">
      {{ data.title }}
    </h1>
    <p class="leaf-page-info-box__text">
      <span class="leaf-page-info-box__start_page">{{ data.startPage }}</span>
      <span> ~ </span>
      <span class="leaf-page-info-box__end_page">{{ data.endPage }}</span>
      쪽
    </p>
  </div>
</template>

<style scoped>
.leaf-page-info-box {
  width: 100%;
  display: flex;
  justify-content: space-between;
  align-items: center;
  h1 {
    width: fit-content;
  }

  p {
    text-align: right;
    span {
      font-size: 24px;
      font-weight: 700;
    }
  }
}

.text-main-title {
  color: var(--main1, #323a27);
  font-weight: var(--bold, 700);
}

.text--title28 {
  font-size: 28px;
}

.text--title24 {
  font-size: 24px;
}

.text--title20 {
  font-size: 20px;
}

.text--title18 {
  font-size: 18px;
}

.text--title16 {
  font-size: 16px;
}

.text--title14 {
  font-size: 14px;
}
</style>
