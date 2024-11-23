<script setup>
import {
  Chart as ChartJS,
  Title,
  Tooltip,
  Legend,
  BarElement,
  CategoryScale,
  LinearScale,
  ArcElement,
  plugins,
} from "chart.js";
import { Bar, Doughnut } from "vue-chartjs";

// Register
ChartJS.register(
  Title,
  Tooltip,
  Legend,
  BarElement,
  ArcElement,
  CategoryScale,
  LinearScale
);

const props = defineProps({
  chartData: {
    labels: ["Red", "Yellow", "Blue"],
    datasets: [
      {
        label: "나의 꼬깃 통계",
        data: [300, 50, 100],
        backbroundColor: ["#FF6384", "#36A2EB", "#FFCE56"],
        hoverOffset: 4,
      },
    ],
  },
  chartOptions: {
    responsive: true,
    maintainAspectRatio: false,
    plugins: {
      legend: {
        display: true,
        position: "bottom",
      },
      tooltip: {
        enabled: true,
      },
      //도넛 중앙에 수 표시
      beforeDraw: (chart) => {
        const { width } = chart;
        const { height } = chart;
        const ctx = chart.ctx;
        ctx.restore();
        ctx.font = "20px Pretendard";
        ctx.textBaseline = "middle";
        const text = chart.data.datasets[0].data.reduce((a, b) => a + b, 0); // 총합 계산
        const textX = Math.round(width / 2);
        const textY = Math.round(height / 2);
        ctx.textAlign = "center";
        ctx.fillStyle = "#666";
        ctx.fillText(text, textX, textY);
        ctx.save();
      },
    },
  },
});
</script>

<template>
  <div class="chart-container">
    <Doughnut :data="chartData" :options="chartOptions" />
  </div>
</template>

<script></script>
