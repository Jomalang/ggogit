<script setup lang="ts">

import { defineProps } from 'vue';
import CardProgressBar from "~/components/card/CardProgressBar.vue";
import CardReactNumbers from "~/components/card/CardReactNumbers.vue";
import LinkFullWidth from "~/components/button/LinkFullWidth.vue";
import TextBookInfo from "~/components/text/TextBookInfo.vue";



const dataLoaded = ref(false);
const props = defineProps<{
  item: {
    hiddenText: string;
    authors?: string | null;
    translators?: string | null;
    publisher?: string | null;
    page?: number | null;
    seedId: number;
    treeDescription: string;
    readPage?: number | null;
    progress?: number | null;
    fullPage?: number | null;
    leaf: number;
    like: number;
    view: number;
  };
}>();

function isDataLoaded() {
  dataLoaded.value = true;
}

function translatorsConverter(translators: string | null): Array<string> {
  if (translators == null || translators === "") {
    return [];
  }
  return translators.split(",");
};
interface DataItem {
  hiddenText: string;
  authors: string;
  translators: string[];
  publisher: string;
  page: number | null;
  seedId: number;
  treeDescription: string;
  readPage: number | null;
  progress: number | null;
  fullPage: number | null;
  leaf: number;
  like: number;
  view: number;
}
const data = reactive<DataItem>({
  hiddenText: '자세히',
  authors: '',
  translators: [],
  publisher: '',
  page: null,
  seedId: 0,
  treeDescription: '',
  readPage: null,
  progress: null,
  fullPage: null,
  leaf: 0,
  like: 0,
  view: 0
});
function updateData(props) {
  data.hiddenText = props.item.hiddenText;
  data.authors = props.item.authors;
  data.translators = translatorsConverter(props.item.translators ?? "");
  data.publisher = props.item.publisher;
  data.page = props.item.page;
  data.seedId = props.item.seedId;
  data.treeDescription = props.item.treeDescription;
  data.readPage = props.item.readPage;
  data.progress = props.item.progress;
  data.fullPage = props.item.fullPage;
  data.leaf = props.item.leaf;
  data.like = props.item.like;
  data.view = props.item.view;

  isDataLoaded(); // 데이터 로드 완료 후 호출

}
let translators:Array<string> =translatorsConverter(props.item.translators ?? "");

const cardProgressBarProps = {
  progress: props.item.progress,
  readPage: props.item.readPage,
  fullPage: props.item.fullPage,
};

const cardReactNumbersProps = {
  leaf: props.item.leaf,
  like: props.item.like,
  view: props.item.view,
};

onMounted(async () => {
  await updateData(props);
});
</script>

<template>
  <!-- (hiddentext, authors,translators,publisher,page,seed, treedescription, progress,readpage,fullpage, leaf,like,view) -->
  <div class="card-tree-info__detail-frame">
    <input class="card-tree-info__detail-input" type="checkbox" id="card-tree-info__detail"/>
    <label class="card-tree-info__detail" for="card-tree-info__detail">{{data.hiddenText}}</label>

    <section class="card-tree-info__detail-content">
      <h3 class="none">트리 상세 설명 보기</h3>

      <section class="card-tree-info__detail-tree-container">
        <h4 class="none">트리 정보</h4>
        <div v-if="dataLoaded">
        <TextBookInfo
            :title="data.title"
            :authors="data.authors"
            :translators="data.translators"
            :publisher="data.publisher"
            :page="data.page"
            :seed="data.seedId" />
        </div>
        <div v-else>
          <p>로딩중...</p>
        </div>
        <!-- <div th:replace="~{fragments/text :: text-book-info&#45;&#45;no-title(${authors},${translators},${publisher},${page},${seed})}"></div> -->
      </section>

      <section class="card-tree__description-title__container">
        <!-- <h3 th:replace="~{fragments/text :: text-main-title(title='트리설명', size=18)}"></h3> -->
        <p class="card-tree__description-content">{{ data.treeDescription }}</p>
      </section>

      <section
          v-if="data.fullPage != null"
          class="card-progress__container" >
        <div class="card-reading-progress-no-background">
          <CardProgressBar :data="cardProgressBarProps" />
          <CardReactNumbers :data="cardReactNumbersProps" />
        </div>
      </section>

      <section class="card-tree-memoir-create-btn-container none">
        <LinkFullWidth link="#" text="회고록 작성" />
      </section>

    </section>
  </div>
</template>

<style scoped>

.card-tree-info-cover-frame {
  display: flex;
  padding-left: 64px;
  height: 70px;
  align-items: flex-end;
  position: relative;
}

.card-tree-info-cover {
  width: 60px;
  height: 90px;
  background-color: var(--main1);
  border-radius: 8px;
  position: absolute;
  top: 0;
  left: 0;
}

.card-tree-title {
  font-size: 16px;
  font-weight: var(--bold);
  line-height: var(--line-height-main);
  letter-spacing: var(--letter-spacing-main);
}
.card-tree-title-frame {
  padding-left : 5px;
}

.card-book-title {
  font-size: 14px;
  font-weight: var(--medium);
  line-height: var(--line-height-main);
  letter-spacing: var(--letter-spacing-main);
}

.card-tree-info__detail-frame {
  width: 100%;
  display: flex;
  flex-direction: column;
}

.card-tree-info__detail-input {
  display: none;
  cursor: none;
}

.card-tree-info__detail {
  padding-top: 7px;
  padding-bottom: 10px;
  margin-left: auto;
  content: "자세히";
  font-size: 10px;
  cursor: pointer;
}

.card-tree-info__detail::after {
  width: 7px;
  height: 7px;
  content: "";
  display: inline-block;
  align-items: center;
  background-image: url("/svg/hidden-on.svg");
  background-repeat: no-repeat;
  background-position: center;
  background-size: contain;
}

.card-tree-info__detail-content {
  width: 100%;
  display: none;
}

.card-tree-info__detail-input:checked + .card-tree-info__detail::after {
  width: 7px;
  height: 7px;
  content: "";
  align-items: center;
  display: inline-block;
  background-image: url("/svg/hidden-off.svg");
  background-position: center;
  background-size: contain;
}

.card-tree-info__detail-input:checked ~ .card-tree-info__detail-content {
  width: 100%;
  display: flex;
  flex-direction: column;
}

.text-book-info--no-title {
  width: 100%;
  display: flex;
  flex-direction: column;
  gap: 4px;
}

.text-book-info--no-title__creaters {
  display: flex;
}

.text-book-info--no-title__page {
  font-size: 14px;
  font-weight: var(--regular);
  color: var(--text-sub);
  line-height: var(--line-height-main);
  letter-spacing: var(--letter-spacing-main);
}

.text-book-info--no-title__seed {
  display: inline-block;
  padding: 2px 8px;
  font-size: 10px;
  font-weight: var(--medium);
  color: var(--white);
  line-height: var(--line-height-main);
  letter-spacing: var(--letter-spacing-main);
  border-radius: 4px;
  background: var(--main1);
}

.card-tree__description-content {
  font-size: 14px;
  font-weight: var(--regular);
  color: var(--text-sub);
  line-height: var(--line-height-sub);
  letter-spacing: var(--letter-spacing-sub);
}

</style>