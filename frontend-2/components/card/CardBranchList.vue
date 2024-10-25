<script setup lang="ts">

import { BranchInfoProps } from "@/types/types";

// const items:  BranchInfoProps[] = [
//   {
//     id: 1,
//     title: '브랜치 제목',
//     leafCount: 100,
//     viewCount: 100,
//     updateTime: '2024-07-11',
//     bookMark: true,
//   },
//   {
//     id: 2,
//     title: '브랜치 제목',
//     leafCount: 100,
//     viewCount: 100,
//     updateTime: '2024-07-11',
//     bookMark: false,
//   },
//   {
//     id: 3,
//     title: '브랜치 제목',
//     leafCount: 100,
//     viewCount: 100,
//     updateTime: '2024-07-11',
//     bookMark: true,
//   },
//   {
//     id: 4,
//     title: '브랜치 제목',
//     leafCount: 100,
//     viewCount: 100,
//     updateTime: '2024-07-11',
//     bookMark: false,
//   },
//   {
//     id: 5,
//     title: '브랜치 제목',
//     leafCount: 100,
//     viewCount: 100,
//     updateTime: '2024-07-11',
//     bookMark: true,
//   },
// ];

const props = defineProps<{
  items: BranchInfoProps[];
}>();

</script>

<template>
  <!-- th:fragment="card-branch__list(lists)" -->
  <div class="card-branch__list-frame"
    v-for="item in props.items"
    :key="item.id"
  >
    <a class="branch-info-frame" href="#">
      <div class="branch-img-frame">
        <img v-if="item.bookMark" src="/public/svg/card-bookmark-icon.svg" alt="브랜치 이미지">
        <img v-else src="/public/svg/card-branch-represent-icon.svg" alt="브랜치 이미지" >
      </div>
      <div class="branch-detail-info">
        <p class="branch-detail-info--name">{{ item.title }}</p>
      </div>
    </a>
    <div class="branch-card-bottom-info-frame">
      <div class="branch-card-bottom-info">
        <span>리프 <p>{{ item.leafCount }}</p></span>
        <span>조회수 <p>{{ item.viewCount }}</p></span></div>
      <div class="branch-card-bottom-info">
        <p class="branch-detail-info--regdate">{{item.updateTime}}</p>
      </div>
    </div>
  </div>
</template>

<style scoped>
.card-branch__list-frame {
  padding: 14px 16px;
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
  gap: 4px;
  margin-top: 10px;
  margin-bottom: 10px;
  border-radius: 8px;
  background: var(--main2--opacity40);
}
.card-branch__list-frame-flex {
  display: flex;
  justify-content: space-between;
}
.branch-info-frame {
  display: flex;
  gap: 4px;
}
.branch-img-frame {
  width: 40px;
  height: 40px;
  display: flex;
  justify-content: center;
  align-items: center;
  flex-shrink: 0;
  border-radius: 4px;
  background: var(--main1);
}
.branch-img-frame-lock{
  width: 40px;
  height: 40px;
  display: flex;
  justify-content: end;
  align-items: start;
}
.branch-detail-info {
  padding-top: 1px;
  padding-bottom: 1px;
  display: flex;
  flex-grow: 1;
  min-width: 0;
  align-items: center;
  text-align: left;
}
.branch-detail-info--regdate {
  font-size: 12px;
  font-weight: var(--regular);
  line-height: var(--line-height-sub);
  letter-spacing: var(--letter-spacing-sub);
}
.branch-detail-info--name {
  font-size: 18px;
  font-weight: var(--bold);
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
  line-height: var(--line-height-main);
  letter-spacing: var(--letter-spacing-main);
}
.branch-card-bottom-info-frame {
  display: flex;
  justify-content: space-between;
}
.branch-card-bottom-info {
  display: flex;
  align-items: end;
  gap: 10px;
}
.branch-card-bottom-info  span {
  display: flex;
  align-items: end;
  color: var(--text-sub);
  font-size: 14px;
  font-weight: var(--regular);
}
.branch-card-bottom-info span > p {
  margin-left: 2px;
  color: var(--text-sub);
  font-size: 14px;
  font-weight: var(--bold);
}
.branch-card-bottom__like-frame {
  width: 24px;
  height: 24px;
  display: flex;
}
.branch-card-bottom__like-input {
  display: none;
  cursor: none;
}

.branch-card-bottom__like {
  cursor: pointer;
}

.branch-card-bottom__like::after {
  width: 24px;
  height: 24px;
  content: "";
  display: flex;
  align-items: center;
  background-image: url("/svg/like.svg");
  background-repeat: no-repeat;
  background-position: center;
  background-size: contain;
}

.branch-card-bottom__like-content {
  width: 100%;
  display: none;
}

.branch-card-bottom__like-input:checked + .branch-card-bottom__like::after {
  width: 24px;
  height: 24px;
  content: "";
  display: flex;
  align-items: center;
  background-image: url("/svg/like-fill.svg");
  background-repeat: no-repeat;
  background-position: center;
  background-size: contain;
}

.branch-card-bottom__like-input:checked ~ .branch-card-bottom__like-content {
  width: 100%;
  display: flex;
  flex-direction: column;
}
</style>