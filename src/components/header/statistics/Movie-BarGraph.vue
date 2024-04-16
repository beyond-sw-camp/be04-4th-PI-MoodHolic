<template>
  <div align="center">
    <button class="but" :class="{ 'active': isYearClicked }" @click="toggleActive('year')">영화</button>
    <button class="but" :class="{ 'active': isMonthClicked }" @click="toggleActive('month')">음악</button>
    <button class="but" :class="{ 'active': isWeekClicked }" @click="toggleActive('week')">음식</button>

  </div>
  <canvas ref="myChartCanvasa"></canvas>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import { Chart, registerables } from 'chart.js';

let memberId = ref(null);

const getMemberId = async()=>{

  const authToken = 'Bearer '+localStorage.getItem('authToken');
  console.log(authToken);

  const headers = {
    'Authorization': authToken
  };

  await fetch('http://localhost:8888/userinfo', {
    method: 'GET',
    headers: headers,
    credentials: 'include'  // 쿠키 포함시킴
  })
  .then(response => {
    // 서버 응답을 처리
    if (!response.ok) {
      throw new Error('네트워크 오류 발생');
    }
    return response.text();
  })
  .then(data => {
    // 응답 데이터 처리
    console.log(data);
    const memberIdPattern = /memberId=(\d+)/;
    const memberIdMatch = data.match(memberIdPattern);
    if (memberIdMatch) {
       memberId = memberIdMatch[1];
       console.log("Member ID:", memberId); // "Member ID: 4"
       getYear();
    } else {
      console.error("멤버 ID를 찾을 수 없음");
    }

  })
  .catch(error => {
    // 오류 처리
    console.error('오류 발생:', error);
  });
}
getMemberId();

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
      min:0,
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


let yearData = null;
let monthData = null;
let weekData = null;


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
    yearData = await fetchData(`http://localhost:8888/category/movie/genres/${memberId}`);
  }
  return yearData;
}

async function getMonthData() {
  if (!monthData) {
    monthData = await fetchData(`http://localhost:8888/category/music/genres/${memberId}`);
  }
  return monthData;
}

async function getWeekData() {
  if (!weekData) {
    weekData = await fetchData(`http://localhost:8888/category/food/genres/${memberId}`);
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
    updateChartWithNewData(newData.movieRankings);
  }
};


const getMonth = async () => {
  const newData = await getMonthData();
  if (newData) {
    isYearClicked.value = false;
    isMonthClicked.value = true;
    isWeekClicked.value = false;
    updateChartWithNewData(newData.musicRankings);
  }
};

const getWeek = async () => {
  const newData = await getWeekData();
  if (newData) {
    isYearClicked.value = false;
    isMonthClicked.value = false;
    isWeekClicked.value = true;
    updateChartWithNewData(newData.foodRankings);
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

<style scoped>
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
