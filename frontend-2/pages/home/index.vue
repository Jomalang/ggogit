<script setup>
import { Splide, SplideSlide, SplideTrack } from "@splidejs/vue-splide";
import "@splidejs/splide/dist/css/splide.min.css";

//-------------------변수 선언--------------------

const config = useRuntimeConfig();
const treeInfoList = ref([]);
const filterTreeInfoList = ref([]);
const seedList = ref([]);
const seedId = ref(0);
let observer = null;
const memberDetail = useMemberStore();
const { _nickname: username } = storeToRefs(memberDetail);

const { data: seedData, status: seedStatus } = await useAuthFetch("seeds", {
  baseURL: `${config.public.apiBase}`,
  method: "GET",
});

const {
  data: treeData,
  status: treeStatus,
  refresh: treeRefresh,
} = await useAuthFetch("trees/tree-home", {
  baseURL: `${config.public.apiBase}`,
  method: "GET",
});

const newTreeFetch = async (newSeedId) => {
  seedId.value = newSeedId;
  const response = await useAuthDataFetch("trees/tree-home-sort", {
    baseURL: `${config.public.apiBase}`,
    method: "GET",
    params: {
      seedId: seedId.value,
    },
  });
  if (response) {
    // console.log(response);
    // console.log(response.treeInfoResponseList);
    filterTreeInfoList.value = [...response.treeInfoResponseList];
  }
};

watchEffect(() => {
  if (treeStatus.value === "success" && treeData.value) {
    treeInfoList.value = [...treeData.value.treeInfoResponseList];
    filterTreeInfoList.value = [...treeData.value.treeInfoResponseList];
  } else {
    // console.log("treeData.value is null");
  }

  if (seedStatus.value === "success" && seedData.value) {
    seedList.value = [...seedData.value.items];
  } else {
    // console.log("seedData.value is null");
  }
});

const splideMounted = (splide) => {
  const selectedElement = document.getElementById("slide-0");
  bookExRemoveNone(selectedElement, 0);
};

const splideMoved = (splide, newIndex, prevIndex) => {
  const index = newIndex;
  const selectedElement = document.getElementById(`slide-${index}`);
  const prevElement = document.getElementById(`slide-${prevIndex}`);
  bookExRemoveNone(selectedElement, index);
};

const bookExRemoveNone = (selectedElement, index) => {
  document.querySelectorAll(".slide-item").forEach((item) => {
    // if (item === selectedElement) {
    //   // console.log("selectedElement", selectedElement);
    //   item.setAttribute("class", "slide-item item__transform");
    // } else {
    //   item.setAttribute("class", "slide-item");
    // }
  });

  document
    .querySelectorAll(".textbox-recent-tree-info__frame")
    .forEach((item) => {
      if (item.classList.contains(`slide-info-${index}`)) {
        item.classList.remove("none");
      } else {
        item.classList.add("none");
      }
    });
  document.querySelectorAll(".book-tree-explanation-item").forEach((item) => {
    if (item.classList.contains(`slide-progress-${index}`)) {
      item.classList.remove("none");
    } else {
      item.classList.add("none");
    }
  });
};

//-------------------LifeCycle-------------------
</script>

