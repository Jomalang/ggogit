<script setup>

import DeleteModal from "~/components/modal/DeleteModal.vue";

const isModalOpen = ref(true);

const confirmText = () => {
  isModalOpen.value = false;
  console.log('confirm');
};

const cancelText = () => {
  isModalOpen.value = false;
  console.log('cancel');
};

</script>

<template>
  <DeleteModal
      :data="{
        message: '트리 삭제',
        isOpen: isModalOpen,
        confirmText: '삭제',
        cancelText: '취소',
      }"
      @close="confirmText"
      @submit="cancelText"
  ></DeleteModal>

</template>

<style scoped>

</style>