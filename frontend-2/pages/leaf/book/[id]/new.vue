<script setup>
import Editor from "@toast-ui/editor";
import "@toast-ui/editor/dist/toastui-editor.css";
import { onMounted, reactive, watch } from "vue";
import axios, { HttpStatusCode } from "axios";

// ----------------------- Model ----------------------- //
const config = useRuntimeConfig();
const router = useRouter();
const route = useRoute();
const parentLeafId = Number(route.params.id);

const beforeLogData = reactive({
  id: 1,
  title: "트리 첫번째 기록",
  date: "2021-10-10",
  tags: [
    { id: 1, name: "태그1" },
    { id: 2, name: "태그2" },
    { id: 3, name: "태그3" },
  ],
});

const totalPage = ref(null);

const leafFormData = useState("leafFormData", () => ({
  // 페이지 번호
  startPage: undefined,
  startPageValidation: true,
  endPage: undefined,
  endPageValidation: true,

  // 태그 데이터
  tagIds: [],
  tagSelected: true,

  // 제목
  title: undefined,
  titleValidation: true,

  // 내용
  content: undefined,

  // 공개성
  visibility: true,
}));

const selectedTags = useState("selectedTags", () => ({
  items: [],
}));

const leafCreateUrl = useState("leafCreateUrl", () => {
  return `/leaf/book/${parentLeafId}/new`;
});

const { data: totalPageData, status: totalPageStatus } = await useAuthFetch(
  `leaves/${parentLeafId}/book/page`,
  {
    baseURL: `${config.public.apiBase}`,
    method: "GET",
  }
);

const { data: beforeLeafData, status: beforeLeafStatus } = await useAuthFetch(
  `leaves/${parentLeafId}/before`,
  {
    baseURL: `${config.public.apiBase}`,
    method: "GET",
  }
);

watchEffect(() => {
  if (totalPageStatus.value !== HttpStatusCode.Ok) {
    // console.log("totalPageData : ", totalPageData.value);
    totalPage.value = totalPageData.value;
  }

  if (beforeLeafStatus.value !== HttpStatusCode.Ok) {
    // console.log("beforeLeafData : ", beforeLeafData.value);
    beforeLogData.id = beforeLeafData.value.id;
    beforeLogData.title = beforeLeafData.value.title;
    beforeLogData.date = beforeLeafData.value.createTime;
    beforeLogData.tags = beforeLeafData.value.tags;
  }

  if (selectedTags.value.items.length !== 0) {
    leafFormData.value.tagIds = selectedTags.value.items.map((tag) => tag.id);
  }

  // console.log("leafFormData : ", leafFormData);
  // console.log("selectedTags : ", selectedTags);
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
  selectedTags.value.items = selectedTags.value.items.filter(
    (item) => item.id !== tag.id
  );
  leafFormData.value.tagIds = selectedTags.value.items.map((tag) => tag.id);
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
  const response = await axios.post(
    `${config.public.apiBase}/book/leaves/${parentLeafId}`,
    leafFormData.value
  );

  if (response.status !== HttpStatusCode.Created) {
    throw new Error("Network response was not ok");
  }

  let leafId = response.data.leafId;

  // 데이터 초기화
  leafFormData.value = {};

  router.push(`/leaf/?leafId=${leafId}`);
};
</script>

<template>
  <header>
    <h1 class="none">리프 생성 페이지</h1>
    <section class="tob-bar-back-container">
      <h1 class="none">리프 생성 상단 바</h1>
      <TopBarBack
        title="리프 생성"
        :link="`/leaf?leafId=${parentLeafId}`"
      ></TopBarBack>
    </section>
  </header>

  <main>
    <section class="first-log-img-container">
      <h1 class="none">이전 리프 생성 이미지</h1>
      <LogBeforeLog :data="beforeLogData"></LogBeforeLog>
    </section>

    <section class="before-log-title-container">
      <h1 class="none"></h1>
      <TextMainTitle :data="{ title: '리프 생성', size: 24 }"></TextMainTitle>
    </section>

    <form class="input-form">
      <section>
        <h1 class="none">리프 생성 데이터 입력</h1>

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
            :selectedTag="selectedTags.items"
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
          <h1 class="none">리프 생성 버튼</h1>
          <ButtonSubmitBtnFullBar
            text="리프 생성"
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
</style>
