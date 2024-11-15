<script setup>
import { defineProps } from "vue";

const props = defineProps({
  data: {
    type: Array,
    default: () => [],
  },
  sideScrollType: {
    type: String,
    required: true,
  },
});


let scrollContainer;
onMounted(() => {
  scrollContainer = document.querySelector(`.card-another-records-list-box.${props.sideScrollType}`);
});

const scroll = (e) => {
  e.preventDefault(); // 기본 스크롤 동작 방지
  const scrollAmount = e.deltaY || -e.wheelDelta || 0;
  const adjustedScrollAmount = scrollAmount * 1; // 스크롤 속도 조절
  scrollContainer.scrollLeft += adjustedScrollAmount;
};

</script>

<template>
  <div class="card-tree-list-box"
    @wheel="scroll"
    :class="`card-another-records-list-box ${props.sideScrollType}`"
  >
    <ul class="card-tree__list">
      <li
        class="card-tree__item"
        v-for="(treeCard, index) in props.data"
        :key="index"
      >
        <CardTree :data="treeCard" />
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
