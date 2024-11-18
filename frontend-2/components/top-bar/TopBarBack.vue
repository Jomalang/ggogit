<script setup>
const props = defineProps({
  title: "",
  link: {
    type: String,
    default: "",
  },
});
</script>

<template>
  <!-- top-bar-back(title, link)-->
  <div class="top-bar--green">
    <div class="top-bar__frame">
      <p class="top-bar__title">{{ props.title }}</p>
      <div
        v-if="
          props.link === '' || props.link === undefined || props.link === null
        "
        class="tob-bar__back-link"
        @click.prevent="useGoBack()"
      >
        <img
          src="~/assets/svg/back-arrow-icon.svg"
          alt="top-bar--backarrow.svg"
        />
      </div>
      <NuxtLink v-else class="tob-bar__back-link" :to="link">
        <img
          src="~/assets/svg/back-arrow-icon.svg"
          alt="top-bar--backarrow.svg"
        />
      </NuxtLink>
      <div class="top-bar__text"></div>
    </div>
  </div>
</template>

<style>
/* ========================================== /
   FRAGMENT: 뒤로가기 상단바 -- 오른쪽 버튼없음, 있음.
/ ========================================== */

.top-bar--green {
  background: var(--main2, #e5eddb);
  display: flex;
  flex-direction: row;
  align-items: flex-end;
  height: 76px;
  position: relative;
  border-bottom: 1px solid var(--gray);
  padding: 0 24px 0 24px;
}

.top-bar--white {
  background-color: var(--white);
  display: flex;
  flex-direction: row;
  align-items: flex-end;
  height: 76px;
  position: relative;
  justify-content: space-between;
  border-bottom: 1px solid var(--gray);
  padding: 0 24px;
}

.top-bar__frame {
  width: 100%;
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 14px;
  position: relative;
}

.tob-bar__title {
  color: var(--main1, #323a27);
  font-size: 20px;
  line-height: var(--line-height-main);
  letter-spacing: var(--letter-spacing-main);
  font-weight: var(--bold);
}

.top-bar__title {
  color: var(--main1, #323a27);
  font-size: 20px;
  line-height: var(--line-height-main);
  letter-spacing: var(--letter-spacing-main);
  font-weight: var(--bold);
  order: 1;
  position: absolute;
  left: 50%;
  transform: translateX(-50%);
}

.top-bar__success-link {
  order: 2;
}

.top-bar__text {
  color: var(--main1, #323a27);
  font-size: 16px;
  letter-spacing: var(--letter-spacing-main);
  font-weight: var(--bold);
  align-self: flex-end;
  background: transparent;
  border: none;
  padding: 0;
  width: 41.49px;
  order: 2;
}
</style>
