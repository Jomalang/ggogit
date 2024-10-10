<script setup>

</script>

<template>
    <div class="header-search-link">
        <a class="header-search-link__link" href="/tree/book/select">
            <div class="header-search-link__input-box">
                <p class="header-search-link__placeholder">나의 트리 검색</p>
                <img class="header-search-link__icon" src="/svg/lens.svg" alt="lens.svg"/>
            </div>
        </a>
    </div>
</template>

<style scoped>
.header-search-link {
    display: flex;
    flex-direction: column;
    align-items: center;
    align-self: stretch;
    position: relative;
}

.header-search-link__link {
    align-self: stretch;
}

.header-search-link__input-box {
    background: var(--main2, #e5eddb);
    border-radius: 100px;
    padding: 0 16px;
    display: flex;
    align-items: center;
    justify-content: space-between;
    align-self: stretch;
    height: 40px;
    position: relative;
}

.header-search-link__placeholder {
    color: var(--text-sub);
    font-size: 14px;
    line-height: var(--line-height-main);
    letter-spacing: var(--letter-spacing-main);
    font-weight: var(--medium);
    position: relative;
    transform: translateY(8%);
}

.header-search-link__icon {
    flex-shrink: 0;
    width: 18px;
    height: 18px;
    position: relative;
    overflow: visible;
}
</style>