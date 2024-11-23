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
        title: '트리 삭제',
        message: '트리에 포함된 모든 데이터를 삭제합니다',

        isOpen: isModalOpen,
        confirmText: '확인',
        cancelText: '취소',
      }"
      @close="confirmText"
      @submit="cancelText"
  ></DeleteModal>

</template>

<style scoped>

</style>