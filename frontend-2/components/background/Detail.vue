<script setup>

const props = defineProps({
  backImgPath: { type: String, default: '' },
  edit: { type: String, default: '' },
  username: { type: String, default: '' },
  userid: { type: Number, default: '' },
  memoirTitle: { type: String, default: '' },
  userUrl: { type: String, default: '' },
});

const backgroundStyle = computed(() => {
  return {
    backgroundImage: `linear-gradient(rgba(0, 0, 0, 0.4), rgba(0, 0, 0, 0.4)), url('/png/${props.backImgPath}')`,
  };
});

</script>

<template>
  <div class="user-tree-user-info-container" :style="backgroundStyle">
    <section class="user-info__top-bar-container">
      <TopBarTransparent :edit="edit" />
    </section>
    <section class="user-info__user-info-bar-container">
      <BarUserInfoNoProfileBtn :username="username" :userid="userid" />
      <BarMemoirTitleBtn :memoirTitle="memoirTitle" :userUrl="userUrl" />
    </section>
  </div>
</template>

<style scoped>
.user-tree-user-info-container {
  padding: 35px 24px 10px;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  height: 200px;
  background-repeat: no-repeat;
  background-size: cover;
  background-position-y: center;
  background-position-x: center;
}
.user-info__top-bar-container {
  display: flex;
}
</style>
