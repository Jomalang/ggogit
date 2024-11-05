<script setup>
import BarUserInfoNoProfileBtn from "@/components/bar/BarUserInfoNoProfileBtn.vue";
import BarMemoirTitleBtn from "@/components/bar/BarMemoirTitleBtn.vue";
const props = defineProps({
  edit: "",
  delete: "",
  backimgpath: "",
  userName: "",
  userId: "",
  bookTitle: "",
  userUrl: "",
});

const backgroundStyle = computed(() => ({
  //TODO: 배경 뒤 이미지 API제작
  backgroundImage: `linear-gradient(rgba(0, 0, 0, 0.4), rgba(0, 0, 0, 0.4)), url('${props.backimgpath}')`,
}));
</script>

<template>
  <div class="user-tree-user-info-container" :style="backgroundStyle">
    <section class="user-info__top-bar-container">
      <TopBarTransparent :edit="props.edit" :delete="props.delete" />
    </section>
    <section class="user-info__user-info-bar-container">
      <BarUserInfoNoProfileBtn
        :username="props.userName"
        :userid="props.userId"
      />
      <BarMemoirTitleBtn
        :memoirTitle="props.bookTitle"
        :userUrl="props.userUrl"
      />
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
