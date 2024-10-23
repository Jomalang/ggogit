<script setup lang="ts">
import { LeafTagProps } from "@/types/types";
import { reactive, watch } from "vue";

const props = defineProps<{
  tags: Array<LeafTagProps>;
}>();

const savedFormData = localStorage.getItem('treeFormData');
const treeFormData = reactive(savedFormData ? JSON.parse(savedFormData) : {
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

watch(
    treeFormData,
    (newVal) => {
      localStorage.setItem('treeFormData', JSON.stringify(newVal));
      window.location.href = '/tree/book/reg';
    },
    { deep: true }
);

// -------------------- Function -------------------- //
const chooseBookCategory = (e: Event) => {
  const target = e.target as HTMLElement;
  const dataId = target.closest('.tag-info__tag-box')?.getAttribute('data-id');
  if (dataId) {
    treeFormData.bookCategoryId = dataId;
    treeFormData.bookCategoryName = target.textContent || '';
  }
};

</script>

<template>
  <!--tag-list(tags)-->
  <div class="tag-info-box">
    <ul class="tag-info__list tag-info__list--unselected">
      <li
        v-for="tag in tags"
        :key="tag.id"
        class="tag-info__item tag-info__item--unselected"
      >
        <div class="tag-info__tag-box"
             @click="chooseBookCategory"
             :data-id="tag.id"
        >
          <q class="tag-info__name">{{ tag.name }}</q>
        </div>
      </li>
    </ul>
  </div>
</template>

<style>
/*  ===========================
    FRAGMENT: 태그 리스트
    =============================  */
.tag-info-box {
  width: 100%;
}

.tag-info__list {
  width: 100%;
}

.tag-info__item {
  width: 100%;
  display: flex;
  border: 1px solid #d9d9d9;
  padding: 8px 0;
  justify-content: space-between;
  align-items: center;
}

.tag-info__tag-box {
  display: inline-flex;
  margin-left: 16px;
  gap: 6px;
  align-items: center;
  padding: 6px 12px;
  border-radius: 12px;
  background-color: var(--main1);
}

.tag-info__name {
  color: var(--white);
}
</style>
