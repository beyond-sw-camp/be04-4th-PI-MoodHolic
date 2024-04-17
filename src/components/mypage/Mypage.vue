<template>
  <div class="page-container">
    <section class="content-container">
      <h1 class="title" style="font-size: 24px">{{nickname}} 님!</h1>
      <h2 class="subtitle">오늘 하루 기분은 어떠신가요?</h2>

      <div class="card-container">
        <div class="card" @click="changeRouter('/mypage/info')">
          <img class="icon" alt="내 정보 아이콘" src="@/assets/icon/Profile/Info/my.png"/>
          <p class="card-title">내 정보</p>
        </div>
        <div class="card" @click="changeRouter('/mypage/diary')">
          <img class="icon" alt="내 다이어리 아이콘" src="@/assets/icon/Profile/Info/diary.png"/>
          <p class="card-title">내 다이어리</p>
        </div>
        <div class="card" @click="changeRouter('/mypage/aigood')">
          <img class="icon" alt="AI 추천 목록 아이콘" src="@/assets/icon/Profile/Info/ai.png"/>
          <p class="card-title">AI 추천 목록</p>
        </div>
      </div>
    </section>
  </div>
</template>

<style scoped>
.page-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  width: 100%;
  padding: 2rem;
  box-sizing: border-box;
}

.content-container {
  max-width: 1024px;
  width: 100%;
  text-align: center;
}

.title {
  font-size: 2.5rem;
  margin-top: 30px;
}

.subtitle {
  font-size: 1.25rem;
  margin-bottom: 2rem;
  margin-top: 30px;
  font-weight: lighter;
}

.card-container {
  display: flex;
  justify-content: center;
  flex-wrap: wrap;
  gap: 1rem;
  margin: 3rem 0;
}

.card {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  padding: 1rem;
  width: calc(33.333% - 2rem); /* Three cards per row, accounting for the gap */
  box-sizing: border-box;
  border-radius: 20px; /* Adjusted to match the design */
  background: #fff;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  transition: transform 0.3s ease;
  cursor: pointer;
  min-width: 200px; /* Minimum width of card */
  max-width: 300px; /* Maximum width of card */
  flex: 1;
}

.card:hover {
  transform: translateY(-5px);
}

.icon {
  width: 64px; /* Adjusted icon size */
  height: auto;
  margin-bottom: 1rem;
}

.card-title {
  font-size: 1rem;
  color: #333;
  margin: 0;
}

@media (max-width: 768px) {
  .card {
    width: calc(50% - 2rem); /* Two cards per row on smaller screens */
  }
}

@media (max-width: 480px) {
  .card {
    width: 90%; /* Full width cards on very small screens */
  }
}

/* This will ensure that the icons gray out as seen in the screenshot */
.development .icon {
  filter: grayscale(100%);
}
</style>

<script setup>
  import { ref, onMounted } from 'vue';
  import { useStore } from 'vuex';
  import router from "@/router/router.js";

  const store = useStore();
  let memberId = store.state.memberId;
  let nickname = store.state.nickname;
  const getMemberId = async()=>{
  console.log(`global: ${memberId}`);
  if(memberId!=null) {
    console.log(`이미 회원 정보 있음 memberId: ${memberId}`);
    return;
  }
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
    const memberEmail = /email=(\S+@\S+\.\S+)/;
    const memberNickPattern = /nickname=([\uAC00-\uD7A3\w]+)/;

    const memberIdMatch = data.match(memberIdPattern);
    const memberEmailMatch = data.match(memberEmail);
    const memberNickMatch = data.match(memberNickPattern);

    if (memberIdMatch) {
       memberId = memberIdMatch[1];
       nickname = memberNickMatch[1];
       const email = memberEmailMatch[1];
       console.log("Member ID:", memberId); // "Member ID: 4"
       console.log("Member:", nickname); // "Member ID: 4"

       updateMemberId(memberId);
       updateNickname(nickname);
       updateEmail(email);
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

const updateMemberId = (newValue) => {
  store.commit('setGlobalVariable', newValue);
};
const updateNickname = (newValue) => {
  store.commit('setNickname', newValue);
}
const updateEmail = (newValue) => {
  store.commit('setEmail', newValue);
}
  const changeRouter = (route) => {
    router.push(route);
  };

</script>
