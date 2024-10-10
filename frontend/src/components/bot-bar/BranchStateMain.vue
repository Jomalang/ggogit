<script setup lang="ts">
import { computed } from 'vue'

interface Branch {
  branch: string;
  log: number;
  like: number;
  view: number;
  date: Date;
}

const props = defineProps<{
  branches: Branch[]
}>();

const formatDate = (date: Date) => {
  return new Date(date).toISOString().split('T')[0];
};
</script>

<template>
  <div class="log-list-bot-bar-info-container">
    <div class="branch-state-main" v-for="branch in branches" :key="branch.branch">
      <div class="branch-state__header">
        <div class="branch-state__icon-box">
          <div>
            <img
                class="branch-state__box-image"
                src='/svg/branch-box--icon.svg'
                alt="브랜치 아이콘"
            />
          </div>
        </div>
        <p class="branch-state__name">{{ branch.branch }}</p>
      </div>
      <div class="branch-state__info">
        <div class="branch-state__stats">
          <p>로그<span class="branch-state__nums">{{ branch.log }}</span></p>
          <p>좋아요<span class="branch-state__nums">{{ branch.like }}</span></p>
          <p>조회<span class="branch-state__nums">{{ branch.view }}</span></p>
        </div>
        <p class="branch-state__date">{{ formatDate(branch.date) }}</p>
      </div>
    </div>
  </div>
</template>

<style>
/*  ==========================================
    FRAGMENT: 브랜치 정보 하단바
    ========================================== */
.log-list-bot-bar-info-container {
  z-index: 10;
}
.branch-state-main {
  box-shadow: var(--shadow-basic);
  transition: all 0.2s linear;
  display: flex;
  flex-direction: column;
  gap: 10px;
  padding: 10px 24px;
  background-color: var(--white, #ffffff);
}

.branch-state-main:hover {
  box-shadow: var(--shadow-active);
}

.branch-state__header {
  display: flex;
  gap: 8px;
}

.branch-state__icon-box {
  box-sizing: border-box;
  background-color: var(--main1);
  display: flex;
  width: 40px;
  height: 40px;
  border-radius: 4px;
  justify-content: center;
  align-items: center;
  padding: 8px;
}

.branch-state__box-image {
  display: flex;
  align-items: center;
  justify-items: center;
}

.branch-state__name {
  display: flex;
  align-items: center;
  color: var(--main1, #323a27);
  font-size: 18px;
  letter-spacing: var(--letter-spacing-main);
  font-weight: var(--bold);
}

.branch-state__info {
  display: flex;
  justify-content: space-between;
}

.branch-state__stats {
  display: flex;
  gap: 6px;
  font-size: 14px;
  color: var(--text-sub, #767676);
  font-weight: var(--regular);
}

.branch-state__nums {
  color: var(--text-sub);
  font-weight: var(--bold);
}

.branch-state__date {
  color: var(--text-sub);
  font-size: 12px;
}
</style>
