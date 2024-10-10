<script setup lang="ts">

const props = defineProps<{
  link: String;
  text: String;
}>();

</script>

<template>
  <div class="btn-full-width">
    <a class="btn-full-width__link" :href="link">
      <div class="btn-full-width__button">
        <p class="btn-full-width__placeholder">{{ text }}</p>
      </div>
    </a>
  </div>
</template>

<style>
.btn-full-width {
  border-radius: 40px;
  box-shadow: var(--shadow-basic);
  transition: all 0.3s linear;
}

.btn-full-width:hover {
  box-shadow: var(--shadow-active);
}

.btn-full-width__button {
  background: #323a27;
  border-radius: 40px;
  display: flex;
  flex-direction: row;
  gap: 10px;
  row-gap: 0;
  align-items: center;
  justify-content: center;
  align-self: stretch;
  flex-shrink: 0;
  height: 56px;
  position: relative;
}

.btn-full-width__placeholder {
  color: var(--white, #ffffff);
  text-align: center;
  font-family: "Pretendard-Bold", sans-serif;
  font-size: 16px;
  line-height: var(--line-height-main, 140%);
  letter-spacing: var(--letter-spacing-main, -0.025em);
  font-weight: var(--bold, 700);
  position: relative;
  display: flex;
  align-items: center;
  justify-content: center;
}
</style>