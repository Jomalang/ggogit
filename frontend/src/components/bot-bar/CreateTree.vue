<script setup>
import { computed } from 'vue'

interface Props {
  bookId: number;
}

const props = defineProps<Props>();

</script>

<template>
  <!-- ==========================================
       FRAGMENT: 트리생성 하단바
       ========================================== -->
  <div class="bot-bar-create-tree-box">
      <div id="bot-bar-create-tree__icon-box-id" class="bot-bar-create-tree__icon-box">
          <img class="bot-bar-create-tree__comment-icon" src="/public/svg/comment--white.svg" alt="댓글 아이콘"/>
      </div>
      <div class="bot-bar-create-tree__icon-box">
          <img class="bot-bar-create-tree__like-icon" src="/public/svg/like--white.svg" alt="좋아요 아이콘"/>
      </div>
      <a :href="`${props.bookId}`" class="bot-bar-create-tree__link">
          <p class="bot-bar-create-tree__tree-create-btn">
              트리 생성
          </p>
      </a>
  </div>
</template>

<style>
/*  ========================================== /
    FRAGMENT: 트리생성 하단바
/   ========================================== */
.bot-bar-create-tree-box {
  background-color: var(--white, #ffffff);
  display: flex;
  padding: 12px 24px;
  gap: 12px;
  box-shadow: 0 -5px 10px -5px rgba(0, 0, 0, 0.3);
}

.bot-bar-create-tree__icon-box {
  background-color: var(--main1, #323a27);
  width: 56px;
  height: 56px;
  display: flex;
  justify-content: center;
  align-items: center;
  border-radius: 4px;
}

.bot-bar-create-tree__link {
  flex-grow: 1;
}

.bot-bar-create-tree__tree-create-btn {
  display: flex;
  border-radius: 4px;
  justify-content: center;
  align-items: center;
  color: var(--white, #ffffff);
  background-color: var(--main1, #323a27);
  height: 56px;
}
</style>
