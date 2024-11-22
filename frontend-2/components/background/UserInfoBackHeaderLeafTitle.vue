<script setup>
const props = defineProps({
  edit: "",
  delete: "",
  backImgPath: "",
  userName: "",
  userId: "",
  bookTitle: "",
  userUrl: "",
  isOwner: false,
});

// console.log("BackgroundUserInfoBackHeaderMemoirTitle");
// console.log(props);

const config = useRuntimeConfig();
</script>

<template>
  <div class="user-tree-user-info-container">
    <section class="user-info__top-bar-container">
      <TopBarTransparentWithoutDelete
        :edit="props.edit"
        :delete="props.delete"
        :deleteBtnActive="props.isOwner"
      />
    </section>

    <section class="user-info__user-info-bar-container">
      <img
        class="user-info__background-image"
        :src="useGetImageUrl(props.backImgPath, 'memberBackground')"
        alt="user-Background"
      />
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
  box-sizing: border-box;
  padding: 24;
  display: flex;
  position: relative;
  flex-direction: column;
  justify-content: space-between;
  height: 250px;
  background-color: rgba(0, 0, 0, 0.3);
  background-repeat: no-repeat;
  background-size: cover;
  background-position-y: center;
  background-position-x: center;
}
.user-info__background-image {
  width: 100%;
  height: 100%;
  object-fit: cover;
  position: absolute;
  top: 0;
  left: 0;
  z-index: -1;
}
.user-info__top-bar-container {
  display: flex;
  flex-direction: column;
  width: 100%;
  height: 250px;
  max-width: var(--max-width-1);
}
</style>
