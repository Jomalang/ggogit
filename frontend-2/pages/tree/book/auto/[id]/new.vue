<script setup>
import { onMounted, watch } from "vue";
import axios, { HttpStatusCode } from "axios";
import { useRouter } from "#vue-router";

// ----------------------- Model ----------------------- //
const router = useRouter();
const route = useRoute();
const config = useRuntimeConfig();
const bookId = route.params.id;

const treeFormData = useState("treeFormData", () => ({
  bookId: null,

  // 트리 총 페이지 정보
  totalPage: null,

  // 트리 정보
  treeTitle: "",
  treeTitleValid: true,

  // 트리 설명 정보
  description: "",
  descriptionValid: true,

  // 공개 여부 정보
  visibility: false,
  visibilityValid: true,
}));

// ----------------------- API ----------------------- //
const { data } = await useFetch(`/books/${bookId}`, {
  method: "GET",
  baseURL: config.public.apiBase,
  headers: {
    "Content-Type": "application/json",
  },
});

watchEffect(() => {
  // console.log("watchEffect data : ", data.value);
  treeFormData.value.totalPage = data.value.totalPage;
  treeFormData.value.bookId = data.value.id;
});

// ----------------------- Life Cycle ----------------------- //
onMounted(() => {
  if (treeFormData.value.imageData) {
    const imgTag = document.getElementById("input-book-img-box__img-id");
    imgTag.src = treeFormData.value.imageData;
  }
});

// ----------------------- Function ----------------------- //

const inputTreeTitle = (value) => {
  // console.log("inputTreeTitle : ", value);
  treeFormData.value.treeTitle = value;
  treeFormData.value.treeTitleValid = true;
  // console.log(treeFormData.value);
};

const inputDescription = (value) => {
  // console.log("inputDescription : ", value);
  treeFormData.value.description = value;
  treeFormData.value.descriptionValid = true;
  // console.log(treeFormData.value);
};

const validateCheck = () => {
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

  return true;
};

const submitFormHandler = async (e) => {
  if (!validateCheck()) {
    return;
  }

  e.preventDefault(); // 데이터 전송 로직
  try {
    const response = await axios.post(
      "http://localhost:8080/api/v1/trees/auto",
      {
        bookId: treeFormData.value.bookId,
        treeTitle: treeFormData.value.treeTitle,
        description: treeFormData.value.description,
        visibility: treeFormData.value.visibility,
      },
      {
        headers: { "Content-Type": "application/json" },
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
          <LinkOneImageDetail :href="`/book/${data.id}`" :src="data.imageFile">
          </LinkOneImageDetail>
        </section>
        <section class="tree-reg-book-info__container">
          <h4 class="none">도서 정보</h4>
          <TextBookInfo
            :data="{
              title: data.title,
              authors: data.authors,
              translators: data.translators,
              publisher: data.publisher,
              category: {
                name: data.bookCategoryName,
                id: data.bookCategoryId,
              },
              page: data.page,
              seed: data.seed,
            }"
          />
        </section>
      </section>

      <section class="tree-info-card__container">
        <CardTreeInfoCard
          :data="{ date: data.publishDate, pageCount: data.totalPage }"
        />
      </section>

      <form class="tree-book-auto-form-container">
        <section class="none">
          <input type="text" name="bookId" :value="data.id" />
        </section>

        <section class="input-form__input-container">
          <h1 class="none">트리 이름 입력</h1>
          <InputTextBox
            :data="{
              label: '*트리 이름',
              name: 'treeTitle',
              placeholder: '트리 이름을 입력해주세요',
              value: treeFormData.treeTitle,
              validate: treeFormData.treeTitleValid,
              validateMessage: '트리 이름을 입력해주세요',
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
