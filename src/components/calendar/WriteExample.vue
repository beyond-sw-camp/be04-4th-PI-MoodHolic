<template>
    
    <form>
        <textarea > 여기는 한줄평... </textarea>
        <textarea > 여기는 작성글... </textarea>
        
        <input type="button" value="제출.."> 
    </form>
    

          <h2>게시물 수정</h2>
          <div class="form-group">
            <label for="editTitle">제목:</label>
            <input type="text" id="editTitle" v-model="editedFeed.summary">
          </div>
          <div class="form-group">
            <label for="editContent">내용:</label>
            <textarea style="height: 450px;" id="editContent" v-model="editedFeed.content"></textarea>
          </div>
          <button @click="saveEditedFeed(1)">저장</button>
          <button @click="saveEditedFeed(0)">임시저장</button>

    
</template>

<script setup>
    import { ref } from "vue";
    let membersId  = ref(null);

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


    const saveEditedFeed = async (getStatus) => {
    const today = new Date();
    const authToken = 'Bearer ' + localStorage.getItem('authToken');
    const headers = {
        'Authorization': authToken,
        'Content-Type': 'application/json'
    };
    // 날짜를 원하는 형식으로 변환
    editedFeed.date = today.toISOString().slice(0, 10);
    editedFeed.status = getStatus;
    editedFeed.member.memberId = membersId;
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
    
        if (!response.ok) {
            throw new Error('Network response was not ok');
        }
    } catch (error) {
        console.error('There was a problem with the fetch operation:', error);
    }
};


</script>

<style scoped>

</style>