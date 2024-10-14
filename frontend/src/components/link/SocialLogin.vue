<script setup lang="ts"></script>

<template>
  <div class="social-login">
    <div class="social-login__icons">
      <a href="#"
        ><div><img src="/public/svg/naver-circle.svg" alt="" /></div
      ></a>
      <a href="#"
        ><div><img src="/public/svg/google-circle.svg" alt="" /></div
      ></a>
      <a href="#"
        ><div><img src="/public/svg/kakao-circle.svg" alt="" /></div
      ></a>
    </div>
  </div>
</template>

<style>
/*===============================================
    FRAGMENT: 소셜 로그인
===============================================*/
.social-login {
  width: 100%;
  display: flex;
  justify-content: center;
}

.social-login__icons {
  display: flex;
  justify-content: center;
  gap: 48px;
}
</style>
