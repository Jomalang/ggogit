<script setup>
const { data } = defineProps(['data']);

</script>

<template>
    <!-- text-login__page-info(label, infoText) -->
    <div class="text-login__page-info">
        <p class="text--title28">{{ data.label }}</p>
        <p class="text--explain">{{ data.infoText }}</p>
    </div>
</template>

<style scoped>
.text-login__page-info {
    display: flex;
    flex-direction: column;
    gap: 4px;
    padding-top: 30px;
}

.text--title28 {
    font-size: 28px;
}

.text--explain {

}
</style>