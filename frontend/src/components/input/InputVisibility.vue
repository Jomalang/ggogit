<script setup lang="ts">
const props = defineProps<{
  name: string;
}>();
</script>

<template>
  <!-- input-visibility(name) -->
  <div class="input-visibility">
    <p class="input-visibility__name">공개 여부</p>
    <div class="input-visibility__btns">
      <label class="input-visibility__label">
        <input
          class="input-visibility__btn"
          label="공개"
          type="radio"
          :name="name"
          id="public"
          value="1"
          checked
        />
      </label>
      <label class="input-visibility__label">
        <input
          class="input-visibility__btn"
          label="비공개"
          type="radio"
          :name="name"
          id="private"
          value="0"
        />
      </label>
    </div>
  </div>
</template>

<style>
/*  ========================================== /
     FRAGMENT: 공개성 여부 체크
/   ========================================== */
.input-visibility {
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  justify-content: flex-start;
  width: 100%;
  height: 114px;
}

.input-visibility__name {
  color: var(--main1);
  font-size: 16px;
  font-weight: var(--bold);
  line-height: var(--line-height-main);
}

.input-visibility__btns {
  border-radius: 12px;
  padding: 8px 0;
  display: flex;
  flex-direction: row;
  gap: 10px;
  align-items: center;
  justify-content: flex-start;
  align-self: stretch;
}

.input-visibility__label {
  flex-grow: 1;
}

.input-visibility__btn {
  background: var(--main3);
  border-radius: 12px;
  color: var(--text-no-active);
  font-weight: var(--bold);
  font-size: 14px;
  border: none;
  padding: 0 46px 0 46px;
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: center;
  height: 56px;
  width: 100%;
  appearance: none;
  cursor: pointer;
}

.input-visibility__btn::after {
  content: attr(label);
}

.input-visibility__btn:checked {
  background: var(--main1);
  color: var(--white);
  box-shadow: 0 4px 4px 0 rgba(0, 0, 0, 0.25);
}
</style>
