<script setup>
import Editor from "@toast-ui/editor";
import "@toast-ui/editor/dist/toastui-editor.css";
import { onMounted, reactive } from "vue";
import { HttpStatusCode } from "axios";

// ----------------------- Model ----------------------- //
const config = useRuntimeConfig();
const router = useRouter();
const route = useRoute();
const leafId = Number(route.params.id);

const beforeLogData = reactive({});

const totalPage = ref(null);

useLeafFormData().init();
useLeafFormData().setCreateUrl(`/leaf/book/${leafId}/edit`);
const leafFormData = useLeafFormData().leafFormData;
const selectedTags = useLeafTagList().selectedTags;

const { data: totalPageData, status: totalPageStatus } = await useAuthFetch(
  `leaves/${leafId}/book/page`,
  {
    baseURL: `${config.public.apiBase}`,
    method: "GET",
  }
);

const { data: beforeLeafData, status: beforeLeafStatus } = await useAuthFetch(
  `leaves/${leafId}/before`,
  {
    baseURL: `${config.public.apiBase}`,
    method: "GET",
  }
);

const { data: leafEditDataApi, status: leafEditStatus } = await useAuthFetch(
  `/book/leaves/${leafId}/edit`,
  {
    baseURL: `${config.public.apiBase}`,
    method: "GET",
  }
);

if (totalPageStatus.value) {
  totalPage.value = totalPageData.value;
}

if (beforeLeafStatus.value) {
  beforeLogData.id = beforeLeafData.value.id;
  beforeLogData.title = beforeLeafData.value.title;
  beforeLogData.date = beforeLeafData.value.createTime;
  beforeLogData.tags = beforeLeafData.value.tags;
}

console.log("leafFormData.value.isLoaded", leafFormData.value.isLoaded);
if (!leafFormData.value.isLoaded) {
  console.log("leafEditData : ", leafEditDataApi.value);
  leafFormData.value.startPage = leafEditDataApi.value.startPage;
  leafFormData.value.endPage = leafEditDataApi.value.endPage;
  leafFormData.value.title = leafEditDataApi.value.title;
  leafFormData.value.content = leafEditDataApi.value.content;
  leafFormData.value.visibility = leafEditDataApi.value.visibility;
  console.log("leafEditData.value.tags : ", leafEditDataApi.value.tags);
  selectedTags.value = leafEditDataApi.value.tags;
  leafFormData.value.isLoaded = true;
}

watchEffect(() => {

  if (selectedTags.value.length !== 0) {
    leafFormData.value.tagIds = selectedTags.value.map((tag) => tag.id);
  }
});

// ----------------------- Life Cycle ----------------------- //

onMounted(() => {
  // console.log("leafFormData : ", leafFormData);

  // 화면 최상단 이동
  window.scrollTo(0, 0);

  const editor = new Editor({
    el: document.querySelector("#editor"),
    height: "300px",
    initialEditType: "wysiwyg",
    previewStyle: "vertical",
    placeholder: "무엇을 느끼셨나요?",
    usageStatistics: false,
    initialValue: leafFormData.value.content || "",
    hooks: {
      async addImageBlobHook(blob, callback) {
        try {
          /**
           * 에디터에 업로드한 이미지를 FormData 객체에 저장
           */
          const treeFormData = new FormData();
          treeFormData.append("image", blob);

          // MemoirFileApiController - uploadEditorImage 메서드 호출
          const response = await fetch(
            `${config.public.apiBase}/leaf/image-upload`,
            {
              method: "POST",
              body: treeFormData,
            }
          );
          // 컨트롤러에서 전달받은 디스크에 저장된 파일 명
          const filename = await response.text();
          // console.log("서버에 저장된 파일 명 : ", filename);

          // addImageBlobHook의 callback을 통해 디스크에 저장된 이미지 에디터에 렌더링
          const imageUrl = `${config.public.apiBase}/leaf/image/${filename}`;
          callback(imageUrl, "image alt attribute");
          // console.log(blob);
          // console.log(callback);
        } catch (error) {
          // console.log("업로드 실패 : ", error);
        }
      },
    },
  });

  editor.on("change", () => {
    document.querySelector("#editor-text").textContent = editor.getMarkdown();
    // console.log("editor.getMarkdown() : ", editor.getMarkdown());
    // console.log("leafFormData.value : ", leafFormData.value);
    leafFormData.value.content = editor.getMarkdown();
  });
});

// ----------------------- Function ----------------------- //
const inputTitle = (title) => {
  leafFormData.value.title = title;
  leafFormData.value.titleValidation = true;
};

const inputStartPage = (data) => {
  leafFormData.value.startPage = data.number;
  leafFormData.value.startPageValidation = data.isValidate;
  pageValidation();
  // console.log("startPage : ", leafFormData.value);
};

const inputEndPage = (data) => {
  leafFormData.value.endPage = data.number;
  leafFormData.value.endPageValidation = data.isValidate;
  pageValidation();
  // console.log("endPage : ", leafFormData.value);
};

