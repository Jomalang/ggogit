<script setup lang="ts">

const props = defineProps<{
    noticeText: String;
}>();

</script>

<template>
    <footer class="tree-footer" notice-text="">
        <header class="tree-footer-header">
            <h1 class="tree-footer-header__title">공지사항</h1>
            <p class="tree-footer-header__added">{{noticeText}}</p>
        </header>
        <div class="tree-footer__membership">
            <form class="display__inline" method="post" action="/member/logout" >
                <button class="membership-login">로그아웃</button>
            </form>
            <a href="#" class="membership-asking">문의하기</a>
        </div>
        <div class="tree-footer__app">
            <a href="#" class="app-info">이용약관</a>
            <span class="app-sep">|</span>
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