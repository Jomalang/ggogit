<script setup>

const { data } = defineProps(["data"]);

</script>

<template>
  <!-- text-main-title(title, size) -->
  <div>
    <h1 class="text-main-title" :class="`text--title${data.size}`">
      {{ data.title }}
    </h1>
  </div>
</template>

<style scoped>
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
