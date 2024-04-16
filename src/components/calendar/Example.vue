<template>
  <div>
    <Calendar/>
  </div>

  <br>

  <div style="background-color: #FFF4CC; padding:30px; width: 80%; border-radius: 30px;">
    <EmotionBarGraph/>
  </div>
  <br>

  <div style="background-color: #FFF4CC; padding:30px; width: 80%; border-radius: 30px;">
    <div style="display: flex; ">
      <div style="width:20% ;"><br><br><br><br>
        <!-- <img src='@/components/calendar/img0.png'  style=" max-width: 70%;" alt=""> <br> -->
        <img src='@/components/calendar/img0.png'  style=" max-width: 70%;" alt=""> <br>
        <img src='@/components/calendar/img1.png'  style=" max-width: 70%;" alt=""> <br>
        <img src='@/components/calendar/img2.png'  style=" max-width: 70%;" alt=""> <br>
      </div>
      <div style="width: 80%; ">
          <EmotionLineGraph/>
      </div>
    </div>
  </div>


  <div style="background-color: #FFF4CC; padding:30px; width: 80%; border-radius: 30px;">
    <MovieBarGraph/>
  </div>

</template>

<script setup>
import MovieBarGraph from "@/components/calendar/Movie-BarGraph.vue";
import EmotionLineGraph from "@/components/calendar/Emotion-LineGraph.vue";
import EmotionBarGraph from "@/components/calendar/Emotion-BarGraph.vue";
import Calendar from "@/components/main/MemberMain.vue";</script>

<style scoped>

</style>
