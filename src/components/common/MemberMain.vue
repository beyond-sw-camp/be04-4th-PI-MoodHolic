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
            <img :src="`${customData.description}`"  style=" max-width: 100px;" alt="">
            <!-- <hr style="margin-top: 0; margin-bottom: 0;"> -->
            <!-- <span style="font-size: 15px;">요약: {{ customData.summary }}</span> <br>
            <span style="font-size: 15px;">내용: {{ customData.content}}</span> <br>   -->
        </div>
      </a>
    </div>
  </template>
  </VCalendar>

  <div class="popup-overlay" v-if="showPopup">
    <div class="popup-content">
      <span class="close" @click="closePopup">&times;</span>

        <div style="display:flex;" align="center">
          <div style="border-radius: 30px; background-color: white; padding: 30px; margin: 10px; height:100%; width:40%; min-width: 100px; ">
            <img :src="`${clickedEmotionImg}`"  style=" max-width: 100%; height: auto;" alt="">
          </div>

          <div style="border-radius: 30px; background-color: white; padding: 30px; margin: 10px; text-align: left; font-size: 3vw; width:60%;  padding-left:30px; width:100%;">
            <div>🗓️ {{diary.diary.date}}</div><br>
            <div>❤️ {{diary.emotion}}<br></div>
          </div>
        </div>

        <div style="border-radius: 30px; background-color: white; padding: 15px; padding-top: 1px;   margin: 10px;  ">
          <h3 style="font-size: 25px;">✨ 오늘의 기분<hr></h3> {{diary.diary.summary}}<br><br><br>
          <h3 style="font-size: 25px;">🌠 하루 이야기<hr></h3> {{diary.diary.content}}<br><br>
        </div>
        <br>
        <div align="center" style="font-size:30px; background-color: pink; border-radius: 20px; font-weight: 1000;">AI's PICK</div>
        <br>

        <div style="display: flex; width:100%; font-weight: 700;" align="center" >
          <div style="border-radius: 30px; background-color: white;  padding-bottom: 7%;   margin: 0 auto; text-align: left; width:30%; ">
            <h3 align="center" > 음식</h3>
            <div style="padding-left: 30%;">🎬 {{diary.food.foodName}}<br></div>
            <div style="padding-left: 30%;">❖ {{diary.food.foodCategory}}<br></div>
            <div style="padding-left: 30%;">🌶️ {{diary.food.foodSpicy}}<br></div>
          </div>
          <div style="border-radius: 30px; background-color: white;  padding-bottom: 7%;   margin: 0 auto; text-align: left; width:30%">
            <h3 align="center"> 영화</h3>
            <div style="padding-left: 30%;">🎬 {{diary.movie.movieName}}<br></div>
            <div style="padding-left: 30%;">❖ {{diary.movie.movieGenre}}<br></div>
          </div>
          <div style="border-radius: 30px; background-color: white;  padding-bottom: 7%;   margin: 0 auto; text-align: left; width:30%">
            <h3 align="center"> 음악</h3>
            <div style="padding-left: 30%;">🎵 {{diary.music.musicName}}<br></div>
            <div style="padding-left: 30%;">🎤 {{diary.music.singer}}<br></div>
            <div style="padding-left: 30%;">❖ {{diary.music.musicGenre}}<br></div>
          </div>
        </div>
     </div>
  </div>

  <button class="but" @click="openWritePopup()" style="margin-left: 35%; margin-right: 30%;"> 글쓰기</button>


  <div class="popup-overlay" v-if="writeActive">
    <div class="popup-content">
          <span class="close" @click="closeWritePopup">&times;</span>
          <h2 align = "center"> 다이어리 작성</h2>
          <div class="form-group">
            <img
                        class="icon"
                        loading="lazy"
                        alt=""
                        src="@/assets/icon/Profile/Diary/Write/-20240405--218-1@2x.png"
                    />
            <label for="editTitle">오늘의 기분</label>
            <!-- <input type="text" id="editTitle" v-model="" > -->
            <input type="text" id="editTitle" placeholder="오늘의 기분을 한줄 요약해 주세요!"  v-model="editedFeed.summary">
          </div>
          <div class="form-group">
            <img
                      class="icon1"
                      loading="lazy"
                      alt=""
                      src="@/assets/icon/Profile/Diary/Write/-20240405--218-1@2x.png"
            />
            <label for="editContent" >하루 이야기</label>
            <!-- <textarea style="height: 450px;" id="editContent" v-model="editedFeed.boardContent"></textarea> -->
            <textarea placeholder="하루 동안 이야기를 들려주세요!" style="height: 450px;"  v-model="editedFeed.content"/>
          </div>
          
          <div class="imgForm">
            <form @submit.prevent="uploadImage">
                    <input id="imgUpload" type="file" @change="previewImage" hidden/> <!-- @change 이벤트를 사용하여 파일 선택 시 previewImage 메서드 호출 -->
                    <label for="imgUpload">
                        <img class="img" v-if="!imgOn" src="@/assets/icon/Profile/Diary/Write/picture.png" />
                        <img class="img" :src="imagePreview" /> <!-- 이미지 미리보기 -->
                    </label>
                      <!-- <img class="imgPrev" :src="imagePreview" /> 이미지 미리보기 -->
                    <br/>
            </form>
          </div>
          <br><br>
          <div class="but-group">
            <button class="but" type="submit" @click="uploadImage(1)" >저장</button> 
            <button class="but" type="submit" @click="uploadImage(0)" >임시저장</button>
          </div>
        </div>
  </div>
