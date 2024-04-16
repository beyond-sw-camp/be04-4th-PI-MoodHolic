<template>

  <h2>게시물 수정</h2>
  <div class="form-group">
    <label for="editTitle">제목:</label>
    <input type="text" id="editTitle" v-model="editedFeed.summary">
  </div>
  <div class="form-group">
    <label for="editContent">내용:</label>
    <textarea style="height: 450px;" id="editContent" v-model="editedFeed.content"></textarea>
  </div>
  <div>

    <form @submit.prevent="uploadImage">
      <input type="file" @change="previewImage" /> <!-- @change 이벤트를 사용하여 파일 선택 시 previewImage 메서드 호출 -->
      <img :src="imagePreview"/> <!-- 이미지 미리보기 -->
      <br />
      <button type="submit" @click="uploadImage(1)" >저장</button>
      <button type="submit" @click="uploadImage(0)" >임시저장</button>
    </form>
  </div>

</template>

<script setup>
import { ref } from "vue";
let membersId  = ref(null);
const img = ref(null);
const fileInput = ref(null); // ref 선언


const getMemberId = async()=>{

  const authToken = 'Bearer '+localStorage.getItem('authToken');


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
        // console.log(data);
        const memberIdPattern = /memberId=(\d+)/;
        const memberIdMatch = data.match(memberIdPattern);
        if (memberIdMatch) {
          membersId = memberIdMatch[1];
          console.log("Member ID:", membersId);
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

const editedFeed = {
  content:'',
  date:'',
  imgPath: '',
  summary: '',
  member: {
    memberId: membersId
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
  editedFeed.member.memberId = membersId;
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

const previewImage = (event) => {
  const file = event.target.files[0];
  if (file) {
    const reader = new FileReader();
    reader.onload = () => {
      imagePreview.value = reader.result;
    };
    reader.readAsDataURL(file);
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

<style scoped>

</style>
