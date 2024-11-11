<script setup>
/*
label: string;
name: string;
placeholder: string;
value: string;
isValid: boolean;
*/
const { data } = defineProps(["data"]);
const emit = defineEmits(["inputData"]);

const inputData = (e) => {
  emit("inputData", e.target.value);
};

</script>

<template>
  <div class="input-text__bar">
    <label class="input-text__label">
      <span class="input-text__label-text">{{ data.label }}</span>
      <input
        class="input-text__input"
        :name="data.name"
        :placeholder="data.placeholder"
        :type="data.inputType"
        @input="inputData"
        autocomplete="off"
        :class="{ 'input-text__input--warning': !data.isValid }"
      />
    </label>
    <div class="wrong-text-box">
      <span v-if="!data.isValid" class="input-text__input-wrong">{{ data.validateMessage }}</span>
    </div>
  </div>
</template>

<style>
/*  ========================================== /
    FRAGMENT: 텍스트 입력 바
/   ========================================== */

.input-text__label {
  display: flex;
  gap: 8px;
  flex-direction: column;
  width: 100%;
  position: relative;
  padding: 30px 0 0 0;
}

.input-text__label-text {
  font-weight: var(--semi-bold);
  font-size: 18px;
}

.input-text__input {
  width: auto;
  height: 56px;
  display: flex;
  border: none;
  border-radius: 12px;
  font-size: 16px;
  font-weight: var(--regular);
  color: var(--text-sub);
  padding-left: 16px;
  background-color: var(--main3, #e5eddb);
}

.input-text__input--warning {
  outline: 2px solid var(--warning);
}

.input-text__input:focus {
  outline: 2px solid var(--filter-checked);
}

.input-text__input:not(:placeholder-shown) {
  color: var(--text-main);
  outline: 2px solid var(--main1, #323a27);
}

.input-text__input-wrong {
  margin: 12px 0 0 12px;
  color: var(--warning, #ba0c0c);
}
.input-text__input:read-only {
  background-color: var(--main2, #e5eddb);
  outline: 3px solid var(--gray);
}

.wrong-text-box {
  display: flex;
  justify-content: flex-start;
  gap: 8px;
  height: 20px;
  margin-top: 2px;
}
</style>
