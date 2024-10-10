<script setup lang="ts">
const props = defineProps<{
  memberId: number;
}>();
</script>

<template>
  <div class="input-tag-select-box">
    <p class="input-tag-select__label-text">태그 선택</p>
    <label class="input-tag-select__bg">
      <div class="input-tag-select__button-box">
        <div class="input-tag-select__tag-box">
          <ul class="input-tag-select__tags"></ul>
          <p class="input-tag-select__placeholder">태그를 선택해주세요</p>
        </div>

        <a
          class="input-tag-select__button"
          id="input-tag-select__button-id"
          href="javascript:void(0)"
        >
          <img src="/public/svg/next.svg" alt="next-button" />
        </a>
      </div>
    </label>
  </div>
</template>

<style>
/*  ==========================================
    FRAGMENT: 태그 선택
    ========================================== */
.input-tag-select-box {
  width: 100%;
  display: flex;
  flex-direction: column;
}

.input-tag-select__bg {
  width: 100%;
  display: flex;
  flex-direction: row;
}

.input-tag-select__label-text {
  margin-bottom: 4px;
  font-weight: var(--bold);
  line-height: var(--line-height-main);
  letter-spacing: var(--letter-spacing-sub);
  color: var(--main1, #323a27);
}

.input-tag-select__button-box {
  display: flex;
  width: 100%;
  flex-direction: row;
  min-height: 24px;
  border-radius: 12px;
  padding: 16px 16px;
  align-items: center;
  justify-content: space-between;
  background-color: var(--main3, #f5f8f1);
}

.input-tag-select__tag-box {
  display: flex;
  align-items: center;
  flex-wrap: wrap;
  gap: 8px;
}

.input-tag-select__placeholder {
  margin: 8px 0;
}

.input-tag-select__button {
  display: flex;
  justify-content: flex-end;
  align-items: center;
  padding-left: 16px;
}

.input-tag-select__tag-label {
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
}

.input-tag-select__tags {
  display: flex;
  flex-wrap: wrap;
  flex-direction: row;
  gap: 10px;
}

.input-tag-select__tag-text {
  display: block;
  font-size: 14px;
  color: var(--white, #ffffff);
}

.input-tag-select__tag {
  display: inline-block;
  flex-direction: row;
  border-radius: 12px;
  padding: 6px 12px 6px 12px;
  background-color: var(--main1, #323a27);
}
</style>
