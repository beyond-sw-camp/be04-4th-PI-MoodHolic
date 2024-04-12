<template>
  <div align="center">
    <button class="but" :class="{ 'active': isYearClicked }" @click="getYear">연간</button>
    <button class="but" :class="{ 'active': isMonthClicked }" @click="getMonth">월간</button>
    <button class="but" :class="{ 'active': isWeekClicked }" @click="getWeek">주간</button>
  </div>
  <canvas ref="myChartCanvasa"></canvas>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import { Chart, registerables } from 'chart.js';
import { Line } from 'vue-chartjs'

Chart.register(...registerables);

const type = 'line';

const data = ref({
  labels: [],
  datasets: [{
    label: 'ㅎㅇㅇ',
    data: [],
    borderWidth: 1,
  }]
});

const options = {
  scales: {
    y: {
      beginAtZero: true,
      max: 10,
      grid: {
        display: false
      }
    },
    x: {
      grid: {
        display: false
      }
    }
  }
};

const myChartCanvasa = ref(null);
let myChart = null;

onMounted(() => {
  createChart();
});

let isYearClicked = ref(false);
let isMonthClicked = ref(false);
let isWeekClicked = ref(false);

let yearData = null;
let monthData = null;
let weekData = null;

async function fetchData(url) {
  try {
    const response = await fetch(url);
    return await response.json();
  } catch (error) {
    console.error('Error fetching data:', error);
    return null;
  }
}

async function fetchDataAndSetData(url, dataRef) {
  const fetchedData = await fetchData(url);
  if (fetchedData) {
    dataRef.value = fetchedData;
  }
}

async function getYearData() {
  if (!yearData) {
    yearData = await fetchData('http://localhost:8888/graph/year/1');
  }
  return yearData;
}

async function getMonthData() {
  if (!monthData) {
    monthData = await fetchData('http://localhost:8888/graph/month/1');
  }
  return monthData;
}

async function getWeekData() {
  if (!weekData) {
    weekData = await fetchData('http://localhost:8888/graph/week/1');
  }
  return weekData;
}

async function updateChartWithNewData(newData) {
  data.value.labels = Object.keys(newData);
  data.value.datasets[0].data = Object.values(newData);
  destroyChart();
  createChart();
}

const getYear = async () => {
  const newData = await getYearData();
  if (newData) {
    isYearClicked.value = true;
    isMonthClicked.value = false;
    isWeekClicked.value = false;
    updateChartWithNewData(newData);
  }
};

const getMonth = async () => {
  const newData = await getMonthData();
  if (newData) {
    isYearClicked.value = false;
    isMonthClicked.value = true;
    isWeekClicked.value = false;
    updateChartWithNewData(newData);
  }
};

const getWeek = async () => {
  const newData = await getWeekData();
  if (newData) {
    isYearClicked.value = false;
    isMonthClicked.value = false;
    isWeekClicked.value = true;
    updateChartWithNewData(newData);
  }
};

function createChart() {
  const ctx = myChartCanvasa.value.getContext('2d');
  myChart = new Chart(ctx, {
    type: type,
    data: data.value,
    options: options
  });
}

function destroyChart() {
  if (myChart) {
    myChart.destroy();
  }
}

</script>

<style>
/* Add your styles here */
.but {
  margin: 10px;
  padding: 10px;
  border-radius: 10px;
  border: 1px solid #000000;
  background-color: #7c7c7c;
  color: #000000;
  font-size: 15px;
  font-weight: 600;
  font-family: 'Inter', sans-serif; 
}

.but:hover {
  background-color: #333333; /* 마우스를 올렸을 때 배경색 변경 */
}

.but.active {
  background-color: yellow;
}
</style>
