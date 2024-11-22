<script setup>
import { defineProps } from "vue";

const props = defineProps({
  filterName: {
    type: String,
    default: "Default Title",
  },
  searchFilterName: {
    type: String,
    default: "전체",
  },
});
const emit = defineEmits(["popup", "bookMark"]);
const bookMark = ref();
</script>

<template>
  <div id="branchSortFilter" class="filter-tree-leaf__card">
    <div class="filter-tree-leaf__gap">
      <label @click="emit('popup')" class="filter-tree-list-img filterEvent">
      </label>
      <label @click="emit('popup')" class="filter-tree-list-btn filterEvent">
        {{ props.searchFilterName }}
      </label>
    </div>
    <div class="filter-tree-leaf__card-sort" id="filter-tree-leaf__card-sort">
      <label
        class="bar-search-current__detail"
        for="bar-search-current__detail"
        >{{ props.filterName }}</label
      >
      <img src="" />
    </div>
  </div>
</template>

<style scoped>
.filter-tree-leaf__card {
  margin-top: 14px;
  display: flex;
  justify-content: space-between;
}
.filter-tree-leaf__gap {
  display: flex;
  gap: 5px;
}

.filter-tree-list-img {
  background-image: url("/png/sort.png");
  background-size: 50%;
  background-position: center;
  background-repeat: no-repeat;
  display: flex;
  padding: 19px 20px;
  white-space: nowrap;
  width: fit-content;
  align-items: center;
  justify-content: center;
  border-radius: 8px;
  background-color: var(--btn-no-active);
  color: var(--text-sub);
  font-weight: var(--medium);
  letter-spacing: var(--letter-spacing-main);
  line-height: var(--line-height-main);
  appearance: none;
  cursor: pointer;
}

.filter-tree-list-img::after {
  display: block;
  content: attr(label);
  font-size: 16px;
}

.filter-tree-list-img {
  background-color: var(--btn-active);
  background-image: url("/png/sort-white.png");
  color: var(--white);
}

.filter-tree-list-btn {
  display: flex;
  padding: 10px 12px;
  white-space: nowrap;
  width: fit-content;
  align-items: center;
  justify-content: center;
  border-radius: 8px;
  background-color: var(--btn-no-active);
  color: var(--text-sub);
  font-weight: var(--medium);
  letter-spacing: var(--letter-spacing-main);
  line-height: var(--line-height-main);
  appearance: none;
  cursor: pointer;
}

.filter-tree-list-btn::after {
  display: block;
  content: attr(label);
  font-size: 16px;
}

.filter-tree-list-btn:checked {
  background-color: var(--btn-active);
  color: var(--white);
}

.filter-tree-leaf__card-sort {
  display: flex;
  align-items: end;
}

.bar-search-current__detail-input {
  display: none;
  cursor: none;
}

.bar-search-current__detail {
  padding-top: 7px;
  padding-bottom: 10px;
  margin-left: auto;
  content: "자세히";
  font-size: 12px;
  font-weight: var(--bold, 700);
  cursor: pointer;
}

.bar-search-current__detail::after {
  width: 7px;
  height: 7px;
  content: "";
  display: inline-block;
  align-items: center;
  background-image: url("/png/hidden-on.png");
  background-repeat: no-repeat;
  background-position: center;
  background-size: contain;
}

.bar-search-current__detail-content {
  width: 100%;
  display: none;
}

.bar-search-current__detail-input:checked + .bar-search-current__detail::after {
  width: 7px;
  height: 7px;
  content: "";
  align-items: center;
  display: inline-block;
  background-image: url("/png/hidden-off.png");
  background-position: center;
  background-size: contain;
}

.bar-search-current__detail-input:checked
  ~ .bar-search-current__detail-content {
  width: 100%;
  display: flex;
  flex-direction: column;
}
</style>
