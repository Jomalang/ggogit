<script setup>
import _ from "lodash";
import HeaderSearchLink from "@/components/header/HeaderSearchLink.vue";
import TextMainTitle from "@/components/text/TextMainTitle.vue";
import BgTreeBookCovers from "@/components/background/BgTreeBookCovers.vue";
import RecentTreeInfo from "@/components/text/RecentTreeInfo.vue";
import RecentTreeEx from "@/components/text/RecentTreeEx.vue";
import CardProgressBar from "@/components/card/CardProgressBar.vue";
import CardReactNumbers from "@/components/card/CardReactNumbers.vue";
import FilterTreeList from "@/components/filter/FilterTreeList.vue";
import CardTreeDetails from "@/components/card/CardTreeDetails.vue";
import Footer from "@/components/footer/Footer.vue";
import NavigationBar from "@/components/nav/NavigationBar.vue";
import BtnShortGreen from "@/components/button/BtnShortAGreen.vue";

import { onBeforeMount, onMounted, reactive } from "vue";

//-------------------------------변수 선언--------------------------------
const treeInfoList = reactive([]);
const seedList = reactive([]);

onBeforeMount(() => {
  const fetchTreeInfoList = async () => {
    const response = await fetch(
      `${import.meta.env.VITE_API_BASE_URL}/api/v1/trees`
    );
    const data = await response.json();
    treeInfoList.push(...data);
  };

  fetchTreeInfoList();
});

