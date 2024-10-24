<script setup>
import { defineProps } from "vue";
import { useRouter } from "vue-router";

const props = defineProps({
  noticeText: {
    type: String,
    default: "서비스 개발 진행중",
  },
});

const router = useRouter();

const handleLogout = async () => {
  try {
    // 로그아웃 API 호출
    await fetch("/member/logout", { method: "POST" });
    // 로그아웃 성공 후 처리
    router.push("/index");
  } catch (error) {
    console.error("로그아웃 실패:", error);
  }
};
</script>

<template>
  <!-- footer(noticeText) -->
  <footer class="tree-footer">
    <header class="tree-footer-header">
      <h1 class="tree-footer-header__title">공지사항</h1>
      <p class="tree-footer-header__added">{{ props.noticeText }}</p>
    </header>
    <div class="tree-footer__membership">
      <form class="display__inline" @submit.prevent="handleLogout">
        <button class="membership-login">로그아웃</button>
      </form>
      <a href="#" class="membership-asking">문의하기</a>
    </div>
    <div class="tree-footer__app">
      <a href="#" class="app-info">이용약관</a>
      <span class="app-sep"> | </span>
      <a href="#" class="app-membership-policy">개인정보처리방침</a>
    </div>
    <div class="tree-footer__logo">
      <img
        src="/img/logo/logo-sample.png"
        style="width: 80px; height: 80px"
        alt="app-logo"
      />
    </div>
  </footer>
</template>

<style scoped>
a {
  text-decoration: none;
}

button {
  border: none;
  background-color: transparent;
  cursor: pointer;
}
.tree-footer::after {
  content: "by RECODERS";
  text-align: center;
  align-self: center;
  width: 100%;
  height: 89px;
  display: block;
  bottom: 0;
}

.tree-footer-header {
  background-color: var(--main3);
  display: flex;
  flex-direction: row;
  align-items: center;
  gap: 30px;
  padding: 10px 24px;
  border: 1px solid rgba(0, 0, 0, 0.1);
}

.tree-footer-header__title {
  font-size: 16px;
  font-weight: var(--bold);
}

.tree-footer-header__added {
  font-size: 14px;
  font-weight: 300;
  text-decoration: underline;
}

.tree-footer__membership {
  margin-top: 20px;
  padding: 0 24px;
  font-size: 14px;
}
.membership-login {
  font-size: 14px;
  font-weight: var(--bold);
  color: var(--text-sub);
  padding: 0;
  margin-right: 10px;
}

.membership-join {
  font-size: 14px;
  font-weight: var(--bold);
  color: var(--text-sub);
  margin-right: 10px;
}

.membership-asking {
  font-size: 14px;
  font-weight: var(--bold);
  color: var(--text-sub);
  margin-right: 10px;
}

.tree-footer__app {
  margin-top: 20px;
  margin-bottom: 20px;
  padding: 0 24px;
}

.app-info {
  font-size: 12px;
  font-weight: 700;
  color: var(--text-sub);
}

.app-sep {
  font-size: 12px;
  color: var(--text-sub);
}

.app-membership-policy {
  font-size: 12px;
  font-weight: var(--bold);
  color: var(--text-sub);
}

.tree-footer__logo {
  display: flex;
  justify-content: end;
  padding-right: 24px;
}
</style>
