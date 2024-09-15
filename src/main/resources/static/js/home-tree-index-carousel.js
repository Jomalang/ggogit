import {bookExRemoveNone, calcMidTree} from "./home-tree-explanation.js";
//첫 화면 렌더링 시 디테일 초기화


const carouselList = document.querySelector(".tree-book-bg__list");
// carousel item 너비
// const width = document.querySelector(".mid__item").clientWidth;
const width = window.innerWidth * 0.3;
// carousel item 전체 갯수
const carouselItemCount = document.querySelectorAll(".mid__item").length / 3;

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

// 기본 동작 방지 (특히 모바일에서의 스크롤)
  if (e.type.includes('touch')) {
    e.preventDefault(); // 기본 터치 스크롤 동작을 방지
  }

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
    -(((-currentTranslateX / width) % carouselItemCount) + carouselItemCount) *
      width +
    dragEndStartGapTranslateX;
  nextTranslateX = currentTranslateX;
  carouselList.style.transform = `translateX(${currentTranslateX}px)`;
};

// drag 중 이벤트
const dragging = (e, clientX) => {
  if (isMove) {
    moveTranslateX = clientX - moveStartX;
    nextTranslateX = currentTranslateX + moveTranslateX;

    // 기본 동작 방지 (특히 모바일에서의 스크롤)
    if (e.type.includes('touch')) {
      e.preventDefault(); // 기본 터치 스크롤 동작을 방지
    }

    // 오른쪽으로 최대 이동한 경우
    if (nextTranslateX < -width * (carouselItemCount * 3 - 1)) {
      nextTranslateX = -width * (carouselItemCount * 3 - 1);
    }

    // 왼쪽으로 최대 이동한 경우
    else if (nextTranslateX > 0) {
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

// 기본 동작 방지 (특히 모바일에서의 스크롤)
    if (e.type.includes('touch')) {
      e.preventDefault(); // 기본 터치 스크롤 동작을 방지
    }
    // 오른쪽으로 이동한 경우
    if (currentTranslateX > nextTranslateX) {
      if ((currentTranslateX - nextTranslateX) % width > moveGap) {
        currentTranslateX = -(Math.floor(-nextTranslateX / width) + 1) * width;
      } else {
        currentTranslateX = -Math.floor(-nextTranslateX / width) * width;
      }
    }

    // 왼쪽으로 이동한 경우
    else if (currentTranslateX < nextTranslateX) {
      if ((nextTranslateX - currentTranslateX) % width > moveGap) {
        currentTranslateX = -Math.floor(-nextTranslateX / width) * width;
      } else {
        currentTranslateX = -(Math.floor(-nextTranslateX / width) + 1) * width;
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


// PC
carouselList.addEventListener("mousedown", (e) => dragStart(e, e.clientX));
window.addEventListener("mousemove", (e) => dragging(e, e.clientX));
window.addEventListener("mouseup", (e) => dragEnd(e));

// Mobile
carouselList.addEventListener("touchstart", (e) =>
  dragStart(e, e.targetTouches[0].clientX)
);
window.addEventListener("touchmove", (e) =>
  dragging(e, e.targetTouches[0].clientX)
);
window.addEventListener("touchend", (e) => dragEnd(e));

const container = document.querySelector(".tree-book-bg");

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
    if(selectedElement){
      bookExRemoveNone(selectedElement);
    }
  },
  { root: container, threshold: 0.3 }
);

document.querySelectorAll(".mid__item").forEach((item) => {
  observer.observe(item);
});

// window.addEventListener("pageshow", () => {
//   bookExRemoveNone(calcMidTree());
// });
