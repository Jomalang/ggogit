<script setup lang="ts">
interface Props {
  username: string;
  userid: string;
}
const props = defineProps<Props>();
</script>

<template>
  <!--==========================================
      user info bar - no profile, btn
      user tree memoir index 사용
      (username,userid)
  ==========================================-->
  <div class="bar-user-info-frame">
    <div class="bar-user-info__left-content">
      <div>
        <div class="bar-user-info__user-name">{{ props.username }}</div>
        <div class="bar-user-info__user-id">{{ props.userid }}</div>
      </div>
    </div>
  </div>
</template>

<style scoped>
/* ==========================================
      user info bar - no profile, btn
      user tree memoir index 사용
  ========================================== */
.bar-user-info-frame {
  display: flex;
  justify-content: space-between;
}
.bar-user-info__left-content {
  display: flex;
  gap: 8px;
}
.bar-user-info__user-name {
  color: var(--white);
  font-size: 14px;
  font-weight: var(--medium);
  line-height: var(--line-height-main);
  letter-spacing: var(--letter-spacing-main);
}
.bar-user-info__user-id {
  color: var(--white);
  font-size: 10px;
  font-weight: var(--regular);
  line-height: var(--line-height-main);
  letter-spacing: var(--letter-spacing-main);
}
</style>
