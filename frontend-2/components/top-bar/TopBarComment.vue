<script setup lang="ts"></script>

<template>
  <!-- top-bar-comment-->
  <div class="top-bar-comment-box">
    <h1 class="none">댓글 상단 바</h1>

    <div class="top-bar-comment__line-box">
      <div class="top-bar-comment__line"></div>
    </div>

    <div class="top-bar-comment__back-box">
      <p class="top-bar-comment__back-text">댓글</p>
      <div
        id="top-bar-comment__back-icon-box-id"
        class="top-bar-comment__back-icon-box"
      >
        <img src="/public/svg/tab-back-icon.svg" alt="탭 뒤로가기 아이콘" />
      </div>
    </div>

    <div class="top-bar-comment__check-box">
      <input
        id="sort-popular"
        class="top-bar-comment__input"
        type="radio"
        name="commentSort"
        checked
      />
      <label for="sort-popular" class="top-bar-comment__label">인기순</label>
      <input
        id="sort-latest"
        class="top-bar-comment__input"
        type="radio"
        name="commentSort"
      />
      <label for="sort-latest" class="top-bar-comment__label">최신순</label>
    </div>
  </div>
</template>

<style>
/*  ========================================== /
    FRAGMENT: 댓글 탭 상단 바
/   ========================================== */
.top-bar-comment-box {
  width: auto;
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
  align-items: center;
  padding: 0 16px;
}

.top-bar-comment__line-box {
  height: 13px;
  display: flex;
  justify-content: center;
  align-items: flex-end;
}

.top-bar-comment__line {
  width: 40px;
  height: 3px;
  background-color: #d6d6d6;
}

.top-bar-comment__back-box {
  width: 100%;
  height: 34px;
  display: flex;
  justify-content: space-between;
  align-items: flex-end;
}

.top-bar-comment__back-text {
  height: 24px;
  display: flex;
  justify-content: center;
  align-items: center;
  font-size: 20px;
  font-weight: var(--bold, 700);
  color: var(--main1, #323a27);
}

.top-bar-comment__back-icon-box {
  display: flex;
  justify-content: center;
  align-items: center;
  width: 24px;
  height: 24px;
}

.top-bar-comment__check-box {
  display: flex;
  gap: 8px;
  justify-content: flex-start;
  align-items: center;
  width: 100%;
  padding: 10px;
}

.top-bar-comment__label {
  display: inline-block;
  padding: 10px 20px;
  background-color: #f7f7f7;
  font-size: 12px;
  border-radius: 8px;
}

.top-bar-comment__input {
  display: none;
}

.top-bar-comment__input:checked + .top-bar-comment__label {
  background-color: var(--main1, #323a27);
  color: var(--white, #ffffff);
  font-weight: var(--regular, 400);
  font-size: 12px;
}
</style>
