<script setup>
import TopBarComment from '../top-bar/TopBarComment';
import CommentList from './CommentList';
import CommentInput from '../input/CommentInput';
</script>

<template>
  <!-- ==========================================
      FRAGMENT: 댓글 탭
      ========================================== -->
  <div class="tab-comment-box">
    <TopBarComment />
    <CommentList />
    <CommentInput />
  </div>
</template>

<style>
/*/////*/
/* div */
/*/////*/
.tab-comment-box {
  position: relative;
  background-color: var(--white, #ffffff);
  width: 100%;
  height: 100%;
  margin-top: 10px;
  border-radius: 18px 18px 0 0;
  box-shadow: 0 -20px 10px -20px rgba(0, 0, 0, 0.8);
}

/*///////////////*/
/* TopBarComment */
/*///////////////*/
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

/*/////////////*/
/* CommentInput */
/*/////////////*/
.input-comment-input-box {
  display: flex;
  position: absolute;
  width: 100%;
  bottom: 0;
  background-color: var(--main3, #f5f8f1);
  z-index: 30;
  margin-bottom: 10px;
  padding-bottom: 4px;
  justify-content: center;
  align-items: center;
}

.input-comment-input__form {
  display: flex;
  justify-content: center;
  align-items: center;
  margin: 12px;
  width: 100%;
}

.input-comment-input__label {
  display: flex;
  flex: 1;
  width: auto;
  gap: 12px;
}

.input-comment-input__input {
  flex: 1;
  position: relative;
  background-color: var(--main2, #e5eddb);
  font-size: 12px;
  border-radius: 16px;
  border: none;
  outline: none;
  overflow: hidden;
  padding: 12px 16px;
  transition: all 0.5s ease;
}

.input-comment-input__input:focus {
  border-radius: 4px;
  font-size: 16px;
  color: var(--main1, #323a27);
}

.input-comment-input__submit {
  display: none;
  transition: all 1s ease;
}

.input-comment-input__submit--active {
  display: block;
}
</style>
