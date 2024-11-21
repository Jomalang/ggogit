<script setup>
import { onMounted, watch } from "vue";
import { useRouter } from "#vue-router";
import useTreeFormData from "~/composables/useTreeFormData.js";
import BookInputImgTreeEdit from "~/components/input/BookInputImgTreeEdit.vue";
import TextBox from "~/components/input/TextBox.vue";
import TextBoxReadOnly from "~/components/input/TextBoxReadOnly.vue";
import TextDateBoxReadOnly from "~/components/input/TextDateBoxReadOnly.vue";
import TextNumberBoxReadOnly from "~/components/input/TextNumberBoxReadOnly.vue";

// ----------------------- Model ----------------------- //
const router = useRouter();
const config = useRuntimeConfig();

const isAuto = ref(false);
const treeInfo = reactive([
  {
    image : "",
    title : "",
    author : "",
    publisher : "",
    publishDate : "",
    totalPage : "",
    bookCategoryId : "",
    bookCategoryName : "",
    treeTitle : "",
    description : "",
    visibility : ""
  }
]);

const imgData = reactive([
  {
    isAuto: false,
    imageData: ""
  }
])

useTreeFormData().init();
useTreeFormData().setCreateUrl("/tree/book/new")
const treeFormData = useTreeFormData().treeFormData;

