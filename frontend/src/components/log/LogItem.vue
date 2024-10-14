<script setup lang="ts">
import {ref} from "vue";
import {Direction} from "@/types/types";
import LogItemInit from "@/components/log/logItem/LogItemInit.vue";
import LogItemStartDown from "@/components/log/logItem/LogItemStartDown.vue";
import LogItemStartRight from "@/components/log/logItem/LogItemStartRight.vue";
import LogItemStartSide from "@/components/log/logItem/LogItemStartSide.vue";
import LogItemStartLeft from "@/components/log/logItem/LogItemStartLeft.vue";
import LogItemDown from "@/components/log/logItem/LogItemDown.vue";
import LogItemRight from "@/components/log/logItem/LogItemRight.vue";
import LogItemSide from "@/components/log/logItem/LogItemSide.vue";
import LogItemLeft from "@/components/log/logItem/LogItemLeft.vue";
import LogItemEndRight from "@/components/log/logItem/LogItemEndRight.vue";
import LogItemEndLeft from "@/components/log/logItem/LogItemEndLeft.vue";
import LogItemEndUp from "@/components/log/logItem/LogItemEndUp.vue";
import {LeafItemProps} from "@/types/types";

// const leafData: Ref<LeafItemProps> = ref({
//   id: 1,
//   direction: 0,
//   title: "리프 제목입니다.",
//   date: "2024-07-11",
//   link: "https://www.naver.com",
//   tags: [
//     {id: 1, name: "태그"},
//     {id: 2, name: "태그"},
//     {id: 3, name: "태그"}
//   ]
// });

/*
* <LogItem :item-data="leafData" />
* */

const props = defineProps<{
  itemData: LeafItemProps;
}>();

const currentDirection = ref<Direction>(props.itemData.direction);

const components = {
  [Direction.INIT]: LogItemInit,
  [Direction.START_DOWN]: LogItemStartDown,
  [Direction.START_RIGHT]: LogItemStartRight,
  [Direction.START_SIDE]: LogItemStartSide,
  [Direction.START_LEFT]: LogItemStartLeft,
  [Direction.DOWN]: LogItemDown,
  [Direction.RIGHT]: LogItemRight,
  [Direction.SIDE]: LogItemSide,
  [Direction.LEFT]: LogItemLeft,
  [Direction.END_RIGHT]: LogItemEndRight,
  [Direction.END_LEFT]: LogItemEndLeft,
  [Direction.END_UP]: LogItemEndUp
};

const DynamicComponent = components[currentDirection.value];

</script>

<template>
  <component :is="DynamicComponent" :item-data="props.itemData" />
</template>

<style>
.log-item__link {
  display: inline-block;
}

.log-item__text-box {
  margin: 22px 0 0 8px;
  display: flex;
  flex-direction: column;
  align-items: flex-start;
}

.log-item__title {
  display: flex;
  align-items: center;
  font-size: 16px;
  color: var(--main1, #323a27);
  font-weight: var(--bold, 700);
  line-height: var(--line-height-main);
  letter-spacing: var(--letter-spacing-main);
}

.log-item__date {
  font-size: 14px;
  color: var(--text-sub, #767676);
  font-weight: var(--regular, 400);
  line-height: var(--line-height-main);
  letter-spacing: var(--letter-spacing-main);
}

.log-item__tag {
  display: inline-block;
  border-radius: 4px;
  margin: 2px;
  padding: 2px 8px;
  background-color: var(--main1, #323a27);
  font-size: 14px;
  font-weight: var(--medium, 500);
  line-height: var(--line-height-main);
  letter-spacing: var(--letter-spacing-main);
  color: var(--white, #ffffff);
}

.log-item__left-box,
.log-item__mid-box,
.log-item__right-box
{
  flex-grow: 0;
  flex-shrink: 0;
  flex-basis: 100%;
  width: 100%;
}

.log-item__circle {
  width: 12px;
  height: 12px;
  border-radius: 100px;
  background-color: var(--main1, #323a27);
}

.log-item__info-box {
  width: 100%;
}

.log-item-box {
  margin: 2px 0 2px 0;
  display: flex;
  flex-direction: row;
}

.log-item__mid-branch {
  display: flex;
  width: 12px;
  flex-grow: 1;
  gap: 4px;
  flex-direction: column;
  align-items: center;
}

.log-item__line-top {
  width: 2px;
  height: 20px;
  background: var(--main1, #323a27);
}

.log-item__line-bot {
  width: 2px;
  flex-grow: 1;
  background: var(--main1, #323a27);
}

.log-item__right-branch {
  width: 100%;
  margin: 29px 0 0 0;
}

.log-item__line-right {
  height: 2px;
  width: 100%;
  background-color: var(--branch-left, #d6d8d4);
}

.log-item__text-box {
  margin: 15px 0 0 8px;
}

.log-item__left-branch {
  padding-top: 29px;
}

.log-item__line-left {
  margin-right: 4px;
  height: 2px;
  width: 20px;
  background-color: var(--main1, #323a27);
}

.node--active {
  background-color: var(--warning, #ba0c0c);
}
</style>