<script setup>
const props = defineProps({
    active: {
        type: String,
        default: ''
    }
})
</script>

<template>
    <!-- navigation-bar(active) -->
    <nav class="nav-box">
        <h1 class="none">네비게이션</h1>
        <ul class="nav-box__list">
            <!-- 홈 -->
            <li class="nav-box__item">
                <a class="nav-box__link" href="/">
                    <div class="nav-box__img-box" :class="{ 'nav-box__img-box--active': props.active === 'home' }">
                        <img
                            class="main-nav__icon"
                            :src="props.active === 'home' ? '/svg/nav-home--active.svg' : '/svg/nav-home.svg'"
                            alt="홈 네비게이션 이미지"
                        />
                    </div>
                    <p class="nav-box__text" :class="{ 'nav-box__text--active': props.active === 'home' }">홈</p>
                </a>
            </li>

            <!-- 탐색 -->
            <li class="nav-box__item">
                <a class="nav-box__link" href="#">
                    <div class="nav-box__img-box" :class="{ 'nav-box__img-box--active': props.active === 'search' }">
                        <img
                            class="main-nav__icon"
                            :src="props.active === 'search' ? '/svg/nav-search--active.svg' : '/svg/nav-search.svg'"
                            alt="탐색 네비게이션 이미지"
                        />
                    </div>
                    <p class="nav-box__text" :class="{ 'nav-box__text--active': props.active === 'search' }">탐색</p>
                </a>
            </li>

            <!-- 커뮤니티 -->
            <li class="nav-box__item">
                <a class="nav-box__link" href="#">
                    <div class="nav-box__img-box" :class="{ 'nav-box__img-box--active': props.active === 'community' }">
                        <img
                            class="main-nav__icon"
                            :src="props.active === 'community' ? '/svg/nav-community--active.svg' : '/svg/nav-community.svg'"
                            alt="커뮤니티 네비게이션 이미지"
                        />
                    </div>
                    <p class="nav-box__text" :class="{ 'nav-box__text--active': props.active === 'community' }">커뮤니티</p>
                </a>
            </li>

            <!-- 마이페이지 -->
            <li class="nav-box__item">
                <a class="nav-box__link" href="#">
                    <div class="nav-box__img-box" :class="{ 'nav-box__img-box--active': props.active === 'mypage' }">
                        <img
                            class="main-nav__icon"
                            :src="props.active === 'mypage' ? '/svg/nav-mypage--active.svg' : '/svg/nav-mypage.svg'"
                            alt="마이페이지 네비게이션 이미지"
                        />
                    </div>
                    <p class="nav-box__text" :class="{ 'nav-box__text--active': props.active === 'mypage' }">나의 꼬깃</p>
                </a>
            </li>
        </ul>
    </nav>
</template>

<style scoped>
.nav-box {
    box-shadow: 0 -5px 10px -5px rgba(0, 0, 0, 0.3);
    background-color: var(--white, #ffffff);
}

.nav-box__list {
    display: flex;
    width: 100%;
}

.nav-box__link {
    text-decoration: none;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
}

.nav-box__item {
    display: flex;
    justify-content: center;
    padding: 12px 16px;
    align-items: center;
    flex: 1;
}

.nav-box__img-box {
    margin: 4px 0;
    display: flex;
    justify-content: center;
    align-items: center;
    width: 64px;
    height: 32px;
}

.nav-box__icon-obj .nav-box__svg-path {
    fill: var(--main1, #323a27);
}

.nav-box__img-box--active {
    margin-bottom: 4px;
    display: flex;
    justify-content: center;
    align-items: center;
    width: 64px;
    height: 32px;
    border-radius: 100px;
    background-color: var(--main2, #e5eddb);
}

.nav-box__text {
    font-weight: var(--semi-bold);
    line-height: var(--line-height-main);
    letter-spacing: var(--letter-spacing-main);
    font-size: 12px;
    color: var(--text-sub, #767676);
}

.nav-box__text--active {
    font-weight: var(--semi-bold);
    line-height: var(--line-height-main);
    letter-spacing: var(--letter-spacing-main);
    font-size: 12px;
    color: var(--main1, #323a27);
}
</style>