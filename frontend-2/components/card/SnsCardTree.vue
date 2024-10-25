<script setup lang="ts">

import {CardType, SnsCardTreeProps} from "@/types/types";

// const data: SnsCardTreeProps = {
//   cardType: CardType.TREE,
//   treeTitle: '트리제목트리제목트리제목트리제목트리제목',
//   treeDate: '2024-08-18',
//   memoirText: '코딩 테스트 합격자 되기 자바스크립트 편의 영감 기록을 남기는 이유는 다음과 같습니다. 영감 기록을 작성하면 책에서 배운 내용을 정리하고 이해도를 높일 수 있습니다.',
//   nickname: '한태규짱',
//   nicknameId: '@gksxorb147',
//   leafCount: 1000,
//   viewCount: 998
// };

const props = defineProps<{
  data: SnsCardTreeProps;
}>();

function modifyCount(count) {
  return 999 < count ? '999+' : String(count);
}

</script>

<template>
  <!-- th:fragment="sns-card-tree(type) -->
  <div class="sns-card-tree-box">
    <div class="sns-card-tree__top-box">
      <a href="">
        <div v-if="data.cardType === CardType.TREE" class="sns-card-tree__top-tree-icon-box">
          <img class="sns-card-tree__top-tree-img" src="/public/svg/tree-icon--white.svg" alt="트리 아이콘">
        </div>
        <div v-else-if="data.cardType === CardType.MEMOIR" class="sns-card-tree__top-memoir-icon-box">
          <img class="sns-card-tree__top-tree-img" src="/public/svg/memoir-icon--white.svg" alt="회고록 아이콘">
        </div>
        <div v-else class="sns-card-tree__top-leaf-icon-box">
          <img class="sns-card-tree__top-tree-img" src="/public/svg/leaf-icon--white.svg" alt="리프 아이콘">
        </div>
      </a>
      <div class="sns-card-tree__top-sns-icon-box">
        <div class="sns-card-tree__top-share-icon-box">
          <img src="/public/svg/comment.svg" alt="댓글 아이콘">
        </div>
        <div class="sns-card-tree__top-like-icon-box">
          <img src="/public/svg/like.svg" alt="좋아요 아이콘">
        </div>
      </div>
    </div>
    <a href="">
      <div class="sns-card-tree__mid-box">
        <p class="sns-card-tree__mid-title">{{ data.treeTitle }}</p>
        <p class="sns-card-tree__mid-date">{{ data.treeDate }}</p>
      </div>

      <div v-if="data.cardType === CardType.MEMOIR" class="sms-card-tree__memoir-text-box">
        <q class="sms-card-tree-memoir-text">{{ data.memoirText }}</q>
      </div>
    </a>
    <div class="sns-card-tree__bot-box">
      <div class="sns-card-tree__bot-nickname-box">
        <a href="">
          <p class="sns-card-tree__bot-nickname">
            <span class="sns-card-tree__bot-nickname-text">{{ data.nickname }}</span>
            <span class="sns-card-tree__bot-nickname-id">{{ data.nicknameId }}</span>
          </p>
        </a>
      </div>
      <a href="">
        <div class="sns-card-tree__bot-statistics-box">
          <p class="sns-card-tree__bot-leaf-text">
            <span class="sns-card-tree__bot-leaf-count">{{ modifyCount(data.leafCount) }}</span> 리프
          </p>
          <p class="sns-card-tree__bot-view-text">
            <span class="sns-card-tree__bot-view-count">{{ modifyCount(data.viewCount) }}</span> 조회수
          </p>
        </div>
      </a>
    </div>
  </div>
</template>

<style scoped>
.sns-card-tree-box {
  display: flex;
  flex-direction: column;
}

.sns-card-tree__top-box {
  display: flex;
  justify-content: space-between;
  align-items: flex-start;
  margin-bottom: 16px;
}

.sns-card-tree__top-tree-icon-box {
  display: flex;
  justify-content: center;
  align-items: center;
  background-color: var(--main1, #323a27);
  border-radius: 10px;
  width: 40px;
  height: 40px;
}

.sns-card-tree__top-memoir-icon-box {
  display: flex;
  justify-content: center;
  align-items: center;
  background-color: #ff6240;
  border-radius: 10px;
  width: 40px;
  height: 40px;
}

.sns-card-tree__top-leaf-icon-box {
  display: flex;
  justify-content: center;
  align-items: center;
  background-color: #57cc20;
  border-radius: 10px;
  width: 40px;
  height: 40px;
}

.sns-card-tree__top-sns-icon-box {
  display: flex;
  justify-content: center;
  align-items: center;
  gap: 8px;
}

.sns-card-tree__mid-box {
  display: flex;
  margin-right: 45px;
  gap: 4px;
  flex-direction: column;
  justify-content: center;
  margin-bottom: 14px;
}

.sns-card-tree__mid-title {
  font-size: 18px;
  font-weight: var(--bold, 700);
  color: var(--main1, #323a27);
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
}

.sns-card-tree__mid-date {
  font-size: 12px;
  font-weight: var(--regular, 400);
  color: var(--text-sub, #767676);
}

.sms-card-tree__memoir-text-box {
  display: -webkit-box;
  -webkit-box-orient: vertical;
  -webkit-line-clamp: 2;
  overflow: hidden;
  text-overflow: ellipsis;
  margin-bottom: 20px;
}

.sms-card-tree-memoir-text {
  font-size: 14px;
  font-weight: var(--regular, 400);
  color: var(--text-sub, #767676);
}

.sns-card-tree__bot-box {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.sns-card-tree__bot-nickname-text {
  font-size: 14px;
  font-weight: var(--bold, 700);
  color: var(--main1, #323a27);
}

.sns-card-tree__bot-nickname-id {
  font-size: 10px;
  font-weight: var(--regular, 400);
  color: var(--text-sub, #767676);
}

.sns-card-tree__bot-statistics-box {
  font-size: 12px;
  font-weight: var(--regular, 400);
  color: var(--text-sub, #767676);
}

.sns-card-tree__bot-leaf-count,
.sns-card-tree__bot-view-count {
  font-size: 14px;
  font-weight: var(--bold, 700);
  color: var(--main1, #323a27);
}
</style>