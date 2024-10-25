<script setup lang="ts">

import {CardType} from "@/types/types";
import {CardItemProps} from "@/types/types";

// const item: CardItemProps = {
//   cardType: CardType.LEAF,
//   bookCategory: '도서 카테고리',
//   bookTitle: '도서 제목',
//   title: '제목입니다.',
//   treeTitle: '트리 제목입니다.',
//   memoirTitle: '회고록 제목입니다.',
//   bookPublishedYear: '2024',
//   bookAuthor: '유시민',
//   bookPublisher: '프리미엄',
//   leafTitle: '웹툰이지만 로깅합니다',
//   leafContent: '코딩 테스트 합격자 되기 자바스크립트 편의 영감 기록을 남기는 이유는 다음과 같습니다. 영감 기록을 작성하면 책에서 배운 내용을 정리하고 이해도를 높일 수 있습니다.',
//   leafCount: 1000,
//   viewCount: 1000,
//   updateTime: "2024-07-11",
// };

const props = defineProps<{
  item: CardItemProps;
}>();

function modifyCount(count) {
  return count > 999 ? '999+' : String(count);
}

</script>

<template>
  <!-- (item, type) -->
  <div class="card-another-records-box">
    <div class="card-another-records__top-box">
      <a class="card-another-records__top-anker" href="">

        <!-- 표지 -->
        <div v-if="item.cardType === CardType.TREE">
          <img class="card-another-records__top-cover-box" src="/public/svg/card-book__info-cover.svg" alt="트리 이미지">
        </div>
        <div v-else-if="item.cardType === CardType.MEMOIR">
          <img class="card-another-records__top-cover-box" src="/public/svg/card-book__log-info-cover.svg" alt="회고록 아이콘">
        </div>
        <div v-else-if="item.cardType === CardType.LEAF">
          <img class="card-another-records__top-cover-box" src="/public/svg/card-book__log-info-cover.svg" alt="리프 아이콘">
        </div>

        <div class="card-another-records__mid-box">

          <div class="card-another-records__mid-seed-frame">
            <span class="card-another-records__mid-seed">{{ item.bookCategory }}</span>
          </div>

          <p class="card-another-records__mid-another--title" v-if="item.cardType === CardType.TREE">{{ item.treeTitle}}</p>
          <p class="card-another-records__mid-another--title" v-else-if="item.cardType === CardType.MEMOIR">{{ item.memoirTitle }}</p>
          <p class="card-another-records__mid-another--title--sub" v-else-if="item.cardType === CardType.LEAF">{{ item.treeTitle }}</p>
          <p class="card-another-records__mid-work-title">{{ item.bookTitle }}</p>

          <div class="card-another-records__info">
            <span class="card-another-records__info">{{ item.bookPublishedYear }}</span>
            <span class="card-another-records__info"> / </span>
            <span class="card-another-records__info">{{ item.bookAuthor }}</span>
            <span class="card-another-records__info"> / </span>
            <span class="card-another-records__info">{{ item.bookPublisher }}</span>
          </div>
        </div>

      </a>
      <div class="card-another-records__icon-box">
        <div class="card-another-records__share-icon-box">
          <img src="/public/svg/comment.svg" alt="댓글 아이콘">
        </div>
        <div class="card-another-records__like-icon-box">
          <input class="card-another-records__like-bold-input" type="checkbox" id="like"/>
          <label class="card-another-records__like-bold" for="like"></label>
        </div>
      </div>
    </div>

    <div class="card-another-records__bot-box">
      <div class="card-another-records__bot-nickname-box" v-if="item.cardType === CardType.LEAF">
        <a href="">
          <div class="card-another-records__log-text-box">
            <q class="card-another-records-log-title">{{ item.leafTitle }}</q>
            <q class="card-another-records-log-content">{{ item.leafContent }}</q>
          </div>
        </a>
      </div>
      <div class="card-another-records__bot-box-etc-info">
        <div class="card-another-records__bot-box-etc-date" >{{ item.updateTime }}</div>
        <div class="card-another-records__bot-statistics-box">
          <a href="">
            <p v-if="item.cardType === CardType.TREE" class="card-another-records__bot-leaf-text">
              <span class="card-another-records__bot-leaf-count">{{ modifyCount(item.leafCount) }}</span> 리프
            </p>
            <p v-if="item.cardType === CardType.MEMOIR" class="card-another-records__bot-leaf-text">
              <span class="card-another-records__bot-leaf-count">{{ modifyCount(item.leafCount) }}</span> 리프
            </p>
            <p class="card-another-records__bot-view-text">
              <span class="card-another-records__bot-view-count">{{ modifyCount(item.viewCount) }}</span> 조회수
            </p>
          </a>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
