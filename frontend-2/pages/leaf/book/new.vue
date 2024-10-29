<script setup>

import Editor from "@toast-ui/editor";
import "@toast-ui/editor/dist/toastui-editor.css";
import { onMounted, reactive, watch } from "vue";
import axios, {HttpStatusCode} from "axios";

// ----------------------- Model ----------------------- //

const config = useRuntimeConfig();
const router = useRouter();


const leafFormData = useState('leafFormData', () => ({
  startPage: undefined,
  endPage: undefined,
  tagIds: [],
  title: undefined,
  content: undefined,
  visibility: undefined,
}));

const selectedTags = useState('selectedTags', () => ({
  items: [],
}));

const leafCreateUrl = useState('leafCreateUrl', () => {
  return "/leaf/book/new";
});

watchEffect(() => {
  console.log("leafFormData : ", leafFormData);
  console.log("selectedTags : ", selectedTags);
});

// ----------------------- Life Cycle ----------------------- //

onMounted(() => {
  console.log("leafFormData : ", leafFormData);

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
          const response = await fetch(`${config.public.apiBase}/leaf/image-upload`, {
            method: "POST",
            body: treeFormData,
          });
          // 컨트롤러에서 전달받은 디스크에 저장된 파일 명
          const filename = await response.text();
          console.log("서버에 저장된 파일 명 : ", filename);

          // addImageBlobHook의 callback을 통해 디스크에 저장된 이미지 에디터에 렌더링
          const imageUrl = `${config.public.apiBase}/leaf/image-print?filename=${filename}`;
          callback(imageUrl, "image alt attribute");
          console.log(blob);
          console.log(callback);
        } catch (error) {
          console.log("업로드 실패 : ", error);
        }
      },
    },
  });

  editor.on("change", () => {
    document.querySelector("#editor-text").textContent = editor.getMarkdown();
    console.log("editor.getMarkdown() : ", editor.getMarkdown());
    console.log("leafFormData.value : ", leafFormData.value);
    leafFormData.value.content = editor.getMarkdown();
  });
});

// ----------------------- Function ----------------------- //
const tagDrop = (tag) => {
  console.log("tagDrop : ", tag);
  const index = selectedTags.value.items.findIndex((item) => item.id === tag.id);
  selectedTags.value.items.splice(index, 1);
};

const submitHandler = async () => {

  console.log("leafFormData POST > : ", leafFormData.value);
  const response = await axios.post(`${config.public.apiBase}/book/first/leaves`, leafFormData.value);

  if (response.status !== HttpStatusCode.Created) {
    throw new Error("Network response was not ok");
  }

  let leafId = response.data.leafId;
  router.push(`/leaf/?leafId=${leafId}`);
};

</script>

<template>
  <header>
    <h1 class="none">리프 생성 페이지</h1>
    <section class="tob-bar-back-container">
      <h1 class="none">리프 생성 상단 바</h1>
      <TopBarBack title="리프 생성" link="/tree/book/reg"></TopBarBack>
    </section>
  </header>

  <main>
    <section class="first-log-img-container">
      <h1 class="none">이전 리프 생성 이미지</h1>
      <LogFirstLog></LogFirstLog>
    </section>

    <section class="first-log-title-container">
      <h1 class="none"></h1>
      <TextMainTitleCenter
          title="트리 첫번째 기록"
          size="28"
      ></TextMainTitleCenter>
    </section>

    <form
        action="/book/first/leaves"
        method="post"
        class="input-form"
        id="input-leaf-register-form-id"
    >
      <section>
        <h1 class="none">리프 생성 데이터 입력</h1>

        <section class="none">
          <h1>씨앗 데이터 타입</h1>
          <label><input type="number" name="seedId" value="1" /></label>
        </section>

        <section class="first-log_page-input-container">
          <h1 class="none">리프 페이지</h1>
          <InputPageNumber
              v-model:start-page="leafFormData.startPage"
              v-model:end-page="leafFormData.endPage"
          >
          </InputPageNumber>
        </section>

        <section class="input-form__select-tag-input-container">
          <h1 class="none">리프 태그 입력</h1>
          <InputTagSelect :selectedTag="selectedTags.items" @drop="tagDrop"></InputTagSelect>
        </section>

        <section class="input-form__input-container">
          <h1 class="none">로그이름 입력</h1>
          <InputTextBox
              label="*제목"
              name="title"
              placeholder="리프 제목을 입려해 주세요."
              v-model="leafFormData.title"
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
          <InputVisibility v-model:visibility="leafFormData.visibility"></InputVisibility>
        </section>

        <section class="book-tree-submit-container">
          <h1 class="none">리프 생성 버튼</h1>
          <ButtonSubmitBtnFullBar text="리프 생성" @submit="submitHandler"></ButtonSubmitBtnFullBar>
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
