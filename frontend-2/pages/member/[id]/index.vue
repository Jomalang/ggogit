<script setup>
import { onMounted, ref } from "vue";
import { CalendarHeatmap } from "vue3-calendar-heatmap";

//----------------variable----------------
const memberId = useMemberStore()._id;
const config = useRuntimeConfig();
//데이터 fetch후 비교해 본인의 개인 페이지인지 판단
const isOwner = ref(false);

//----------------Model----------------
let tree = ref({
  id: 1,
  memberId: 1,
  seedId: 1,
  bookId: 1,
  title: "토마토 나무",
  description: "토마토 나무의 성장 과정을 나타내는 나무입니다.",
  visibility: true,
  createdAt: "2024-10-01T10:00",
  updatedAt: "2024-10-01T10:00",
});

let member = ref({
  id: 1,
  nickName: "nickname1",
  userName: "user1",
  email: "user1@example.com",
  memberBackgroundImage: "",
  memberProfileImage: "",
  introduction: "안녕하세요",
});

let memoir = ref({
  id: 0,
  title: "",
  text: "",
  visibility: true,
});

let book = ref({
  id: 1,
  publishDate: "2007",
  totalPage: 759,
  bookCategoryId: 4,
  author: "J.K. 롤링",
  isbn: "978-3-16-148410-0",
  publisher: "블룸즈버리",
  title: "해리 포터와 죽음의 성물",
  imageFile: "harry_potter_cover.jpg",
  createTime: "24-10-01",
  updateTime: "24-10-01",
});

const seeds = ref({});

const domainCount = ref({
  treeCnt: 0,
  leafCnt: 0,
  memoirCnt: 0,
  bookCnt: 0,
});

//-------fetch----------------

//member정보
const { data: memberData, error: memberError } = await useAuthFetch(
  `/members/${memberId}`,
  {
    method: "GET",
    baseURL: `${config.public.apiBase}`,
  }
);

if (memberData.value) {
  member.value = memberData.value;
  console.log(member.value);
  isOwner.value = member.value.id === memberId;
}

//차트에 표시할 씨앗 정보
const { data: seedData, status: seedStatus } = await useAuthFetch("seeds", {
  baseURL: `${config.public.apiBase}`,
  method: "GET",
});

if (seedData.value) {
  seeds.value = seedData.value;
  console.log(seeds.value);
}

//차트에 표시할 도메인 개수 정보
const { data: domainCntData, status: domainCntStatus } = await useAuthFetch(
  `/members/${memberId}/domain-count`,
  {
    baseURL: `${config.public.apiBase}`,
    method: "GET",
  }
);

if (domainCntData.value) {
  Object.assign(domainCount.value, domainCntData.value);
  console.log(domainCount.value);
}

// const { data, error } = await useAuthFetch(`/memoirs/${useRoute().params.id}`, {
//   method: "GET",
//   baseURL: `${config.public.apiBase}`,
// });

// if (data.value) {
//   memoir.value = data.value.memoirDto;
//   book.value = data.value.bookDto;
//   member.value = data.value.memberDto;
//   tree.value = data.value.treeDto;
//   isOnwer.value = data.value.owner;
// } else {
//   console.error("회고록 조회 실패 : ");
//   console.log(data.value);
//   //이전페이지로 이동
//   // useRouter().push("/home");
// }

// //카드 아이템
// const treeItems = ref([]);
// const memoirItems = ref([]);
// const leafItems = ref([]);

// //fetch
// const { data: leafCardData, error: leafCardError } = await useAuthFetch(
//   `/members/${member.value.id}/leaves/book/cards`,
//   {
//     method: "GET",
//     baseURL: `${config.public.apiBase}`,
//   }
// );

// const { data: treeCardData, error: treeCardError } = await useAuthFetch(
//   `trees/members/${member.value.id}/trees/book/cards`,
//   {
//     method: "GET",
//     baseURL: `${config.public.apiBase}`,
//   }
// );

// const { data: memoirCardData, error: memoirCardError } = await useAuthFetch(
//   `memoirs/members/${member.value.id}/memoirs/book/cards`,
//   {
//     method: "GET",
//     baseURL: `${config.public.apiBase}`,
//   }
// );

// if (leafCardData.value) {
//   // console.log(leafCardData.value);
//   leafItems.value = [...leafCardData.value.items];
// }

// if (treeCardData.value) {
//   // console.log(treeCardData.value);
//   treeItems.value = [...treeCardData.value.treeBookCardResponse];
// }

// if (memoirCardData.value) {
//   // console.log(memoirCardData.value);
//   memoirItems.value = [...memoirCardData.value.memoirBookCardDtoResponse];
// }

//-----------function----------------

//----------------Life Cycle----------------

//---------chart----------------
const DougnutChartData = ref({
  labels: seeds.value.items.map((seed) => seed.korName),
  datasets: [
    {
      label: "트리 통계",
      backgroundColor: ["#323a27", "#e5eddb", "#323a271a", "#f5f8f1"],
      data: [300, 50, 100, 40],
    },
  ],
});

const DougnutChartOptions = ref({
  responsive: true,
  maintainAspectRatio: false,
  plugins: {
    legend: {
      display: true,
      position: "bottom",
    },
  },
});

const yBarChartData = ref({
  labels: ["🌲 트리", "🌿 리프", "📖 회고록", "📗직접 등록한 도서"],
  datasets: [
    {
      data: [100, 100, 100, 100],
      backbroundColor: ["#FF6384", "#36A2EB", "#FFCE56", "#323a27"],
      borderWidht: 1,
    },
  ],
});

