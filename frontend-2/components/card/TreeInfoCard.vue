<script setup>
const { data } = defineProps(["data"]);
</script>

<template>
  <div class="card-tree-info-box">
    <div class="card-tree-info">기본 정보</div>
    <div class="card-tree-info__frame">
      <div>
        <p class="card-tree-info__title">발행(출시)일자</p>
        <p class="card-tree-info__title">쪽수</p>
        <p class="card-tree-info__title">ISBN</p>
      </div>
      <div>
        <p class="card-tree-info__content">{{ data.date }}</p>
        <p class="card-tree-info__content">{{ data.pageCount }}</p>
        <p v-if="data.isbn" class="card-tree-info__content">{{ data.isbn }}</p>
      </div>
    </div>
  </div>
</template>

<style scoped>
.card-tree-info-box {
  border-bottom: 1px solid var(--branch-left, #d6d8d4);
}

.card-tree-info {
  height: 41px;
  border-bottom: 1px solid var(--main1);
  font-size: 20px;
  color: var(--main1, #323a27);
  font-weight: var(--bold);
  line-height: var(--line-height-main);
  letter-spacing: var(--letter-spacing-main);
}

.card-tree-info__frame {
  width: 245px;
  height: 75px;
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.card-tree-info__title {
  font-size: 14px;
  color: var(--main1, #323a27);
  font-weight: var(--medium);
  line-height: var(--line-height-main);
  letter-spacing: var(--letter-spacing-main);
}

.card-tree-info__content {
  font-size: 14px;
  color: var(--text-sub, #767676);
  font-weight: var(--regular);
  line-height: var(--line-height-main);
  letter-spacing: var(--letter-spacing-main);
}
</style>
