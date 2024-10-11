<script setup lang="ts">
const props = defineProps<{
  edit: string;
}>();
</script>

<template>
  <!--top-bar__transparent(edit)-->
  <div class="top-bar__transparent-frame">
    <a
      class="top-bar__transparent-back-btn"
      href="javascript:history.back()"
    ></a>
    <a class="top-bar__transparent-setting-btn" :href="edit"></a>
  </div>
</template>

<style></style>
