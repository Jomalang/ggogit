<script setup lang="ts">

import LogItemData from "@/components/log/logItem/LogItemData.vue";
import {LeafItemProps} from "@/types/types";

const props = defineProps<{
  itemData: LeafItemProps;
}>();

</script>

<template>
  <!-- type:6 (type, title, date, link, tags) -->
  <div class="log-item">
    <div class="log-item-box leaf-item">

      <div class="log-item__left-branch">
        <div class="log-item__line-left hidden"></div>
      </div>

      <div class="log-item__mid-branch">
        <div class="log-item__line-top"></div>
        <div class="log-item__circle node"></div>
        <div class="log-item__line-bot"></div>
      </div>
      <div class="log-item__info-box">
        <div class="log-item__right-branch">
          <div class="log-item__line-right"></div>
        </div>
        <LogItemData :item-data="props.itemData" />
      </div>
    </div>
  </div>
</template>

<style scoped>

</style>