.card-another-records-box {
  margin-right: 30px;
  width: 332px;
  border-radius: 16px;
  background: var(--white);
  padding: 16px;
  box-shadow: var(--shadow-active);
}

.card-another-records__top-anker {
  display: flex;
  flex: 1 0 auto;
  gap: 8px;
}

.card-another-records__top-box {
  margin-bottom: 12px;
  width: 100%;
  height: 120px;
  display: flex;
  justify-content: space-around;
}

.card-another-records__top-cover-box {
  width: 80px;
  height: 120px;
  object-fit: cover;
  display: flex;
  justify-content: center;
  align-items: center;
  border-radius: 10px;
}

.card-another-records__mid-box {
  display: flex;
  flex-direction: column;
  align-items: flex-start;
}

.card-another-records__mid-seed-frame {
  display: flex;
  gap: 4px;
}

.card-another-records__mid-seed {
  color: var(--text-sub);
  font-size: 10px;
  font-weight: var(--medium);
  line-height: var(--line-height-sub);
  letter-spacing: var(--letter-spacing-sub);
}

.card-another-records__mid-another--title {
  color: var(--text-main);
  font-size: 18px;
  font-weight: var(--bold);
  line-height: var(--line-height-main);
  letter-spacing: var(--letter-spacing-main);
}

.card-another-records__mid-another--title--sub {
  color: var(--text-sub);
  font-size: 18px;
  font-weight: var(--bold);
  line-height: var(--line-height-main);
  letter-spacing: var(--letter-spacing-main);
}

.card-another-records__mid-work-title {
  color: var(--text-sub);
  font-size: 14px;
  font-weight: var(--medium);
  line-height: var(--line-height-sub);
  letter-spacing: var(--letter-spacing-sub);
}

.card-another-records__info {
  color: var(--text-sub);
  font-size: 14px;
  font-weight: var(--medium);
  line-height: var(--line-height-sub);
  letter-spacing: var(--letter-spacing-sub);
}

.card-another-records__icon-box {
  height: 21px;
  display: flex;
  justify-content: space-between;
  align-items: center;
  gap: 8px;
}
.card-another-records__like-icon-box {
  width: 21px;
  height: 21px;
}

.card-another-records__like-bold-input {
  display: none;
  cursor: none;
}

.card-another-records__like-bold {
  cursor: pointer;
}

.card-another-records__like-bold::after {
  width: 21px;
  height: 21px;
  content: "";
  display: flex;
  align-items: center;
  background-image: url("/svg/like.svg");
  background-repeat: no-repeat;
  background-position: center;
  background-size: contain;
}

.card-another-records__like-bold-content {
  width: 100%;
  display: none;
}

.card-another-records__like-bold-input:checked
+ .card-another-records__like-bold::after {
  width: 21px;
  height: 21px;
  content: "";
  display: flex;
  align-items: center;
  background-image: url("/svg/like-fill.svg");
  background-repeat: no-repeat;
  background-position: center;
  background-size: contain;
}

.card-another-records__bot-box {
  display: flex;
  flex-direction: column;
  align-items: flex-start;
}

.card-another-records-log-title {
  color: var(--text-main);
  font-size: 18px;
  font-weight: var(--bold);
  line-height: var(--line-height-main);
  letter-spacing: var(--letter-spacing-main);
}

.card-another-records-log-content {
  padding-top: 10px;
  color: var(--text-sub);
  font-size: 14px;
  font-weight: var(--regular);
  line-height: var(--line-height-sub);
  letter-spacing: var(--letter-spacing-sub);
}

.card-another-records__bot-box-etc-info {
  width: 100%;
  margin-top: 10px;
  display: flex;
  justify-content: space-between;
}

.card-another-records__bot-box-etc-date {
  display: flex;
  color: var(--text-sub);
  align-items: flex-end;
  font-size: 14px;
  font-weight: var(--medium);
  line-height: var(--line-height-sub);
  letter-spacing: var(--letter-spacing-sub);
}

.card-another-records__bot-statistics-box {
  display: flex;
}

.card-another-records__bot-leaf-text,
.card-another-records__bot-view-text {
  color: var(--text-sub);
  font-size: 8px;
  font-weight: var(--regular);
  line-height: var(--line-height-sub);
  letter-spacing: var(--letter-spacing-sub);
}

.card-another-records__bot-leaf-count,
.card-another-records__bot-view-count {
  color: var(--text-main);
  font-size: 12px;
  font-weight: var(--medium);
  line-height: var(--line-height-main);
  letter-spacing: var(--letter-spacing-main);
}

.card-another-records__log-text-box {
  display: flex;
  flex-direction: column;
  align-items: flex-start;
}

.text-memoir-content {
  color: var(--text-main);
  font-size: 14px;
  font-weight: var(--regular);
  line-height: var(--line-height-high);
  letter-spacing: var(--letter-spacing-main);
}
</style>