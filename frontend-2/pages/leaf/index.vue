<script setup>
import { useRoute } from "vue-router";
import { Tree } from "~/composables/Tree.js";

// ----------------------- Model ----------------------- //
const route = useRoute();
const config = useRuntimeConfig();
const leafId = Number(route.query.leafId);

const screenWidth = ref(0);
const targetNode = ref(null);
const isDragging = ref(false);
const seedType = ref({});
const tree = ref({});

const branch = reactive({
  branchName: "브랜치 이름",
  leafCount: 10,
  likeCount: 10,
  viewCount: 10,
  updateTime: "2024-10-19",
});

const leafCreateBtn = reactive({
  id: leafId,
  canCreate: true,
});

const touchValue = reactive({
  cooldown: 1000, // 쿨다운 시간 (밀리초단위 1000 = 1초)
  lastEventTime: new Date().getTime(),
  moveStartX: 0,
  moveLock: false,
  threshold: window.innerWidth * 0.1, // 스와이프 인식 거리
});

const breadcrumb = reactive({
  treeName: "트리이름",
  branchName: "브랜치 이름",
  leafName: "리프 이름",
});

const focusNodeDate = reactive({
  title: "2024년 10월 19일",
  size: 24,
});

const btn = reactive({
  text: "리프 생성",
});

const nodes = ref([]);

const { data: seedTypeData, error: seedTypeDataError } = await useAuthFetch(
  () => `leaves/${leafId}/seed`,
  {
    baseURL: config.public.apiBase,
  }
);

const { data: breadcrumbData, error: breadcrumbDataError } = await useAuthFetch(
  () => `leaves/${leafId}/breadcrumb`,
  {
    baseURL: config.public.apiBase,
  }
);

const { data: leafAllData, error: leafAllDataError } = await useAuthFetch(
  () => `leaves/${leafId}/all`,
  {
    baseURL: config.public.apiBase,
  }
);

const { data: branchInfoData, error: branchInfoDataError } = await useAuthFetch(
  () => `leaves/${leafId}/branch`,
  {
    baseURL: config.public.apiBase,
  }
);

// ----------------------- Init ----------------------- //
if (seedTypeData.value) {
  seedType.value = seedTypeData.value.seedType;
}

if (leafAllData.value && seedTypeData.value) {
  tree.value = new Tree(leafAllData.value, seedTypeData.value.seedType);
  nodes.value = tree.value.getNodeAll(leafId); // 리프 보여주는 구간
}

if (breadcrumbData.value) {
  breadcrumb.treeName = breadcrumbData.value.treeName;
  breadcrumb.branchName = breadcrumbData.value.branchName;
  breadcrumb.leafName = breadcrumbData.value.leafName;
}

if (branchInfoData.value) {
  branch.branchName = branchInfoData.value.branchName;
  branch.leafCount = branchInfoData.value.leafCount;
  branch.likeCount = branchInfoData.value.likeCount;
  branch.viewCount = branchInfoData.value.viewCount;
  branch.createdAt = branchInfoData.value.createdAt;
}

// ----------------------- Life Cycle ----------------------- //
onBeforeMount(() => {
  // 화면 크기 변경 리스너 제거
  window.removeEventListener("resize", updateWidth);
});

onMounted(() => {
  screenWidth.value = 1024 <= window.innerWidth ? 1024 : window.innerWidth;

  // 화면 크기 변경에 대응하도록 리스너 추가
  window.addEventListener("resize", updateWidth);
  document.body.addEventListener("scroll", scrollHandler);
  scrollToElement();
});

onUnmounted(() => {
  // 화면 크기 변경 리스너 제거
  window.removeEventListener("resize", updateWidth);
  document.body.removeEventListener("scroll", scrollHandler);
});

// ----------------------- Function ----------------------- //

const updateWidth = () => {
  screenWidth.value = 1024 <= window.innerWidth ? 1024 : window.innerWidth;
  // console.log("화면 크기 변경", screenWidth.value);
};

const scrollHandler = (event) => {
  // console.log("스크롤 이벤트 발생", event);
  findFocusNode();
};

