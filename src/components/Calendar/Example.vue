<template>
  <div>
    <calendar/>
  </div>

  <br>

  <div style="background-color: #FFF4CC; padding:30px; width: 80%; border-radius: 30px;">
    <emotion-bar-graph/>
  </div>
  <br>

  <div style="background-color: #FFF4CC; padding:30px; width: 80%; border-radius: 30px;">
    <div style="display: flex; ">
      <div style="width:20% ;"><br><br><br><br>
        <img src='@/components/Calendar/img0.png'  style=" max-width: 70%;" alt=""> <br>
        <img src='@/components/Calendar/img1.png'  style=" max-width: 70%;" alt=""> <br>
        <img src='@/components/Calendar/img2.png'  style=" max-width: 70%;" alt=""> <br>
      </div>
      <div style="width: 80%; ">
          <emotion-line-graph/>
      </div>
    </div>
  </div>


  <div style="background-color: #FFF4CC; padding:30px; width: 80%; border-radius: 30px;">
    <movie-bar-graph/>
  </div>

</template>

<script setup>
import MovieBarGraph from "@/components/Calendar/Movie-BarGraph.vue";
import EmotionLineGraph from "@/components/Calendar/Emotion-LineGraph.vue";
import EmotionBarGraph from "@/components/Calendar/Emotion-BarGraph.vue";
import Calendar from "@/App.vue";
</script>

<style scoped>

</style>
