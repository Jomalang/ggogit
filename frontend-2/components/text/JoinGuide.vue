<script setup>

const { data } = defineProps(['data']);


</script>

<template>
    <!-- join-guide(infoText, label, href) -->
    <div class="join-guide">
        <p class="text--title14">{{ data.infoText }}</p>
        <NuxtLink :to="data.href">
          <p class="text--title16">{{ data.label }}</p>
        </NuxtLink>
    </div>
</template>

<style scoped>
.join-guide {
    display: flex;
    justify-content: flex-end;
    align-items: center;
    text-align: center;
    padding: 50px 24px 40px 24px;
    gap: 4px;
}

.text--title14 {
    font-size: 14px;
    font-weight: 400;
}

.text--title16 {
    font-size: 16px;
}
</style>