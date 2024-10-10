<script setup lang="ts">
import BranchStateMain from "@/components/bot-bar/BranchStateMain.vue";

import CommentList from "@/components/tab-comment/CommentList.vue";


// CommentList test
// const datas: Comment[] = [
//   {
//     commentId: 1,
//     memberId: 1,
//     memberImage: '/public/svg/card-comment-author.svg',
//     memberNickname: '빵형',
//     commentContent: '텍스트 테스트입니다.',
//     likeCount: 20,
//     createTime: new Date('2022-10-10T16:35:34.448039')
//   },
//   {
//     commentId: 2,
//     memberId: 1,
//     memberImage: '/public/svg/card-comment-author.svg',
//     memberNickname: '빵형',
//     commentContent: '텍스트 테스트입니다.',
//     likeCount: 20,
//     createTime: new Date('2024-04-10T16:02:34.448039')
//   },
//   {
//     commentId: 3,
//     memberId: 1,
//     memberImage: '/public/svg/card-comment-author.svg',
//     memberNickname: '빵형',
//     commentContent: '텍스트 테스트입니다.',
//     likeCount: 20,
//     createTime: new Date('2024-03-10T16:02:34.448039')
//   },
//   {
//     commentId: 4,
//     memberId: 1,
//     memberImage: '/public/svg/card-comment-author.svg',
//     memberNickname: '빵형',
//     commentContent: '텍스트 테스트입니다.',
//     likeCount: 20,
//     createTime: new Date('2024-02-10T16:02:34.448039')
//   },
//   {
//     commentId: 5,
//     memberId: 1,
//     memberImage: '/public/svg/card-comment-author.svg',
//     memberNickname: '빵형',
//     commentContent: '텍스트 테스트입니다.',
//     likeCount: 20,
//     createTime: new Date('2024-01-10T16:02:34.448039')
//   }
// ];

//BranchStateMain TEST
const datas: Branch[] = [
  {
    view : 1,
    log : 1,
    like : 1,
    branch : '1',
    date : new Date('2024-01-10T16:02:34.448039')
  },
  {
    view : 2,
    log : 2,
    like : 2,
    branch : '2',
    date : new Date('2024-01-10T16:02:34.448039')
  },
  {
    view : 3,
    log : 3,
    like : 3,
    branch : '3',
    date : new Date('2024-01-10T16:02:34.448039')
  },
  {
    view : 4,
    log : 4,
    like : 4,
    branch : '4',
    date : new Date('2024-01-10T16:02:34.448039')
  },
  {
    view : 5,
    log : 5,
    like : 5,
    branch : '5',
    date : new Date('2024-01-10T16:02:34.448039')
  },
];

</script>

<template>
<!--  <CommentList :comments="datas"/>-->
  <BranchStateMain :branches="datas"/>
</template>

<style scoped>

</style>