onMounted(() => {
  const carouselList = document.querySelector(".tree-book-bg__list");
  // carousel item 너비
  // const width = document.querySelector(".mid__item").clientWidth;
  const width = window.innerWidth * 0.3;
  const midWidth = window.innerWidth * 0.2;
  //carousel items
  const carouselItems = document.querySelectorAll(".mid__item");
  // carousel item 전체 갯수
  const carouselItemCount = carouselItems.length / 3;
  // --------------- 한 가운데 item 찾아내기 위한 IntersectionObserver코드 --------------
  const container = carouselList.querySelector(".tree-book-bg");
  const observer = new IntersectionObserver(
    (entries) => {
      let selectedElement = null;

      entries.forEach((entry) => {
        if (entry.isIntersecting) {
          entry.target.classList.add("selected");
        } else {
          entry.target.classList.remove("selected");
        }
      });
      selectedElement = calcMidTree();
      if (selectedElement) {
        bookExRemoveNone(selectedElement);
      }
    },
    { root: container, threshold: 0.6 }
  );

  carouselItems.forEach((item) => {
    observer.observe(item);
  });

  //-----------------------------------carousel 이동 로직-----------------------------------

  // 현재 X값, 무엇의?
  let currentTranslateX = 0;
  // drag 시작여부
  let isMove = false;
  // drag 시작위치 X값
  let moveStartX = 0;
  // drag에 의해 움직인 X값
  let moveTranslateX = 0;
  // drag에 의해 변경될 X값
  let nextTranslateX = 0;
  // item 이동을 위한 gap 기준값
  const moveGap = 30;
  // drag 종료시간을 얻기 위한 기준 시간 획득
  let dragEndTime = new Date().getTime();

  // drag 시작 이벤트
  const dragStart = (e, clientX) => {
    isMove = true;
    moveStartX = clientX;

    // carousel list transition 제거
    carouselList.classList.remove("tree-book-bg__list--transition");
    // drag 종료시점으로부터 transition 시간이 지났는지 확인
    const dragEndStartGapTime = new Date().getTime() - dragEndTime; // drag 종료부터 다시 시작하기까지 걸린 시간 (단위 ms)

    let dragEndStartGapTranslateX = 0; // drag 종료부터 다시 시작하기까지 이동하지 못한 translateX

    if (dragEndStartGapTime <= 600) {
      // transition 시간보다 적은 경우
      dragEndStartGapTranslateX =
        (nextTranslateX - currentTranslateX) *
        ((600 - dragEndStartGapTime) / 600 / 1.5);
    }

    // 두번째 carousel-item 위치로 변경
    currentTranslateX =
      -(
        ((-currentTranslateX / width) % carouselItemCount) +
        carouselItemCount
      ) *
        width +
      dragEndStartGapTranslateX;
    nextTranslateX = currentTranslateX;
    carouselList.style.transform = `translateX(${currentTranslateX}px)`;
  };

  // drag 중 이벤트
  const dragging = (e) => {
    if (isMove) {
      let clientX = e.clientX;
      if (e.type === "touchmove") {
        clientX = e.targetTouches[0].clientX;
      }
      moveTranslateX = clientX - moveStartX;
      nextTranslateX = currentTranslateX + moveTranslateX;

      // 기본 동작 방지 (특히 모바일에서의 스크롤)
      if (e.type.includes("touch")) {
        e.preventDefault(); // 기본 터치 스크롤 동작을 방지
      }

      // 오른쪽으로 최대 이동한 경우
      if (nextTranslateX < -midWidth * (carouselItemCount * 3)) {
        console.log("right end");
        nextTranslateX = -midWidth * (carouselItemCount * 3);
      }

      // 왼쪽으로 최대 이동한 경우
      else if (nextTranslateX > 0) {
        console.log("left end");
        nextTranslateX = 0;
      }
      carouselList.style.transform = `translateX(${nextTranslateX}px)`;
    }
  };

  // drag 종료 이벤트
  const dragEnd = (e) => {
    if (isMove) {
      // 초기화
      isMove = false;
      moveStartX = 0;
      carouselList.classList.add("tree-book-bg__list--transition");
      dragEndTime = new Date().getTime();

      // 오른쪽으로 이동한 경우
      if (currentTranslateX > nextTranslateX) {
        if ((currentTranslateX - nextTranslateX) % width > moveGap) {
          currentTranslateX =
            -(Math.floor(-nextTranslateX / width) + 1) * width;
        } else {
          currentTranslateX = -Math.floor(-nextTranslateX / width) * width;
        }
      }

      // 왼쪽으로 이동한 경우
      else if (currentTranslateX < nextTranslateX) {
        if ((nextTranslateX - currentTranslateX) % width > moveGap) {
          currentTranslateX = -Math.floor(-nextTranslateX / width) * width;
        } else {
          currentTranslateX =
            -(Math.floor(-nextTranslateX / width) + 1) * width;
        }
      }

      // 동일한 위치인 경우
      else {
        // item 중간을 기준으로 오른쪽에 더 치우친 경우
        if (Math.abs(currentTranslateX) % width >= width / 2) {
          currentTranslateX =
            -(Math.floor(-currentTranslateX / width) + 1) * width;
        }

        // item 중간을 기준으로 왼쪽에 더 치우친 경우
        else {
          currentTranslateX = -Math.floor(-currentTranslateX / width) * width;
        }
      }
      carouselList.style.transform = `translateX(${currentTranslateX}px)`;
    }
  };
  //--------------------------------가운데 트리 확대 로직--------------------------------
  const bookExRemoveNone = (selectedElement) => {
    const id = selectedElement.classList[1];

    carouselList.querySelectorAll(".mid__item").forEach((item) => {
      if (item === selectedElement) {
        item.querySelector(".mid__img").classList.add("center__img");
      } else {
        item.querySelector(".mid__img").classList.remove("center__img");
      }
    });

    document
      .querySelectorAll(".textbox-recent-tree-info__frame")
      .forEach((item) => {
        if (item.classList.contains(id)) {
          item.classList.remove("none");
        } else {
          item.classList.add("none");
        }
      });

    document.querySelectorAll(".book-tree-explanation-item").forEach((item) => {
      if (item.classList.contains(id)) {
        item.classList.remove("none");
      } else {
        item.classList.add("none");
      }
    });
  };

  const calcMidTree = () => {
    let ids = [];
    let ret = null;
    carouselList.querySelectorAll(".selected").forEach((item) => {
      ids.push(item.classList[1]);
    });
    const midId = String(ids[1]);
    carouselList.querySelectorAll(".selected").forEach((item) => {
      if (item.classList[1] === midId) {
        ret = item;
      }
    });
    return ret;
  };
  //---------------------eventListener 등록---------------------
  //쓰로틀링 적용
  const throttledDragging = _.throttle((e) => {
    if (isMove) {
      dragging(e);
    }
  }, 10);

  // PC
  carouselList.addEventListener("mousedown", (e) => dragStart(e, e.clientX));
  // window.addEventListener("mousemove", (e) => dragging(e, e.clientX));
  window.addEventListener("mousemove", throttledDragging, { passive: false });
  window.addEventListener("mouseup", (e) => dragEnd(e));

  // Mobile
  carouselList.addEventListener("touchstart", (e) =>
    dragStart(e, e.targetTouches[0].clientX)
  );
  // window.addEventListener("touchmove", (e) =>
  //   dragging(e, e.targetTouches[0].clientX)
  // );

  window.addEventListener("touchmove", throttledDragging, { passive: false });
  window.addEventListener("touchend", (e) => dragEnd(e));
});
//---------------------------onMount 끝-----------------------------------
</script>

