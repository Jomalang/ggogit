<script setup lang="ts">

</script>

<template>
  <!-- (seedList) -->
  <div class="filter-tree-list" >
    <label>
      <input class="filter-tree-list-img seedFilterEvent" type="radio" name="treeList" data-id="0" label="" checked/>
    </label>
    <label>
      <input class="filter-tree-list-btn seedFilterEvent" type="radio" name="treeList" data-id="${seed.id}"/>
    </label>
  </div>
</template>

<style scoped>
.filter-tree-list {
  margin-bottom: 30px;
  display: flex;
  gap: 5px;
  flex-direction: row;
}
.filter-tree-list-img{
  background-image: url("/svg/sort.svg");
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
.filter-tree-list-img:checked {
  background-color: var(--btn-active);
  background-image: url("/svg/sort-white.svg");
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

.sortIcon {
  display: flex;
  align-items: center;
  align-self: center;
  height: fit-content;
  padding: 12px;
  border-radius: 8px;
  background-color: var(--btn-no-active);
  margin-right: 3px;
}

/* 태블릿 사이즈 768 */
@media screen and (min-width: 768px) {
  .filter-tree-list-btn {
    padding: 9px 12px;
  }
}

.__like-frame {
  width: 24px;
  height: 24px;
  display: flex;
}
.__like-input {
  display: none;
  cursor: none;
}

.__like {
  cursor: pointer;
}

.__like::after {
  width: 24px;
  height: 24px;
  content: "";
  display: flex;
  align-items: center;
  background-image: url("/svg/like.svg");
  background-repeat: no-repeat;
  background-position: center;
  background-size: contain;
}

.__like-content {
  width: 100%;
  display: none;
}

.__like-input:checked + .__like::after {
  width: 24px;
  height: 24px;
  content: "";
  display: flex;
  align-items: center;
  background-image: url("/svg/like-fill.svg");
  background-repeat: no-repeat;
  background-position: center;
  background-size: contain;
}

.__like-input:checked ~ .__like-content {
  width: 100%;
  display: flex;
  flex-direction: column;
}
</style>