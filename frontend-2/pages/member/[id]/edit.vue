<script setup>
import { onMounted, ref } from "vue";

//----------------variable----------------
const memberId = useMemberStore()._id;
const config = useRuntimeConfig();
//데이터 fetch후 비교해 본인의 개인 페이지인지 판단
const isOwner = ref(false);
const memberFormData = ref(new FormData());

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
  isOwner.value = member.value.id === memberId;
}

//-----------function----------------

//TODO: 배경 이미지과 프로필 이미지 구별해야 함.
const backgroundStyle = computed(() => {
  const imageUrl = useGetImageUrl(member.backImgPath, "memberBackground");
  return {
    backgroundImage: `url(${imageUrl})`,
  };
});

// 사용자가 입력한 profile 이미지를 임시로 화면에 보여주고 서버 전송을 위한 FormData에도 담아둠
const changeProfileHandler = async () => {
  // 서버에 이미지를 전송할 그릇인 FormData 생성
  const fileInput = document.createElement("input");
  fileInput.type = "file";
  fileInput.accept = "image/*";
  fileInput.onchange = (event) => {
    const file = event.target.files[0];
    memberFormData.value.append("memberProfileImage", file, file.name);

    // 이미지 파일을 읽고, 화면에 출력
    const reader = new FileReader();
    reader.onload = (e) => {
      member.value.memberProfileImage = e.target.result;
    };
    reader.readAsDataURL(file);
  };
  fileInput.click();
};

//사용자가 입력한 backGround이미지를 리소스 서버에 formData로 전송 후 이미지 url을 받아옴
const changeBackgroundHandler = async () => {
  // 서버에 이미지를 전송할 그릇인 FormData 생성
  const fileInput = document.createElement("input");
  fileInput.type = "file";
  fileInput.accept = "image/*";
  fileInput.onchange = (event) => {
    const file = event.target.files[0];
    memberFormData.value.append("memberBackgroundImage", file, file.name);

    // 이미지 파일을 읽고,
    const reader = new FileReader();
    reader.onload = (e) => {
      member.value.memberBackgroundImage = e.target.result;
    };
    reader.readAsDataURL(file);
  };
  fileInput.click();
};

const memberEditHandler = async () => {
  //memberImageFormaData에 이미지와 바뀐 문자열을 담아서 서버로 전송
  //member정보 수정
  memberFormData.value.append("nickname", member.value.nickname);
  memberFormData.value.append("introduction", member.value.introduction);

  console.log(memberFormData);

  const response = await useAuthDataFetch(`/members/${memberId}/edit`, {
    method: "POST",
    baseURL: `${config.public.apiBase}`,
    body: memberFormData.value,
  });
  console.log(response);

  navigateTo(`/member/${memberId}`);
};
//----------------Life Cycle----------------
</script>

<template>
  <Title>나의 꼬깃</Title>
  <header>
    <div class="user-info-container">
      <div class="user-info__background-frame">
        <section class="user-info__top-bar-container">
          <div class="top-bar__transparent-frame">
            <div @click.prevent="useGoBack()">
              <img src="/assets/png/back-icon--white.png" />
            </div>
            <div class="top-bar__transparent-btns" v-if="isOwner">
              <div
                class="top-bar__transparent-save-btn"
                @click.prevent="memberEditHandler"
              ></div>
            </div>
          </div>
        </section>
        <img
          class="user-info__background-image"
          :src="
            useGetImageUrl(member.memberBackgroundImage, 'memberBackground')
          "
          alt="user-Background"
        />
        <div class="user-info__background-plus-box">
          <img
            class="user-info__background-plus"
            src="/assets/png/plus-btn.png"
            @click.prevent="changeBackgroundHandler"
          />
        </div>
        <section class="user-info__bot-bar-container">
          <div class="bar-user-info-frame">
            <div class="bar-user-info__left-content">
              <div class="bar-user-info__img-wrapper">
                <img
                  class="bar-user-info__profile"
                  :src="
                    useMemberProfileImg(member.memberProfileImage, 'member')
                  "
                  alt="user-profile"
                />
                <img
                  class="user-info__img-plus"
                  src="/assets/png/plus-btn.png"
                  @click.prevent="changeProfileHandler"
                />
              </div>
              <div class="bar-user-info__user-frame">
                <input
                  class="bar-user-info__user-name-edit"
                  v-model="member.nickname"
                />
              </div>
            </div>
            <!-- <div class="bar-user-info__right-btns">
      <input
        class="__like-bold-input"
        type="checkbox"
        id="bar-user-info__like"
      />
      <label class="__like-bold" for="bar-user-info__like"></label>
      <a class="bar-user-info__set-btn" :href="`${props.userUrl}`"></a>
    </div> -->
          </div>
        </section>
      </div>
    </div>
  </header>

  <main>
    <div class="mypage-user-instroduction__frame">
      <img
        class="mypage-user-instroduction__quote"
        src="/assets/png/quote.png"
      />
      <label for="introduction" class="mypage-user-instroduction"></label>
      <textarea
        class="mypage-user-instroduction_form"
        name="introduction"
        v-model="member.introduction"
      />
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
      <NavNavigationBar :active="'home'" />
    </section>
  </aside>
