<template>
  <div class="main-container">
    <div class="login-card">
      <div class="greeting-wrapper">
        <h2 class="greeting">오늘도 당신의</h2>
        <h2 class="greeting">기분을 듣고 싶습니다!</h2>
      </div>

      <div>
        <div class="brand-title-wrapper">
          <h1 class="brand-title">Mood Holic</h1>
        </div>
        <p class="existing-account">기존 계정으로 로그인</p>
        <div class="login-button-wrapper">
          <button @click="onGoogleLogin" class="login-button google">
            <img class="button-icon" alt="Google 로고" src="@/assets/icon/MainPage/LogIn&SignUp/google.png" />
            구글 계정으로 간편 로그인
          </button>
          <button @click="onKakaoLogin" class="login-button kakao">
            <img class="button-icon" alt="Kakao 로고" src="@/assets/icon/MainPage/LogIn&SignUp/kakao.png" />
            카카오톡으로 간편 로그인
          </button>
        </div>
      </div>

      <div class="signup-invitation">
        <p class="signup-invitation">아직 회원이 아니신가요?</p>
        <button class="signup-button" style="cursor: pointer;" @click="SignUp('/signup')" >새 계정으로 회원가입</button>
      </div>
    </div>
  </div>
</template>

<script setup>
import router from "@/router/router.js";

const SignUp = (route) => {
  router.push(route);
};

const onGoogleLogin = async () => {
  window.location.href = 'http://localhost:30004/oauth2/authorization/google';
};

const onKakaoLogin = async () => {
  window.location.href = 'http://localhost:30004/oauth2/authorization/kakao';
};

</script>



<style scoped>
  @import url('https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700&display=swap');

.main-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  min-height: 100vh;
  background-color: #eceff1; /* 배경색 */
  padding: 20px;
}

.login-card {
  background-color:  #f5f5f5;
  padding: 50px;
  border-radius: 10px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  text-align: center;
  max-width: 450px;
  width: 100%;
  height: auto;
  min-height: 590px;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  margin-bottom: auto;
}

.greeting-wrapper {
  display: flex;
  flex-direction: column;
  align-items: center;
  margin-bottom: 0; /* 여백 제거 */
}

.greeting {
  font-family: 'Inter', sans-serif;
  margin: 0;
  font-weight: 500;
  line-height: 1.1;
  display: block;
}

.brand-title-wrapper {
  position: relative;
  padding-bottom: 5px;
}

.brand-title-wrapper::after {
  content: '';
  display: block;
  width: 100%; /* 밑줄의 너비를 조정 */
  height: 1px;
  background-color: #8a8989;
  position: absolute;
  left: 50%;
  transform: translateX(-50%); /* 중앙 정렬 */
  bottom: 0;
}

.brand-title {
  font-family: 'Inter', sans-serif;
  font-weight: 700;
  font-size: 2rem;
  color: #000;
  text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.25);
  margin-bottom: 0;
  padding-bottom: 0.5rem;
}

.login-button-wrapper {
  display: flex;
  flex-direction: column;
  gap: 10px;
  margin-top: 0; /* 버튼과 타이틀 사이의 여백 */
}

.login-button {
  font-family: 'Inter', sans-serif;
  border: 1px solid #ccc;
  border-radius: 22px;
  padding: 12px 20px;
  font-size: 1rem;
  font-weight: 500;
  display: flex;
  align-items: center;
  justify-content: center;
  gap: 12px;
  background-color: #f9f9f9;
  cursor: pointer;
  margin-bottom: 0;
  margin-top: 0.5rem;
}

.button-icon {
  width: 26px;
  height: 26px;
}

.signup-invitation {
  margin-top: 15px;
  font-family: 'Inter', sans-serif;
  font-weight: 700; /* 진하게 */
  font-size: 1rem;
  text-align: center;
}

.signup-button {
  background: none;
  border: none;
  color: #0077cc;
  padding: 0;
  cursor: pointer;
  text-decoration: underline;
  font-weight: 700;
  margin-top: 10px;
}

.existing-account {
  font-family: 'Inter', sans-serif;
  font-weight: 500;
  font-size: 1rem;
  color: #666;
  margin-top: 50px;
  margin-bottom: 30px;
  padding: 0;
}

</style>
