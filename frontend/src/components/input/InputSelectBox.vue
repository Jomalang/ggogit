<script setup lang="ts">
const props = defineProps<{
  label: string;
  id: string;
}>();
</script>

<!-- input-select-box(label, id)-->
<template>
  <div class="input-select-box">
    <label class="input-select__subject">
      <span>{{ label }}</span>
      <div class="input-select--select__selected">
        <p class="input-select--select__selected-description">선택해주세요</p>
        <button class="input-next-button" :id="id" type="button">
          <img src="/public/svg/next.svg" alt="next-button" />
        </button>
      </div>
    </label>
  </div>
</template>

<style scoped>
/*  ========================================== /
     FRAGMENT: 필터 선택 박스
/   ========================================== */
.input-select__subject {
  display: flex;
  flex-direction: column;
  flex-grow: 1;
  font-size: 16px;
  font-weight: var(--extra-bold);
}

.input-select--select__selected {
  display: flex;
  height: 56px;
  margin-top: 10px;
  border: none;
  border-radius: 12px;
  background-color: var(--main3, #f5f8f1);
  align-content: center;
  text-align: right;
  justify-content: space-between;
}

.input-select--select__selected--checked {
  outline: 2px solid var(--main1, #323a27);
}

.input-select--select__selected-description {
  display: flex;
  justify-content: center;
  align-items: center;
  margin-left: 16px;
  color: var(--text-sub);
  font-weight: var(--regular, 400);
  font-size: 16px;
}

.input-select--select__selected-description--checked {
  font-weight: var(--bold, 700);
  color: var(--main1, #323a27);
}

.input-next-button {
  border: none;
  margin-right: 16px;
  background-color: var(--main3, #f5f8f1);
}
</style>
