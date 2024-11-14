<script setup>

import useBackNavigation from "~/composables/useBackNavigation.js";

const goBack = () => { useBackNavigation().popPageFromStack(); };
const { getLastPage } = useBackNavigation();
const lastPage = computed(() => getLastPage());

</script>

<template>
    <div>
      <NuxtLink :to="lastPage" @click="goBack">
        <img src="/svg/back.svg" alt="back-button">
      </NuxtLink>
    </div>
</template>