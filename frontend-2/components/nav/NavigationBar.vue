<script setup>
const navStore = useNavStore();
const backStore = useBackStore();
const { _id } = useMemberStore();

const {
  home: _home,
  search: _search,
  community: _community,
  mypage: _mypage,
  currentSpot: _currentSpot,
} = storeToRefs(navStore);

const setCur = (spotNum) => {
  //nav를 통해 이동하는 경우에는 스택을 쌓지 않는다.
  backStore.IsBackToTrue();
  //어떤 nav를 클릭했는지 알기 위해 spotNum을 넘겨준다.
  navStore.setCurrentSpot(spotNum);
};

const props = defineProps({
  active: {
    type: String,
    default: "home",
  },
});

const developing = () => {
  alert("개발중입니다.");
};

const handleDbAction = (event, type, path) => {
  if (type === "dbclick" || type === "doubletap") {
    if (path === 1) {
      navigateTo("/home");
    } else if (path === 2) {
      navigateTo("/search/book");
    } else if (path === 4) {
      navigateTo(`/member/${_id}`);
    }
  }
};
</script>

<template>
  <!-- navigation-bar(active) -->
  <nav class="nav-box">
    <h1 class="none">네비게이션</h1>
    <div class="nav-frame">
      <ul class="nav-box__list">
        <!-- 홈 -->
        <li
          class="nav-box__item"
          v-double-action="(event, type) => handleDbAction(event, type, 1)"
        >
          <NuxtLink class="nav-box__link" :to="_home" @click="setCur(1)">
            <div
              class="nav-box__img-box"
              :class="{
                'nav-box__img-box--active': _currentSpot === '_home',
              }"
            >
              <img
                class="main-nav__icon"
                :src="
                  props.active === 'home'
                    ? '/svg/nav-home--active.svg'
                    : '/svg/nav-home.svg'
                "
                alt="홈 네비게이션 이미지"
              />
            </div>
            <p
              class="nav-box__text"
              :class="{
                'nav-box__text--active': _currentSpot === '_home',
              }"
            >
              홈
            </p>
          </NuxtLink>
        </li>

        <!-- 탐색 -->
        <li
          class="nav-box__item"
          v-double-action="(event, type) => handleDbAction(event, type, 2)"
        >
          <NuxtLink class="nav-box__link" :to="_search" @click="setCur(2)">
            <div
              class="nav-box__img-box"
              :class="{
                'nav-box__img-box--active': _currentSpot === '_search',
              }"
            >
              <img
                class="main-nav__icon"
                :src="
                  props.active === 'search'
                    ? '/svg/nav-search--active.svg'
                    : '/svg/nav-search.svg'
                "
                alt="탐색 네비게이션 이미지"
              />
            </div>
            <p
              class="nav-box__text"
              :class="{ 'nav-box__text--active': _currentSpot === '_search' }"
            >
              탐색
            </p>
          </NuxtLink>
        </li>

        <!-- 커뮤니티 -->
        <li class="nav-box__item">
          <NuxtLink class="nav-box__link" :to="''" @click="developing">
            <div
              class="nav-box__img-box"
              :class="{
                'nav-box__img-box--active': _currentSpot === '_community',
              }"
            >
              <img
                class="main-nav__icon"
                :src="
                  props.active === 'community'
                    ? '/svg/nav-community--active.svg'
                    : '/svg/nav-community.svg'
                "
                alt="커뮤니티 네비게이션 이미지"
              />
            </div>
            <p
              class="nav-box__text"
              :class="{
                'nav-box__text--active': _currentSpot === '_community',
              }"
            >
              커뮤니티
            </p>
          </NuxtLink>
        </li>

        <!-- 마이페이지 -->
        <li
          class="nav-box__item"
          v-double-action="(event, type) => handleDbAction(event, type, 4)"
        >
          <NuxtLink class="nav-box__link" :to="_mypage" @click="setCur(4)">
            <div
              class="nav-box__img-box"
              :class="{
                'nav-box__img-box--active': _currentSpot === '_mypage',
              }"
            >
              <img
                class="main-nav__icon"
                :src="
                  props.active === 'mypage'
                    ? '/svg/nav-mypage--active.svg'
                    : '/svg/nav-mypage.svg'
                "
                alt="마이페이지 네비게이션 이미지"
              />
            </div>
            <p
              class="nav-box__text"
              :class="{ 'nav-box__text--active': _currentSpot === '_mypage' }"
            >
              나의 꼬깃
            </p>
          </NuxtLink>
        </li>
      </ul>
    </div>
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
