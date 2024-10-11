<script setup lang="ts">
import CardAnotherRecords from "@/components/card/CardAnotherRecords.vue";
import {CardItemProps, CardType} from "@/types/types";

const items = [
  {
    cardType: CardType.LEAF,
    bookCategory: '도서 카테고리',
    bookTitle: '도서 제목',
    title: '제목입니다.',
    treeTitle: '트리 제목입니다.',
    memoirTitle: '회고록 제목입니다.',
    bookPublishedYear: '2024',
    bookAuthor: '유시민',
    bookPublisher: '프리미엄',
    leafTitle: '웹툰이지만 로깅합니다',
    leafContent: '코딩 테스트 합격자 되기 자바스크립트 편의 영감 기록을 남기는 이유는 다음과 같습니다. 영감 기록을 작성하면 책에서 배운 내용을 정리하고 이해도를 높일 수 있습니다.',
    leafCount: 1000,
    viewCount: 1000,
    updateTime: "2024-07-11",
  },
  {
    cardType: CardType.LEAF,
    bookCategory: '도서 카테고리',
    bookTitle: '도서 제목',
    title: '제목입니다.',
    treeTitle: '트리 제목입니다.',
    memoirTitle: '회고록 제목입니다.',
    bookPublishedYear: '2024',
    bookAuthor: '유시민',
    bookPublisher: '프리미엄',
    leafTitle: '웹툰이지만 로깅합니다',
    leafContent: '코딩 테스트 합격자 되기 자바스크립트 편의 영감 기록을 남기는 이유는 다음과 같습니다. 영감 기록을 작성하면 책에서 배운 내용을 정리하고 이해도를 높일 수 있습니다.',
    leafCount: 1000,
    viewCount: 1000,
    updateTime: "2024-07-11",
  },
  {
    cardType: CardType.LEAF,
    bookCategory: '도서 카테고리',
    bookTitle: '도서 제목',
    title: '제목입니다.',
    treeTitle: '트리 제목입니다.',
    memoirTitle: '회고록 제목입니다.',
    bookPublishedYear: '2024',
    bookAuthor: '유시민',
    bookPublisher: '프리미엄',
    leafTitle: '웹툰이지만 로깅합니다',
    leafContent: '코딩 테스트 합격자 되기 자바스크립트 편의 영감 기록을 남기는 이유는 다음과 같습니다. 영감 기록을 작성하면 책에서 배운 내용을 정리하고 이해도를 높일 수 있습니다.',
    leafCount: 1000,
    viewCount: 1000,
    updateTime: "2024-07-11",
  }
]

const props = defineProps<{
  items: CardItemProps[];
}>();

</script>

<template>

  <div class="card-another-records-list-box">
    <ul class="card-another-records__list">
      <li class="card-another-records__item" v-for="item in items"
          :key="item.id">
        <CardAnotherRecords :item="item" />
      </li>
    </ul>
  </div>

</template>

<style scoped>
</style>