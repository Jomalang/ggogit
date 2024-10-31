<script setup>
const props = defineProps({
  categories: Array
});

const emit = defineEmits(['choose']);

</script>

<template>
  <!--tag-list(tags)-->
  <div class="tag-info-box">
    <ul class="tag-info__list tag-info__list--unselected">
      <li
        v-for="category in categories"
        :key="category.id"
        class="tag-info__item tag-info__item--unselected"
      >
        <div class="tag-info__tag-box"
             @click="emit('choose', category)"
             :data-id="category.id"
        >
          <q class="tag-info__name">{{ category.name }}</q>
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
