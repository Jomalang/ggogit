<script setup lang="ts">
interface DymProps {
  [key: string]: any;
}

const props = defineProps<{
  name: string;
  values: DymProps;
}>();
</script>

<template>
  <!-- default-data(name, values) -->
  <div class="input-none none">
    <label class="input-none__label">
      <input
        class="input-none__input"
        type="text"
        v-for:each="value in values"
        :name="name"
        :value="value"
      />
    </label>
  </div>
</template>

<style></style>
