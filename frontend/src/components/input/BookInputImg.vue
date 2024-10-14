<script setup lang="ts"></script>

<template>
  <!--book-input-img  -->
  <div class="input-book-img-box">
    <label class="input-book-img-box__label">
      <input
        type="file"
        name="img"
        id="input-book-img-box__input-id"
        class="input-book-img-box__input"
        accept="image/*"
      />
      <img
        id="input-book-img-box__img-id"
        src="/public/svg/book-photo-form.svg"
        alt="Upload Image"
        class="input-book-img-box__img"
      />
    </label>
  </div>
</template>

<style>
/*  ========================================== /
    FRAGMENT: 트리 직접 입력 도서 이미지 받기
/   ========================================== */
.input-book-img-box__input {
  display: none;
}

.input-book-img-box__img {
  width: 135px;
  height: 198px;
  border-radius: 12px;
  object-fit: cover;
  cursor: pointer;
  box-shadow: 0 4px 4px 0 rgba(0, 0, 0, 0.25);
}
</style>
