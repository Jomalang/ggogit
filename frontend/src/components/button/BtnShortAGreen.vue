<script setup lang="ts">
import { TextBtnGreenProps } from "@/types/types";

const props = defineProps<{
  link: string;
  text: string;
}>();
</script>

<template>
  <a class="text__btn--green" :href="link">{{ text }}</a>
</template>

<style>
.text__btn--green {
  width: fit-content;
  min-width: 56px;
  background-color: var(--main1);
  color: var(--white);
  box-shadow: 0 4px 16px rgba(0, 0, 0, 0.25);
  text-align: center;
  font-size: 16px;
  font-weight: var(--bold);
  border-radius: 16px;
  transition: all 0.2s linear;
  padding: 20px;
}
.text__btn--green:focus {
  outline: 0;
}
.text__btn--green:hover {
  background-color: var(--main1);
  cursor: pointer;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.25);
}
</style>
