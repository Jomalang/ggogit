<script setup lang="ts">

const emit = defineEmits(['tagDelete']);

</script>

<template>
  <div class="delete-seed__form">
    <button class="delete-seed__btn"
            type="button"
            @click.prevent="emit('tagDelete')"
    >
      <img src="/public/svg/delete.svg" alt="delete" />
      <p class="delete-seed__btn-msg">삭제</p>
    </button>
  </div>
</template>

<style>
/* ===================================
            delete-btn
  ===================================  */
.delete-seed__form {
  display: flex;
  height: 58px;
  border: none;
  background-color: var(--main3);
  align-items: center;
  border-top: 1px solid var(--gray);
  border-bottom: 1px solid var(--gray);
}
.delete-seed__btn {
  display: flex;
  border: none;
  height: 24px;
  padding-left: 16px;
  background-color: var(--main3);
}
.delete-seed__btn-msg {
  padding-left: 16px;
  font-size: 16px;
  font-weight: var(--regular);
  color: var(--text-sub);
}

.join-policy-agreement {
  display: flex;
  align-items: center;
  gap: 12px;
}
</style>
