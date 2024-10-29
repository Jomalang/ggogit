<script setup>
import {onMounted, watch} from "vue";
import axios, { HttpStatusCode } from "axios";
import {useRouter} from "#vue-router";

// ----------------------- Model ----------------------- //
const router = useRouter();

const treeFormData = useState('treeFormData', () => ({
  // 트리 씨앗 정보
  seedId: 1,

  // 도서 정보
  bookTitle: "",
  author: "",
  publishDate: "",
  totalPage: "",

  // 도서 카테고리 정보
  bookCategoryId: null,
  bookCategoryName: null,
  isSelected: false,

  // 트리 정보
  treeTitle: "",
  description: "",
  visibility: true,
  imageData: "",
}));

watch(treeFormData.value, (newVal) => {
  console.log("treeFormData:", newVal);
});

// ----------------------- Life Cycle ----------------------- //
onMounted(() => {
  if (treeFormData.value.imageData) {
    const imgTag = document.getElementById("input-book-img-box__img-id");
    imgTag.src = treeFormData.value.imageData;
  }
});

// ----------------------- Function ----------------------- //
const handleImageSelected = (imageData) => {
  console.log("Selected image data:", imageData);
  treeFormData.value.imageData = imageData;
};

const submitFormHandler = async (e) => {
  e.preventDefault(); // 데이터 전송 로직
  try {
    const treeFormDataToSend = new FormData();
    for (const key in treeFormData.value) {
      treeFormDataToSend.append(key, treeFormData.value[key]);
    }

    // 이미지 파일이 있을 경우
    const imgTag = document.getElementById("input-book-img-box__img-id");
    if (imgTag && imgTag.src) {
      const response = await fetch(imgTag.src);
      const blob = await response.blob();
      treeFormDataToSend.append("image", blob, "image.jpg");
    }

    const response = await axios.post(
        "http://localhost:8080/api/v1/trees",
        treeFormDataToSend,
        {
          headers: { "Content-Type": "multipart/form-data" },
        }
    );

    if (response.status !== HttpStatusCode.Created) {
      throw new Error("Network response was not ok");
    }

    router.push("/leaf/book/new");
  } catch (error) {
    console.error("Error submitting form:", error);
  }
};

const dropBookCategory = () => {
  treeFormData.value.bookCategoryId = null;
  treeFormData.value.bookCategoryName = null;
  treeFormData.value.isSelected = false;
};

</script>

<template>
  <header>
    <h1 class="none">도서 트리 생성 페이지</h1>
    <section class="tob-bar-back-container">
      <h1 class="none">트리 생성 상단 바</h1>
      <TopBarBack title="트리 생성" link=""></TopBarBack>
    </section>
  </header>

  <main>
    <section class="book-tree-input-form-container">
      <h1 class="none">도서 정보 입력</h1>

      <section class="select-title-container">
        <TextMainTitle :data="{ title: '도서 직접 입력', size: 28 }"></TextMainTitle>
      </section>

      <form
          id="book-tree-input-form-id"
          class="book-tree-input-form"
          action="/tree/book/reg?auto=false"
          method="post"
          enctype="multipart/form-data"
      >
        <section class="none">
          <h1 class="none">씨앗 카테고리</h1>
          <input
              type="number"
              name="seedCategoryId"
              v-model="treeFormData.seedId"
          />
        </section>

        <section class="book-tree-input-form__photo-container">
          <h1 class="none">도서 이미지 입력</h1>
          <InputBookInputImg @image-selected="handleImageSelected"></InputBookInputImg>
        </section>

        <section class="input-form__input-container">
          <h1 class="none">도서 이름 입력</h1>
          <InputTextBox
              label="*도서 이름"
              name="bookTitle"
              v-model="treeFormData.bookTitle"
              placeholder="도서 이름을 입력해주세요"
          >
          </InputTextBox>
        </section>

        <section class="input-form__input-container">
          <h1 class="none">지은이 입력</h1>
          <InputTextBox
              label="*지은이"
              name="author"
              v-model="treeFormData.author"
              placeholder="지은이를 입력해주세요"
          >
          </InputTextBox>
        </section>

        <section class="input-form__input-container">
          <h1 class="none">출판사 입력</h1>
          <InputTextBox
              label="*출판일"
              name="publishDate"
              v-model="treeFormData.publishDate"
              placeholder="출판일을 입력해주세요"
          >
          </InputTextBox>
        </section>

        <section class="input-form__input-container">
          <h1 class="none">총페이지 입력</h1>
          <InputTextNumberBox
              label="*총페이지"
              name="totalPage"
              :min="1"
              v-model="treeFormData.totalPage"
              placeholder="총페이지를 입력해주세요"
          >
          </InputTextNumberBox>
        </section>

        <section class="input-form__input-container">
          <h1 class="none">카테고리 선택</h1>
          <InputBookCategorySelect
              :isSelected="treeFormData.isSelected"
              :id="treeFormData.bookCategoryId"
              :name="treeFormData.bookCategoryName"
              @drop="dropBookCategory"
          >
          </InputBookCategorySelect >
        </section>

        <section class="input-form__input-container">
          <h1 class="none">트리이름 입력</h1>
          <InputTextBox
              label="*트리 이름"
              name="treeTitle"
              v-model="treeFormData.treeTitle"
              placeholder="트리 이름을 입력해주세요"
          >
          </InputTextBox>
        </section>

        <section class="book-tree-input-form__large-input-container">
          <h1 class="none">설명글 작성</h1>
          <InputTextareaBox
              label="*트리 설명"
              name="description"
              v-model="treeFormData.description"
              placeholder="트리를 설명할 글을 작성해 주세요"
          >
          </InputTextareaBox>
        </section>

        <section class="input-form__input-container">
          <h1 class="none">공개성 선택</h1>
          <InputVisibility name="visibility" v-model="treeFormData.visibility">
          </InputVisibility>
        </section>

        <section class="book-tree-submit-container">
          <h1 class="none">트리 생성 버튼</h1>
          <ButtonSubmitBtnFullBar
              text="트리 생성"
              @click.prevent="submitFormHandler"
          ></ButtonSubmitBtnFullBar>
        </section>
      </form>
    </section>
  </main>

  <aside class="nav-container">
    <h1 class="none">네비게이션 하단</h1>
    <NavNavigationBar active="home"></NavNavigationBar>
  </aside>
</template>

<style scoped></style>
