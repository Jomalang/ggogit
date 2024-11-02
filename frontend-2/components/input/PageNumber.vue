<script setup>

const { data } = defineProps(['data']);

const emit = defineEmits(['startPage', 'endPage']);

const isValidate = ref(true);
const message = ref(isValidate.valueMessage);

const inputStartPage = (number) => {

  // 마지막 페이지보다 큰 페이지를 입력했는지 확인
  if (number > data.endPage) {
    isValidate.value = false;
    message.value = '시작 페이지가 마지막 페이지보다 큽니다.';
    emit('startPage', { number, isValidate: isValidate.value });
    return;
  }

  // 최대 페이지 수를 넘어가는지 확인
  if (number > data.maxPage) {
    isValidate.value = false;
    message.value = '최대 페이지 수를 넘어갑니다.';
    emit('startPage', { number, isValidate: isValidate.value });
    return;
  }

  isValidate.value = true;
  emit('startPage', { number, isValidate: isValidate.value });
};

const inputEndPage = (number) => {

  // 시작 페이지보다 작은 페이지를 입력했는지 확인
  if (number < data.startPage) {
    isValidate.value = false;
    message.value = '마지막 페이지가 시작 페이지보다 작습니다.';
    emit('endPage', { number, isValidate: isValidate.value });
    return;
  }

  // 최대 페이지 수를 넘어가는지 확인
  if (number > data.maxPage) {
    isValidate.value = false;
    message.value = '최대 페이지 수를 넘어갑니다.';
    emit('endPage', { number, isValidate: isValidate.value });
    return;
  }

  isValidate.value = true;
  emit('endPage', { number, isValidate: isValidate.value });
};

</script>

<template>
  <div class="input-page-number-box">
    <div class="input-page-number-text-box">
      <p class="input-page-number__title">*읽은 페이지</p>
      <p class="input-page-number__max-page">{{ data.maxPage }} Max</p>
    </div>
    <div class="input-page-number__frame">
      <label class="input-page-number__label">
        <input
            class="input-page-number__input input-page-number__input--start"
            :class="{'.input-text__input--warning': !isValidate}"
            id="input-page-number__input--start-id"
            :value="data.startPage"
            name="startPage"
            type="number"
            placeholder="시작 페이지"
            min="0"
            @input="(event) => inputStartPage(Number(event.target.value))"
        />
      </label>
      <label class="input-page-number__label">
        <input
            class="input-page-number__input input-page-number__input--end"
            :class="{'.input-text__input--warning': !isValidate}"
            id="input-page-number__input--end-id"
            :value="data.endPage"
            name="endPage"
            type="number"
            placeholder="마지막 페이지"
            min="0"
            @input="(event) => inputEndPage(Number(event.target.value))"
        />
      </label>
    </div>
    <div v-if="!isValidate" class="input-text__wrong-box">
      <p class="input-text__wrong-text">{{ message }}</p>
    </div>
  </div>
</template>

<style>
/*  ==========================================
    FRAGMENT: 리프 페이지 구간 기록
    ========================================== */
.input-page-number-box {
  width: 100%;
  display: flex;
  padding-bottom: 23px;
  flex-direction: column;
  justify-content: flex-start;
  align-items: flex-start;
}

.input-page-number__title, input-page-number__max-page {
  display: block;
  margin-bottom: 4px;
  color: var(--main1);
  text-align: center;
  font-size: 16px;
  line-height: var(--line-height-main);
  letter-spacing: var(--letter-spacing-main);
  font-weight: var(--bold);
}

.input-page-number__frame {
  display: flex;
  flex-direction: row;
  width: 100%;
  gap: 11px;
  align-items: flex-start;
  justify-content: flex-start;
}

.input-page-number__label {
  width: 50%;
  box-sizing: border-box;
  display: inline-block;
  flex-grow: 1;
}

.input-page-number__input {
  width: 100%;
  box-sizing: border-box;
  display: inline-block;
}

.input-page-number__input--start {
  background: var(--main3);
  border-radius: 12px;
  border: none;
  padding: 17px 16px 17px 16px;
  display: flex;
  flex-direction: row;
  align-items: center;
  appearance: none;
}

.input-page-number__input--start:focus {
  font-weight: var(--semi-bold);
  outline: 2px solid var(--filter-checked);
}

.input-page-number__input--start:not(:placeholder-shown) {
  color: var(--text-main);
  outline: 2px solid var(--filter-checked);
  font-weight: var(--bold);
}

.input-page-number__input--start::placeholder {
  font-size: 16px;
  font-weight: var(--regular);
  line-height: var(--line-height-main);
  letter-spacing: var(--letter-spacing-sub);
  color: var(--text-sub);
}

.input-page-number__input--start:focus::placeholder {
  font-weight: var(--semi-bold);
}

.input-page-number__input--end {
  background: var(--main3);
  border-radius: 12px;
  border: none;
  padding: 17px 16px 17px 16px;
  display: flex;
  flex-direction: row;
  align-items: center;
  appearance: none;
}

.input-page-number__input--end:focus {
  outline: 2px solid var(--filter-checked);
  font-weight: var(--bold);
}

.input-page-number__input--end:not(:placeholder-shown) {
  color: var(--text-main);
  outline: 2px solid var(--filter-checked);
  font-weight: var(--bold);
}

.input-page-number__input--end::placeholder {
  font-size: 16px;
  font-weight: var(--regular);
  line-height: var(--line-height-main);
  letter-spacing: var(--letter-spacing-sub);
  color: var(--text-sub);
}

.input-page-number__input--end:focus::placeholder {
  font-weight: var(--semi-bold);
}

.input-page-number-text-box {
  display: flex;
  width: 100%;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 8px;
}

.input-text__wrong-box {
  margin: 12px 10px;
  p {
    color: var(--warning, #ba0c0c);
  }
}

.input-text__input--warning {
  outline: 2px solid var(--warning);
}
</style>
