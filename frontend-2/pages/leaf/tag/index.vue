<script setup>
import { onMounted, reactive, watch } from "vue";
import useLeafFormData from "~/composables/useLeafFormData.js";

// ----------------------- Model ----------------------- //

const config = useRuntimeConfig();
const backLink = useLeafFormData().getCreateUrl();
const tagData = useLeafTagList();

const searchValue = reactive({
  name: "",
});

// ----------------------- Life Cycle ----------------------- //
onMounted(() => {
  tagListApi();
});

// ----------------------- API ----------------------- //
const tagListApi = async () => {

  try {
    const response = await useAuthDataFetch("tags", {
      baseURL: `${config.public.apiBase}`,
      method: "GET",
      params: {
        s: searchValue.name
      }
    })

    if (!response !== '태그 목록 조회 성공') {
      console.log("태그 목록 조회 실패 : ");
    }

    console.log('response', response);
    tagData.initTags(response.tags); // 태그 데이터 초기화

  } catch (error) {
    console.log("태그 리스트 조회 실패 : ", error);
  }
};

const tagCreateApi = async (tagName) => {

  try {

    // console.log(tagName);
    const response = await useAuthDataFetch("tags", {
      baseURL: `${config.public.apiBase}`,
      method: "POST",
      body: {
        name: tagName
      }
    })

    // console.log(response);
    if (response.message !== '태그 생성 성공') {
      alert("태그 생성에 실패하였습니다.");
    }

    return response.id;
  } catch (error) {
    throw new Error("태그 생성 실패 : " + error);
  }
};

// ----------------------- Function ----------------------- //
const tagSelectedHandler = (tag) => {

  if (!tagData.canSelectTag(tag)) {
    alert("태그는 3개까지 선택 가능합니다.");
    return;
  }

  tagData.selectTag(tag)
};

const tagUnSelectedHandler = (tag) => {
  // 선택 해제된 태그 리스트에 추가
  tagData.deselectTag(tag);
};

const handleTagSearch = (value) => {
  searchValue.name = value;
  tagListApi();
};

const tagCreateHandler = async (name) => {
  const id = await tagCreateApi(name);

  const newTag = {
    id: id,
    name: name,
  };

  // 생성한 태그 선택 리스트에 태그가 3개 미만이면 추가
  if (tagData.canSelectTag(newTag)) {
    tagData.selectTag(newTag);
    return;
  }

  // 3개 이상이면 리스트에 추가
  tagData.addTag(newTag);
};

// ----------------------- Function ----------------------- //
</script>

<template>

  <header>
    <h1 class="none">태그 이름</h1>
    <TopBarBack title="태그 이름" :link="backLink"></TopBarBack>
  </header>
  <main>
    <section>
      <h2 class="none">태그 검색</h2>
      <InputTagSearchOrRegisterBar
          @tagSearch="handleTagSearch"
          tag="null"
      ></InputTagSearchOrRegisterBar>
    </section>

    <section>
      <h2 class="none">태그 선택 태그</h2>
      <TagSelectedList
          :tags="tagData.getSelectedTags()"
          @tagUnSelected="tagUnSelectedHandler"
      ></TagSelectedList>
    </section>

    <section>
      <h2 class="none">태그 기능 알림</h2>
      <TopBarTagInfo text="옵션 선택 또는 생성"></TopBarTagInfo>
    </section>

    <section>
      <h2 class="none">태그 리스트</h2>
      <TagListBox
          :tags="tagData.getTags()"
          :createTag="searchValue.name"
          :selected-tag="tagData.getSelectedTags()"
          @tagSelected="tagSelectedHandler"
          @tagCreate="tagCreateHandler"
      >
      </TagListBox>
    </section>
  </main>
</template>

<style scoped></style>
