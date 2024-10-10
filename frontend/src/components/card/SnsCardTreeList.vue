<script setup lang="ts">

import SnsCardTree from "@/components/card/SnsCardTree.vue";
import {CardType, SnsCardTreeProps} from "@/types/types";

const list: SnsCardTreeProps[] = [
  {
    id: 1,
    cardType: CardType.TREE,
    treeTitle: '트리제목트리제목트리제목트리제목트리제목',
    treeDate: '2024-08-18',
    memoirText: '코딩 테스트 합격자 되기 자바스크립트 편의 영감 기록을 남기는 이유는 다음과 같습니다. 영감 기록을 작성하면 책에서 배운 내용을 정리하고 이해도를 높일 수 있습니다.',
    nickname: '한태규짱',
    nicknameId: '@gksxorb147',
    leafCount: 1000,
    viewCount: 998
  },
  {
    id: 2,
    cardType: CardType.LEAF,
    treeTitle: '트리제목트리제목트리제목트리제목트리제목',
    treeDate: '2024-08-18',
    memoirText: '코딩 테스트 합격자 되기 자바스크립트 편의 영감 기록을 남기는 이유는 다음과 같습니다. 영감 기록을 작성하면 책에서 배운 내용을 정리하고 이해도를 높일 수 있습니다.',
    nickname: '한태규짱',
    nicknameId: '@gksxorb147',
    leafCount: 1000,
    viewCount: 998
  },
  {
    id: 3,
    cardType: CardType.LEAF,
    treeTitle: '트리제목트리제목트리제목트리제목트리제목',
    treeDate: '2024-08-18',
    memoirText: '코딩 테스트 합격자 되기 자바스크립트 편의 영감 기록을 남기는 이유는 다음과 같습니다. 영감 기록을 작성하면 책에서 배운 내용을 정리하고 이해도를 높일 수 있습니다.',
    nickname: '한태규짱',
    nicknameId: '@gksxorb147',
    leafCount: 1000,
    viewCount: 998
  },
];

</script>

<template>
  <div class="sns-card-tree-list-box">
    <ul class="sns-card-tree__list">
      <li class="sns-card-tree__item" v-for="item in list"
          :key="item.id">
        <SnsCardTree :data="item" />
      </li>
    </ul>
  </div>
</template>

<style scoped>
.sns-card-tree__item {
  background-color: var(--white, #ffffff);
  width: 290px;
  padding: 16px;
  margin-left: 10px;
  border-radius: 16px;
  box-shadow: 4px 4px 8px rgba(0, 0, 0, 0.25);
}

.sns-card-tree-list-box {
  display: flex;
  overflow-x: auto;
  padding-bottom: 10px;
  margin: 0;
  list-style: none;
}

.sns-card-tree-list-box::-webkit-scrollbar {
  display: none;
}

.sns-card-tree__list {
  display: flex;
}

.sns-card-tree__list li:last-child {
  margin-right: 60px; /* 마지막 아이템의 오른쪽에 공백 추가 */
}
</style>