</template>

<style scoped>
.user-info-container {
  position: relative;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
}
.user-info__background-frame {
  box-sizing: border-box;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  background-repeat: no-repeat;
  background-size: cover;
  background-position-y: center;
  background-position-x: center;
  width: 100%;
  height: 300px;
  max-width: var(--max-width-1);
  padding: 24px;
}

.user-info__background-image {
  width: 100%;
  height: 100%;
  object-fit: cover;
  position: absolute;
  top: 0;
  left: 0;
  z-index: -1;
}

.mypage-user-instroduction__quote {
  position: relative;
  top: 20px;
  right: 10px;
  margin-top: 10px;
  width: 40px;
  height: auto;
}

.user-info__background-plus-box {
  position: relative;
  display: flex;
  margin: 0 auto;
  z-index: 2;
}

/* .user-info__background-input {
  position: absolute;
  content-visibility: hidden;
  padding: 10px;
  width: 70px;
  height: 70px;
  z-index: 1;
} */

.user-info__background-plus {
  width: 40px;
  height: 40px;
  background-size: contain;
  cursor: pointer;
}
.user-info__background-frame::before {
  display: flex;
  content: "";
  justify-content: center;
  align-items: center;
  position: absolute;
  top: 0;
  left: 0;
  background-color: rgba(0, 0, 0, 0.5);
  background-size: cover;
  background-position-y: center;
  background-position-x: center;
  width: 100%;
  height: 300px;
  max-width: var(--max-width-1);
}

.user-info__top-bar-container {
  display: flex;
}
.user-info__bot-bar-container {
  transform: translateY(60%);
}
.mypage-user-instroduction__frame {
  box-sizing: border-box;
  width: 100%;
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
  font-size: 16px;
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
.bar-user-info__left-content {
  display: flex;
  gap: 8px;
}
.bar-user-info__img-wrapper {
  width: 60px;
  height: 60px;
  display: flex;
  align-items: center;
  justify-content: center;
  overflow: hidden;
}
.bar-user-info__profile {
  width: 60px;
  height: 60px;
  border-radius: 50%;
}
.bar-user-info__img-wrapper::after {
  position: absolute;
  border-radius: 50%;
  width: 60px;
  height: 60px;
  content: " ";
  background-color: rgba(0, 0, 0, 0.5);
}
.user-info__img-plus {
  position: absolute;
  z-index: 1;
  width: 25px;
  height: 25px;
}

.bar-user-info__user-frame {
  transform: translateY(10%);
}

.bar-user-info__user-name-edit {
  width: 200px;
  padding: 0 4px 0 4px;
  background-color: var(--main2--opacity40);
  border: none;
  color: var(--white);
  border-radius: 5px;
  font-size: 24px;
  font-weight: var(--medium);
  line-height: var(--line-height-main);
  letter-spacing: var(--letter-spacing-main);
}
.bar-user-info__user-id {
  color: var(--white);
  font-size: 14px;
  font-weight: var(--regular);
  line-height: var(--line-height-main);
  letter-spacing: var(--letter-spacing-main);
}
.bar-user-info__right-btns {
  display: flex;
  gap: 6px;
  align-items: center;
}

.__like-bold-input {
  display: none;
  cursor: none;
}

.__like-bold {
  cursor: pointer;
}

.__like-bold-input:checked + .__like-bold::after {
  width: 24px;
  height: 24px;
  content: "";
  display: flex;
  align-items: center;
  background-image: url("/png/like-fill.png");
  background-repeat: no-repeat;
  background-position: center;
  background-size: contain;
}

.__like-bold-input:checked ~ .__like-bold-content {
  width: 100%;
  display: flex;
  flex-direction: column;
}
.__like-bold::after {
  width: 24px;
  height: 24px;
  content: "";
  display: flex;
  align-items: center;
  background-image: url("/png/like-bold.png");
  background-repeat: no-repeat;
  background-position: center;
  background-size: contain;
}
.top-bar__transparent-frame {
  width: 100%;
  height: 40px;
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  align-items: center;
  background: transparent;
  z-index: 1;
}

.top-bar__transparent-btns {
  display: flex;
  gap: 15px;
}

.top-bar__transparent-save-btn {
  width: 30px;
  height: 30px;
  display: flex;
  background: url("/assets/png/save-btn.png") no-repeat center;
  background-size: contain;
  cursor: pointer;
}
</style>
