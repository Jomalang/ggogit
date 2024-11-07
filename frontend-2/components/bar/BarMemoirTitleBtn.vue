<script setup lang="ts">
interface Props {
  memoirTitle: string;
  userUrl: string;
}
const props = defineProps<Props>();
</script>

<template>
  <!--==========================================
      memoir title + btns
      user tree memoir index 사용
      (memoirTitle,userUrl)
      TODO: A태그 요청 페이지 경로 확인 필요
  ==========================================-->
  <div class="bar-user-info-frame">
    <div class="bar-user-info__left-content">
      <p class="bar-memoir-title">{{ props.memoirTitle }}</p>
    </div>
    <div class="bar-user-info__right-btns">
      <input
        class="__like-bold-input"
        type="checkbox"
        id="bar-user-info__like"
      />
      <label class="__like-bold" for="bar-user-info__like"></label>
      <a class="bar-user-info__share-btn" :href="`${props.userUrl}`"></a>
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
.bar-memoir-title {
  color: var(--white);
  font-size: 24px;
  font-weight: var(--bold);
  line-height: var(--line-height-main);
  letter-spacing: var(--letter-spacing-main);
}
.bar-user-info__right-btns {
  display: flex;
  gap: 15px;
  align-items: center;
}

.__like-bold-input {
  display: none;
  cursor: none;
}

.__like-bold {
  cursor: pointer;
}

.__like-bold::after {
  width: 24px;
  height: 24px;
  content: "";
  display: flex;
  align-items: center;
  background-image: url("/public/svg/like-bold.svg");
  background-repeat: no-repeat;
  background-position: center;
  background-size: contain;
}

.__like-bold-input:checked + .__like-bold::after {
  width: 24px;
  height: 24px;
  content: "";
  display: flex;
  align-items: center;
  background-image: url("/public/svg/like-fill.svg");
  background-repeat: no-repeat;
  background-position: center;
  background-size: contain;
}

.__like-bold-input:checked ~ .__like-bold-content {
  width: 100%;
  display: flex;
  flex-direction: column;
}
.bar-user-info__share-btn {
  width: 25px;
  height: 25px;
  display: flex;
  background: transparent url("/svg/share.svg") no-repeat center;
  background-size: contain;
}
</style>
