<script setup lang="ts">
const props = defineProps<{
  text: string;
}>();
</script>

<template>
  <div class="checkbox">
    <label>
      <input type="checkbox" class="checkbox__input" />
      <span class="checkbox__check"></span>
      <p>{{ text }}</p>
    </label>
  </div>
</template>

<style>
/** ==========================================
      FRAGMENT: 체크박스 버튼
==================================== **/
.checkbox {
  /** height값은 컴포넌트 제작 당시엔 없었음. align center 정렬을 위해 추가함.**/
  height: 24px;
  position: relative;
  font-size: 24px;
  font-weight: var(--bold);
  color: var(--main1);
  cursor: pointer;
}
.checkbox__input {
  position: absolute;
  opacity: 0;
}
.checkbox__check {
  position: relative;
  display: inline-block;
  width: 24px;
  height: 24px;
  border: 1px solid var(--main1);
  border-radius: 4px;
}
.checkbox__input:checked ~ .checkbox__check {
  background: var(--main1);
  border-color: var(--main1);
}
.checkbox__check::before {
  content: " ";
  position: absolute;
  top: 4px;
  left: 2px;
  width: 15px;
  height: 6px;
  border-bottom: 4px solid var(--white);
  border-left: 4px solid var(--white);
  transform: scale(0) rotate(-45deg);
}
.checkbox__input:checked ~ .checkbox__check::before {
  transform: scale(0.9) rotate(-45deg);
}
</style>
