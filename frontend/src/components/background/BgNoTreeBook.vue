<script setup>

</script>

<template>
  <div class="bg-no-tree-book">
    <ul class="bg-no-tree-book__list">
      <li class="bg-no-tree-book__item">
        <img
            class="bg-no-tree-book__image"
            src="/public/png/no-tree-left-book.png"
            alt="트리 없음 왼쪽 이미지"
        />
      </li>
      <li class="bg-no-tree-book__item">
        <img
            class="bg-no-tree-book__image"
            src="/public/png/no-tree-mid-book.png"
            alt="트리 없음 중앙 이미지"
        />
      </li>
      <li class="bg-no-tree-book__item">
        <img
            class="bg-no-tree-book__image"
            src="/public/png/no-tree-right-book.png"
            alt="트리 없음 오른쪽 이미지"
        />
      </li>
    </ul>
  </div>
</template>

<style scoped>
.bg-no-tree-book {
  flex-grow: 1;
  display: flex;
  flex-direction: row;
  gap: 21px;
  align-items: center;
  justify-content: center;
}

.bg-no-tree-book__list {
  display: flex;
  flex-direction: row;
  gap: 10px;
  align-items: flex-end;
  justify-content: center;
  align-self: stretch;
  flex: 1;
  position: relative;
}
</style>