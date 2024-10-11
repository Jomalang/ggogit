<script setup lang="ts">
interface Props {
    memoirtitle: string;
    userurl: string;
}
const props = defineProps<Props>()
</script>

<template>
  <!--==========================================
      memoir title + btns
      user tree memoir index 사용
      (memoirtitle,userurl)
      TODO: A태그 요청 페이지 경로 확인 필요
  ==========================================-->
  <div class="bar-user-info-frame" >
      <div class="bar-user-info__left-content">
          <p class="bar-memoir-title">{{props.memoirtitle}}</p>
      </div>
      <div class="bar-user-info__right-btns">
          <input
                  class="__like-bold-input"
                  type="checkbox"
                  id="bar-user-info__like"
          />
          <label
                  class="__like-bold"
                  for="bar-user-info__like"
          ></label>
          <a class="bar-user-info__set-btn" :href="`${props.userurl}`"></a>
      </div>
  </div>
</template>

<style>

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
  color: var(--main1);
  font-size: 24px;
  font-weight: var(--bold);
  line-height: var(--line-height-main);
  letter-spacing: var(--letter-spacing-main);
}
.bar-user-info__right-btns {
  display: flex;
  gap: 6px;
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
</style>
