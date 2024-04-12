<template>
  <VCalendar expanded title-position="center"  :max-date="new Date()"  :attributes='attributes' >
    <template #vc-day-content = "{dayTitle, attributes}">
        <img :src="`${ happyImg }`">
    </template>
    <template #day-popover="{ dayTitle, attributes }">
    <div class="px-1">
          
            <a
              v-for="{ key, customData,dates } in attributes"
              :key="key"
              style="font-size: 40px;"
            >
        <div class="pop-over-content" @click="clickContent(customData.diaryId)" align="center">
            <img :src="`${customData.description}`"  style=" max-width: 100px;" alt=""> <hr style="margin-top: 0; margin-bottom: 0;">
            <span style="font-size: 15px;">요약: {{ customData.summary }}</span> <br>
            <span style="font-size: 15px;">내용: {{ customData.content}}</span> <br>  
        </div>
      </a>
    </div>
  </template>
  </VCalendar>

  <div class="popup-overlay" v-if="showPopup">
    <div class="popup-content">
      <span class="close" @click="closePopup">&times;</span>
        <h2>상세 보.기.</h2>
          기분 한줄: {{diary.diary.summary}}<br>
          내용: {{diary.diary.content}}<br>
          날짜: {{diary.diary.date}}<br>
          기분: {{diary.emotion}}<br>
          <div style="display: flex;">
            <div style="margin-right: 50px;">
              <h3>추천 음식</h3>
              이름: {{diary.food.foodName}}<br>
              종류: {{diary.food.foodCategory}}<br>
              맵기: {{diary.food.foodSpicy}}<br>
            </div>
            <div>
              <h3 style="margin-right: 50px;" >추천 영화</h3>
              이름: {{diary.movie.movieName}}<br>
              장르: {{diary.movie.movieGenre}}<br>
            </div>
            <div>
              <h3 style="margin-right: 50px;">추천 음악</h3>
              이름: {{diary.music.musicName}}<br>
              가수: {{diary.music.singer}}<br>
              장르: {{diary.music.musicGenre}}<br>
            </div>
          </div>
    </div>
  </div>



</template>

<script setup>
import {ref} from 'vue';
import happyImg from './img0.png'; 
import sadImg from './img1.png';
import sosoImg from './img2.png';



const calendar = ref(null);
const date = ref([]);
const attributes = ref([]);
const diary = ref(null);


const showPopup = ref(false);

const openPopup = (index) => {
  getDiary(index);
  showPopup.value = true;
};


const closePopup = () => {
  showPopup.value = false;
};

function moveToday() {
  calendar.value.move(new Date());
}


const getMemberDate = async()=> {
  await fetch('http://localhost:8888/calendar/1')
  .then(response => {
            if (!response.ok) {
            throw new Error('Network response was not ok');
            }
            
            return response.json();
  })
  .then(data=>{
    console.log(data);
    date.value = data;
    console.log(date.value);
    updateAttributes(data.length); // Update attributes after receiving data
  })
  };
getMemberDate();


function updateAttributes(dataLength) {
  attributes.value = [];
  for (let i = 0; i < dataLength; i++) {
    attributes.value.push({
        key: `attribute-${i}`,
        highlight: {
          color: checkStatus(date.value[i].status),
          // fillMode: 'solid',
          contentClass: 'outline',
        },
        popover: {

      visibility: 'hover',
      hideIndicator: true,
    },
        customData: {
          description: getEmotion(date.value[i].emotion),
          summary: date.value[i].summary
          ,content: date.value[i].content
          ,emotion: date.value[i].emotion
          ,diaryId: date.value[i].diaryId
        },
        dates: new Date(date.value[i].year, date.value[i].month-1, date.value[i].day),
      });
  }
  // console.log(attributes);
}

function checkStatus(status) {
  if(status=='1'){
    return 'green';
  }else{
    return 'gray'; 
  }
}

function getEmotion(emotion) {
  if(emotion=='좋음'){
    // return '☀️';
    return happyImg;
  }else if(emotion=='보통'){
    return sosoImg;
  }
  else{
    return sadImg;
}
}

function clickContent(diaryId) {
  console.log(diaryId);
  openPopup(diaryId);
}


const getDiary = async(index)=> {
  await fetch(`http://localhost:8888/diary/${index}`)
  .then(response => {
    if (!response.ok) {
      throw new Error('Network response was not ok');
    }            
    return response.json();
  })
  .then(data=>{
    console.log(data);
    diary.value = data;
    console.log(diary.value);
  })
  };
  
</script>





<style>

.vc-container .vc-weekday-1, .vc-container .vc-weekday-7 {
  color: red;
  /* height: 100; */
}
.vc-weeks{
  
  margin-top: 60px;
  margin-bottom: 30px;
  margin-right: 30px;
  margin-left: 30px;
  /* width: 1000px; */
  background-color: white;
  border-radius: 30px;
}
.vc-weekdays{
  margin-top: 30px;
  margin-bottom: 40px;
  /* width: 700px; */
}

.vc-day-box-center-center{
  align-items: self-start;
}
.vc-week{
  height: 100px;
  /* width: 100px; */
  
}


.vc-title{
  font-size: 40px;
  background-color: rgba(255, 0, 0, 0);
}
.vc-header{
  margin-top: 100px;
}


.vc-pane{
  background-color: #FFD930; 
  border-radius: 30px;
  border: 3px solid rgb(202, 202, 37);
}

.vc-arrow{
  background-color: rgba(0,0,0,0);
}
.vc-top{
  margin-top: 100px;
}

.vc-header.is-lg{
  margin-top: 50px;
  
}
.px-2{
  padding-right: 10px;
}
.vc-highlights .vc-day-layer{
  /* padding-right: 60px; */
  background-color: #67ff303e;
  border-radius: 30px;
  margin: 5px ;

}
.vc-day-layer{
  align-items: left;
}

.vc-day-content{
  font-size: 100px;
  width: 500px;
}




/* 수정 팝업 스타일 */
.popup-overlay {
        position: fixed;
        top: 0;
        left: 0;
        width: 100%;
        height: 100%;
        background-color: rgba(0, 0, 0, 0.5);
        z-index: 999;
    }

    .popup-content {
        position: fixed;
        top: 50%;
        left: 50%;
        transform: translate(-50%, -50%);
        background-color: white;
        padding: 20px;
        border-radius: 5px;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.5);
        width: 800px;
        height: 800px;
    
    }

    .popup-content h2 {
        margin-bottom: 20px;
    }

    .popup-content .form-group {
        margin-bottom: 20px;
    }

    .popup-content label {
        font-weight: bold;
    }

    .popup-content input[type="text"],
    .popup-content textarea {
        width: 100%;
        padding: 10px;
        border-radius: 5px;
        border: 1px solid #ccc;
        box-sizing: border-box;
        resize: none;
            height: auto; /* 초기 높이를 자동으로 설정 */
    }

    .popup-content button {
        padding: 10px 20px;
        background-color: #4CAF50;
        color: white;
        border: none;
        border-radius: 5px;
        cursor: pointer;
    }
    .popup-content button:hover {
        background-color: #45a049;
    }

</style>

