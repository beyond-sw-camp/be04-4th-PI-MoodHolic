<template>
  <div class="main-container">
    <header>
      <header-component />
    </header>

    <div class="login-container">
      <div class="login-card">
        <h2 class="greeting">오늘도 당신의</h2>
        <h2 class="greeting">기분을 듣고 싶습니다!</h2>

        <h1 class="brand-title">Mood Holic</h1>
        <hr class="divider" />

        <p class="signup-invitation">새 계정으로 회원가입</p>

        <!-- 구글 로그인 버튼 -->
        <button @click="onGoogleLogin" class="login-button google">
          <img class="button-icon" alt="Google Logo" src="@/assets/icon/MainPage/SighUp/google.png" />
          구글 계정으로 간편 회원가입
        </button>

        <!-- 카카오 로그인 버튼 -->
        <button @click="onKakaoLogin" class="login-button kakao">
          <img class="button-icon" alt="Kakao Logo" src="@/assets/icon/MainPage/SighUp/kakao.png" />
          카카오 계정으로 간편 회원가입
        </button>
      </div>
    </div>
  </div>
</template>

<script setup>
function onGoogleLogin() {
  window.location.href = ' http://localhost:8888/login/oauth2/code/google';
}

function onKakaoLogin() {
  window.location.href = 'http://localhost:8888/login/oauth2/code/kakao';

}
</script>

<style scoped>

.main-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  height: 100vh;
  padding: 20px;
  box-sizing: border-box;
}

.login-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  width: 100%;
}

.login-card {
  background-color: #f5f5f5;
  padding: 40px;
  border-radius: 10px;
  box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
  text-align: center;
  max-width: 400px;
  width: 100%;
  margin: auto;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
}

.brand-title {
  margin: 20px 0 0.1rem;
  font-size: 1.5rem;
  font-family: 'Inter', sans-serif;
}

.divider {
  margin: 20px 0;
}

.login-button {
  background-color: #ffffff;
  border: 1px solid #ddd;
  border-radius: 15px;
  padding: 10px 20px;
  margin-top: 10px;
  cursor: pointer;
  display: flex;
  align-items: center;
  justify-content: center;
  width: 100%;
  box-sizing: border-box;
  margin-bottom: 1rem;
}

.button-icon {
  margin-right: 10px;
  width: 24px; /* 이미지 크기 조정 */
  height: 24px; /* 이미지 크기 조정 */
}

.signup-invitation {
  margin: 2rem 0 1.5rem;
  color: #555; /* 이 부분은 원하는 색상으로 변경해 주세요 */
  font-size: 1rem;
  font-weight: 600;
}

.greeting {
  font-size: 1.2rem;
  margin: 0;
  padding: 0;
  line-height: 1.2;
  font-family: 'Inter', sans-serif;
}

@import url('https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700&display=swap');
</style>