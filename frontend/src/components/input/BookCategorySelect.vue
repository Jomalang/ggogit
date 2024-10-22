<script setup lang="ts">

import { reactive } from "vue";

const savedFormData = localStorage.getItem('formData');
const formData = reactive(savedFormData ? JSON.parse(savedFormData) : {
  seedCategoryType: '',
  bookTitle: '',
  author: '',
  publishDate: '',
  totalPage: '',
  treeTitle: '',
  description: '',
  visibility: true,
  bookCategoryId: '',
  bookCategoryName: ''
});

const bookCategory = reactive({
  isSelected: formData.bookCategoryId !== '',
  id: formData.bookCategoryId,
  name: formData.bookCategoryName
});

// -------------------- Function -------------------- //
const dropBookCategory = () => {
  bookCategory.isSelected = false;
  bookCategory.id = '';
  bookCategory.name = '';
  formData.bookCategoryId = '';
  formData.bookCategoryName = '';
};

</script>

<template>
  <div class="input-tag-select-box">
    <p class="input-tag-select__label-text">도서 카테고리 선택</p>
    <div class="input-tag-select__bg">
      <div class="input-tag-select__button-box">
        <div class="input-tag-select__tag-box">
          <p v-if="!bookCategory.isSelected" class="input-tag-select__placeholder">도서 카테고리를 선택해주세요</p>
          <div v-else class="input-tag-select__tag">
            <label class="input-tag-select__tag-label">
              <span class="input-tag-select__tag-text">{{ bookCategory.name }}</span>
              <button
                  class="input-tag-select__tag-delete-btn"
                  type="button"
                  @click="dropBookCategory"
              >
                <img src="/svg/x-button.svg" alt="next-button" />
              </button>
              <input class="none input-tag-select__input" name="bookCategoryId" :value="bookCategory.id" />
            </label>
          </div>
        </div>
        <RouterLink
            class="input-tag-select__button"
            id="input-tag-select__button-id"
            to="/book/category/list"
        >
          <img src="/public/svg/next.svg" alt="next-button" />
        </RouterLink>
      </div>
    </div>
  </div>
</template>

<style>
/*  ==========================================
    FRAGMENT: 태그 선택
    ========================================== */
.input-tag-select__tag-delete-btn {
  background-color: var(--main1, #323a27);
  background: none;
  border: none;
  font: inherit;
  color: inherit;
  cursor: pointer;
}

.input-tag-select-box {
  width: 100%;
  display: flex;
  flex-direction: column;
}

.input-tag-select__bg {
  width: 100%;
  display: flex;
  flex-direction: row;
}

.input-tag-select__label-text {
  margin-bottom: 4px;
  font-weight: var(--bold);
  line-height: var(--line-height-main);
  letter-spacing: var(--letter-spacing-sub);
  color: var(--main1, #323a27);
}

.input-tag-select__button-box {
  display: flex;
  width: 100%;
  flex-direction: row;
  min-height: 24px;
  border-radius: 12px;
  padding: 16px 16px;
  align-items: center;
  justify-content: space-between;
  background-color: var(--main3, #f5f8f1);
}

.input-tag-select__tag-box {
  display: flex;
  align-items: center;
  flex-wrap: wrap;
  gap: 8px;
}

.input-tag-select__placeholder {
  margin: 8px 0;
}

.input-tag-select__button {
  display: flex;
  justify-content: flex-end;
  align-items: center;
  padding-left: 16px;
}

.input-tag-select__tag-label {
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
}

.input-tag-select__tags {
  display: flex;
  flex-wrap: wrap;
  flex-direction: row;
  gap: 10px;
}

.input-tag-select__tag-text {
  display: block;
  font-size: 14px;
  color: var(--white, #ffffff);
}

.input-tag-select__tag {
  display: inline-block;
  flex-direction: row;
  border-radius: 12px;
  padding: 6px 12px 6px 12px;
  background-color: var(--main1, #323a27);
}
</style>