<template>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8" />
    <meta name="viewport" content="initial-scale=1, width=device-width" />

    <link rel="stylesheet" href="./global.css" />
    <link rel="stylesheet" href="./index.css" />
    <link
      rel="stylesheet"
      href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700&display=swap"
    />
  </head>
  <body>
    <div class="div">
      <header class="line-parent">
        <div class="frame-child"></div>
        <img
          class="moodholiclogo-2-icon"
          loading="lazy"
          alt=""
          src="./public/moodholiclogo-25@2x.png"
        />

        <h3 class="h3">오늘 날씨</h3>
        <div class="image-1-parent">
          <img
            class="image-1-icon"
            loading="lazy"
            alt=""
            src="./public/image-16@2x.png"
          />

          <div class="div1">14 ℃</div>
        </div>
        <img
          class="group-icon"
          loading="lazy"
          alt=""
          src="./public/group7@2x.png"
        />

        <img class="group-icon1" alt="" src="./public/group3.svg" />

        <img class="uimgraph-bar-icon" alt="" src="./public/uimgraphbar.svg" />
      </header>
      <section class="inner">
        <div class="day-of-week-label-parent">
          <div class="day-of-week-label">
            <div class="day-of-week-label-child"></div>
            <div class="wrapper">
              <div class="div2">4월 5일 금요일</div>
            </div>
            <div class="frame-parent">
              <div class="frame-group">
                <div class="date-picker-parent">
                  <div class="date-picker">
                    <div class="mood-palette">
                      <img
                        class="icon"
                        loading="lazy"
                        alt=""
                        src="./public/-20240405--218-1@2x.png"
                      />

                      <div class="current-mood">
                        <div class="div3">오늘의 기분</div>
                      </div>
                    </div>
                  </div>
                  <input
                    class="frame-item"
                    placeholder="오늘의 기분을 한줄 요약해주세요!"
                    type="text"
                  />
                </div>
                <div class="frame-wrapper">
                  <div class="parent">
                    <img
                      class="icon1"
                      loading="lazy"
                      alt=""
                      src="./public/-20240405--218-2@2x.png"
                    />

                    <div class="container">
                      <div class="div4">하루 이야기</div>
                    </div>
                  </div>
                </div>
              </div>
              <textarea class="cancel-button" placeholder="하루 동안 이야기를 들려주세요!" rows="{17}" cols="{32}"></textarea>
            </div>
            <div class="vector-parent">
              <img
                class="frame-inner"
                alt=""
                src="./public/rectangle-510.svg"
              />

              <div class="thumbnail-label">
                <img
                  class="image-upload-input"
                  loading="lazy"
                  alt=""
                  src="./public/vector5.svg"
                />
              </div>
              <div class="div5">오늘을 대표하는 사진 1장을 남겨주세요!</div>
            </div>
          </div>
          <div class="frame-container">
            <button class="rectangle-parent">
              <div class="rectangle-div"></div>
              <div class="div6">임시저장</div>
            </button>
            <button class="rectangle-group">
              <div class="frame-child1"></div>
              <div class="div7">취소</div>
            </button>
            <button class="rectangle-container">
              <div class="frame-child2"></div>
              <div class="div8">작성완료</div>
            </button>
          </div>
        </div>
      </section>
    </div>
  </body>
</html>
</template>

<script setup>

</script>

<style scoped>

</style>