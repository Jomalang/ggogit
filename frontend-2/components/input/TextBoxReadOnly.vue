<script setup>

const { data } = defineProps(["data"]);

// const props = defineProps({
//   label: String,
//   name: String,
//   placeholder: String,
//   modelValue: String,
//   validate: Boolean,
//   validateMessage: {
//     type: String,
//     default: '올바른 형식이 아닙니다.'
//   }
// });

const emit = defineEmits(['inputData']);

</script>

<template>
  <div class="input-text__bar">
    <label class="input-text__label">
      <span class="input-text__label-text">{{ data.label }}</span>
      <input class="input-text__input"
             :name="data.name"
             :placeholder="data.placeholder"
             :value="data.value"
             :class="{ 'input-text__input--warning': !data.validate }"
             @input="emit('inputData', $event.target.value)"
             autocomplete="off"
             readonly
      />
    </label>
    <div v-if="!data.validate" class="input-text__wrong-box">
      <p class="input-text__wrong-text">{{ data.validateMessage }}</p>
    </div>
  </div>
</template>

<style scoped>
.input-text__label {
  display: flex;
  gap: 8px;
  flex-direction: column;
  width: 100%;
  position: relative;
}

.input-text__label-text {
  font-weight: var(--semi-bold);
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
.input-text__input:read-only{
  background-color: var(--main2, #e5eddb);
  outline: 3px solid var(--gray);
}

.input-text__wrong-box {
  margin: 12px 10px;
  p {
    color: var(--warning, #ba0c0c);
  }
}
</style>