const findFocusNode = () => {
  const nodes = document.querySelectorAll(".node");
  const focusY = window.innerHeight / 2;
  // const focusX = (window.innerWidth / 2);

  // console.log('start > scroll');
  // 포커스 적용 계산
  let minDistance = Number.MAX_SAFE_INTEGER;
  let minNode = null;
  for (let node of nodes) {
    // 가장 가까운 노드 찾기
    node.classList.remove("node--active");
    const position = node.getBoundingClientRect();
    const dist = distanceY(position.y, focusY);

    if (dist < minDistance) {
      minDistance = dist;
      minNode = node;
    }
  }

  // 포커스 적용
  const focusNodeId = minNode.getAttribute("data-id");
  for (let node of nodes) {
    let id = node.getAttribute("data-id");
    if (id === focusNodeId) {
      leafCreateBtn.id = id;
      leafCreateBtn.canCreate = tree.value.isCreateBranch(id);
      // console.log('focus node', id);
      node.classList.add("node--active");
    }
  }

  // 날짜 변경
  const item = minNode.closest(".leaf-item");
  const dateTag = item.querySelector(".log-item__date"); // 화면 날짜 적용
  if (dateTag.innerText === "") {
    focusNodeDate.title = "비공개 리프입니다.";
  } else {
    focusNodeDate.title = formatDate(dateTag.innerText); // 화면 날짜 적용;
  }

  // 브래드 스크럼 변경
  const titleTag = item.querySelector(".log-item__title");
  if (titleTag.innerText === "") {
    breadcrumb.leafName = "비공개 리프입니다.";
  } else {
    breadcrumb.leafName = titleTag.innerText;
  }
};

const formatDate = (dateString) => {
  const [year, month, day] = dateString.split("-");
  return `${year}년 ${month}월 ${day}일`;
};

const distanceY = (y1, y2) => {
  return Math.abs(y2 - y1);
};

const touchStartHandler = (event) => {
  nodeSideStartEventHandler(event, targetNode.value[0]);
};

const touchMoveHandler = async (event, node) => {
  await nodeSideMoveEventHandler(event, node);
};

const nodeSideStartEventHandler = (event, node) => {
  if (event.type === "touchstart") {
    touchValue.moveStartX =
      event.touches[0].pageX - event.currentTarget.offsetLeft;
  } else if (event.type === "mousedown") {
    isDragging.value = true;
    touchValue.moveStartX = event.pageX - event.currentTarget.offsetLeft;
  }
};

const nodeSideMoveEventHandler = async (event, node) => {
  const currentTime = new Date().getTime();

  if (currentTime - touchValue.lastEventTime < touchValue.cooldown) {
    return; // 쿨다운 시간동안 이벤트 무시
  }

  let currentX = 0;
  if (event.type === "touchmove") {
    currentX = event.touches[0].pageX - event.currentTarget.offsetLeft;
  } else if (event.type === "mousemove") {
    if (!isDragging.value) {
      return;
    } // 드래그 중이 아닐때
    currentX = event.pageX - event.currentTarget.offsetLeft;
  }

  const diffX = currentX - touchValue.moveStartX;
  if (!(Math.abs(diffX) > touchValue.threshold)) {
    return; // 스와이프 인식 거리 이하
  }

  if (touchValue.moveLock) {
    return; // 스와이프 이동 중
  }

  // 스와이프 인식 거리 이상 이동
  if (0 < diffX && 0 < node.translateIndex) {
    // 왼쪽으로 스와이프
    // console.log('인덱스 스와이프값 감소');
    node.translateIndex--;
    touchValue.moveLock = true;
  } else if (diffX < 0 && node.translateIndex < node.childLength - 1) {
    // 오른쪽으로 스와이프
    // console.log('인덱스 스와이프값 증가');
    node.translateIndex++;
    touchValue.moveLock = true;
  }

  // 스와이프 이동
  // console.log('스와이프 이동');
  // console.log('node', node); // 현재 스와이프 이동한 노드의 자식들을 호출함

  // 리프 정보 변경
  // console.log("타겟 노드", node.id);
  const nodeIndex = await nodes.value.findIndex((item) => item.id === node.id);
  // console.log("새로운 리스트", nodes.value.slice(0, nodeIndex + 1));
  nodes.value = nodes.value.slice(0, nodeIndex + 1);

  const swipeChildId = node.getSwipeChildId; // console.log('getSwipeChildId', node.getSwipeChildId); // 스와이프 이동한 노드의 자식들을 호출함
  const newChildrenNode = await tree.value.getNodeToEnd(swipeChildId);

  for (let child of newChildrenNode) {
    child.translateIndexInit();
    nodes.value.push(child);
  }

  // 브랜치 정보 변경
  const branchInfoData = tree.value.getBranchInfo(swipeChildId);
  branchInfoData.then((data) => {
    // 브랜치 정보 변경
    branch.branchName = data.branchName;
    branch.leafCount = data.leafCount;
    branch.likeCount = data.likeCount;
    branch.viewCount = data.viewCount;
    branch.updateTime = data.updateTime;
    // 브래드 스크럼 변경
    breadcrumb.branchName = data.branchName;
  });
  // console.log('branchInfoData', branchInfoData);

  // console.log('new children nodes', newChildrenNode);
  findFocusNode(); // 새로운 포커싱
  touchValue.lastEventTime = new Date().getTime();
  touchValue.moveLock = false;

  if (event.type === "mousemove") {
    isDragging.value = false;
  }
};

