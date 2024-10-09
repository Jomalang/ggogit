<script setup lang="ts">

const props = defineProps<{
  text: String;
}>();

</script>

<template>
  <!-- text -->
  <div class="btn-full-bar">
    <button
        id="book-tree-input-form-id"
        class="btn-full-bar__btn"
        type="submit"
    >{{ text }}</button>
  </div>
</template>

<style scoped>
.btn-full-bar {
  width: 100%;
  height: 56px;
  display: flex;
  justify-content: center;
  align-items: center;
}

.btn-full-bar__btn {
  color: var(--white, #ffffff);
  background-color: var(--main1, #323a27);
  height: 56px;
  width: 100%;
  border-radius: 20px;
  box-shadow: var(--shadow-basic);
  font-size: 18px;
  font-weight: var(--bold, 700);
}
</style>