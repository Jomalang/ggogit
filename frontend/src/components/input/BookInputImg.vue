<script setup lang="ts">

import {onMounted, reactive} from "vue";

// ----------------------- Model ----------------------- //

const savedFormData = localStorage.getItem('formData');
const formData = reactive(savedFormData ? JSON.parse(savedFormData) : {
  seedCategoryType: '',
  bookTitle: '',
  author: '',
  publishDate: '',
  totalPage: '',
  treeTitle: '',
  description: '',
  visibility: false,
  bookCategoryId: '',
  imageData: ''
});

const emit = defineEmits(['image-selected']);

onMounted(() => {
  const imgTag = document.getElementById('input-book-img-box__input-id') as HTMLInputElement;
  imgTag.addEventListener('change', function (event: Event) {
    const target = event.target as HTMLInputElement;
    const file = target.files ? target.files[0] : null;
    if (file) {
      const reader = new FileReader();
      reader.onload = function (e: ProgressEvent<FileReader>) {
        const imagePreview = document.getElementById('input-book-img-box__img-id') as HTMLImageElement;
        if (e.target) {
          imagePreview.src = e.target.result as string;
          imagePreview.style.display = 'block';

          // 이미지 데이터를 부모 컴포넌트로 전달
          const imageData = e.target.result as string;
          emit('image-selected', imageData);
        }
      };
      reader.readAsDataURL(file);
    }
  });
});

</script>

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
