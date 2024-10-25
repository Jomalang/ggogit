<script setup lang="ts">
interface Props {
  userimg: string;
  username: string;
  userid: string;
  userurl: string;
}
const props = defineProps<Props>();
</script>

<template>
  <!--==========================================
      user info bar
      user tree index 사용 
      (userimg,username,userid,userurl)
      TODO: A태그 어디다 쓰는거?
  ==========================================-->
  <div class="bar-user-info-frame">
    <div class="bar-user-info__left-content">
      <div class="bar-user-info__img-wrapper">
        <img :src="`/img/card/${props.userimg}`" alt="user-profile" />
      </div>
      <div>
        <div class="bar-user-info__user-name">{{ props.username }}</div>
        <div class="bar-user-info__user-id">{{ props.userid }}</div>
      </div>
    </div>
    <div class="bar-user-info__right-btns">
      <input
        class="__like-bold-input"
        type="checkbox"
        id="bar-user-info__like"
      />
      <label class="__like-bold" for="bar-user-info__like"></label>
      <a class="bar-user-info__set-btn" :href="`${props.userurl}`"></a>
    </div>
  </div>
</template>

<style scoped>
/* ==========================================
      user info bar
      user tree index 사용 
  ========================================== */

.bar-user-info-frame {
  display: flex;
  justify-content: space-between;
}
.bar-user-info__left-content {
  display: flex;
  gap: 8px;
}
.bar-user-info__img-wrapper {
  width: 40px;
  height: 40px;
  display: flex;
  align-items: center;
  justify-content: center;
  overflow: hidden;
}
.bar-user-info__img-wrapper img {
  width: 40px;
  height: 40px;
  border-radius: 50%;
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

.__like-bold-input:checked + .__like-bold::after {
  width: 24px;
  height: 24px;
  content: "";
  display: flex;
  align-items: center;
  background-image: url("/svg/like-fill.svg");
  background-repeat: no-repeat;
  background-position: center;
  background-size: contain;
}

.__like-bold-input:checked ~ .__like-bold-content {
  width: 100%;
  display: flex;
  flex-direction: column;
}
.__like-bold::after {
  width: 24px;
  height: 24px;
  content: "";
  display: flex;
  align-items: center;
  background-image: url("/svg/like-bold.svg");
  background-repeat: no-repeat;
  background-position: center;
  background-size: contain;
}
</style>
