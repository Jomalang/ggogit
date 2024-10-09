<script setup lang="ts"></script>

<template>
  <div class="input-select-box">
    <label class="input-select__subject">
      <span>{{ label:string }}</span>
      <div class="input-select--select__selected">
        <p class="input-select--select__selected-description">선택해주세요</p>
        <button class="input-next-button" :id="id" type="button">
          <img src="@/svg/next.svg" alt="next-button" />
        </button>
      </div>
    </label>
  </div>
</template>

<style scoped></style>