// ----------------------- Life Cycle ----------------------- //
onMounted(() => {
  if (treeFormData.value.imageData) {
    const imgTag = document.getElementById("input-book-img-box__img-id");
    imgTag.src = treeFormData.value.imageData;
  }
  if (treeData.value){
    treeInfo.image = treeData.value.coverImageName;
    treeInfo.title = treeData.value.bookTitle;
    treeInfo.author = treeData.value.bookAuthor;
    treeInfo.publisher = treeData.value.bookPublisher;
    treeInfo.publishDate = treeData.value.bookPublishedYear;
    treeInfo.totalPage = treeData.value.bookTotalPage;
    treeFormData.value.bookCategoryId = treeData.value.bookCategoryId;
    treeFormData.value.bookCategoryName = treeData.value.bookCategoryName;
    treeInfo.treeTitle = treeData.value.title;
    treeInfo.description = treeData.value.description;
    treeInfo.visibility = treeData.value.visibility;
    imgData.imageData = treeData.value.coverImageName;
    if (treeInfo.image.startsWith("https://image.aladin.co.kr/product/")) {
      isAuto.value = true;
      imgData.isAuto = true;
    }
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
    if (imgTag && imgTag.src.startsWith("data:image")) {
      const response = await fetch(imgTag.src);
      const blob = await response.blob();
      treeFormDataToSend.append("image", blob, "image.jpg");
    }

    const response = await useAuthDataFetch("/trees", {
      method: "PUT",
      baseURL: `${config.public.apiBase}`,
      body: treeFormDataToSend,
    });

    console.log("response:", response);

    if (response.message !== '도서 트리 임시 저장 성공') {
      throw new Error("Network response was not ok");
    }

    router.push(`/tree/${useRoute().params.id}`);
  } catch (error) {
    console.error("Error submitting form:", error);
  }
};

const dropBookCategory = () => {
  treeFormData.value.bookCategoryId = null;
  treeFormData.value.bookCategoryName = null;
  treeFormData.value.bookCategorySelected = true;
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

// path에서 data를 가져올 수 있도록 설정
const { data: treeData, error: infoError } = await useAuthFetch(
    () => `trees/${Number(useRoute().params.id)}/info`,
    {
      baseURL: config.public.apiBase,
    }
);
</script>

<template>
  <header>
    <h1 class="none">도서 트리 수정 페이지</h1>
    <section class="tob-bar-back-container">
      <h1 class="none">트리 수정 상단 바</h1>
      <TopBarBack title="트리 수정"  ></TopBarBack>
    </section>
  </header>

  <main>
    <section class="book-tree-input-form-container">
      <h1 class="none">도서 정보 입력</h1>

      <section class="select-title-container">
        <TextMainTitle
          :data="{ title: '도서 트리', size: 28 }"
        ></TextMainTitle>
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
          <BookInputImgTreeEdit
              :data = imgData
              @image-selected="handleImageSelected"
          ></BookInputImgTreeEdit>
        </section>

        <section class="input-form__input-container">
          <h1 class="none">도서 이름 입력</h1>

          <TextBoxReadOnly
              v-if="isAuto"
            :data="{
              label: '*도서 이름',
              name: 'bookTitle',
              placeholder: '',
              value: treeInfo.title,
              validate: treeFormData.bookTitleValid,
              validateMessage: '도서 이름을 입력해주세요.',
            }"
            @inputData="inputBookTitle"
          >
          </TextBoxReadOnly>
          <TextBox
              v-else
            :data="{
              label: '*도서 이름',
              name: 'bookTitle',
              placeholder: '도서 이름을 입력해주세요',
              value: treeInfo.title,
              validate: treeFormData.bookTitleValid,
              validateMessage: '도서 이름을 입력해주세요.',
            }"
            @inputData="inputBookTitle"
              ></TextBox>
        </section>

        <section class="input-form__input-container">
          <h1 class="none">지은이 입력</h1>
          <TextBoxReadOnly
              v-if="isAuto"
              :data="{
              label: '*지은이 이름',
              name: 'author',
              placeholder: '',
              value: treeInfo.author,
              validate: treeFormData.authorValid,
              validateMessage: '지은이 이름을 입력해주세요.',
            }"
              @inputData="inputAuthor"
          >
          </TextBoxReadOnly>
          <InputTextBox
              v-else
              :data="{
              label: '*지은이 이름',
              name: 'author',
              placeholder: treeInfo.author,
              value: treeInfo.author,
              validate: treeFormData.authorValid,
              validateMessage: '지은이 이름을 입력해주세요.',
            }"
              @inputData="inputAuthor"
          >
          </InputTextBox>
        </section>

        <section class="input-form__input-container">
          <h1 class="none">출판사 입력</h1>
          <TextBoxReadOnly
              v-if="isAuto"
              :data="{
              label: '*출판사',
              name: 'publisher',
              placeholder: '',
              value: treeInfo.publisher,
              validate: treeFormData.publisherValid,
              validateMessage: '출판사를 입력해주세요.',
            }"
              @inputData="inputPublisher"
          >
          </TextBoxReadOnly>
          <InputTextBox
              v-else
              :data="{
              label: '*출판사',
              name: 'publisher',
              placeholder: treeInfo.publisher,
              value: treeInfo.publisher,
              validate: treeFormData.publisherValid,
              validateMessage: '출판사를 입력해주세요.',
            }"
              @inputData="inputPublisher"
          >
          </InputTextBox>
        </section>

        <section class="input-form__input-container">
          <h1 class="none">출판일 입력</h1>
          <TextDateBoxReadOnly
              v-if="isAuto"
              :data="{
              label: '*출판일',
              name: 'publishDate',
              placeholder: '출판일을 입력해주세요',
              value: treeInfo.publishDate,
              validate: treeFormData.publishDateValid,
              validateMessage: '출판일을 입력해주세요. (2024-11-01 형식)',
            }"
              @inputData="inputPublishDate"
          >
          </TextDateBoxReadOnly>
          <InputTextDateBox
              v-else
              :data="{
              label: '*출판일',
              name: 'publishDate',
              placeholder: 'treeInfo.publishDate',
              value: treeInfo.publishDate,
              validate: treeFormData.publishDateValid,
              validateMessage: '출판일을 입력해주세요. (2024-11-01 형식)',
            }"
              @inputData="inputPublishDate"
          >
          </InputTextDateBox>
        </section>

        <section class="input-form__input-container">
          <h1 class="none">총페이지 입력</h1>
          <TextNumberBoxReadOnly
              v-if="isAuto"
              :data="{
              label: '*총페이지',
              name: 'totalPage',
              placeholder: '총페이지를 입력해주세요',
              min: 0,
              value: treeInfo.totalPage,
              validate: treeFormData.totalPageValid,
              validateMessage: '양수의 숫자만 입력해주세요.',
            }"
              @inputData="inputTotalPage"
          >
          </TextNumberBoxReadOnly>
          <InputTextNumberBox
              v-else
              :data="{
              label: '*총페이지',
              name: 'totalPage',
              placeholder: 'treeInfo.totalPage',
              min: 0,
              value: treeInfo.totalPage,
              validate: treeFormData.totalPageValid,
              validateMessage: '양수의 숫자만 입력해주세요.',
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
              isSelected: treeFormData.bookCategorySelected,
            }"
            @drop="dropBookCategory"
          >
          </InputBookCategorySelect>
        </section>

        <section class="input-form__input-container">
          <h1 class="none">트리이름 입력</h1>
          <InputTextBox
            :data="{
              label: '*트리 이름',
              name: 'treeTitle',
              placeholder: '트리 이름을 입력해주세요',
              value: treeInfo.treeTitle,
              validate: treeFormData.treeTitleValid,
              validateMessage: '트리 이름을 입력해주세요.',
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
              value: treeInfo.description,
              validate: treeFormData.descriptionValid,
              validateMessage: '트리에 대한 설명을 입력해주세요.',
            }"
            @inputData="inputDescription"
          >
          </InputTextareaBox>
        </section>

        <section class="input-form__input-container">
          <h1 class="none">공개성 선택</h1>
          <InputVisibility
            name="visibility"
            v-model="treeFormData.visibility"
          ></InputVisibility>
        </section>

        <section class="book-tree-submit-container">
          <h1 class="none">트리 수정 버튼</h1>
          <ButtonSubmitBtnFullBar
            text="트리 수정"
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