<script setup>
import { onBeforeMount, onMounted, ref } from "vue";
import Editor from "@toast-ui/editor";
import "@toast-ui/editor/dist/toastui-editor.css";
import LInkOneImageDetail from "~/components/link/LInkOneImageDetail.vue";
import SubmitBtnFullBar from "~/components/button/SubmitBtnFullBar.vue";
import NavigationBar from "~/components/nav/NavigationBar.vue";

//----------------variable----------------
//save 호출 API
const tmpSaveUrl = `${
  import.meta.env.VITE_API_BASE_URL
}/api/v1/memoir-image/upload-tmp`;

//이미지 전체 경로 호출 API
const tmpPathUrl = `${
  import.meta.env.VITE_API_BASE_URL
}/api/v1/memoir-image/path-tmp?fileName=`;

const tmpRenderUrl = `${
  import.meta.env.VITE_API_BASE_URL
}/api/v1/memoir-image/return-byte-tmp?filePath=`;

//트리 아이디
const treeId = useRoute().params.id;

//editor 객체
let editor;

//----------------model---------------

const memoir = ref({
  title: "",
  text: "",
  visibility: true,
});

const memoirId = ref(0);

const book = ref({
  bookId: 0,
  bookTitle: "default-title",
  bookAuthor: "default-author",
  //배열로 전달
  bookTranslator: ["default-translator"],
  bookPublisher: "default-publisher",
  bookImage: "book-cover-dummy1.svg",
  bookCategory: "default-category",
});

const fileNames = ref([]);

//----------------function----------------
//save로직
const savePost = async () => {
  //에디터에서 작성한 내용을 획득
  memoir.value.text = editor.getHTML();

  //useFetch
  const { data, error } = await useFetch(
    import.meta.env.VITE_API_BASE_URL + "/api/v1/memoir/" + treeId,
    {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        title: memoir.value.title,
        text: memoir.value.text,
        visibility: memoir.value.visibility,
        fileNames: fileNames.value,
      }),
    }
  );

  if (error.value) {
    console.error("회고록 등록 실패 : ", error.value);
    //TODO : 에러 출력
    alert(error.value.data.message);
    return;
  } else {
    alert("회고록이 성공적으로 등록되었습니다.");
    memoirId.value = data.value.id;
    //리다이렉션
    await navigateTo(`/memoir/${memoirId.value}`);
  }
};

//---------------Life Cycle----------------
onMounted(() => {
  editor = new Editor({
    el: document.querySelector("#editor"),
    height: "450px",
    initialEditType: "wysiwyg",
    previewStyle: "vertical",
    placeholder: "무엇을 느끼셨나요?",
    usageStatistics: false,
    hooks: {
      async addImageBlobHook(blob, callback) {
        try {
          /**
           * 에디터에 업로드한 이미지를 FormData 객체에 저장
           */
          const memoirImageFormData = new FormData();
          memoirImageFormData.append("image", blob);

          // MemoirFileApiController - uploadEditorImage 메서드 호출
          const response = await fetch(tmpSaveUrl, {
            method: "POST",
            body: memoirImageFormData,
          });
          // 컨트롤러에서 전달받은 디스크에 저장된 파일 명
          const fileName = await response.text();
          console.log("서버에 저장된 파일 명 : ", fileName);
          fileNames.value.push(fileName);

          //획득한 이미지경로 바탕으로 바이트 코드 획득
          callback(tmpRenderUrl + `${fileName}`, "image alt");
        } catch (error) {
          console.log("업로드 실패 : ", error);
        }
      },
    },
  });
});

// TODO: 도서, 트리 API이용해 데이터 가져오기
// onBeforeMount(async () => {
//   const { data, error } = await useFetch(
//     import.meta.env.VITE_API_BASE_URL + "/api/v1/tree/" + treeId
//   );

//   if (error.value) {
//     console.error("트리 정보 조회 실패 : ", error.value);
//     return;
//   } else {
//     book.value = data.value.book;
//   }
// });
</script>

