<template>
  <div>
    <canvas ref="myChartCanvas"></canvas>
  </div>
  <button @click="getYear">연간 데이터 불러오기</button>
  <button @click="getMonth">월간 데이터 불러오기</button>
  <button @click="getWeek">주간 데이터 불러오기</button>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import { Chart, registerables } from 'chart.js';
Chart.register(...registerables);

const type = 'bar';

const data = ref({
  labels: [],
  datasets: [{
    label: '# of Votes',
    data: [],
    borderWidth: 1
  }]
});

const options = {
  scales: {
    y: {
      beginAtZero: true
    }
  }
};

const myChartCanvas = ref(null);
let myChart = null;

onMounted(() => {
  createChart();
});

function createChart() {
  const ctx = myChartCanvas.value.getContext('2d');
  myChart = new Chart(ctx, {
    type: type,
    data: data.value,
    options: options
  });
}

const getYear = async () => {
  try {
    const response = await fetch('http://localhost:8888/graph/year/1');
    const responseData = await response.json();
    data.value.labels = Object.keys(responseData);
    data.value.datasets[0].data = Object.values(responseData);
    
    // Destroy previous chart before rendering new data
    destroyChart();
    createChart();
  } catch (error) {
    console.error('Error fetching data:', error);
  }
}

const getMonth = async () => {
  try {
    const response = await fetch('http://localhost:8888/graph/month/1');
    const responseData = await response.json();
    data.value.labels = Object.keys(responseData);
    data.value.datasets[0].data = Object.values(responseData);
    // Destroy previous chart before rendering new data
    destroyChart();
    createChart();
  } catch (error) {
    console.error('Error fetching data:', error);
  }
}

const getWeek = async () => {
  try {
    const response = await fetch('http://localhost:8888/graph/week/1');
    const responseData = await response.json();
    data.value.labels = Object.keys(responseData);
    data.value.datasets[0].data = Object.values(responseData);
    // Destroy previous chart before rendering new data
    destroyChart();
    createChart();
  } catch (error) {
    console.error('Error fetching data:', error);
  }
}

function destroyChart() {
  // Check if the chart instance exists and destroy it
  if (myChart) {
    myChart.destroy();
  }
}
</script>

<style>
/* Add your styles here */
</style>
