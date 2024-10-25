<script setup lang="ts">

const props = defineProps<{
  startPage: number;
  endPage: number;
}>();

const emit = defineEmits<{
  (e: 'update:startPage', value: number): void;
  (e: 'update:endPage', value: number): void;
}>();

</script>

<template>
  <!-- page-number(startPage, endPage) -->
  <div class="input-page-number-box">
    <p class="input-page-number__title">*페이지</p>
    <div class="input-page-number__frame">
      <label class="input-page-number__label">
        <input
            class="input-page-number__input input-page-number__input--start"
            id="input-page-number__input--start-id"
            :value="startPage"
            name="startPage"
            type="number"
            placeholder="시작 페이지"
            min="0"
            @input="(event) => emit('update:startPage', Number(event.target.value))"
        />
      </label>
      <label class="input-page-number__label">
        <input
            class="input-page-number__input input-page-number__input--end"
            id="input-page-number__input--end-id"
            :value="endPage"
            name="endPage"
            type="number"
            placeholder="마지막 페이지"
            min="0"
            @input="(event) => emit('update:endPage', Number(event.target.value))"
        />
      </label>
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

.input-page-number__title {
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
</style>
