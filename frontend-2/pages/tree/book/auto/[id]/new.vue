<script setup>
import {onMounted, watch} from "vue";
import axios, { HttpStatusCode } from "axios";
import {useRouter} from "#vue-router";

// ----------------------- Model ----------------------- //
const router = useRouter();
const route = useRoute();
const config = useRuntimeConfig();
const bookId = route.params.id;

// ----------------------- API ----------------------- //
const { data } = await useFetch(`/books/${bookId}`, {
  method: "GET",
  baseURL: config.public.apiBase,
  headers: {
    "Content-Type": "application/json",
  },
});

watchEffect(() => {
  console.log("watchEffect data : ", data.value);
});

const treeFormData = useState('treeFormData', () => ({
  // 트리 씨앗 정보
  seedId: 1,

  // 도서 정보
  bookTitle: "",
  author: "",
  publishDate: "",
  publisher: "",
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
      <TopBarBack title="트리 생성" link="/tree/seed"></TopBarBack>
    </section>
  </header>

  <main>
    <section class="book-tree-input-form-container">
      <h1 class="none">도서 정보 입력</h1>

      <section class="tree-book-auto-title-container">
        <TextMainTitle :data="{ title: '도서 정보', size: 28 }"></TextMainTitle>
      </section>

      <section class="tree-reg-cover-info__container">
        <h3 class="none">도서 커버 및 도서 정보</h3>
        <section class="tree-reg-cover__container">
          <h4 class="none">도서 커버</h4>
          <LinkOneImageDetail
              :href="`/book/${data.id}`"
              :src="data.imageFile">
          </LinkOneImageDetail>
        </section>
        <section class="tree-reg-book-info__container">
          <h4 class="none">도서 정보</h4>
          <TextBookInfo :data="{
            title: data.title,
            authors: data.authors,
            translators: translators,
            publisher: data.publisher,
            page: data.page,
            seed: data.seed,
          }"></TextBookInfo>
        </section>
      </section>

      <section class="tree-info-card__container">
        <CardTreeInfoCard :data="{ date: data.publishDate, pageCount: data.totalPage }" />
      </section>


      <form class="tree-book-auto-form-container">

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
          <InputVisibility name="visibility" v-model="treeFormData.visibility"></InputVisibility>
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