const pageValidation = () => {
  // 마지막 페이지가 시작 페이지보다 큰지 확인
  if (leafFormData.value.startPage <= leafFormData.value.endPage) {
    leafFormData.value.startPageValidation = true;
    leafFormData.value.endPageValidation = true;
  }
};

const tagDrop = (tag) => {
  // console.log("tagDrop : ", tag);
  selectedTags.value = selectedTags.value.filter(
    (item) => item.id !== tag.id
  );
  leafFormData.value.tagIds = selectedTags.value.map((tag) => tag.id);
};

const validate = () => {
  if (
    !leafFormData.value.startPage ||
    !leafFormData.value.startPageValidation
  ) {
    leafFormData.value.startPageValidation = false;
    alert("시작 페이지를 잘못 입력하셨습니다.");
    return false;
  }

  if (!leafFormData.value.endPage || !leafFormData.value.endPageValidation) {
    leafFormData.value.endPageValidation = false;
    alert("마지막 페이지를 잘못 입력하셨습니다.");
    return false;
  }

  if (!leafFormData.value.title) {
    leafFormData.value.titleValidation = false;
    alert("리프 제목을 입력해 주세요.");
    return false;
  }

  return true;
};

const submitHandler = async () => {
  if (!validate()) {
    return;
  }

  // console.log("leafFormData POST > : ", leafFormData.value);
  const response = await useAuthDataFetch(`book/leaves/${leafId}`, {
    baseURL: `${config.public.apiBase}`,
    method: "PUT",
    body: leafFormData.value,
  });

  if (response.statusCode !== HttpStatusCode.Ok) {
    throw new Error("Network response was not ok");
  }

  // 데이터 초기화
  useLeafFormData().postInit();
  useLeafTagList().postInit();

  console.log("response : ", useLeafFormData().leafFormData);

  router.push(`/leaf/?leafId=${leafId}`);
};
</script>

<template>
  <header>
    <h1 class="none">리프 수정 페이지</h1>
    <section class="tob-bar-back-container">
      <h1 class="none">리프 수정 상단 바</h1>
      <TopBarBack
        title="리프 수정"
        :link="`/leaf?leafId=${leafId}`"
      ></TopBarBack>
    </section>
  </header>

  <main>
    <section class="before-log-img-container">
      <h1 class="none">이전 리프 수정 이미지</h1>
      <LogCurrentLog :data="beforeLogData"></LogCurrentLog>
    </section>

    <section class="before-log-title-container">
      <h1 class="none"></h1>
      <TextMainTitle :data="{ title: '리프 수정', size: 24 }"></TextMainTitle>
    </section>

    <form class="input-form">
      <section>
        <h1 class="none">리프 수정 데이터 입력</h1>

        <section class="none">
          <h1>씨앗 데이터 타입</h1>
          <label><input type="number" name="seedId" value="1" /></label>
        </section>

        <section class="first-log_page-input-container">
          <h1 class="none">리프 페이지</h1>
          <InputPageNumber
            :data="{
              startPage: leafFormData.startPage,
              endPage: leafFormData.endPage,
              maxPage: totalPage,
            }"
            @startPage="inputStartPage"
            @endPage="inputEndPage"
          >
          </InputPageNumber>
        </section>

        <section class="input-form__select-tag-input-container">
          <h1 class="none">리프 태그 입력</h1>
          <InputTagSelect
            :selectedTag="selectedTags"
            @drop="tagDrop"
          ></InputTagSelect>
        </section>

        <section class="input-form__input-container">
          <h1 class="none">로그이름 입력</h1>
          <InputTextBox
            :data="{
              label: '*제목',
              name: 'title',
              placeholder: '리프 제목을 입력해 주세요.',
              value: leafFormData.title,
              validate: leafFormData.titleValidation,
              validateMessage: '리프 제목을 입력해 주세요.',
            }"
            @inputData="inputTitle"
          ></InputTextBox>
        </section>

        <section class="book-tree-input-form__large-input-container">
          <h1 class="none">토스트 에디터</h1>
          <div class="toastui-editor-text">*내용</div>
          <div id="editor"></div>
        </section>

        <section class="book-tree-input-form__large-input-container none">
          <h1 class="none">리프 내용 입력</h1>
          <label>
            <textarea class="hidden" id="editor-text" name="content"></textarea>
          </label>
        </section>

        <section class="input-form__input-container">
          <h1 class="none">공개성 선택</h1>
          <InputVisibility
            v-model:visibility="leafFormData.visibility"
          ></InputVisibility>
        </section>

        <section class="book-tree-submit-container">
          <h1 class="none">리프 수정 버튼</h1>
          <ButtonSubmitBtnFullBar
            text="리프 수정"
            @submit="submitHandler"
          ></ButtonSubmitBtnFullBar>
        </section>
      </section>
    </form>
  </main>

  <aside class="nav-container">
    <h1 class="none">네비게이션 하단</h1>
    <NavNavigationBar active="home"></NavNavigationBar>
  </aside>
</template>

<style scoped>
.toastui-editor-text {
  font-weight: var(--semi-bold);
  margin-bottom: 8px;
}

.input-form {
  height: 1400px;
}

</style>