</template>

<script setup>
import {ref} from 'vue';
import happyImg from '@/assets/icon/MainPage/AfterLogInMain/-20240405--402@2x.png';
import sadImg from '@/assets/icon/MainPage/AfterLogInMain/-20240405--405-1@2x.png'
import sosoImg from '@/assets/icon/MainPage/AfterLogInMain/-20240405--4053@2x.png'

let memberId = ref(null);
const writeActive = ref(false);

const openWritePopup = ()=>{
  writeActive.value=true;
}
const closeWritePopup = ()=>{
  writeActive.value=false;
}
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
       getMemberDate();
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

const calendar = ref(null);
const date = ref([]);
const attributes = ref([]);
const diary = ref(null);
let clickedEmotionImg = ref('');

// const memberId = ref([]);
// try{
//   memberId =
// }catch(e){

// }


const showPopup = ref(false);

const openPopup = (index) => {
  getDiary(index);
  showPopup.value = true;
};


const closePopup = () => {
  showPopup.value = false;
};


const getMemberDate = async()=> {
  console.log(memberId);
  await fetch(`http://localhost:8888/calendar/${memberId}`)
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
    clickedEmotionImg = getEmotion(diary.value.emotion);
    console.log(clickedEmotionImg);
  })
  };

  const img = ref(null);
  const fileInput = ref(null); // ref 선언
  const editedFeed = {
        content:'',
        date:'',
        imgPath: '',
        summary: '',
        member: {
            memberId: memberId
        },
        status:'',
    };

    console.log(Date.now());


    const saveEditedFeed = async (imgUrl,getStatus) => {
        const today = new Date();
        const authToken = 'Bearer ' + localStorage.getItem('authToken');
        const headers = {
            'Authorization': authToken,
            'Content-Type': 'application/json'
        };
        // 날짜를 원하는 형식으로 변환
        console.log(today);
        const year = today.getFullYear();
        const month = String(today.getMonth() + 1).padStart(2, '0');
        const day = String(today.getDate()).padStart(2, '0');
        const formattedDate = `${year}-${month}-${day}`; // "2024-04-15"와 같은 형식으로 반환됩니다.

        editedFeed.status = getStatus;
        editedFeed.member.memberId = memberId;
        editedFeed.date = formattedDate;
        editedFeed.imgPath = imgUrl;
        console.log(editedFeed);

        try {
            const response = await fetch(`http://localhost:8888/diary`, {
                method: 'POST',
                headers: headers,
                credentials: 'include',
                body: JSON.stringify(editedFeed) // 객체를 JSON 문자열로 변환하여 요청 본문으로 설정
            }).then(data=>{
                console.log(data);
            });
        
        } catch (error) {
            console.error('There was a problem with the fetch operation:', error);
        }
    };

    const imagePreview = ref(null); // 이미지 미리보기 URL
    const imgOn = ref(false);
    const previewImage = (event) => {
        const file = event.target.files[0];
        if (file) {
            const reader = new FileReader();
            reader.onload = () => {
                imagePreview.value = reader.result;
            };
            reader.readAsDataURL(file);
            imgOn.value = true;
        }   
    };

    const uploadImage = async(status) => {
        const authToken = 'Bearer ' + localStorage.getItem('authToken');
        const headers = {
            'Authorization': authToken,
        };
        const fileInput = document.querySelector('input[type="file"]');
        const file = fileInput.files[0];
        if(file ==null) {
            saveEditedFeed("",status);
            return;
        }
        const formData = new FormData();
        formData.append('file', file);

        // fetch(`http://localhost:8000/member-service/image/profile/${memberId.value}`, {
        await fetch(`http://localhost:8888/image`, {
            method: 'POST',
            headers: headers,
            credentials: 'include',
            body: formData
        })
        .then(response => {
            // 서버 응답을 JSON으로 파싱하지 않음
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            // 응답 반환
            return response.json();
        })
        .then(data => {
            console.log('Image URL:', data);
            saveEditedFeed(data.imgUrl,status);
        })
        .catch(error => {
            console.error('Error:', error);
        });
    };

</script>





<style>
@import "/src/assets/css/Profile/Diary/Write/global.css";
  @import "@/assets/css/Profile/Diary/Write/write.css";
  @import url('https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700&display=swap');

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
        background-color: #b1beff;
        padding: 20px;
        border-radius: 30px;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.5);
        width: 75%;
        max-width: 800px;
        height: 80%;
        overflow-y: auto; /* 수직 스크롤 활성화 */
        max-height: 80vh; /* 팝업 창의 최대 높이 지정 */

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
    .img{
      height: 250px;
    }
    .imgForm{
      background-color: #b7b7b7;
      height: 300px;
      display: flex;
      justify-content: center; /* 수평 가운데 정렬 */
      align-items: center; /* 수직 가운데 정렬 */
    }

    .but-group{
      display: flex;
      justify-content: center; /* 수평 가운데 정렬 */
      align-items: center; /* 수직 가운데 정렬 */
    }
    .but {
      
      margin: 20px;
      padding: 10px;
      border-radius: 10px;
      border: 1px solid #D9D9D9;
      background-color: #D9D9D9;
      color: #000000;
      font-size: 30px;
      font-weight: 600;
      font-family: 'Inter', sans-serif;
      width: 30%;
    }

    .but:hover {
      background-color: #333333; /* 마우스를 올렸을 때 배경색 변경 */
    }

    .but.active {
      background-color: #FEDB56;
      border-color: #FEDB56;
    }
</style>

