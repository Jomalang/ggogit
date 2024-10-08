<script setup lang="ts">

import LogItemData from "@/components/log/logItem/LogItemData.vue";
import {LeafItemProps} from "@/types/types.js";

const props = defineProps<{
  itemData: LeafItemProps;
}>();

</script>

<template>
  <div class="log-item">
    <div class="log-item-box leaf-item">

      <div class="log-item__left-branch">
        <div class="log-item__line-left"></div>
      </div>

      <div class="log-item__mid-branch">
        <div class="log-item__line-top hidden"></div>
        <div class="log-item__circle node"></div>
        <div class="log-item__line-bot hidden"></div>
      </div>
      <div class="log-item__info-box">
        <div class="log-item__right-branch hidden">
          <div class="log-item__line-right hidden"></div>
        </div>
        <LogItemData :item-data="props.itemData" />
      </div>
    </div>
  </div>
</template>

<style scoped>

</style>