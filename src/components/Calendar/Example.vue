<template>
  <div>
    <calendar></calendar>
  </div>

  <br>

  <div style="background-color: #FFF4CC; padding:30px; width: 80%; border-radius: 30px;">
    <graph>  </graph>
  </div>
  <br>
  
  <div style="background-color: #FFF4CC; padding:30px; width: 80%; border-radius: 30px;">
    <div style="display: flex; ">
      <div style="width:20% ;"><br><br><br><br>
        <img src='./img0.png'  style=" max-width: 70%;" alt=""> <br>
        <img src='./img1.png'  style=" max-width: 70%;" alt=""> <br>
        <img src='./img2.png'  style=" max-width: 70%;" alt=""> <br>
      </div>
      <div style="width: 80%; ">
          <lines>
          </lines>
      </div>
    </div>
  </div>
  

  <div style="background-color: #FFF4CC; padding:30px; width: 80%; border-radius: 30px;">
    <MovieRanking>  </MovieRanking>
  </div>

</template>

<script setup>
  import calendar from './calendar.vue';
  import lines from './Emotion-LineGraph.vue';
  import graph from './Emotion-BarGraph.vue';
  import MovieRanking from './Movie-BarGraph.vue';
</script>

<style scoped>

</style>