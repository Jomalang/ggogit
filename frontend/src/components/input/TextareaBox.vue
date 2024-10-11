<script setup lang="ts">
const props = defineProps<{
  label: string;
  name: string;
  placeholder: string;
}>();
</script>

<template>
  <!-- text-area__box(label, placeholder, name) -->
  <div class="input-textarea__box">
    <label class="input-textarea__label tree-input__large-text-label">
      <span>{{ label }}</span>
      <textarea
        class="input-textarea__textarea tree-input__large-textarea"
        :name="name"
        :placeholder="placeholder"
      ></textarea>
    </label>
  </div>
</template>

<style>
/*  ========================================== /
    FRAGMENT: 많은 텍스트 입력
/   ========================================== */
.input-textarea__label {
  display: flex;
  gap: 8px;
  flex-direction: column;
  font-weight: var(--semi-bold);
}

.input-textarea__textarea {
  padding: 17px 16px;
  border: none;
  border-radius: 12px;
  font-size: 16px;
  font-weight: var(--medium, 500);
  color: var(--main1);
  background-color: var(--main3);
  height: 230px;
}

.input-textarea__textarea:focus {
  outline: 2px solid var(--main1, #323a27);
}

.input-textarea__textarea:not(:placeholder-shown) {
  font-size: 16px;
  font-weight: var(--semi-bold);
  color: var(--main1);
  outline: 2px solid var(--main1, #323a27);
}
</style>
