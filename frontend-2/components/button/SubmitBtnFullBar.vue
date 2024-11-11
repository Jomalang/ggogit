<script setup>

const props = defineProps({
  text: {
    type: String,
    required: true,
  },
  isSubmit: {
    type: Boolean,
    required: false,
    default: false,
  }
});

const emit = defineEmits(['submit']);

</script>

<template>
  <!-- text -->
  <div class="btn-full-bar">
    <button
        id="book-tree-input-form-id"
        class="btn-full-bar__btn"
        :class="{ 'btn-full-bar__btn--disabled': isSubmit }"
        :disabled="isSubmit"
        type="submit"
        @click.prevent="$emit('submit')"
    >{{ text }}</button>
  </div>
</template>

<style scoped>
.btn-full-bar {
  width: 100%;
  height: 56px;
  display: flex;
  justify-content: center;
  align-items: center;
  padding: 30px 0 30px 0;
}

.btn-full-bar__btn {
  color: var(--white, #ffffff);
  background-color: var(--main1, #323a27);
  height: 56px;
  width: 100%;
  border-radius: 20px;
  box-shadow: var(--shadow-basic);
  font-size: 18px;
  font-weight: var(--bold, 700);
}

.btn-full-bar__btn--disabled {
  background-color: var(--main2, #e5eddb);
  color: var(--main1, #323a27);
}
</style>