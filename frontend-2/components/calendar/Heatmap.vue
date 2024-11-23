<script setup>
import { CalendarHeatmap } from "vue3-calendar-heatmap";

const props = defineProps({
  values: {
    // 히트맵 데이터
    type: Array,
    required: false,
    default: () => [],
  },
  endDate: {
    // 마지막 날짜 (기본값은 오늘)
    type: String,
    required: false,
    default: () => new Date().toISOString().split("T")[0],
  },
  round: {
    // 둥근 테두리 범위
    type: number,
    required: false,
    default: 5,
  },
  darkMode: {
    // 다크 모드 사용 여부
    type: Boolean,
    required: false,
    default: false,
  },
  rangeColor: {
    // 색상 범위
    type: Array,
    required: false,
    default: () => ["#ebedf0", "#c6e48b", "#7bc96f", "#239a3b", "#196127"],
  },
  noDataText: {
    // 데이터 없을 때 표시할 텍스트
    type: String,
    required: false,
    default: "꼬깃이 없어요...😢",
  },
  max: {
    // 데이터 최대값
    type: Number,
    required: false,
    default: 5,
  },
  tooltip: {
    // 툴팁 사용 여부
    type: Boolean,
    required: false,
    default: true,
  },
  tooltipUnit: {
    // 툴팁 단위
    type: String,
    required: false,
    default: "",
  },
  tooltipFormattter: {
    // 툴팁 형식
    type: Function,
    required: false,
    default: (value) => value,
  },
  vertical: {
    // 수직 표시 여부
    type: Boolean,
    required: false,
    default: false,
  },
});
</script>

<template>
  <div class="calendar-heatmap">
    <CalendarHeatmap
      :values="values"
      :endDate="endDate"
      :round="round"
      :darkMode="darkMode"
      :rangeColor="rangeColor"
      :max="max"
      :tooltip="tooltip"
      :tooltipUnit="tooltipUnit"
      :tooltipFormattter="tooltipFormattter"
      :vertical="vertical"
    />
  </div>
</template>

<style scoped>
html,
body {
  font-size: 10px;
}
.calendar-heatmap {
  font-size: 12px !important;
}
</style>
