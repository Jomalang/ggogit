<script setup>

const { data } = defineProps(["data"]);
const emit = defineEmits(['inputData']);

</script>

<template>
  <div class="input-textarea__box">
    <label class="input-textarea__label tree-input__large-text-label""
    >
      <span>{{ data.label }}</span>
      <textarea
        class="input-textarea__textarea tree-input__large-textarea"
        :class="{ 'input-text__input--warning': data.validate }"
        :name="data.name"
        :value="data.value"
        @input="$emit('inputData', $event.target.value)"
        :placeholder="data.placeholder"
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

.input-text__input--warning {
  outline: 2px solid var(--warning);
}
</style>
