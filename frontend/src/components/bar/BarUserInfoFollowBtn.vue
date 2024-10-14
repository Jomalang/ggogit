<script setup lang="ts">

interface Props {
    followId: string;
    userimg: string;
    username: string;
    userid: string;
}
const props = defineProps<Props>();
</script>

<template>
  <!--==========================================
      user info - follow btn
      user tree memoir index 사용
      (userimg,username,userid)
      TODO: followId 사용 관련 로직 구현 필요.
  ==========================================-->
  <div class="bar-user-info-follow-btn-frame">
      <div class="bar-user-info__left-content">
          <div class="bar-user-info__img-wrapper">
              <img :src="`/img/card/${props.userimg}`" alt="user-profile">
          </div>
          <div>
              <div class="bar-user-info__user-name--color-main1">{{props.username}}</div>
              <div class="bar-user-info__user-id--color-main1">{{props.userid}}</div>
          </div>
      </div>
      <div>
          <form class="bar-user-info__right-btns" action="#" method="post">
              <input
                      class="__follow-btn"
                      type="checkbox"
                      id="bar-user-info__follow"
              />
              <label
                      class="__follow-btn-msg"
                      for="bar-user-info__follow"
              >팔로우</label>
          </form>
      </div>
  </div>
</template>

<style>

/* ==========================================
      user info - follow btn
      user tree memoir index 사용
  ========================================== */
.bar-user-info-follow-btn-frame {
  display: flex;
  align-items: center;
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
.bar-user-info__user-name--color-main1 {
  color: var(--main1);
  font-size: 14px;
  font-weight: var(--medium);
  line-height: var(--line-height-main);
  letter-spacing: var(--letter-spacing-main);
}
.bar-user-info__user-id--color-main1 {
  color: var(--main1);
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
.__follow-btn {
  display: none;
  cursor: none;
}

.__follow-btn-msg {
  width: 60px;
  height: 30px;
  display: flex;
  align-items: center;
  border-radius: 12px;
  color: var(--main1);
  justify-content: center;
  font-size: 14px;
  font-weight: var(--semi-bold);
  line-height: var(--line-height-main);
  letter-spacing: var(--letter-spacing-main);
  background: var(--main2);
  box-shadow: var(--shadow-active);
}

.__follow-btn:checked + .__follow-btn-msg {
  width: 60px;
  height: 30px;
  display: flex;
  align-items: center;
  border-radius: 12px;
  color: var(--white);
  text-align: center;
  font-size: 14px;
  font-weight: var(--semi-bold);
  line-height: var(--line-height-main);
  letter-spacing: var(--letter-spacing-main);
  background: var(--main1);
}

</style>
