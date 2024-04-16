<template>
  <html>
  <body>
  <div class="div">
    <header class="line-parent">

    </header>
    <section class="inner">
      <div class="frame-parent">
        <div class="emotion-diary-parent">
          <div class="emotion-diary">
            <img
                class="image-3-icon"
                loading="lazy"
                alt=""
                src="../../assets/icon/MainPage/BeforeLogInMain/image-3@2x.png"/>

            <div class="div3">
              <b style="font-size: 40px;">인공지능 심리전문가</b>
              <span class="span" style="font-size: 30px;">가 다이어리에 적은 오늘의 기분을 분석해</span>
            </div>
          </div>
          <div class="wrapper">
            <h3 class="h3">
              <span> </span>
              <b class="b">음식, 음악, 영화, 조언</b>
              <span>을 추천하는 서비스를 제공합니다!</span>
            </h3>
          </div>
        </div>
        <div class="frame-wrapper">
          <footer class="frame-group">
            <div class="frame-container">
              <button class="rectangle-parent" @click="redirectToLogin">
                <div class="frame-item"></div>
                <b class="mood-holic">Mood Holic 시작하기</b>
                <div class="diary-writing-wrapper">
                  <img
                      class="diary-writing-icon"
                      alt=""
                      src="../../assets/icon/MainPage/BeforeLogInMain/diary-writing.png"/>
                </div>
              </button>
            </div>
            <div class="frame-div">
              <div class="ai-wrapper">
                <div class="ai">
                  다이어리 작성 후 AI가 점수를 내주고 그 점수에 따라 감정
                  아이콘이 자동으로 기록됩니다!
                </div>
              </div>
              <div class="rectangle-group">
                <div class="frame-inner"></div>
                <div class="frame-parent1">
                  <div class="container">
                    <img
                        class="icon"
                        loading="lazy"
                        alt=""
                        src="../../assets/icon/MainPage/BeforeLogInMain/-20240405--402@2x.png"/>
                  </div>
                  <button class="rectangle-container">
                    <div class="rectangle-div"></div>
                    <b class="b1">좋음이</b>
                  </button>
                </div>
                <div class="good-category-parent">
                  <div class="good-category">
                    <img
                        class="icon1"
                        loading="lazy"
                        alt=""
                        src="../../assets/icon/MainPage/BeforeLogInMain/-20240405--405@2x.png"/>
                  </div>
                  <button class="group-button">
                    <div class="frame-child1"></div>
                    <b class="b2">보통이</b>
                  </button>
                </div>
                <div class="parent">
                  <img
                      class="icon2"
                      alt=""
                      src="../../assets/icon/MainPage/BeforeLogInMain/-20240405--405-1@2x.png"/>

                  <button class="rectangle-parent1">
                    <div class="frame-child2"></div>
                    <b class="b3">나쁨이</b>
                  </button>
                </div>
              </div>
            </div>
          </footer>
        </div>
      </div>
    </section>
  </div>
  </body>
  </html>
</template>

<script setup>
  import { useRouter } from 'vue-router';

  const router = useRouter();

  const redirectToLogin = () => {
    router.replace('/login');
  };
</script>

<style>
  @import '@/assets/css/MainPage/BeforeLogInMain/global.css';
  @import '@/assets/css/MainPage/BeforeLogInMain/BeforeLogInMain.css';
</style>
