<script setup lang="ts">

interface CardTreeProps {
  treeTitle: string;
  treeDate: string;
  leafCount: number;
  viewCount: number;
}

const data: CardTreeProps  = {
  treeTitle: '트리 제목',
  treeDate: '2024-07-10',
  leafCount: 1000,
  viewCount: 500
};

const props = defineProps<{
  data: CardTreeProps;
}>();

function modifyCount(count) {
  return 999 < count ? '999+' : String(count);
}

</script>

<template>
  <div class="card-tree-box">
    <div class="card-tree__top-box">
      <a href="">
        <div class="card-tree__top-tree-icon-box">
          <img class="card-tree__top-tree-img" src="/public/svg/tree-icon--white.svg" alt="트리 아이콘">
        </div>
      </a>
      <div class="card-tree__top-sns-icon-box">
        <div class="card-tree__top-share-icon-box">
          <img src="/public/svg/comment.svg" alt="댓글 아이콘">
        </div>
        <div class="card-tree__top-like-icon-box">
          <img src="/public/svg/like.svg" alt="좋아요 아이콘">
        </div>
      </div>
    </div>
    <a href="">
      <div class="card-tree__bot-box">
        <div class="card-tree__bot-info-box">
          <p class="card-tree__bot-title">{{ data.treeTitle }}</p>
          <p class="card-tree__bot-date">{{ data.treeDate }}</p>
        </div>
        <div class="card-tree__bot-statistics-box">
          <p class="card-tree__bot-leaf-text">
            <span class="card-tree__bot-leaf-count">{{ modifyCount(data.leafCount) }}</span> 리프
          </p>
          <p class="card-tree__bot-view-text">
            <span class="card-tree__bot-view-count">{{ modifyCount(data.viewCount) }}</span> 조회수
          </p>
        </div>
      </div>
    </a>
  </div>
</template>

<style scoped>
.card-tree__item {
  background-color: var(--main3, #f5f8f1);
  width: 290px;
  margin: 0 0 0 10px;
  padding: 16px;
  border-radius: 16px;
  display: flex;
  flex-direction: column;
  flex: 0 0 auto;
  gap: 8px;
  box-shadow: 4px 4px 8px rgba(0, 0, 0, 0.25);
}

.card-tree__top-box {
  display: flex;
  justify-content: space-between;
  align-items: flex-start;
  margin-bottom: 8px;
}

.card-tree__top-tree-icon-box {
  display: flex;
  justify-content: center;
  align-items: center;
  background-color: var(--main1, #323a27);
  border-radius: 10px;
  width: 40px;
  height: 40px;
}

.card-tree__top-sns-icon-box {
  display: flex;
  justify-content: center;
  align-items: center;
  gap: 8px;
}

.card-tree__bot-box {
  display: flex;
  justify-content: space-between;
  align-items: flex-end;
}

.card-tree__bot-info-box {
  display: flex;
  flex-direction: column;
  gap: 4px;
}

.card-tree__bot-title {
  font-size: 18px;
  width: 200px;
  font-weight: var(--bold, 700);
  color: var(--main1, #323a27);
  margin-right: 10px;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
}

.card-tree__bot-date {
  font-size: 12px;
  font-weight: var(--regular, 400);
  color: var(--text-sub, #767676);
}

.card-tree__bot-statistics-box {
  display: flex;
  flex-direction: column;
  font-size: 12px;
  font-weight: var(--regular, 400);
  color: var(--text-sub, #767676);
}

.card-tree__bot-leaf-text,
.card-tree__bot-view-text {
  white-space: nowrap;
}

.card-tree__bot-leaf-count,
.card-tree__bot-view-count {
  font-size: 14px;
  font-weight: var(--bold, 700);
  color: var(--main1, #323a27);
}

.card-tree__img-frame {
  display: flex;
  height: 120px;
  width: 80px;
  margin-right: 8px;
  justify-items: center;
}

.card-tree__img-frame-no-tree {
  display: flex;
  height: 120px;
  width: 80px;
  justify-items: center;
}
</style>