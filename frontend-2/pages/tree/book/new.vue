<script setup>
import {onMounted, watch} from "vue";
import axios, { HttpStatusCode } from "axios";
import {useRouter} from "#vue-router";
import {value} from "lodash/seq.js";

// ----------------------- Model ----------------------- //
const router = useRouter();

const treeFormData = useState('treeFormData', () => ({
  // 트리 씨앗 정보
  seedId: 1,

  // 도서 정보
  bookTitle: "",
  bookTitleValid: true,

  // 지은이 정보
  author: "",
  authorValid: true,

  // 출판일 정보
  publishDate: "",
  publishDateValid: true,

  // 출판사 정보
  publisher: "",
  publisherValid: true,

  // 총 페이지 정보
  totalPage: "",
  totalPageValid: true,

  // 도서 카테고리 정보
  bookCategoryId: null,
  bookCategoryIdValid: true,

  // 도서 카테고리 이름 정보
  bookCategoryName: null,
  bookCategorySelected: true,

  // 트리 정보
  treeTitle: "",
  treeTitleValid: true,

  // 트리 설명 정보
  description: "",
  descriptionValid: true,

  // 공개 여부 정보
  visibility: false,
  visibilityValid: true,

  // 이미지 정보
  imageData: "",

  // 트리 생성 경로
  createUrl: `/tree/book/new`,
}));

watch(treeFormData.value, (newVal) => {
  // console.log("treeFormData:", newVal);
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
  // console.log("Selected image data:", imageData);
  treeFormData.value.imageData = imageData;
};

const validateCheck = () => {

  let isValid = true;

  // 도서 이름 확인
  if (!treeFormData.value.bookTitle) {
    treeFormData.value.bookTitleValid = false;
    alert("도서 이름을 입력해주세요.");
    return false;
  }

  // 지은이 확인
  if (!treeFormData.value.author) {
    treeFormData.value.authorValid = false;
    alert("지은이 이름을 입력해주세요.");
    return false;
  }

  // 출판사 확인
  if (!treeFormData.value.publisher) {
    treeFormData.value.publisherValid = false;
    alert("출판사를 입력해주세요.");
    return false;
  }

  // 출판일 확인
  if (!treeFormData.value.publishDate) {
    treeFormData.value.publishDateValid = false;
    alert("출판일을 입력해주세요.");
    return false;
  }

  // 총페이지 확인
  if (!treeFormData.value.totalPage) {
    treeFormData.value.totalPageValid = false;
    alert("총페이지를 입력해주세요.");
    return false;
  }

  // 카테고리 확인
  if (!treeFormData.value.bookCategoryId) {
    treeFormData.value.bookCategoryIdValid = false;
    alert("카테고리를 선택해주세요.");
    return false;
  }

  // 트리 이름 확인
  if (!treeFormData.value.treeTitle) {
    treeFormData.value.treeTitleValid = false;
    alert("트리 이름을 입력해주세요.");
    return false;
  }

  // 트리 설명 확인
  if (!treeFormData.value.description) {
    treeFormData.value.descriptionValid = false;
    alert("트리 설명을 입력해주세요.");
    return false;
  }

  // 공개 여부 확인
  if (!treeFormData.value.visibilityValid) {
    treeFormData.value.visibilityValid = false;
    alert("공개 여부를 선택해주세요.");
    return false;
  }

  return isValid;
};

const submitFormHandler = async (e) => {

  // 검증 로직
  if (!validateCheck()) {
    return;
  }

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

    // alert("트리가 생성되었습니다.");
    router.push("/leaf/book/new");
  } catch (error) {
    console.error("Error submitting form:", error);
  }
};

const dropBookCategory = () => {
  treeFormData.value.bookCategoryId = null;
  treeFormData.value.bookCategoryName = null;
  treeFormData.value.bookCategorySelected = false;
};

const inputBookTitle = (value) => {
  treeFormData.value.bookTitle = value;
  treeFormData.value.bookTitleValid = true;
};

const inputAuthor = (value) => {
  treeFormData.value.author = value;
  treeFormData.value.authorValid = true;
};

const inputPublisher = (value) => {
  treeFormData.value.publisher = value;
  treeFormData.value.publisherValid = true;
};

const inputPublishDate = (value) => {
  treeFormData.value.publishDate = value;
  treeFormData.value.publishDateValid = true;
};