<template>
  <header>
    <h1 class="none">나의 트리</h1>
    <section class="header-search-container">
      <h2 class="none">나의 트리 검색 링크</h2>
      <HeaderSearchLink :link="`/tree/search`" />
    </section>
  </header>

  <main v-if="treeInfoList.length === 0">
    <section class="my-tree-container">
      <h1 class="none">나의 트리 정보</h1>

      <section class="my-tree-title-container">
        <TextMainTitle
          :data="{ title: `${username}님의 최근 트리`, size: 28 }"
        />
      </section>

      <section class="book-img-container">
        <h1 class="none">트리 이미지</h1>
        <BackgroundBgNoTreeBook />
      </section>
    </section>

    <section class="margin-bottom160">
      <TextInfo
        text="현재 기록중인 트리가 없습니다"
        boldText="트리를 생성해주세요"
      />
    </section>
  </main>

  <main v-else>
    <section class="my-tree-container">
      <h2 class="none">나의 트리 정보</h2>
      <section class="my-tree-title-container">
        <h3>
          <TextMainTitle
            :data="{ title: `${username}님의 최근 트리`, size: 28 }"
          />
        </h3>
      </section>

      <section class="book-tree-info-container">
        <h3 class="none">최근 트리 이미지 캐러셀</h3>
        <section class="book-img-container">
          <!-- <BackgroundBgTreeBookCovers :treeInfoList="treeInfoList" /> -->
          <Splide
            :options="{
              type: 'loop',
              perPage: 3,
              width: '100%',
              focus: 'center',
              heightRatio: 0.5,
              speed: 800,
              easing: 'ease',
              dragMinThreshold: {
                mouse: 100,
                touch: 30,
              },
              arrow: true,
              padding: 0,
              pagination: true,
              breakpoints: {
                768: {
                  perPage: 2,
                  heightRatio: 0.8,
                },
              },
            }"
            aria-label="Tree-books"
            @splide:moved="splideMoved"
            @splide:mounted="splideMounted"
          >
            <SplideSlide
              class="slide-frame"
              v-for="(tree, index) in treeInfoList"
            >
              <NuxtLink
                class="slide-item"
                :to="`/tree/${tree.treeId}`"
                :id="`slide-${index}`"
              >
                <img
                  class="mid__img"
                  :src="
                    tree.coverImageName
                      ? useGetImageUrl(tree.coverImageName)
                      : useGetImageUrl(tree.treeImage, 'tree')
                  "
                  alt="도서 예시 이미지"
                />
              </NuxtLink>
            </SplideSlide>
            <SplideSlide v-if="treeInfoList.length === 1">
              <NuxtLink class="slide-item" :to="`/tree/seed`" id="slide-1">
                <img
                  class="mid__img"
                  src="~/assets/png/tree-book-blank.png"
                  alt="도서 예시 이미지"
                />
              </NuxtLink>
            </SplideSlide>
            <SplideSlide v-if="treeInfoList.length <= 2">
              <NuxtLink class="slide-item" :to="`/tree/seed`" id="slide-2">
                <img
                  class="mid__img"
                  src="~/assets/png/tree-book-blank.png"
                  alt="도서 예시 이미지"
                />
              </NuxtLink>
            </SplideSlide>
          </Splide>
        </section>

        <section class="textbox-recent-tree-info-container">
          <h3 class="none">트리 약식 정보</h3>
          <ul>
            <li
              v-for="(tree, index) in treeInfoList"
              :class="`textbox-recent-tree-info__frame slide-info-${index} none`"
              :key="index"
            >
              <TextRecentTreeInfo :tree="tree" />
            </li>
          </ul>
        </section>

        <section class="book-tree-explanation-container">
          <h3 class="none">트리 설명</h3>
          <ul class="book-tree-explanation-list">
            <li
              v-for="(tree, index) in treeInfoList"
              :class="`book-tree-explanation-item slide-progress-${index} none`"
              :key="index"
            >
              <TextRecentTreeEx :text="tree.description" />
              <section class="card-progress-home-container">
                <h4 class="none">트리 진행률</h4>
                <div v-if="tree.seedId === 1">
                  <CardProgressBar
                    :progress="
                      tree.seedId === 1
                        ? (
                            (tree.readingPage * 100.0) /
                            tree.bookTotalPage
                          ).toFixed(1)
                        : 0
                    "
                    :readingPage="tree.readingPage"
                    :totalPage="tree.bookTotalPage"
                  />
                </div>
                <CardReactNumbers
                  :leaf="tree.treeLeafCnt"
                  :like="tree.treeLikeCnt"
                  :view="tree.treeViewCnt"
                />
              </section>
            </li>
          </ul>
        </section>
      </section>
    </section>

    <section class="my-tree-list">
      <h2 class="none">나의 트리 목록</h2>
      <TextTreeCount :num="treeInfoList.length" />
      <section id="seed-filter">
        <h2 class="none">트리 정렬 필터 버튼</h2>
        <div>
          <FilterTreeList :seedList="seedList" @seed-filter="newTreeFetch" />
        </div>
      </section>
    </section>

    <section>
      <h2 class="none">트리 검색 결과</h2>
      <section class="tree-card-list" id="tree-card-list">
        <h3 class="none">트리 검색 리스트</h3>
        <div v-if="filterTreeInfoList.length === 0">
          <div class="tree-card-list__main">
            <div class="card-tree__img-frame-no-tree">
              <img
                class="card-tree__book-cover"
                src="/png/no-tree-mid-book.png"
                alt="treeCover"
              />
            </div>
            <p class="text--title20">조회된 트리가 없습니다.</p>
          </div>
        </div>
        <div
          class="card-tree-details"
          v-for="tree in filterTreeInfoList"
          :key="tree.treeId"
        >
          <CardTreeDetails :tree="tree" />
        </div>
      </section>
    </section>
  </main>

  <footer>
    <Footer :noticeText="`개발중입니다.`" />
  </footer>

  <section class="nav-back-container">
    <h3 class="none">네비 바 뒤 공백</h3>
  </section>

  <aside class="nav-container">
    <section class="short-btn-container">
      <h4 class="none">트리 생성 버튼</h4>
      <ButtonBtnShortAGreen :link="`/tree/seed`" :text="`트리 생성`" />
    </section>
    <NavNavigationBar active="home" />
  </aside>
</template>

<style scoped>
.tree-card-list {
  display: flex;
  flex-direction: column;
  gap: 20px;
  margin-left: 24px;
  margin-right: 24px;
}

.tree-card-list__main {
  display: flex;
  flex-direction: column;
  gap: 20px;
  margin-top: 24px;
  margin-left: 24px;
  margin-right: 24px;
  align-items: center;
}

.tree-card-list::after {
  content: " ";
  display: block;
  width: 100%;
  height: 50px;
}

.slide-item {
  display: block;
  max-width: 80%;
  margin: 30px;
  transition: all 0.5s ease;
}

.mid__img {
  width: 90%;
  height: auto;
  object-fit: cover;
  border-radius: 10px;
}

@media screen and (max-width: 768px) {
  .mid__img {
    width: 100%;
    height: auto;
  }
}
@media screen and (max-width: 480px) {
  .mid__img {
    width: 120%;
    height: auto;
  }
}
.item__transform {
  transform: scale(1.2);
}
</style>
