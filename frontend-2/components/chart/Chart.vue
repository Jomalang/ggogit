<script setup>
import Chart from "chart.js/auto";

const props = defineProps({
  chartData: [],
  chartOptions: {},
  chartType: {
    type: String,
    default: "donut",
  },
});

const chartCanvas = ref(null);
//차트 인스턴스 변수
const chartInstance = null;

//차트 초기화 - CSR환경에서만 가능
const initChart = () => {
  if (chartCanvas.value) {
    chartInstance = new Chart(chartCanvas.value, {
      type: props.chartType,
      data: props.chartData,
      options: props.chartOptions,
    });
  }
};

//차트 업데이트
watch(
  () => props.chartData,
  (newVal) => {
    if (chartInstance) {
      chartInstance.data = newVal;
      chartInstance.update();
    }
  },
  { deep: true }
);

//차트 생성
onMounted(() => {
  initChart();
});

//차트 제거(컴포넌트 제거시 차트 인스턴스도 삭제해줘야 메모리 누수 방지 가능)
onBeforeUnmount(() => {
  if (chartInstance) {
    chartInstance.destroy();
  }
});
</script>

<template>
  <div class="chart-container">
    <canvas id="Chart"> </canvas>
  </div>
</template>

<script></script>
