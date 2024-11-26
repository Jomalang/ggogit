<script setup>
const props = defineProps({
  edit: "",
  backImgPath: "",
  userProfileImg: "",
  userName: "",
  userEmail: "",
  userUrl: "",
  isOwner: false,
});

const config = useRuntimeConfig();
</script>

<template>
  <div class="user-info-container">
    <div class="user-info__background-frame">
      <img
        class="user-info__background-image"
        :src="useGetImageUrl(props.backImgPath, 'memberBackground')"
        alt="user-Background"
      />
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
          :userEmail="props.userEmail"
          :userUrl="props.userUrl"
        />
      </section>
    </div>
  </div>
</template>

<style scoped>
.user-info-container {
  width: 100%;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
}
.user-info__background-frame {
  box-sizing: border-box;
  position: relative;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  background-color: rgba(0, 0, 0, 0.3);
  background-repeat: no-repeat;
  background-size: cover;
  background-position-y: center;
  background-position-x: center;
  height: 300px;
  width: 100%;
  max-width: var(--max-width-1);
  padding: 24px;
}

.user-info__top-bar-container {
  display: flex;
}
.user-info__bot-bar-container {
  transform: translateY(60%);
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
</style>
