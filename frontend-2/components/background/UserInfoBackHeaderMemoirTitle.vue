<script setup>
import TopBarTransparent from "@/components/top-bar/TopBarTransparent.vue";
import BarUserInfoNoProfileBtn from "@/components/bar/BarUserInfoNoProfileBtn.vue";
import BarMemoirTitleBtn from "@/components/bar/BarMemoirTitleBtn.vue";
defineProps({
  backimgpath: String,
  edit: String,
  username: String,
  userid: String,
  memoirTitle: String,
  userUrl: String,
});

const backgroundStyle = computed(() => ({
  backgroundImage: `linear-gradient(rgba(0, 0, 0, 0.4), rgba(0, 0, 0, 0.4)), url('/png/${props.backimgpath}')`,
}));
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
