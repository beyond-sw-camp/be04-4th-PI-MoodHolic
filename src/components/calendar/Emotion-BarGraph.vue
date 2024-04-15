<template>
  <div align="center">
    <button class="but" :class="{ 'active': isYearClicked }" @click="toggleActive('year')">연간</button>
    <button class="but" :class="{ 'active': isMonthClicked }" @click="toggleActive('month')">월간</button>
    <button class="but" :class="{ 'active': isWeekClicked }" @click="toggleActive('week')">주간</button>
    <button class="but" :class="{ 'active': isDayClicked }" @click="toggleActive('day')">일간</button>
  </div>
  <canvas ref="myChartCanvasa"></canvas>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import { Chart, registerables } from 'chart.js';
import { Line } from 'vue-chartjs'

Chart.register(...registerables);

const type = 'bar';

const data = ref({
  labels: [],
  datasets: [{
    data: [],
    borderWidth: 1,
  }]
});

const options = {
  scales: {
    y: {
      beginAtZero: false,
      max: 10,
      grid: {
        display: false
      }
    },
    x: {
      grid: {
        beginAtZero: false,
        display: false
      }
    }
  }
};

const myChartCanvasa = ref(null);
let myChart = null;

let isYearClicked = ref(false);
let isMonthClicked = ref(false);
let isWeekClicked = ref(false);
let isDayClicked = ref(false);

let yearData = null;
let monthData = null;
let weekData = null;
let dayData = null;

onMounted(() => {
  createChart();
});

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

async function getDayData() {
  if (!dayData) {
    dayData = await fetchData('http://localhost:8888/graph/day/1');
  }
  return dayData;
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
    isDayClicked.value = false;
    updateChartWithNewData(newData);
  }
};
getYear();

const getMonth = async () => {
  const newData = await getMonthData();
  if (newData) {
    isYearClicked.value = false;
    isMonthClicked.value = true;
    isWeekClicked.value = false;
    isDayClicked.value = false;
    updateChartWithNewData(newData);
  }
};

const getWeek = async () => {
  const newData = await getWeekData();
  if (newData) {
    isYearClicked.value = false;
    isMonthClicked.value = false;
    isWeekClicked.value = true;
    isDayClicked.value = false;
    updateChartWithNewData(newData);
  }
};
const getDay = async () => {
  const newData = await getDayData();
  if (newData) {
    isYearClicked.value = false;
    isMonthClicked.value = false;
    isWeekClicked.value = false;
    isDayClicked.value = true;
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

function toggleActive(period) {
  isYearClicked.value = period === 'year';
  isMonthClicked.value = period === 'month';
  isWeekClicked.value = period === 'week';
  isDayClicked.value = period === 'day';
  switch (period) {
    case 'year':
      getYear();
      break;
    case 'month':
      getMonth();
      break;
    case 'week':
      getWeek();
      break;
    case 'day':
      getDay();
      break;
  }
}
</script>

<style>
/* Add your styles here */
.but {
  margin: 10px;
  padding: 10px;
  border-radius: 10px;
  border: 1px solid #D9D9D9;
  background-color: #D9D9D9;
  color: #000000;
  font-size: 15px;
  font-weight: 600;
  font-family: 'Inter', sans-serif; 
}

.but:hover {
  background-color: #333333; /* 마우스를 올렸을 때 배경색 변경 */
}

.but.active {
  background-color: #FEDB56;
  border-color: #FEDB56;
  
}
</style>
