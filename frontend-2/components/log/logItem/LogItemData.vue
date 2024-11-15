<script setup lang="ts">
import {LeafItemProps} from "@/types/types.js";

const props = defineProps<{
  itemData: LeafItemProps;
}>();

</script>

<template>
  <NuxtLink class="log-item__link" :to="itemData.link">
    <div class="log-item__text-box">
      <p class="log-item__title">{{itemData.title}}</p>
      <p class="log-item__date">{{itemData.date}}</p>
      <ul class="log-item__tags">
        <li class="log-item__tag"
            v-for="tag in itemData.tags" :key="tag.id"
        >{{tag.name}}</li>
      </ul>
    </div>
  </NuxtLink>
</template>

<style scoped>

</style>