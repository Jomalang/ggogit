<script setup lang="ts">
import { LeafTagProps } from "@/types/types";

const props = defineProps<{
  tag: LeafTagProps;
}>();

const emit = defineEmits(['tagSearch']);

</script>

<template>
  <!-- tag-search-or-register-bar(tag) -->
  <div class="tree-input-text__form">
    <label class="tree-input-text__subject">
      <input
        id="tree-input-text__rectangle-id"
        name="name"
        class="tree-input-text__rectangle"
        placeholder="태그를 검색하거나 등록하세요."
        @input="$emit('tagSearch', $event.target.value)"
        :value="tag.name"
      />
    </label>
  </div>
</template>

<style>
/* ======================================
            tree-input-text__form
    ==================================== */
.tree-input-text__form {
  display: flex;
  justify-content: center;
}
.tree-input-text__subject {
  display: flex;
  flex-direction: column;
  flex-grow: 1;
  font-size: 16px;
  font-weight: var(--semi-bold);
}
.tree-input-text__rectangle {
  display: flex;
  flex-grow: 1;
  height: 56px;
  border: none;
  font-size: 16px;
  font-weight: var(--regular);
  color: var(--text-sub);
  background-color: var(--main3);
  padding-left: 16px;
}
.tree-input-text__rectangle:focus {
  outline: none;
}

.tree-input-text__rectangle__re-enter {
  display: block;
  height: 56px;
  border-color: var(--warning);
  font-size: 16px;
  font-weight: var(--regular);
  color: var(--warning);
  background-color: var(--main3);
  padding-left: 16px;
}
</style>