const mouseUpHandler = () => {
  isDragging.value = false;
};

const scrollToElement = () => {
  // console.log('scrollToElement', targetNode);
  targetNode.value[0].scrollIntoView({ behavior: "smooth", block: "center" });
};
</script>

<template @scroll="scrollHandler">
  <header class="log-list-header-container">
    <h1 class="none">리프 목록</h1>
    <section>
      <h1 class="none">뒤로가기 상단바</h1>
      <TopBarBack title="브랜치" link=""></TopBarBack>
    </section>

    <section>
      <h1 class="none">현재 포커싱 리프 정보</h1>
      <section class="log-path-container">
        <h1 class="none">리프 경로</h1>
        <BarLogPath
          :data="{
            tree: breadcrumb.treeName,
            branch: breadcrumb.branchName,
            leaf: breadcrumb.leafName,
          }"
        ></BarLogPath>
      </section>

      <section class="log-list-date-title-container">
        <h1 class="none">리프 날짜</h1>
        <TextMainTitleRight
          :title="focusNodeDate.title"
          :size="focusNodeDate.size"
        ></TextMainTitleRight>
      </section>
    </section>
  </header>

  <main @mouseup="mouseUpHandler">
    <section class="log-list-container">
      <h1 class="none">리프 리스트</h1>
      <section
        v-for="node in nodes"
        :ref="node.id === leafId ? 'targetNode' : ''"
      >
        <div
          class="log-item-container"
          @touchstart="touchStartHandler"
          @touchmove="(event) => touchMoveHandler(event, node)"
          @mousedown="touchStartHandler"
          @mousemove="(event) => touchMoveHandler(event, node)"
          :style="{
            transform: `translateX(-${node.translateSize(screenWidth)}px)`,
          }"
        >
          <div v-if="node.isLeft" class="log-item__left-box">
            <LogItem :data="node.leftData"></LogItem>
          </div>
          <div class="log-item__mid-box">
            <LogItem :data="node.midData"></LogItem>
          </div>
          <div v-if="node.isRight" class="log-item__right-box">
            <LogItem :data="node.rightData"></LogItem>
          </div>
        </div>
      </section>
    </section>
    <section class="log-tmp-box"></section>
  </main>

  <aside class="log-list-bot-bar-container">
    <h1 class="none">브랜치 정보 알림 하단 바</h1>
    <section class="log-list-bot-btn-container">
      <h1 class="none">리프 생성 버튼</h1>
      <ButtonBtnShortAGreen
        :visibility="leafCreateBtn.canCreate"
        :link="`/leaf/${seedType}/${leafCreateBtn.id}/new`"
        :text="`리프 생성`"
      />
    </section>

    <section class="log-list-bot-bar-info-container">
      <h1 class="none">브랜치 정보 하단 바</h1>
      <BotBarBranchStateMain :branch="branch"></BotBarBranchStateMain>
    </section>
    <section class="nav-back-container">
      <h3 class="none">네비 바 뒤 공백</h3>
    </section>
  </aside>
  <footer>
    <Footer :noticeText="`개발중입니다.`" />
  </footer>

  <aside class="nav-container">
    <NavNavigationBar active="home" />
  </aside>
</template>

<style scoped>
main {
  width: 100%;
  height: 100%;
}

.log-path-container {
  width: auto;
  padding: 0 24px 0 24px;
  background-color: var(--main2, #e5eddb);
}

.log-list-date-title-container {
  margin: 14px 24px 0 24px;
  position: absolute;
  right: 0;
  z-index: 20;
}

.log-list-bot-bar-container {
  width: 100%;
  position: fixed;
  bottom: 0;
  z-index: 10;
}

.log-list-bot-bar-info-container {
  z-index: 10;
}

.log-list-bot-btn-container {
  margin: 0 24px 16px 24px;
  display: flex;
  justify-content: flex-end;
  align-items: center;
}

.log-tmp-box {
  height: 1px;
  width: 100%;
}
</style>
