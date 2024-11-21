<script setup>
import { onMounted, ref } from "vue";

//----------------variable----------------
let isOnwer = ref(false);
const memberId = useMemberStore._id;
const config = useRuntimeConfig();

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
  backImgName: "background-image.png",
  profileImgName: "",
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

//fetch
const { data, error } = await useAuthFetch(`/memoirs/${useRoute().params.id}`, {
  method: "GET",
  baseURL: `${config.public.apiBase}`,
});

if (data.value) {
  memoir.value = data.value.memoirDto;
  book.value = data.value.bookDto;
  member.value = data.value.memberDto;
  tree.value = data.value.treeDto;
  isOnwer.value = data.value.owner;
} else {
  console.error("회고록 조회 실패 : ");
  console.log(data.value);
  //이전페이지로 이동
  // useRouter().push("/home");
}

//카드 아이템
const treeItems = ref([]);
const memoirItems = ref([]);
const leafItems = ref([]);

//fetch
const { data: leafCardData, error: leafCardError } = await useAuthFetch(
  `/members/${member.value.id}/leaves/book/cards`,
  {
    method: "GET",
    baseURL: `${config.public.apiBase}`,
  }
);

const { data: treeCardData, error: treeCardError } = await useAuthFetch(
  `trees/members/${member.value.id}/trees/book/cards`,
  {
    method: "GET",
    baseURL: `${config.public.apiBase}`,
  }
);

const { data: memoirCardData, error: memoirCardError } = await useAuthFetch(
  `memoirs/members/${member.value.id}/memoirs/book/cards`,
  {
    method: "GET",
    baseURL: `${config.public.apiBase}`,
  }
);

if (leafCardData.value) {
  // console.log(leafCardData.value);
  leafItems.value = [...leafCardData.value.items];
}

if (treeCardData.value) {
  // console.log(treeCardData.value);
  treeItems.value = [...treeCardData.value.treeBookCardResponse];
}

if (memoirCardData.value) {
  // console.log(memoirCardData.value);
  memoirItems.value = [...memoirCardData.value.memoirBookCardDtoResponse];
}

//-----------function----------------

//----------------Life Cycle----------------
</script>

<template>
  <Title>나의 꼬깃</Title>
  <header>
    <BackgroundUserInfoHeader
      :edit="`/member/${member.id}/edit`"
      :isOnwer="isOnwer"
      :backImgPath="member.backImgName"
      :userProfileImg="member.profileImgName"
      :userName="member.nickName"
      :userId="member.email"
      :userUrl="member.email"
    />
  </header>

  <main>
    <div class="mypage-user-description__frame">
      <p class="mypage-user-description">
        {{ member.description || "안녕하세요" }}
      </p>
    </div>
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
      <!-- 트리 생성 언더바  -->
      <NavNavigationBar :active="'home'" />
    </section>
  </aside>
</template>

<style scoped>
.mypage-user-description__frame {
  width: 100%;
  height: 100px;
  padding-top: 40px;
  padding-left: 25px;
}
.mypage-user-description {
  font-size: 18px;
  line-height: var(--line-height-main);
  letter-spacing: var(--letter-spacing-main);
  font-weight: var(--bold);
  color: var(--main);
}
</style>
