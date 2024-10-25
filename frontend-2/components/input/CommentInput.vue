<script setup lang="ts">
const props = defineProps<{
  action: string;
}>();
</script>

<template>
  <!--commnet-input(action)  -->
  <div class="input-comment-input-box">
    <form class="input-comment-input__form" :action="action">
      <label class="input-comment-input__label">
        <img
          class="input-comment-input__profile-img"
          src="/public/svg/comment-profile.svg"
          alt="프로필 이미지"
        />
        <textarea
          id="input-comment-input__input-id"
          rows="1"
          class="input-comment-input__input"
          placeholder="댓글 추가..."
          type="text"
        ></textarea>
      </label>
      <button
        id="input-comment-input__submit-id"
        class="input-comment-input__submit"
        type="submit"
      >
        <img src="/public/svg/comment-send.svg" alt="send-button" />
      </button>
    </form>
  </div>
</template>

<style>
/*  ========================================== /
    FRAGMENT: 댓글 입력 박스
/   ========================================== */
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
