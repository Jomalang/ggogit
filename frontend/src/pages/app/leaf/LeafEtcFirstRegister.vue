<script setup lang="ts">

import TextMainTitleCenter from "@/components/text/TextMainTitleCenter.vue";
import TopBarBack from "@/components/top-bar/TopBarBack.vue";
import TagSelect from "@/components/input/TagSelect.vue";
import InputVisibility from "@/components/input/InputVisibility.vue";
import SubmitBtnFullBar from "@/components/button/SubmitBtnFullBar.vue";
import FirstLog from "@/components/log/FirstLog.vue";
import NavigationBar from "@/components/nav/NavigationBar.vue";
import TextBox from "@/components/input/TextBox.vue";

import Editor from '@toast-ui/editor';
import '@toast-ui/editor/dist/toastui-editor.css';
import {onMounted} from "vue";

onMounted(() => {
  const editor = new Editor({
    el: document.querySelector('#editor'),
    height: '300px',
    initialEditType: 'wysiwyg',
    previewStyle: 'vertical',
    placeholder: '무엇을 느끼셨나요?',
    usageStatistics: false,
    hooks: {
      async addImageBlobHook(blob, callback) {
        try {
          /**
           * 에디터에 업로드한 이미지를 FormData 객체에 저장
           */
          const formData = new FormData();
          formData.append('image', blob);

          // MemoirFileApiController - uploadEditorImage 메서드 호출
          const response = await fetch('/api/v1/leaf/image-upload', {
            method : 'POST',
            body : formData,
          });
          // 컨트롤러에서 전달받은 디스크에 저장된 파일 명
          const filename = await response.text();
          console.log('서버에 저장된 파일 명 : ', filename);

          // addImageBlobHook의 callback을 통해 디스크에 저장된 이미지 에디터에 렌더링
          const imageUrl = `/api/v1/leaf/image-print?filename=${filename}`;
          callback(imageUrl, 'image alt attribute');
          console.log(blob);
          console.log(callback);
        } catch (error){
          console.log("업로드 실패 : ", error);
        }
      }
    }
  });

  editor.on('change', () => {
    document.querySelector('#editor-text').textContent = editor.getMarkdown();
  });
});

</script>

<template>
  <header>
    <h1 class="none">리프 생성 페이지</h1>
    <section class="tob-bar-back-container">
      <h1 class="none">리프 생성 상단 바</h1>
      <TopBarBack title="리프 생성" link=""></TopBarBack>
    </section>
  </header>

  <main>
    <section class="first-log-img-container">
      <h1 class="none">이전 리프 생성 이미지</h1>
      <FirstLog></FirstLog>
    </section>

    <section class="first-log-title-container">
      <h1 class="none"></h1>
      <TextMainTitleCenter title="트리 첫번째 기록" size="28"></TextMainTitleCenter>
    </section>

    <form action="/leaf/first/reg" method="post" class="input-form" id="input-leaf-register-form-id">
      <section>
        <h1 class="none">리프 생성 데이터 입력</h1>

        <section class="none">
          <h1>씨앗 데이터 타입</h1>
          <label><input type="number" name="seedId" value="1"></label>
        </section>

        <section class="input-form__select-tag-input-container">
          <h1 class="none">리프 태그 입력</h1>
          <TagSelect member-id="1"></TagSelect>
        </section>

        <section class="input-form__input-container">
          <h1 class="none">로그이름 입력</h1>
          <TextBox label="*제목" name="title" placeholder="리프 제목을 입려해 주세요."></TextBox>
        </section>

        <section class="book-tree-input-form__large-input-container">
          <h1 class="none">토스트 에디터</h1>
          <div id="editor"></div>
        </section>

        <section class="book-tree-input-form__large-input-container none">
          <h1 class="none">리프 내용 입력</h1>
          <label>
            <textarea class="hidden" id="editor-text" name="content" ></textarea>
          </label>
        </section>

        <section class="input-form__input-container">
          <h1 class="none">공개성 선택</h1>
          <InputVisibility name="visibility"></InputVisibility>
        </section>

        <section class="book-tree-submit-container">
          <h1 class="none">리프 생성 버튼</h1>
          <SubmitBtnFullBar text="리프 생성"></SubmitBtnFullBar>
        </section>
      </section>
    </form>

  </main>

  <aside class="nav-container">
    <h1 class="none">네비게이션 하단</h1>
    <NavigationBar active="home"></NavigationBar>
  </aside>
</template>

<style scoped>

</style>