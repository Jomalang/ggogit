<script setup>
import TopBarBack from "@/components/top-bar/TopBarBack.vue";
import TextMainTitle from "@/components/text/TextMainTitle.vue";
import LinkOneImageDetail from "@/components/link/LinkOneImageDetail.vue";
import TextBookInfo from "@/components/text/TextBookInfo.vue";
import SubmitBtnFullBar from "@/components/button/SubmitBtnFullBar.vue";
import NavigationBar from "@/components/nav/NavigationBar.vue";

import { onBeforeMount, onMounted, ref } from "vue";
import Editor from "@toast-ui/editor";
//----------------variable----------------
const tmpSaveUrl = `${
  import.meta.env.VITE_API_BASE_URL
}/api/v1/memoir-image/upload-tmp`;

const tmpRenderUrl = `${
  import.meta.env.VITE_API_BASE_URL
}/api/v1/memoir-image/render-tmp`;

//----------------model---------------

const memoir = ref({
  title: "",
  text: "",
  visibility: 1,
});

let failname = ref({});

//----------------methods---------------

// const savePost = async () => {
//   try {
//     const response = await fetch("/api/v1/memoir", {
//       method: "POST",
//       headers: {
//         "Content-Type": "application/json",
//       },
//       body: JSON.stringify(memoir.value),
//     });
//     if (response.ok) {
//       alert("회고록이 성공적으로 등록되었습니다.");
//       location.href = "/app/memoir";
//     } else {
//       alert("회고록 등록에 실패하였습니다.");
//     }
//   } catch (error) {
//     console.error("회고록 등록 실패 : ", error);
//   }
// };

//----------------editor logic----------------
onMounted(() => {
  const editor = new Editor({
    el: document.querySelector("#editor"),
    height: "300px",
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
          const filename = await response.text();
          console.log("서버에 저장된 파일 명 : ", filename);

          // addImageBlobHook의 callback을 통해 디스크에 저장된 이미지 에디터에 렌더링
          const imageUrl = tmpRenderUrl + `/${filename}`;
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
  });
});

const updatePost = () => {
  console.log("updatePost");
  console.log(memoir.value);
};
</script>

<template>
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
      <TextMainTitle :title="`완독한 도서`" :size="28" />
    </section>
    <section class="tree-reg-cover-info__container" th:object="${treeInfo}">
      <h3 class="none">도서 커버 및 도서 정보</h3>
      <section class="tree-reg-cover__container">
        <h4 class="none">도서 커버</h4>
        <LinkOneImageDetail
          :src="`book/${bookImage}`"
          :href="'javascript:history.back()'"
        />
      </section>
      <section class="tree-reg-book-info__container">
        <h4 class="none">도서 정보</h4>
        <TextBookInfo
          :seed="bookCategory"
          :title="bookTitle"
          :author="bookAuthor"
          :translators="bookTranslator"
          :publisher="bookPublisher"
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
            v-model="title"
            :placeholder="'제목을 입력해 주세요.'"
          />
        </label>
      </div>
    </section>

    <section class="register__input-container">
      <h3 class="none">회고록 설명글</h3>
      <!--            toast 에디터2.0 -->
      <div id="editor"></div>
      <input class="hidden" id="editor-text" v-model="text" />
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
              v-model="visibility"
              name="visibility"
              label="공개"
              id="public"
              value="1"
              checked
            />
          </label>
          <label class="input-visibility__label">
            <input
              class="input-visibility__btn"
              label="비공개"
              type="radio"
              v-model="visibility"
              name="visibility"
              id="private"
              value="0"
            />
          </label>
        </div>
      </div>
    </section>
    <section class="register__input-container--last">
      <h3 class="none">회고록 생성 버튼</h3>
      <!-- 컴포넌트 -->
      <SubmitBtnFullBar :text="'회고록 생성하기'" @click="updatePost" />
    </section>
    <input class="hidden" id="fileNames" v-model="fileNames" />
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
</style>
