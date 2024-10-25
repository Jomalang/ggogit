<script setup lang="ts">

import TagSearchOrRegisterBar from "@/components/input/TagSearchOrRegisterBar.vue";
import TopBarTagEdit from "@/components/top-bar/TopBarTagEdit.vue";
import TopBarTagInfo from "@/components/top-bar/TopBarTagInfo.vue";
import DefaultData from "@/components/input/DefaultData.vue";
import DeleteFormBtn from "@/components/input/DeleteFormBtn.vue";
import {onMounted, reactive, ref} from "vue";
import axios, {HttpStatusCode} from "axios";
import {useRoute} from "vue-router";

// ----------------------- Model --
const route = useRoute();
const tag = reactive({
  id: route.params.id,
  name: '',
});
let newTagName = '';
// ----------------------- Life Cycle ----------------------- //

onMounted(() => {
  tagDetailApi();
});

// ----------------------- API ----------------------- //

const tagDetailApi = async () => {
  try {
    const response = await axios.get(`http://localhost:8080/api/v1/tags/${tag.id}`);

    if (response.status !== HttpStatusCode.Ok) {
      throw new Error('태그 정보를 불러오는데 실패했습니다.');
    }

    tag.name = response.data.name;
    newTagName = tag.name;

  } catch (error) {
    console.error(error);
  }
};

const tagUpdateApi = async (newName: string) => {
  try { // TODO: 태규 진행 작업
        // 리프 태그 수정 진행중 여기 작업 해야함
    const response = await axios.put(`http://localhost:8080/api/v1/tags/${tag.id}`,
        {
          name: newName,
        }
    );

    if (response.status === HttpStatusCode.Conflict) {
      alert('이미 존재하는 태그 이름입니다.');
      return;
    }

    if (response.status !== HttpStatusCode.Ok) {
      throw new Error('태그 정보를 수정하는데 실패했습니다.');
    }

    alert('태그 정보가 수정되었습니다.');
    location.href = '/tag/list';
  } catch (error) {

    if (error.response.status === HttpStatusCode.Conflict) {
      alert('이미 존재하는 태그 이름입니다.');
      return;
    }

    console.error(error);
  }
};

const tagDeleteApi = async () => {
  try {

    const response = await axios.delete(`http://localhost:8080/api/v1/tags/${tag.id}`);

    console.log('response : ', response);
    if (response.status !== HttpStatusCode.NoContent) {
      throw new Error('태그 정보를 삭제하는데 실패했습니다.');
    }

    alert('태그 정보가 삭제되었습니다.');
    location.href = '/tag/list';
  } catch (error) {
    console.error(error);
  }
};

// ----------------------- Function ----------------------- //

const tagUpdateHandler = () => {

  if (newTagName === '') {
    alert('태그 이름을 입력해주세요.');
    return;
  }

  if (tag.name === newTagName) {
    alert('변경된 사항이 없습니다.');
    location.href = '/tag/list';
    return;
  }

  tagUpdateApi(newTagName);
};

const changeTagNameHandler = (tagName) => {
  newTagName = tagName;
  console.log('태그 이름 변경 : ', newTagName);
};

const tagDeleteHandler = () => {
  tagDeleteApi();
};


</script>

<template>
  <header>
    <h1 class="none">태그 수정 페이지</h1>

    <form action="/tag/edit" method="post">
      <section>
        <h2 class="none">뒤로가기 및 완료 바</h2>
        <TopBarTagEdit :tag="tag" @tagUpdate="tagUpdateHandler"></TopBarTagEdit>
      </section>

      <section class="none">
        <h2>태그 아이디</h2>
        <label><input type="text" name="id"/></label>
      </section>

      <section>
        <h2 class="none">태그 입력</h2>
        <TagSearchOrRegisterBar v-model:tag="tag" @tagSearch="changeTagNameHandler" ></TagSearchOrRegisterBar>
      </section>
    </form>
  </header>

  <main>
    <section>
      <h2 class="none">태그 기능 알림</h2>
      <TopBarTagInfo text="옵션 선택 또는 생성"></TopBarTagInfo>
    </section>

    <form id="tag-delete-form-id" action="/tag/delete" method="post">
      <section>
        <h1 class="none">삭제 데이터</h1>
        <DefaultData name="" values=""></DefaultData>
      </section>

      <section>
        <h1 class="none">삭제 버튼</h1>
        <DeleteFormBtn @tagDelete="tagDeleteHandler" ></DeleteFormBtn>
      </section>
    </form>
  </main>

</template>

<style scoped>

</style>