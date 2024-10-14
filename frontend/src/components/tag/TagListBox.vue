<script setup lang="ts">
import { LeafTagProps } from "@/types/types";

const props = defineProps<{
  tags: Array<LeafTagProps>;
}>();
</script>

<template>
  <!--tag-list(tags)-->
  <div class="tag-info-box">
    <ul class="tag-info__list tag-info__list--unselected">
      <li
        v-for="tag in tags"
        class="tag-info__item tag-info__item--unselected"
        :id="tag.id + '--unselected'"
      >
        <div class="tag-info__tag-box">
          <q class="tag-info__name">{{ tag.name }}</q>
        </div>
        <div class="tag-info__option-box">
          <RouterLink class="tag-info__option-link" to="`/tag/edit/${tag.id}`">
            <img
              class="tag-info__option-btn"
              src="/public/svg/icon-option.svg"
              alt="태그 선택 제거 이미지"
            />
          </RouterLink>
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

.tag-info__item--create {
  justify-content: flex-start;
}

.tag-info__create-btn {
  margin-left: 16px;
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

.tag-info__btn {
  padding: 0;
}

.tag-info__option-box {
  display: flex;
  align-items: center;
  margin-right: 16px;
}

.tag-info__option-link {
  display: flex;
  align-items: center;
}
</style>