const inputTotalPage = (value) => {
  treeFormData.value.totalPage = value;
  treeFormData.value.totalPageValid = true;
};

const inputTreeTitle = (value) => {
  treeFormData.value.treeTitle = value;
  treeFormData.value.treeTitleValid = true;
};

const inputDescription = (value) => {
  treeFormData.value.description = value;
  treeFormData.value.descriptionValid = true;
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
              :data="{
                label: '*도서 이름',
                name: 'bookTitle',
                placeholder: '도서 이름을 입력해주세요',
                value: treeFormData.bookTitle,
                validate: treeFormData.bookTitleValid,
                validateMessage: '도서 이름을 입력해주세요.'
              }"
              @inputData="inputBookTitle"
          >
          </InputTextBox>
        </section>

        <section class="input-form__input-container">
          <h1 class="none">지은이 입력</h1>
          <InputTextBox
              :data="{
                label: '*지은이 이름',
                name: 'author',
                placeholder: '지은이 이름을 입력해주세요',
                value: treeFormData.author,
                validate: treeFormData.authorValid,
                validateMessage: '지은이 이름을 입력해주세요.'
              }"
              @inputData="inputAuthor"
          >
          </InputTextBox>
        </section>

        <section class="input-form__input-container">
          <h1 class="none">출판사 입력</h1>
          <InputTextBox
              :data="{
                label: '*출판사',
                name: 'publisher',
                placeholder: '출판사를 입력해주세요',
                value: treeFormData.publisher,
                validate: treeFormData.publisherValid,
                validateMessage: '출판사를 입력해주세요.'
              }"
              @inputData="inputPublisher"
          >
          </InputTextBox>
        </section>

        <section class="input-form__input-container">
          <h1 class="none">출판일 입력</h1>
          <InputTextDateBox
              :data="{
                label: '*출판일',
                name: 'publishDate',
                placeholder: '출판일을 입력해주세요',
                value: treeFormData.publishDate,
                validate: treeFormData.publishDateValid,
                validateMessage: '출판일을 입력해주세요. (2024-11-01 형식)'
              }"
              @inputData="inputPublishDate"
          >
          </InputTextDateBox>
        </section>

        <section class="input-form__input-container">
          <h1 class="none">총페이지 입력</h1>
          <InputTextNumberBox
              :data="{
                label: '*총페이지',
                name: 'totalPage',
                placeholder: '총페이지를 입력해주세요',
                min: 0,
                value: treeFormData.totalPage,
                validate: treeFormData.totalPageValid,
                validateMessage: '양수의 숫자만 입력해주세요.'
              }"
              @inputData="inputTotalPage"
          >
          </InputTextNumberBox>
        </section>

        <section class="input-form__input-container">
          <h1 class="none">카테고리 선택</h1>
          <InputBookCategorySelect
              :data="{
                id: treeFormData.bookCategoryId,
                name: treeFormData.bookCategoryName,
                isSelected: treeFormData.bookCategorySelected
              }"
              @drop="dropBookCategory"
          >
          </InputBookCategorySelect >
        </section>

        <section class="input-form__input-container">
          <h1 class="none">트리이름 입력</h1>
          <InputTextBox
              :data="{
                label: '*트리 이름',
                name: 'treeTitle',
                placeholder: '트리 이름을 입력해주세요',
                value: treeFormData.treeTitle,
                validate: treeFormData.treeTitleValid,
                validateMessage: '트리 이름을 입력해주세요.'
              }"
              @inputData="inputTreeTitle"
          >
          </InputTextBox>
        </section>

        <section class="book-tree-input-form__large-input-container">
          <h1 class="none">설명글 작성</h1>
          <InputTextareaBox
              :data="{
                label: '*설명글',
                name: 'description',
                placeholder: '트리에 대한 설명을 입력해주세요',
                value: treeFormData.description,
                validate: treeFormData.descriptionValid,
                validateMessage: '트리에 대한 설명을 입력해주세요.'
              }"
              @inputData="inputDescription"
          >
          </InputTextareaBox>
        </section>

        <section class="input-form__input-container">
          <h1 class="none">공개성 선택</h1>
          <InputVisibility name="visibility" v-model="treeFormData.visibility"></InputVisibility>
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
