<script setup>
import axios, { HttpStatusCode } from "axios";
import { useRoute } from "vue-router";

// ----------------------- Model --
const config = useRuntimeConfig();
const router = useRouter();
const route = useRoute();
const tag = reactive({
  id: route.params.id,
  name: "",
});
let newTagName = "";
// ----------------------- Life Cycle ----------------------- //

onMounted(() => {
  tagDetailApi();
});

// ----------------------- API ----------------------- //

const tagDetailApi = async () => {
  try {

    const response = await useAuthDataFetch(`tags/${tag.id}`, {
      baseURL: `${config.public.apiBase}`,
      method: "GET"
    })

    tag.name = response.name;
    newTagName = tag.name;
  } catch (error) {
    console.error(error);
  }
};

const tagUpdateApi = async (newName) => {
  try {

    const response = await useAuthDataFetch(`tags/${tag.id}`, {
      baseURL: `${config.public.apiBase}`,
      method: "PUT",
      body: {
        name: newName
      }
    });

    if (response.statusCode === HttpStatusCode.Conflict) {
      alert("이미 존재하는 태그 이름입니다.");
      return;
    }

    if (response.statusCode !== HttpStatusCode.Ok) {
      throw new Error("태그 정보를 수정하는데 실패했습니다.");
    }

    alert("태그 정보가 수정되었습니다.");
    router.push("/leaf/tag");
  } catch (error) {
    if (error.response.status === HttpStatusCode.Conflict) {
      alert("이미 존재하는 태그 이름입니다.");
      return;
    }

    console.error(error);
  }
};

const tagDeleteApi = async () => {
  try {

    const response = await useAuthDataFetch(`tags/${tag.id}`, {
      baseURL: `${config.public.apiBase}`,
      method: "DELETE"
    });

    alert("태그 정보가 삭제되었습니다.");
    router.push("/leaf/tag");
  } catch (error) {
    console.error(error);
  }
};

// ----------------------- Function ----------------------- //

const tagUpdateHandler = () => {

  if (newTagName === "") {
    alert("태그 이름을 입력해주세요.");
    return;
  }

  if (tag.name === newTagName) {
    alert("변경된 사항이 없습니다.");
    router.push("/leaf/tag");
    return;
  }

  tagUpdateApi(newTagName);
};

const changeTagNameHandler = (tagName) => {
  newTagName = tagName;
  // console.log("태그 이름 변경 : ", newTagName);
};

const tagDeleteHandler = () => {
  tagDeleteApi();
};

</script>

<template>
    <header>
      <h1 class="none">태그 수정 페이지</h1>

      <form action="/leaf/tag/edit" method="post">
        <section>
          <h2 class="none">뒤로가기 및 완료 바</h2>
          <TopBarTagEdit :tag="tag" @update="tagUpdateHandler"></TopBarTagEdit>
        </section>

        <section class="none">
          <h2>태그 아이디</h2>
          <label><input type="text" name="id" /></label>
        </section>

        <section>
          <h2 class="none">태그 입력</h2>
          <InputTagSearchOrRegisterBar
              v-model:tag="tag"
              @tagSearch="changeTagNameHandler">
          </InputTagSearchOrRegisterBar>
        </section>
      </form>
    </header>

    <main>
      <section>
        <h2 class="none">태그 기능 알림</h2>
        <TopBarTagInfo text="옵션 선택 또는 생성"></TopBarTagInfo>
      </section>

      <form id="tag-delete-form-id" action="/leaf/tag/delete" method="post">
        <section>
          <h1 class="none">삭제 데이터</h1>
          <InputDefaultData name="" values=""></InputDefaultData>
        </section>

        <section>
          <h1 class="none">삭제 버튼</h1>
          <InputDeleteFormBtn @tagDelete="tagDeleteHandler"></InputDeleteFormBtn>
        </section>
      </form>
    </main>

    <!-- 삭제 안내 버튼 -->
    <section class="info-pop-up">
      <h1 class="none">삭제 안내</h1>
      <InfoPopUp :data="{
      title: '태그 삭제',
      content: '태그를 삭제하시겠습니까?',
      confirm: '삭제',
      cancel: '취소'
    }" />
    </section>

</template>

<style scoped>
</style>
