<script setup>
import { defineProps } from "vue";
import CardProgressBar from "~/components/card/CardProgressBar.vue";
import CardReactNumbers from "~/components/card/CardReactNumbers.vue";
import LinkFullWidth from "~/components/button/LinkFullWidth.vue";
const hidden = ref(false);
const { data } = defineProps(["data"]);
const progress = ((data.readingPage * 100) / data.bookTotalPage).toFixed(2);

function translatorsConverter(translators) {
  if (!translators) {
    return [];
  }
  return translators.split(",");
}

let translators = translatorsConverter(data.bookTranslator ?? "");

const textBookInfoProps = {
  title: data.bookTitle,
  authors: data.bookAuthor,
  translators: translators,
  publisher: data.bookPublisher,
  page: data.bookTotalPage,
  seed: data.seedId,
};

const cardProgressBarProps = {
  progress: progress,
  readPage: data.readingPage,
  fullPage: data.bookTotalPage,
};

const cardReactNumbersProps = {
  leaf: data.treeLeafCnt,
  like: data.treeLikeCnt,
  view: data.treeViewCnt,
};
onMounted(() => {});

function hiddenText() {
  const detailHidden = document.getElementById("detail-hidden");

  if (!hidden.value) {
    hidden.value = true;
    detailHidden.classList.add("card-tree-info__detail-content");
    console.log(detailHidden.classList);
  } else {
    hidden.value = false;
    detailHidden.classList.remove("card-tree-info__detail-content");
  }
}
</script>

<template>
  <!-- (hiddentext, authors,translators,publisher,page,seed, treedescription, progress,readpage,fullpage, leaf,like,view) -->
  <client-only>
    <div class="card-tree-info__detail-frame">
      <div class="card-tree-info__detail-visibility">
        <NuxtLink
          :to="`/tree/${data.treeId}/edit`"
          class="top-bar__transparent-setting-btn"
        ></NuxtLink>
        <input
          class="card-tree-info__detail-input"
          type="checkbox"
          id="card-tree-info__detail"
        />
        <div @click.prevent="hiddenText">
          <label class="card-tree-info__detail" for="card-tree-info__detail">
            자세히
          </label>
        </div>
      </div>
      <section id="detail-hidden" class="none">
        <h3 class="none">트리 상세 설명 보기</h3>

        <section class="card-tree-info__detail-tree-container">
          <h4 class="none">트리 정보</h4>
          <TextBookInfoNoTitle :data="textBookInfoProps" />
        </section>

        <section class="card-tree__description-title__container">
          <p class="card-tree__description-content">{{ data.description }}</p>
        </section>

        <section
          v-if="data.bookTotalPage != null"
          class="card-progress__container"
        >
          <div class="card-reading-progress-no-background">
            <CardProgressBar
              :progress="progress"
              :readingPage="data.readingPage"
              :totalPage="data.bookTotalPage"
            />
            <CardReactNumbers
              :leaf="data.treeLeafCnt"
              :like="data.treeLikeCnt"
              :view="data.treeViewCnt"
            />
          </div>
        </section>

        <section
          v-if="
            progress >= 80 && data.treeLeafCnt >= 1 && data.memoirId === null
          "
          class="card-tree-memoir-create-btn-container"
        >
          <LinkFullWidth
            :link="'/memoir/' + data.treeId + '/reg'"
            text="회고록 작성"
          />
        </section>
        <section
          v-if="data.memoirId"
          class="card-tree-memoir-create-btn-container"
        >
          <LinkFullWidth
            :link="'/memoir/' + data.memoirId"
            text="회고록 보기"
          />
        </section>
      </section>
    </div>
  </client-only>
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
  padding-left: 5px;
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
  justify-content: space-between;
  align-items: center;
}
.card-tree-info__detail-visibility {
  width: 100%;
  display: flex;
  justify-content: space-between;
  align-items: center;
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
  font-size: 12px;
  cursor: pointer;
}

.card-tree-info__detail::after {
  width: 7px;
  height: 7px;
  content: "";
  display: inline-block;
  align-items: center;
  background-image: url("/png/hidden-on.png");
  background-repeat: no-repeat;
  background-position: center;
  background-size: contain;
}

.card-tree-info__detail-input:checked + .card-tree-info__detail::after {
  width: 7px;
  height: 7px;
  content: "";
  align-items: center;
  display: inline-block;
  background-image: url("/png/hidden-off.png");
  background-position: center;
  background-size: contain;
}

.card-tree-info__detail-content {
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
.top-bar__transparent-setting-btn {
  margin-left: 70px;
  width: 20px;
  height: 20px;
  display: flex;
  background: transparent url("/png/edit-dark.png") no-repeat center;
  background-size: contain;
  align-items: center;
}
.card-tree__description-content {
  font-size: 14px;
  font-weight: var(--regular);
  color: var(--text-sub);
  line-height: var(--line-height-sub);
  letter-spacing: var(--letter-spacing-sub);
}
</style>
