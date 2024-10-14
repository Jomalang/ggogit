<script setup lang="ts">
import CardTree from "@/components/card/CardTree.vue";

</script>

<template>
  <div class="card-tree-list-box">
    <ul class="card-tree__list">
      <li class="card-tree__item">
        <CardTree data="" />
      </li>
      <li class="card-tree__item">
        <CardTree data="" />
      </li>
    </ul>
  </div>
</template>

<style scoped>
.card-tree__item {
  background-color: var(--main3, #f5f8f1);
  width: 290px;
  margin: 0 0 0 10px;
  padding: 16px;
  border-radius: 16px;
  display: flex;
  flex-direction: column;
  flex: 0 0 auto;
  gap: 8px;
  box-shadow: 4px 4px 8px rgba(0, 0, 0, 0.25);
}

.card-tree-list-box {
  display: flex;
  overflow-x: auto;
  padding-bottom: 10px;
  margin: 0;
  list-style: none;
}

.card-tree-list-box::-webkit-scrollbar {
  display: none;
}

.card-tree__list {
  display: flex;
}

.card-tree__list li:last-child {
  margin-right: 60px; /* 마지막 아이템의 오른쪽에 공백 추가 */
}
</style>