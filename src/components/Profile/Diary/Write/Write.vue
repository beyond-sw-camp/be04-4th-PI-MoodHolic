<template>
  <body>
  <div class="popup-overlay">
    <section class="inner">
      <div class=“layerPopup” v-if=showPopup>
        <div class=“popup-content”>
      <div class="day-of-week-label-parent">
        <div class="day-of-week-label">
          <div class="day-of-week-label-child"></div>
          <span class=“close” @click=closePopup>&times;</span>
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
                        src="@/assets/icon/Profile/Diary/Write/-20240405--218-1@2x.png"
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
                      src="@/assets/icon/Profile/Diary/Write/-20240405--218-1@2x.png"
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
                src="@/assets/icon/Profile/Diary/Write/rectangle-510.png"
            />

              <div class="image-container">
                <label for="imageInput" class="image-button">
                  <img :src="imageUrl" alt="Today Select Photo">
<!--                  <img-->
<!--                      class="image-button"-->
<!--                      src="@/assets/icon/Profile/Diary/Write/picture.png"-->
<!--                      alt="Select Image">-->
                </label>
                <input type="file" id="imageInput" @change="handleImageChange" style="display: none;">
              </div>

                <div class="div5">오늘을 대표하는 사진 1장을 담아주세요!</div>

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
        </div>
      </div>
    </section>
  </div>
  </body>
</template>

<script setup>
import {ref} from 'vue';

const showPopup = ref(true);
const openPopup = (index) => {
  showPopup.value = true;
};
const closePopup = () => {
  showPopup.value = false;
};

// 사진 파일 추가
const imageUrl = ref('@/assets/icon/Profile/Diary/Write/picture.png');
let selectedImage = imageUrl;

const handleImageChange = (event) => {
  const file = event.target.files[0];
  if (file) {
  const reader = new FileReader();
  reader.onload = () => {
  imageUrl.value = reader.result;
  selectedImage = file;
};
  reader.readAsDataURL(file);
}
};

const buttonClick = () => {
  alert('Button Clicked!');
};
</script>

<style>
  @import "@/assets/css/Profile/Diary/Write/global.css";
  @import "@/assets/css/Profile/Diary/Write/write.css";
  @import url('https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700&display=swap');
</style>