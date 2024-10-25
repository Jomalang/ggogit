<script>
import TopBarTransparent from "@/components/top-bar/TopBarTransparent.vue";
import BarUserInfo from "@/components/bar/BarUserInfo.vue";

export default {
  components: {
    TopBarTransparent,
    BarUserInfo
  },
  props: {
    imgSrc: String,
    src: String,
    backimgpath: String,
    userimg: String,
    username: String,
    userid: String,
    userurl: String
  },
  computed: {
    backgroundStyle() {
      return {
        backgroundImage: `linear-gradient(rgba(0, 0, 0, 0.4), rgba(0, 0, 0, 0.4)), url('/uploads/image/book/${this.backimgpath}')`
      }
    }
  }
}
</script>

<template>
  <div id="bg-book-detail-box-id" class="bg-book-detail-box">
    <img class="bg-book-detail__image" :src="imgSrc" alt="도서 이미지" />
    <div id="bg-book-detail__front-img-box-id" class="bg-book-detail__front-img-box">
      <img class="bg-book-detail__front-img" :src="imgSrc" alt="도서 이미지" />
    </div>
    <div class="user-tree-user-info-container" :style="backgroundStyle">
      <section class="user-info__top-bar-container">
        <top-bar-transparent :src="src" />
      </section>
      <section class="user-info__user-info-bar-container">
        <bar-user-info
            :userimg="userimg"
            :username="username"
            :userid="userid"
            :userurl="userurl"
        />
      </section>
    </div>
  </div>
</template>

<style scoped>
.bg-book-detail-box {
  width: 100%;
  height: 404px;
  position: relative;
  overflow: hidden;
}

.bg-book-detail__image {
  width: 100%;
  object-fit: cover;
  object-position: top;
  filter: blur(10px) brightness(0.8);
  transform: scale(1.5);
}

.bg-book-detail__front-img-box {
  position: absolute;
  width: 170px;
  left: 50%;
  bottom: 50px;
  transform: translateX(-50%);
  transition: all 0.5s ease;
}

.bg-book-detail__front-img-box--comment-active {
  position: absolute;
  width: 120px;
  left: 50%;
  top: 40px;
  bottom: auto;
  transform: translateX(-50%);
}

.bg-book-detail__front-img {
  width: 100%;
  box-shadow: 4px 4px 8px rgba(0, 0, 0, 0.25);
}
</style>