<template>
  <Title>회고록 작성</Title>
  <header>
    <h1 class="none">도서 완독 후 회고록 생성</h1>

    <section>
      <h2 class="none">회고록 생성</h2>
      <TopBarBack :title="`회고록 생성`" :link="`trees/${id}`" />
    </section>
  </header>

  <main>
    <h2 class="none">완독한 도서</h2>
    <section class="tree-book-reg-search-book__title-container">
      <h3 class="none">도서 TEXT 컨테이너</h3>
      <TextMainTitle :data="{ title: '완독한 도서', size: 28 }" />
    </section>
    <section class="tree-reg-cover-info__container">
      <h3 class="none">도서 커버 및 도서 정보</h3>
      <section class="tree-reg-cover__container">
        <h4 class="none">도서 커버</h4>
        <LInkOneImageDetail
          :src="`book/${book.bookImage}`"
          :href="'javascript:history.back()'"
        />
      </section>
      <section class="tree-reg-book-info__container">
        <h4 class="none">도서 정보</h4>
        <TextBookInfo
          :seed="book.bookCategory"
          :title="book.bookTitle"
          :author="book.bookAuthor"
          :translators="book.bookTranslator"
          :publisher="book.bookPublisher"
        />
      </section>
    </section>

    <section class="register__input-container">
      <h3 class="none">회고록 제목 입력</h3>
      <!-- 컴포넌트 -->
      <div class="input-text__bar">
        <label class="input-text__label">
          <span class="input-text__label-text">회고록 제목</span>
          <input
            class="input-text__input"
            v-model="memoir.title"
            :placeholder="'제목을 입력해 주세요.'"
          />
        </label>
      </div>
    </section>

    <section class="register__input-container">
      <h3 class="none">회고록 설명글</h3>
      <!--            toast 에디터2.0 -->
      <div id="editor"></div>
      <input class="hidden" id="editor-text" v-model="memoir.text" />
    </section>
    <section class="register__input-container">
      <h3 class="none">공개 여부</h3>
      <!-- 컴포넌트 -->
      <div class="input-visibility">
        <p class="input-visibility__name">공개 여부</p>
        <div class="input-visibility__btns">
          <label class="input-visibility__label">
            <input
              class="input-visibility__btn"
              type="radio"
              v-model="memoir.visibility"
              name="visibility"
              label="공개"
              id="public"
              value="true"
              checked
            />
          </label>
          <label class="input-visibility__label">
            <input
              class="input-visibility__btn"
              label="비공개"
              type="radio"
              v-model="memoir.visibility"
              name="visibility"
              id="private"
              value="false"
            />
          </label>
        </div>
      </div>
    </section>
    <section class="register__input-container--last">
      <h3 class="none">회고록 생성 버튼</h3>
      <!-- 컴포넌트 -->
      <SubmitBtnFullBar :text="'회고록 생성하기'" @click="savePost" />
    </section>
  </main>

  <section class="nav-back-container">
    <h2 class="none">네비게이션 바 뒤 빈 공백</h2>
  </section>

  <aside>
    <section class="nav-container">
      <h2 class="none">네비게이션 바</h2>
      <NavigationBar :active="`home`" />
    </section>
  </aside>
</template>

<style scoped>
/*  ========================================== /
     FRAGMENT: 공개성 여부 체크
/   ========================================== */
.input-visibility {
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  justify-content: flex-start;
  width: 100%;
  height: 114px;
}

.input-visibility__name {
  color: var(--main1);
  font-size: 16px;
  font-weight: var(--bold);
  line-height: var(--line-height-main);
}

.input-visibility__btns {
  border-radius: 12px;
  padding: 8px 0;
  display: flex;
  flex-direction: row;
  gap: 10px;
  align-items: center;
  justify-content: flex-start;
  align-self: stretch;
}

.input-visibility__label {
  flex-grow: 1;
}

.input-visibility__btn {
  background: var(--main3);
  border-radius: 12px;
  color: var(--text-no-active);
  font-weight: var(--bold);
  font-size: 14px;
  border: none;
  padding: 0 46px 0 46px;
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: center;
  height: 56px;
  width: 100%;
  appearance: none;
  cursor: pointer;
}

.input-visibility__btn::after {
  content: attr(label);
}

.input-visibility__btn:checked {
  background: var(--main1);
  color: var(--white);
  box-shadow: 0 4px 4px 0 rgba(0, 0, 0, 0.25);
}
/*  ========================================== /
    FRAGMENT: 텍스트 입력 바
/   ========================================== */
.input-text__label {
  display: flex;
  gap: 8px;
  flex-direction: column;
  width: 100%;
  position: relative;
}

.input-text__label-text {
  font-weight: var(--semi-bold);
}

.input-text__input {
  width: auto;
  height: 56px;
  display: flex;
  border: none;
  border-radius: 12px;
  font-size: 16px;
  font-weight: var(--regular);
  color: var(--text-sub);
  padding-left: 16px;
  background-color: var(--main3, #e5eddb);
}

.input-text__input--warning {
  outline: 2px solid var(--warning);
}

.input-text__input:focus {
  outline: 2px solid var(--filter-checked);
}

.input-text__input:not(:placeholder-shown) {
  color: var(--text-main);
  outline: 2px solid var(--main1, #323a27);
}

.input-text__input-wrong {
  margin: 12px 0 0 12px;
  color: var(--warning, #ba0c0c);
}
.input-text__input:read-only {
  background-color: var(--main2, #e5eddb);
  outline: 3px solid var(--gray);
}
</style>
