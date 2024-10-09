<script setup lang="ts">
import { computed } from 'vue'

interface Comment {
  commentId: number;
  memberId: number;
  memberImage: string;
  memberNickname: string;
  commentContent: string;
  createTime: Date;
}

const props = defineProps<{
  comment: Comment
}>();

const timeSince = computed(() => {
  const now = new Date();
  const createAt = new Date(props.comment.createTime);
  const afterAt = Math.floor((now.getTime() - createAt.getTime()) / 1000);

  if (afterAt < 60) return `${afterAt}초 전`;
  else if (afterAt < 3600) return `${Math.floor(afterAt / 60)}분 전`;
  else if (afterAt < 86400) return `${Math.floor(afterAt / 3600)}시간 전`;
  else if (afterAt < 2592000) return `${Math.floor(afterAt / 86400)}일 전`;
  else if (afterAt < 31104000) return `${Math.floor(afterAt / 2592000)}월 전`;
  else return `${Math.floor(afterAt / 31104000)}년 전`;
});
</script>

<template>
  <!-- ==========================================
     FRAGMENT: 댓글 리스트 (memberImage, memberNickname, createTime, commentContent, )
     ========================================== -->
  <div class="tab-comment-list-box">
    <ul class="tab-comment__list">
      <li class="tab-comment__item">
        <div>
          <a :href="`${comment.memberId}`">
            <div class="card-log__author-img-frame">
              <img
                class="card-log__author-img"
                :src="`/public/svg/${comment.memberImage}`"
                alt="author-img"
              />
            </div>
          </a>
        </div>
        <div class="card-log__info">
          <a href="#">
            <div class="card-log__create">
              <span class="card-log__create-info">{{ comment.memberNickname }}</span>
              <span class="card-log__create-info">·</span>
              <span class="card-log__create-info">{{ timeSince }}</span>
            </div>
          </a>
          <div class="card-log__content-form">
            <input
              class="card-log__content-detail-show"
              type="checkbox"
              id="card-log__content-detail"
            />
            <label
              class="card-log__content-detail"
              for="card-log__content-detail"
            ></label>
            <p class="card-log__content">
              {{ comment.commentContent }}
            </p>
          </div>
          <div class="card-log__like-info">
            <input class="card-log__like" type="checkbox" id="card-log__like" />
            <label class="card-log__like-frame" for="card-log__like" :id={{ comment.commentId }}>
              <img
                class="card-log__like-icon"
                src="/svg/tumbsup-on.svg"
                alt="like"
              />
            </label>
            <p class="card-log__like-num">26</p>
          </div>
        </div>
        <a class="card-log__detail-frame-link" href="#">
          <div class="card-log__detail-frame">
            <img src="/svg/card-detail.svg" alt="detail" />
          </div>
        </a>
      </li>
    </ul>
  </div>
</template>

<style>
/*/////////////*/
/* CommentList */
/*/////////////*/
.tab-comment-list-box {
  display: flex;
  position: absolute;
  width: auto;
  top: 103px;
  bottom: 0;
  gap: 16px;
  padding: 12px 16px;
  overflow: hidden;
  overflow-y: auto;
}

.tab-comment__list {
  display: flex;
  width: auto;
  gap: 24px;
  flex-direction: column;
}

.tab-comment__item {
  width: auto;
  display: flex;
  justify-content: flex-start;
  align-content: center;
}

.tab-comment__item:last-child {
  padding-bottom: 150px;
}

.card-log__author-img-frame {
  display: flex;
  padding-right: 13px;
  justify-content: center;
  text-align: center;
  width: 24px;
  height: 24px;
  border-radius: 70%;
  overflow: hidden;
}

.card-log__author-img {
  object-fit: cover;
}
.card-log__create {
  display: flex;
  gap: 2px;
}
.card-log__create-info {
  color: var(--text-sub);
  font-size: 12px;
  font-weight: var(--regular);
  line-height: var(--line-height-main);
  letter-spacing: var(--letter-spacing-main);
}

.card-log__content-form {
  display: flex;
  flex-direction: column;
}

.card-log__content-detail-show {
  order: 2;
  display: none;
}

.card-log__content-detail {
  order: 3;
}

.card-log__content-detail-show:not(:checked) + .card-log__content-detail:after {
  color: var(--text-sub);
  font-size: 12px;
  font-weight: var(--semi-bold);
  line-height: var(--line-height-main);
  letter-spacing: var(--letter-spacing-main);
  height: 14px;
  cursor: pointer;
  content: '자세히 보기';
}

.card-log__content-detail-show:not(:checked) ~ .card-log__content {
  order: 1;
  padding-top: 4px;
  margin-bottom: 4px;
  color: var(--main1);
  font-size: 14px;
  font-weight: var(--semi-bold);
  line-height: var(--line-height-main);
  letter-spacing: var(--letter-spacing-main);
  display: -webkit-box;
  -webkit-box-orient: vertical;
  -webkit-line-clamp: 3;
  overflow: hidden;
}

.card-log__content-detail-show:checked + .card-log__content-detail:after {
  color: var(--text-sub);
  font-size: 12px;
  font-weight: var(--semi-bold);
  line-height: var(--line-height-main);
  letter-spacing: var(--letter-spacing-main);
  height: 14px;
  cursor: pointer;
  content: '간략히 보기';
}

.card-log__content-detail-show:checked ~ .card-log__content {
  order: 1;
  padding-top: 4px;
  margin-bottom: 4px;
  color: var(--main1);
  font-size: 14px;
  font-weight: var(--semi-bold);
  line-height: var(--line-height-main);
  letter-spacing: var(--letter-spacing-main);
}

.card-log__like-info {
  padding-top: 14px;
  display: flex;
  gap: 4px;
  align-items: center;
}

.card-log__like-num {
  font-size: 10px;
}

.card-log__like {
  display: none;
}

.card-log__like:disabled + .card-log__like-frame {
  background-image: url('/src/main/resources/static/svg/tumbsup-on.svg');
}

.card-log__like:checked + .card-log__like-frame {
  background-image: url('/src/main/resources/static/svg/tumbsup-on.svg');
}

.card-log__like-frame {
  display: flex;
  height: 14px;
  width: 14px;
  cursor: pointer;
  flex-direction: column;
  justify-content: center;
}

.card-log__detail-frame-link {
  height: 24px;
}

.card-log__detail-frame {
  height: 24px;
  width: 24px;
  padding-left: 13px;
}
</style>
