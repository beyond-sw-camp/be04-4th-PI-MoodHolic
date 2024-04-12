<template>
<html>
  <body>
    <div class="div">
      <header class="line-parent">

      </header>
      <section class="inner">
        <div class="rectangle-parent">
          <div class="frame-item"></div>
          <div class="wrapper">
            <h2 class="h2">
              <p class="p">오늘도 당신의</p>
              <p class="p1">기분을 듣고 싶습니다!</p>
            </h2>
          </div>
          <div class="sign-in-button">
            <div class="mood-holic-wrapper">
              <h1 class="mood-holic">Mood Holic</h1>
            </div>
            <div class="sign-in-button-child"></div>
          </div>
          <div class="google-login-button-wrapper">
            <div class="google-login-button">
              <div class="container">
                <b class="b">기존 계정으로 로그인</b>
              </div>
              <button class="create-account-button">
                <div class="create-account-button-child"></div>
                <img
                  class="create-account-button-item"
                  alt=""
                  src="@/assets/icon/MainPage/LogIn&SignUp/google.png"
                />

                <b class="b1">구글로 간편 로그인</b>
              </button>
            </div>
          </div>
          <div class="sign-in-header">
            <button class="rectangle-group">
              <div class="frame-inner"></div>
              <div class="rectangle-container">
                <div class="rectangle-div"></div>
                <img class="vector-icon" alt="" src="@/assets/icon/MainPage/LogIn&SignUp/kakao.png" />
              </div>
              <b class="b2">카카오톡으로 간편 로그인</b>
            </button>
          </div>
          <div class="frame-wrapper">
            <div class="parent">
              <b class="b3">아직 회원이 아니신가요?</b>
              <div class="frame">
                <b class="b4">새 계정으로 로그인</b>
              </div>
            </div>
          </div>
        </div>
      </section>
    </div>
  </body>
</html>

</template>

<script setup>

</script>

<style>
@import '@/assets/css/MainPage/LogIn&SignUp/global.css';
@import '@/assets/css/MainPage/LogIn&SignUp/LogIn&SignUp.css';
</style>