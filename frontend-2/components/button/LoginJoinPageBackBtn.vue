<script setup>

const { data } = defineProps(['data']);

</script>

<template>
    <div>
      <NuxtLink :to="data.link"><img src="/svg/back.svg" alt="back-button"></NuxtLink>
    </div>
</template>