<template>
  <div style="margin-top: 5%; ">
    <div class="button-container">
      <button class="but" :class="{ 'active': showEmotion }" @click="showEmotionGraph">기분 그래프</button>
      <button class="but" :class="{ 'active': showMovie }" @click="showMovieGraph">추천 랭킹 분석</button>
    </div>

    <div v-if="showEmotion">

      <div class="graph">
        <div class="line" style="display: flex; ">
          <div style="width:20%; padding-top:10%">
            <img src='../../common/calendar/img0.png' style="max-width: 70%;" alt=""><br>
            <img src='../../common/calendar/img1.png' style="max-width: 70%;" alt=""><br>
            <img src='../../common/calendar/img2.png' style="max-width: 70%;" alt=""><br>
          </div>
          <div style="width: 80%;">
            <EmotionLineGraph/>
          </div>
        </div>
      </div>
    </div>

    <div v-if="showMovie">
      <div class="graph">
        <MovieBarGraph/>
      </div>
    </div>
  </div>
</template>

<script setup>
import MovieBarGraph from "@/components/header/statistics/Movie-BarGraph.vue";
import EmotionLineGraph from "@/components/header/statistics/Emotion-LineGraph.vue";

import { ref } from 'vue';

const showEmotion = ref(true);
const showMovie = ref(false);

const showEmotionGraph = () => {
  showEmotion.value = true;
  showMovie.value = false;
};

const showMovieGraph = () => {
  showEmotion.value = false;
  showMovie.value = true;
};
</script>

<style scoped>
  .graph {
    background-color: #FFF4CC;
    padding: 30px;
    border-radius: 30px;
    align-items: center;
    margin-left: 5%;
    margin-right: 5%;
  }

  .button-container {
    text-align: center;
    margin-bottom: 20px;
  }

  .button-container button {
    margin: 0 10px;
  }

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
    transition: background-color 0.3s ease;
  }

  .but:hover {
    background-color: #333333; /* 마우스를 올렸을 때 배경색 변경 */
    color: #FFFFFF; /* 글자색 변경 */
  }

  .but.active {
    background-color: #FEDB56;
    border-color: #FEDB56;
  }
</style>