<template>
  <body>
    <header>
      <h1 class="none">나의 트리</h1>
      <section class="header-search-container">
        <h2 class="none">나의 트리 검색 링크</h2>
        <HeaderSearchLink />
      </section>
    </header>

    <main>
      <section class="my-tree-container">
        <h2 class="none">나의 트리 정보</h2>
        <section class="my-tree-title-container">
          <h3>
            <TextMainTitle :title="'나의 트리'" :size="28" />
          </h3>
        </section>

        <section class="book-tree-info-container">
          <h3 class="none">최근 트리 이미지 캐러셀</h3>
          <section class="book-img-container">
            <div>
              <BgTreeBookCovers trees="treeInfoList" />
            </div>
          </section>

          <section>
            <h3 class="none">트리 약식 정보</h3>
            <ul>
              <li
                class="none textbox-recent-tree-info__frame"
                v-for="(tree, index) in treeInfoList"
                :key="index"
                :class="index"
              >
                <div><RecentTreeInfo :tree="tree" />"</div>
              </li>
            </ul>
          </section>

          <section class="book-tree-explanation-container">
            <h3 class="none">트리 설명</h3>
            <ul class="book-tree-explanation-list">
              <li
                class="book-tree-explanation-item none"
                v-for="tree in treeInfoList"
                :key="index"
                :class="index"
              >
                <div><RecentTreeEx :text="tree.description" />"</div>

                <section
                  class="card-progress-home-container"
                  :readingPage="tree.readingPage"
                  :totalPage="tree.bookTotalPage"
                  :leaf="tree.treeLeafCnt"
                  :like="tree.treeLikeCnt"
                  :view="tree.treeViewCnt"
                  :progress="
                    tree.seedId === 1
                      ? (
                          (tree.readingPage * 100.0) /
                          tree.bookTotalPage
                        ).toFixed(1)
                      : 0
                  "
                >
                  <h4 class="none">트리 진행률</h4>
                  <div v-if="tree.seedId === 1">
                    <CardProgressBar
                      :progress="progress"
                      :readingPage="readingPage"
                      :totalPage="totalPage"
                    />
                  </div>
                  <CardReactNumbers :leaf="leaf" :like="like" :view="view" />
                </section>
              </li>
            </ul>
          </section>
        </section>
      </section>

      <section class="my-tree-list">
        <h2 class="none">나의 트리 목록</h2>
        <div>{{ treeInfoList.length }}</div>
        <section id="seed-filter">
          <h2 class="none">트리 정렬 필터 버튼</h2>
          <div>
            <FilterTreeList :seedList="seedList" />
          </div>
        </section>
      </section>

      <section>
        <h2 class="none">트리 검색 결과</h2>
        <section class="tree-card-list" id="tree-card-list">
          <h3 class="none">트리 검색 리스트</h3>
          <div
            class="card-tree-details"
            v-for="tree in treeInfoList"
            :key="tree.id"
          >
            <CardTreeDetails :tree="tree" />
          </div>
        </section>
      </section>
    </main>

    <footer>
      <Footer message="개발 중입니다~" />
    </footer>

    <section class="nav-back-container">
      <h3 class="none">네비 바 뒤 공백</h3>
    </section>

    <aside class="nav-container">
      <section class="short-btn-container">
        <h4 class="none">트리 생성 버튼</h4>
        <BtnShortGreen href="/seed/index" text="트리 생성" />
      </section>
      <NavigationBar active="home" />
    </aside>
  </body>
</template>

<style>
@import url(/src/assets/css/layout.css);
</style>
