<template>
  <div>
    <canvas ref="myChartCanvas"></canvas>
  </div>
  <button @click="getData('year')">연간 데이터 불러오기</button>
  <button @click="getData('month')">월간 데이터 불러오기</button>
  <button @click="getData('week')">주간 데이터 불러오기</button>
  <button @click="getData('day')">일간 데이터 불러오기</button>
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

let yearData = null;
let monthData = null;
let weekData = null;
let dayData = null;

onMounted(() => {
  createChart();
});

async function getData(period) {
  let targetData = null;
  switch(period) {
    case 'year':
      targetData = yearData;
      break;
    case 'month':
      targetData = monthData;
      break;
    case 'week':
      targetData = weekData;
      break;
    case 'day':
      targetData = dayData;
      break;
    default:
      return;
  }

  if (!targetData) {
    targetData = await fetchData(period);
  }

  if (targetData) {
    data.value.labels = Object.keys(targetData);
    data.value.datasets[0].data = Object.values(targetData);
    updateChart();
  }
}

async function fetchData(period) {
  let url = '';
  switch(period) {
    case 'year':
      url = 'http://localhost:8888/graph/year/1';
      break;
    case 'month':
      url = 'http://localhost:8888/graph/month/1';
      break;
    case 'week':
      url = 'http://localhost:8888/graph/week/1';
      break;
    case 'day':
      url = 'http://localhost:8888/graph/day/1';
      break;
    default:
      return null;
  }

  try {
    const response = await fetch(url);
    const responseData = await response.json();
    switch(period) {
      case 'year':
        yearData = responseData;
        break;
      case 'month':
        monthData = responseData;
        break;
      case 'week':
        weekData = responseData;
        break;
      case 'day':
        dayData = responseData;
        break;
    }
    return responseData;
  } catch (error) {
    console.error('Error fetching data:', error);
    return null;
  }
}

function createChart() {
  const ctx = myChartCanvas.value.getContext('2d');
  myChart = new Chart(ctx, {
    type: type,
    data: data.value,
    options: options
  });
}

function updateChart() {
  if (myChart) {
    myChart.data = data.value;
    myChart.update();
  } else {
    createChart();
  }
}
</script>

<style>
/* Add your styles here */
</style>
