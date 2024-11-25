<script setup>
import {
  Chart as ChartJS,
  Title,
  Tooltip,
  Legend,
  BarElement,
  CategoryScale,
  LinearScale,
  scales,
} from "chart.js";
import { Bar } from "vue-chartjs";

// Register
ChartJS.register(
  Title,
  Tooltip,
  Legend,
  BarElement,
  CategoryScale,
  LinearScale,
  scales
);

const props = defineProps({
  chartData: {
    labels: ["트리", "리프", "회고록", "직접 등록 도서"],
    datasets: [
      {
        data: [100, 100, 100, 100],
        backbroundColor: ["#FF6384", "#36A2EB", "#FFCE56", "#323a27"],
        borderWidht: 1,
      },
    ],
  },
  chartOptions: {
    responsive: true,
    maintainAspectRatio: false,
    indexAxis: "y",
    scales: {
      x: {
        beginAtZero: true,
      },
      y: {
        ticks: {
          autoSkip: false,
        },
      },
    },
    plugins: {
      // legend: {
      //   display: true,
      //   position: "top",
      // },
      tooltip: {
        enabled: true,
      },
    },
  },
});
</script>

<template>
  <div class="chart-container">
    <Bar :data="props.chartData" :options="props.chartOptions" />
  </div>
</template>

<script></script>
