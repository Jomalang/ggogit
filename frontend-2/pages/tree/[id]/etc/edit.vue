<script setup>
import { onMounted, watch } from "vue";
import axios, { HttpStatusCode } from "axios";
import { useRouter } from "#vue-router";
import { value } from "lodash/seq.js";
import useTreeFormData from "~/composables/useTreeFormData.js";
import BookInputImgEtcTree from "~/components/input/BookInputImgEtcTree.vue";

// ----------------------- Model ----------------------- //
const route = useRoute();
const router = useRouter();
const config = useRuntimeConfig();
const treeId = route.params.id;
let originTreeImage = '';
useTreeFormData().init();
const treeFormData = useTreeFormData().treeFormData;

// ----------------------- API ----------------------- //
const { data: seedData, error: infoError } = await useAuthFetch(
  () => `seeds/trees/${treeId}`,
  {
    baseURL: config.public.apiBase,
  }
);
const { data: treeData, error: treeError } = await useAuthFetch(
  () => `trees/${treeId}/info`,
  {
    baseURL: config.public.apiBase,
  }
);

watchEffect(() => {
  console.log("seedData:", seedData.value);
});

// ----------------------- Life Cycle ----------------------- //
onMounted(() => {

  treeFormData.value.seedId = seedData.value.id;
  treeFormData.value.imageData = treeData.value.treeImage;
  treeFormData.value.treeId = treeId;
  treeFormData.value.treeTitle = treeData.value.title;
  treeFormData.value.description = treeData.value.description;
  treeFormData.value.visibility = treeData.value.visibility;
  originTreeImage = treeData.value.treeImage;
  if (treeFormData.value.imageData) {
    const imgTag = document.getElementById("input-book-img-box__img-id");
    console.log("imgTag:", imgTag);
    imgTag.src = treeFormData.value.imageData;
  }
});

// ----------------------- Function ----------------------- //
const handleImageSelected = (imageData) => {
  // console.log("Selected image data:", imageData);
  treeFormData.value.imageData = imageData;
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

      if(originTreeImage !== treeFormData.value.imageData) {
        console.log("imgTag.src:", imgTag);
        const response = await fetch(imgTag.src);
        const blob = await response.blob();
        treeFormDataToSend.append("image", blob, "image.jpg");
      }
    }

    const response = await useAuthDataFetch('trees/etc/edit', {
      baseURL: config.public.apiBase,
      method: 'PUT',
      body: treeFormDataToSend,
    });

    if (response.statusCode !== HttpStatusCode.Created) {
      console.error("트리 수정에 실패했습니다.");
    }

    router.push(`/tree/${treeId}`);
  } catch (error) {
    console.error("Error submitting form:", error);
  }
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
    <h1 class="none">도서 트리 수정 페이지</h1>
    <section class="tob-bar-back-container">
      <h1 class="none">트리 수정 상단 바</h1>
      <TopBarBack title="트리 수정" link=""></TopBarBack>
    </section>
  </header>

  <main>
    <section class="book-tree-input-form-container">
      <h1 class="none">도서 정보 입력</h1>

      <section class="select-title-container">
        <TextMainTitle
          :data="{ title: `${seedData.name}`, size: 28 }"
        ></TextMainTitle>
      </section>

      <form class="book-tree-input-form">
        <section class="none">
          <h1 class="none">씨앗 카테고리</h1>
          <input
            type="number"
            name="seedCategoryId"
            v-model="treeFormData.treeId"
          />
        </section>

        <section class="book-tree-input-form__photo-container">
          <h1 class="none">트리 이미지 입력</h1>
          <BookInputImgEtcTree
              :imageData = treeFormData.imageData
            @image-selected="handleImageSelected"
          ></BookInputImgEtcTree>
        </section>

        <section class="input-form__input-container">
          <h1 class="none">트리이름 입력</h1>
          <InputTextBox
            :data="{
              label: '*트리 이름',
              name: 'treeTitle',
              placeholder: '트리 이름을 입력해주세요',
              value: treeData.title,
              validate: treeFormData.treeTitleValid,
              validateMessage: '트리 이름을 입력해주세요.',
            }"
            @inputData="inputTreeTitle"
          >
          </InputTextBox>
        </section>

        <section class="book-tree-input-form__large-input-container">
          <h1 class="none">설명글 or URL</h1>
          <InputTextareaBox
            :data="{
              label: '*설명글 or URL',
              name: 'description',
              placeholder: '트리에 대한 설명을 입력해주세요',
              value: treeData.description,
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
