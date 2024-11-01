<script setup>
import { onMounted, reactive, watch } from "vue";
import axios, { HttpStatusCode } from "axios";
import {useRoute} from "vue-router";

// ----------------------- Model ----------------------- //

const config = useRuntimeConfig();
const backLink = useState("leafCreateUrl");
const selectedTags = useState('selectedTags');

const tags = reactive({
  items: [],
});

const searchValue = reactive({
  name: "",
});

watch(
    [tags, selectedTags.value, searchValue],
    [
      (newVal) => {},
      (newVal) => {
        if (selectedTags.value.items.length === 0) {
          localStorage.setItem("selectedTags", JSON.stringify([]));
        } else {
          localStorage.setItem(
              "selectedTags",
              JSON.stringify(selectedTags.value.items)
          );
        }
      },
      (newVal) => {},
    ]
);

// ----------------------- Life Cycle ----------------------- //
onMounted(() => {
  tagListApi();
});

// ----------------------- API ----------------------- //
const tagListApi = async () => {

  try {
    const response = await axios.get(`${config.public.apiBase}/tags?s=${searchValue.name}`);

    if (response.status !== HttpStatusCode.Ok) {
      console.log("태그 리스트 조회 실패 : ", response);
    }

    // 선택 되어있는 태그 제외
    tags.items = response.data.tags.filter((tag) => {
      for (let i = 0; i < selectedTags.value.items.length; i++) {
        if (tag.id === selectedTags.value.items[i].id) { return false; }
      }
      return true;
    });

  } catch (error) {
    console.log("태그 리스트 조회 실패 : ", error);
  }
};

const tagCreateApi = async (tagName) => {
  try {
    const response = await axios.post(`http://localhost:8080/api/v1/tags`, {
      name: tagName,
    });

    if (response.status !== HttpStatusCode.Created) {
      alert("태그 생성에 실패하였습니다.");
    }

    return response.data;
  } catch (error) {
    throw new Error("태그 생성 실패 : " + error);
  }
};

// ----------------------- Function ----------------------- //
const tagSelectedHandler = (tag) => {
  // 3개 이상 선택 불가
  if (3 <= selectedTags.value.items.length) {
    alert("태그는 3개까지 선택 가능합니다.");
    return;
  }

  // 선택된 태그 리스트에 추가
  selectedTags.value.items.push(tag);

  // 선택 하면 리스트에서 제거
  tags.items = tags.items.filter((item) => item.id !== tag.id);
};

const tagUnSelectedHandler = (tag) => {
  // 선택 해제된 태그 리스트에 추가
  tags.items.push(tag);

  // 선택 해제 하면 리스트에서 제거
  selectedTags.value.items = selectedTags.value.items.filter((item) => item.id !== tag.id);

  // 로컬 스토리지에서 제거
  localStorage.setItem("selectedTags", JSON.stringify(selectedTags.value.items));
};

const handleTagSearch = (value) => {
  searchValue.name = value;
  tagListApi();
};

const tagCreateHandler = async (name) => {
  const data = await tagCreateApi(name);

  const newTag = {
    id: data.id,
    name: name,
  };

  // 생성한 태그 선택 리스트에 태그가 3개 미만이면 추가
  if (selectedTags.value.items.length < 3) {
    selectedTags.value.items.push(newTag);
    return;
  }

  // 3개 이상이면 리스트에 추가
  tags.items.push(newTag);
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
          :tags="selectedTags.items"
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
          :tags="tags.items"
          :createTag="searchValue.name"
          :selected-tag="selectedTags.items"
          @tagSelected="tagSelectedHandler"
          @tagCreate="tagCreateHandler"
      >
      </TagListBox>
    </section>
  </main>
</template>

<style scoped></style>
