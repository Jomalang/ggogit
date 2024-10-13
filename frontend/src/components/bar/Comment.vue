<script setup lang="ts">
interface Props {
  commentCount: number;
  profileImg: string;
}

const props = defineProps<Props>();
</script>

<template>
  <!-- ==========================================
       FRAGMENT: 댓글 탭 활성화 박스
       (profileImg, commentCount)
        TODO: 이미지 경로 재설정
       ==========================================-->
  <div id="bar-comment-id" class="bar-comment-box">
      <p class="bar-comment__text">댓글
          <span class="bar-comment__count">{{props.commentCount}}</span>
      </p>
      <div class="bar-comment__profile-frame">
          <img class="bar-comment__profile-img" :src="`${props.profileImg}`" alt="프로필 이미지" />
          <p class="bar-comment__placeholder">댓글 추가...</p>
      </div>
  </div>
</template>

<style>

/*  ========================================== /
    FRAGMENT: 댓글 탭 활성화 박스
/   ========================================== */
.bar-comment-box {
  padding: 0 24px;
  background-color: var(--main3, #f5f8f1);
  flex-direction: column;
  display: flex;
  justify-content: center;
  gap: 8px;
}

.bar-comment__text {
  margin-top: 16px;
  font-weight: var(--bold, 700);
  font-size: 14px;
  line-height: var(--line-height-main);
  letter-spacing: var(--letter-spacing-main);
  color: var(--main1, #323a27);
}

.bar-comment__count {
  font-size: 12px;
  font-weight: var(--regular, 400);
}

.bar-comment__profile-frame {
  margin-bottom: 24px;
  display: flex;
  gap: 12px;
  justify-content: flex-start;
  align-items: center;
}

.bar-comment__profile-img {
  width: 25px;
  height: 25px;
}

.bar-comment__placeholder {
  width: 100%;
  padding: 8px 0 8px 16px;
  background-color: var(--main2, #e5eddb);
  border-radius: 16px;
  font-size: 12px;
  color: var(--text-sub, #767676);
  font-weight: var(--regular, 400);
}

</style>
