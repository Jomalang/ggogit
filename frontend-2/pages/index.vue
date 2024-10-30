<script setup lang="ts"></script>

<template>
  <div class="welcome-container">
    <a class="welcome" href="member/login">꼬깃 시작하기</a>
  </div>
</template>

<style scoped>
html,
body {
  height: 100vh;
}
.welcome-container {
  display: flex;
  justify-content: center;
  align-items: center;
  margin-top: 40vh;
}
.welcome {
  color: var(--main1);
  font-size: 48px;
  font-weight: var(--medium);
  box-shadow: var(--shadow-basic);
  transition: all 0.3s linear;
  padding: 20px;
  border-radius: 10px;
  background-color: var(--main2);
}
.welcome:hover {
  box-shadow: var(--shadow-active);
}
</style>