const yBarChartOptions = ref({
  responsive: true,
  maintainAspectRatio: false,
  indexAxis: "y",
  scales: {
    x: {
      beginAtZero: true, //x축 0부터 시작
    },
    y: {
      ticks: {
        autoSkip: false, //y축 레이블 잘리지 않도록 설정
      },
    },
  },
  plugins: {
    // legend: {
    //   //범례
    //   display: true,
    //   position: "bottom",
    // },
    tooltip: {
      enabled: true,
    },
  },
});

//---------calendar----------------
const calendarData = ref({
  values: [
    { date: "2024-11-23", count: 1 },
    { date: "2024-01-02", count: 2 },
    { date: "2024-01-03", count: 3 },
    // Add more data as needed
  ],
  // endDate: new Date(Date.now() + 86400000).toISOString().split("T")[0],
  endDate: "2024-05-23",
  round: 4,
  darkMode: false,
  noDataText: "꼬깃이 없어요...😢",
  rangeColor: ["#ebedf0", "#c6e48b", "#7bc96f", "#239a3b", "#196127"],
  max: 4,
  tooltip: true,
  tooltipUnit: "꼬깃",
  tooltipFormatter: (v) => `${v.count}개의 꼬깃을 남겼어요!`,
  vertical: false,
});
</script>

<template>
  <Title>나의 꼬깃</Title>
  <header>
    <BackgroundUserInfoHeader
      :edit="`/member/${member.id}/edit`"
      :isOnwer="isOnwer"
      :backImgPath="member.memberBackgroundImage"
      :userProfileImg="member.memberProfileImage"
      :userName="member.nickname"
      :userEmail="member.email"
      :userUrl="member.id"
    />
  </header>

  <main>
    <div class="mypage-user-instroduction__frame">
      <div class="mypage-user__cnt-info">
        <p>
          <span>{{ domainCount.treeCnt }}</span
          >개의 트리
        </p>
        <p>
          <span>{{ domainCount.leafCnt }}</span
          >개의 리프
        </p>
        <p>
          <span>{{ domainCount.memoirCnt }}</span
          >개의 회고록
        </p>
        <p>
          <span>{{ domainCount.bookCnt }}</span
          >개의 직접 등록한 도서
        </p>
      </div>
      <img
        class="mypage-user-instroduction__quote"
        src="/assets/png/quote.png"
      />
      <p class="mypage-user-instroduction_form" name="introduction">
        {{ member.introduction }}
      </p>
    </div>

    <section class="mypage-statistics">
      <h2 class="none">나의 꼬깃 통계</h2>
      <section class="mypage-calendar__box">
        <h2 class="none">캘린더 히트맵</h2>
        <p><span>100</span>일째 꼬깃 중!🌿</p>
      </section>
      <CalendarHeatmap v-bind="calendarData" />
      <div class="mypage__chart-frame">
        <ChartDougnut
          :chartData="DougnutChartData"
          :chartOptions="DougnutChartOptions"
        />
      </div>
    </section>
  </main>

  <footer>
    <Footer :noticeText="`개발 중입니다.`" />
  </footer>

  <section class="nav-back-container">
    <h2 class="none">네비바 뒤 공백</h2>
  </section>

  <aside>
    <section class="nav-container">
      <h2 class="none">네비게이션</h2>
      <NavNavigationBar :active="'home'" />
    </section>
  </aside>
</template>

<style scoped>
.mypage-user-instroduction__frame {
  box-sizing: border-box;
  max-width: var(--max-width-1);
  padding: 0 0 40px 24px;
}

.mypage-user-instroduction_form {
  box-sizing: border-box;
  border: none;
  width: 100%;
  height: fit-content;
  max-width: var(--max-width-1);
  padding: 16px;
  background-color: var(--main3);
  font-size: 20px;
  font-weight: var(--medium, 500);
  color: var(--main1);
  line-height: var(--line-height-main);
  letter-spacing: var(--letter-spacing-main);
  font-weight: var(--medium);
}
.bar-user-info-frame {
  display: flex;
  justify-content: space-between;
}
.mypage-user-description__frame {
  box-sizing: border-box;
  width: 100%;
  height: 100px;
  max-width: 1024px;
  padding-left: 25px;
}
.mypage-user-instroduction__quote {
  position: relative;
  top: 20px;
  right: 10px;
  width: 40px;
  height: auto;
}
.mypage-user-description {
  font-size: 16px;
  line-height: var(--line-height-main);
  letter-spacing: var(--letter-spacing-main);
  font-weight: var(--medium);
  color: var(--main);
}

.mypage-statistics {
  display: flex;
  flex-direction: column;
  gap: 20px;
  width: 100%;
  max-width: var(--max-width-1);
  padding: 0 24px 40px 24px;
}

.mypage-domain-cnt {
  display: flex;
  flex-direction: column;
  gap: 8px;
  font-weight: var(--semi-bold);
}

.mypage__chart-frame {
  width: 100%;
  height: 100%;
  max-width: var(--max-width-1);
  padding: 0 24px 40px 24px;
}

.mypage-user__cnt-info {
  display: flex;
  flex-direction: row;
  gap: 20px;
  padding-top: 40px;
  padding-bottom: 10px;
  color: var(--text-sub);
  font-size: 20px;
  flex-wrap: nowrap;
  span {
    font-weight: var(--bold);
    font-size: 22px;
  }
}

.mypage-calendar__box {
  display: flex;
  flex-direction: column;
  gap: 40px;
  padding: 0 24px 40px 24px;
  p {
    align-self: flex-end;
    font-size: 24px;
    font-weight: var(--semi-bold);
    color: var(--text-sub);
    span {
      font-weight: var(--bold);
      font-size: 28px;
    }
  }
}
</style>
