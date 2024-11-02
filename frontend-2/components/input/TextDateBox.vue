<script setup>

const { data } = defineProps(["data"]);
const emit = defineEmits(['inputData']);

const formattedDate = ref(data.value || '');
const validateMessage = ref(data.validateMessage || '');
const validate = ref(data.validate || true);

const dateInput = (value) => {

  validateMessage.value = '';

  // // 숫자랑 -만 입력 가능
  if (!/^[0-9-]*$/.test(value)) {
    console.log('value', value);
    validate.value = false;
    validateMessage.value = '숫자만 입력해주세요.';

    console.log('validate', validate.value);
    console.log('validateMessage', validateMessage.value);
    return;
  }

  let sanitizedValue = value.replace(/[^0-9]/g, ''); // 숫자만 남기기
  if (sanitizedValue.length >= 4) sanitizedValue = sanitizedValue.slice(0, 4) + '-' + sanitizedValue.slice(4); // 연도 뒤에 "-" 추가
  if (sanitizedValue.length >= 7) sanitizedValue = sanitizedValue.slice(0, 7) + '-' + sanitizedValue.slice(7); // 월 뒤에 "-" 추가
  formattedDate.value = sanitizedValue.slice(0, 10); // 최대 길이 10자로 제한

  // 날짜 형식에 맞춰야 한다.
  // 1 ~ 12월 사이 값
  // 1 ~ 31일 사이 값

  if (formattedDate.value.length === 10) {
    const [year, month, day] = formattedDate.value.split('-');
    const yearNum = parseInt(year, 10);
    const monthNum = parseInt(month, 10);
    const dayNum = parseInt(day, 10);

    // 년도 4자리
    if (year.length !== 4) {
      validate.value = false;
      validateMessage.value = '년도는 4자리로 입력해주세요.';
      return;
    }

    // 월 1 ~ 12
    if (monthNum < 1 || monthNum > 12) {
      validate.value = false;
      validateMessage.value = '월은 1 ~ 12 사이로 입력해주세요.';
      return;
    }

    // 일 1 ~ 31
    if (dayNum < 1 || dayNum > 31) {
      validate.value = false;
      validateMessage.value = '일은 1 ~ 31 사이로 입력해주세요.';
      return;
    }
  }

  emit('inputData', formattedDate.value); // 부모 컴포넌트로 값 전송
}

watch(() => data.value, (newValue) => {
  formattedDate.value = newValue || '';
});

</script>

<template>
  <div class="input-text__bar">
    <label class="input-text__label">
      <span class="input-text__label-text">{{ data.label }}</span>
      <input class="input-text__input"
             :name="data.name"
             :placeholder="data.placeholder"
             :value="formattedDate"
             type="text"
             maxlength="10"
             :class="{ 'input-text__input--warning': !validate }"
             @input="dateInput($event.target.value)"
             autocomplete="off"/>
    </label>
    <div v-if="!validate" class="input-text__wrong-box">
      <p class="input-text__wrong-text">{{ validateMessage }}</p>
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