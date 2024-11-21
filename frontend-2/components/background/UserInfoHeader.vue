<script setup>
const props = defineProps({
  edit: "",
  backImgPath: "background-image.png",
  userProfileImg: "",
  userName: "",
  userId: "",
  userUrl: "",
  isOwner: false,
});

// console.log("BackgroundUserInfoBackHeaderMemoirTitle");
// console.log(props);

const config = useRuntimeConfig();
//TODO: 배경 이미지과 프로필 이미지 구별해야 함.
const backgroundStyle = computed(() => ({
  backgroundImage: useGetImageUrl(props.backImgPath, "memberBackground"),
  backgroundSize: "cover", // 이미지를 화면에 맞게 조절
  backgroundPosition: "center", // 이미지를 중앙 정렬
  width: "100%",
  height: "100%",
}));
</script>

<template>
  <div class="user-info-container">
    <div class="user-info__background-frame" :style="backgroundStyle">
      <section class="user-info__top-bar-container">
        <TopBarTransparentOnlyEdit
          :edit="props.edit"
          :editBtnActive="props.isOwner"
        />
      </section>
      <section class="user-info__bot-bar-container">
        <BarUserInfoMyPage
          :userImg="props.userProfileImg"
          :username="props.userName"
          :userid="props.userId"
          :userUrl="props.userUrl"
        />
      </section>
    </div>
  </div>
</template>

<style scoped>
.user-info-container {
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  height: 250px;
  background-repeat: no-repeat;
  background-size: cover;
  background-position-y: center;
  background-position-x: center;
}
.user-info__background-frame {
  display: flex;
  flex-direction: column;
  background-color: rgba(0, 0, 0, 0.4);
}

.user-info__top-bar-container {
  display: flex;
}
.user-info__bot-bar-container {
}
</style>
