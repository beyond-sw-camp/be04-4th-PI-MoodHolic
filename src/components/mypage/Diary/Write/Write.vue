<template>
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
                        src="../../../../assets/icon/Profile/Diary/Write/-20240405--218-1@2x.png"
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
                      src="../../../../assets/icon/Profile/Diary/Write/-20240405--218-1@2x.png"
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
                src="../../../../assets/icon/Profile/Diary/Write/rectangle-510.png"
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

<style scoped>
  @import url('https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700&display=swap');
  body {
    margin: 0;
    line-height: normal;
  }

  :root {
    /* fonts */
    --font-inter: Inter;

    /* font sizes */
    --font-size-2xl: 21px;
    --font-size-mid: 17px;
    --font-size-xs: 12px;
    --font-size-mini: 15px;
    --font-size-4xl: 23px;
    --font-size-lg: 18px;
    --font-size-11xl: 30px;
    --font-size-5xl: 24px;
    --font-size-6xl: 25px;
    --font-size-xl: 20px;
    --font-size-base: 16px;
    --font-size-4xs: 9px;
    --font-size-3xs: 10px;
    --font-size-31xl: 50px;
    --font-size-21xl: 40px;
    --font-size-9xs: 4px;

    /* Colors */
    --color-white: #fff;
    --color-royalblue-200: #617aff;
    --color-royalblue-100: #3a54d9;
    --color-salmon: #ff7878;
    --color-brown: #cc4545;
    --color-gold: #fedb56;
    --color-goldenrod: #e6c23d;
    --color-gainsboro: #e6e6e6;
    --color-gainsboro-100: #d9d9d9;
    --color-gray-200: #939393;
    --color-gray-100: #777;
    --color-gray-300: #757575;
    --color-gray-400: #262626;
    --color-dimgray-200: #5e5e5e;
    --color-dimgray: #535353;
    --color-dimgray-100: #505050;
    --color-black: #000;
    --color-whitesmoke: #ededed;
    --color-darkgray-100: #adadad;
    --color-darkgray: #aaa;
    --color-darkslategray-100: #494949;
    --color-darkslategray: #464646;
    --color-darkslategray-200: #3f3f3f;
    --color-darkslategray-300: #353535;
    --color-oldlace: #fffaea;
    --color-lemonchiffon: #fff1bd;
    --color-lavender: #e6eaff;
    --color-silver: #bfbfbf;
    --color-cornsilk: #fff8de;
    --color-lightsteelblue: #cbd4ff;
    --color-tomato: #ff4f4f;
    --color-pink: #ffc1c1;
    --color-mediumslateblue: #3254ff;

    /* Gaps */
    --gap-base: 16px;
    --gap-13xl: 32px;
    --gap-9xl-2: 28.2px;
    --gap-15xl-9: 34.9px;
    --gap-mid: 17px;
    --gap-xs-7: 11.7px;
    --gap-8xl-4: 27.4px;
    --gap-6xs-4: 6.4px;
    --gap-sm-6: 13.6px;
    --gap-7xs-1: 5.1px;
    --gap-8xs-4: 4.4px;
    --gap-8xs-9: 4.9px;
    --gap-lgi: 19px;
    --gap-19xl: 38px;
    --gap-9xl: 28px;
    --gap-4xl: 23px;
    --gap-12xl-8: 31.8px;
    --gap-16xl: 35px;
    --gap-lg: 18px;
    --gap-21xl: 40px;
    --gap-xl: 20px;
    --gap-20xl: 39px;
    --gap-4xs-8: 8.8px;
    --gap-5xs: 8px;
    --gap-5xl-8: 24.8px;
    --gap-6xs-6: 6.6px;
    --gap-3xs-9: 9.9px;
    --gap-mini-6: 14.6px;
    --gap-3xs-6: 9.6px;
    --gap-5xs-6: 7.6px;
    --gap-2xs-4: 10.4px;
    --gap-mini-8: 14.8px;
    --gap-3xl: 22px;
    --gap-xs-3: 11.3px;
    --gap-2xs-5: 10.5px;
    --gap-lg-5: 18.5px;
    --gap-4xs: 9px;
    --gap-sm-5: 13.5px;
    --gap-8xs-8: 4.8px;
    --gap-7xl: 26px;
    --gap-14xl: 33px;
    --gap-19xl-8: 38.8px;
    --gap-4xs-7: 8.7px;
    --gap-3xs-5: 9.5px;
    --gap-7xs-7: 5.7px;
    --gap-smi-6: 12.6px;
    --gap-4xs-9: 8.9px;
    --gap-6xs-3: 6.3px;
    --gap-6xs-7: 6.7px;
    --gap-7xs-3: 5.3px;
    --gap-smi-1: 12.1px;
    --gap-10xs-2: 2.2px;
    --gap-11xs: 2px;
    --gap-7xs-6: 5.6px;
    --gap-smi-7: 12.7px;
    --gap-6xs-8: 6.8px;
    --gap-4xs-5: 8.5px;
    --gap-5xs-3: 7.3px;
    --gap-smi-9: 12.9px;
    --gap-10xs-1: 2.1px;
    --gap-7xs-4: 5.4px;
    --gap-8xs-3: 4.3px;
    --gap-5xs-4: 7.4px;
    --gap-5xl: 24px;
    --gap-5xl-4: 24.4px;
    --gap-8xs-2: 4.2px;
    --gap-7xs-5: 5.5px;
    --gap-5xs-5: 7.5px;
    --gap-mini-5: 14.5px;
    --gap-base-5: 15.5px;

    /* Paddings */
    --padding-4xs: 9px;
    --padding-24xl: 43px;
    --padding-5xl-6: 24.6px;
    --padding-21xl: 40px;
    --padding-2xl: 21px;
    --padding-11xs: 2px;
    --padding-xs-7: 11.7px;
    --padding-6xl: 25px;
    --padding-7xl-9: 26.9px;
    --padding-smi-7: 12.7px;
    --padding-lgi: 19px;
    --padding-2xs-7: 10.7px;
    --padding-6xl-8: 25.8px;
    --padding-7xl-1: 26.1px;
    --padding-xs-6: 11.6px;
    --padding-15xl-6: 34.6px;
    --padding-17xl-7: 36.7px;
    --padding-30xl-3: 49.3px;
    --padding-xl: 20px;
    --padding-44xl-3: 63.3px;
    --padding-50xl: 69px;
    --padding-48xl-2: 67.2px;
    --padding-sm-3: 13.3px;
    --padding-lg-5: 18.5px;
    --padding-mini: 15px;
    --padding-5xs-6: 7.6px;
    --padding-base-5: 15.5px;
    --padding-4xs-4: 8.4px;
    --padding-3xl: 22px;
    --padding-6xs: 7px;
    --padding-66xl: 85px;
    --padding-8xl: 27px;
    --padding-36xl: 55px;
    --padding-sm: 14px;
    --padding-51xl: 70px;
    --padding-58xl: 77px;
    --padding-lgi-1: 19.1px;
    --padding-7xl: 26px;
    --padding-10xs: 3px;
    --padding-8xs: 5px;
    --padding-49xl: 68px;
    --padding-25xl: 44px;
    --padding-base: 16px;
    --padding-7xs: 6px;
    --padding-5xs: 8px;
    --padding-4xl: 23px;
    --padding-11xs-7: 1.7px;
    --padding-base-4: 15.4px;
    --padding-8xs-5: 4.5px;
    --padding-22xl-1: 41.1px;
    --padding-9xl-7: 28.7px;
    --padding-smi: 13px;
    --padding-lg: 18px;
    --padding-2xs: 11px;
    --padding-3xl-7: 22.7px;
    --padding-xl-5: 20.5px;
    --padding-mini-1: 14.1px;
    --padding-794xl: 813px;
    --padding-387xl: 406px;
    --padding-184xl: 203px;
    --padding-16xl-3: 35.3px;
    --padding-7xs-6: 5.6px;
    --padding-23xl: 42px;
    --padding-34xl: 53px;
    --padding-6xl-3: 25.3px;
    --padding-22xl-8: 41.8px;
    --padding-12xs: 1px;
    --padding-15xl: 34px;
    --padding-9xl: 28px;
    --padding-3xs: 10px;
    --padding-9xs-8: 3.8px;
    --padding-11xs-5: 1.5px;
    --padding-3xl-8: 22.8px;
    --padding-9xs-9: 3.9px;
    --padding-11xs-1: 1.1px;
    --padding-14xl: 33px;
    --padding-6xl-9: 25.9px;
    --padding-9xs-2: 3.2px;
    --padding-8xs-3: 4.3px;
    --padding-9xs: 4px;
    --padding-4xs-9: 8.9px;
    --padding-17xl: 36px;
    --padding-xs: 12px;
    --padding-4xs-6: 8.6px;
    --padding-smi-4: 12.4px;
    --padding-5xs-5: 7.5px;
    --padding-23xl-4: 42.4px;
    --padding-6xs-5: 6.5px;
    --padding-smi-9: 12.9px;
    --padding-21xl-9: 40.9px;
    --padding-5xl: 24px;
    --padding-smi-1: 12.1px;
    --padding-3xs-1: 9.1px;
    --padding-4xs-2: 8.2px;
    --padding-15xl-4: 34.4px;
    --padding-10xs-5: 2.5px;
    --padding-6xs-6: 6.6px;
    --padding-2xs-4: 10.4px;
    --padding-2xs-3: 10.3px;
    --padding-12xl-8: 31.8px;
    --padding-11xs-3: 1.3px;
    --padding-5xs-8: 7.8px;
    --padding-smi-6: 12.6px;
    --padding-smi-3: 12.3px;
    --padding-4xs-7: 8.7px;
    --padding-xs-1: 11.1px;
    --padding-base-2: 16.2px;
    --padding-5xs-3: 7.3px;
    --padding-7xs-5: 5.5px;
    --padding-sm-1: 13.1px;
    --padding-10xs-1: 2.1px;
    --padding-5xs-2: 7.2px;
    --padding-8xs-9: 4.9px;
    --padding-30xl: 49px;
    --padding-xs-3: 11.3px;
    --padding-3xs-6: 9.6px;
    --padding-2xs-2: 10.2px;
    --padding-6xs-8: 6.8px;
    --padding-3xs-9: 9.9px;
    --padding-11xs-6: 1.6px;
    --padding-10xs-9: 2.9px;
    --padding-mid-1: 17.1px;
    --padding-37xl-6: 56.6px;
    --padding-41xl-1: 60.1px;
    --padding-45xl: 64px;
    --padding-12xl-9: 31.9px;
    --padding-14xl-2: 33.2px;
    --padding-35xl-7: 54.7px;
    --padding-3xs-8: 9.8px;
    --padding-11xs-2: 1.2px;
    --padding-37xl-7: 56.7px;
    --padding-41xl-2: 60.2px;
    --padding-3xl-6: 22.6px;
    --padding-14xl-3: 33.3px;
    --padding-6xs-7: 6.7px;
    --padding-11xs-4: 1.4px;
    --padding-2xs-1: 10.1px;
    --padding-8xs-2: 4.2px;
    --padding-smi-2: 12.2px;
    --padding-xs-4: 11.4px;
    --padding-3xl-1: 22.1px;
    --padding-3xs-5: 9.5px;
    --padding-4xs-3: 8.3px;
    --padding-4xs-8: 8.8px;
    --padding-3xl-9: 22.9px;
    --padding-4xs-5: 8.5px;
    --padding-54xl-7: 73.7px;
    --padding-2xs-5: 10.5px;
    --padding-54xl-3: 73.3px;
    --padding-3xs-7: 9.7px;
    --padding-3xs-4: 9.4px;
    --padding-5xs-7: 7.7px;
    --padding-mini-7: 14.7px;
    --padding-11xs-9: 1.9px;
    --padding-base-1: 16.1px;
    --padding-4xs-1: 8.1px;
    --padding-5xs-9: 7.9px;
    --padding-11xs-8: 1.8px;
    --padding-8xl-2: 27.2px;
    --padding-5xs-4: 7.4px;
    --padding-mini-8: 14.8px;

    /* Border radiuses */
    --br-mini: 15px;
    --br-xl: 20px;
    --br-3xs: 10px;
    --br-11xl: 30px;
    --br-11xs: 2px;
    --br-6xl: 25px;
  }
  .image-container {
    position: relative;
    width: 500px;
    height: 300px;
    overflow: auto;
  }

  .image-container img {
    width: 100%;
    height: 100%;
  }

  .image-button {
    image: "@/assets/icon/Profile/Diary/Write/picture.png";
    position: absolute;
    left: 0;
    width: 100%;
    height: 100%;
    background: none;
    cursor: pointer;
  }
  .popup-overlay {
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    height: 200%;
    background-color: rgba(0, 0, 0, 0.5);
    z-index: 999;
  }
  .layerPopup {
    position: fixed;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background-color: rgba(0, 0, 0, 0.5);
    display: flex;
    justify-content: center;
    align-items: center;
  }

  .popup-content {
    background-color: #fff;
    padding: 20px;
    border-radius: 10px;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0);
    overflow-y: auto; /* 수직 스크롤 활성화 */
    max-height: 50vh; /* 팝업 창의 최대 높이 지정 */
  }

  .close {
    position: absolute;
    top: 10px;
    right: 10px;
    cursor: pointer;
    font-size: 24px;
    color: #555;
    margin-top: 20px;
    align-items: flex-end;
  }

  .frame-child {
    position: absolute;
    top: 121.2px;
    left: 0;
    border-top: 1px solid var(--color-gray-200);
    box-sizing: border-box;
    width: 1837.9px;
    height: 1px;
  }
  .moodholiclogo-2-icon {
    position: absolute;
    height: calc(100% - 1.2px);
    top: 0;
    bottom: 1.2px;
    left: 829.5px;
    max-height: 100%;
    width: 167px;
    object-fit: cover;
  }
  .h3 {
    margin: 0;
    position: absolute;
    top: 61px;
    left: 29.6px;
    font-size: inherit;
    font-weight: 500;
    font-family: inherit;
    display: inline-block;
    min-width: 71px;
    white-space: nowrap;
    z-index: 1;
  }
  .image-1-icon {
    height: 59.8px;
    width: 59.8px;
    position: absolute;
    margin: 0 !important;
    top: -17.9px;
    left: -53.9px;
    object-fit: cover;
  }
  .div1 {
    width: 60px;
    position: relative;
    font-weight: 500;
    display: inline-block;
    min-width: 60px;
    white-space: nowrap;
    z-index: 1;
  }
  .image-1-parent {
    position: absolute;
    top: 59px;
    left: 153.5px;
    display: flex;
    flex-direction: row;
    align-items: flex-start;
    justify-content: flex-start;
    font-size: var(--font-size-4xl);
    color: var(--color-black);
  }
  .group-icon,
  .group-icon1 {
    position: absolute;
    top: 45px;
  }
  .group-icon {
    left: 1760px;
    width: 38.7px;
    height: 35.2px;
    object-fit: contain;
  }
  .group-icon1 {
    left: 1643px;
    width: 35px;
    height: 35px;
  }
  .uimgraph-bar-icon {
    position: absolute;
    top: 42px;
    left: 1696px;
    width: 46px;
    height: 41px;
    overflow: hidden;
  }
  .line-parent {
    align-self: stretch;
    height: 122.2px;
    position: relative;
    text-align: center;
    font-size: var(--font-size-lg);
    color: var(--color-gray-300);
    font-family: var(--font-inter);
  }
  .day-of-week-label-child {
    width: 703.3px;
    height: 783.8px;
    position: relative;
    border-radius: var(--br-xl);
    background-color: var(--color-gainsboro-100);
    display: none;
    max-width: 100%;
  }
  .div2 {
    margin-top: 1px;
    position: relative;
    font-weight: 600;
    z-index: 1;
  }
  .wrapper {
    align-self: stretch;
    display: flex;
    flex-direction: row;
    align-items: flex-start;
    justify-content: center;
    padding: 0 var(--padding-11xs) 0 0;
  }
  .div3,
  .icon {
    position: relative;
    flex-shrink: 0;
    debug_commit: f6aba90;
    z-index: 1;
  }
  .icon {
    height: 25.3px;
    width: 24.6px;
    object-fit: cover;
  }
  .div3 {
    line-height: 18.4px;
    font-weight: 600;
    display: inline-block;
    min-width: 73px;
  }
  .current-mood {
    display: flex;
    flex-direction: column;
    align-items: flex-start;
    justify-content: flex-start;
    padding: var(--padding-4xs-4) 0 0;
  }
  .mood-palette {
    flex-direction: row;
    gap: var(--gap-8xs-9);
  }
  .date-picker,
  .date-picker-parent,
  .frame-item,
  .mood-palette {
    display: flex;
    align-items: flex-start;
    justify-content: flex-start;
  }
  .date-picker {
    flex-direction: row;
    padding: 0 var(--padding-mini);
  }
  .date-picker-parent,
  .frame-item {
    align-self: stretch;
  }
  .frame-item {
    width: 100%;
    border: 0;
    outline: 0;
    background-color: var(--color-white);
    height: 46px;
    border-radius: var(--br-3xs);
    flex-direction: row;
    padding: var(--padding-base-5) var(--padding-lg-5);
    box-sizing: border-box;
    font-family: var(--font-inter);
    font-weight: 600;
    font-size: var(--font-size-xs);
    color: var(--color-gray-100);
    min-width: 250px;
    z-index: 1;
  }
  .date-picker-parent {
    flex-direction: column;
    gap: var(--gap-8xs-4);
  }
  .div4,
  .icon1 {
    position: relative;
    flex-shrink: 0;
    debug_commit: f6aba90;
    z-index: 1;
  }
  .icon1 {
    height: 25.3px;
    width: 24.6px;
    object-fit: cover;
    min-height: 25px;
  }
  .div4 {
    line-height: 18.4px;
    font-weight: 600;
    display: inline-block;
    min-width: 73px;
  }
  .container,
  .frame-wrapper,
  .parent {
    display: flex;
    align-items: flex-start;
    justify-content: flex-start;
  }
  .container {
    flex-direction: column;
    padding: var(--padding-5xs-6) 0 0;
  }
  .frame-wrapper,
  .parent {
    flex-direction: row;
  }
  .parent {
    gap: var(--gap-7xs-1);
  }
  .frame-wrapper {
    padding: 0 var(--padding-mini);
  }
  .frame-group {
    flex-direction: column;
    gap: var(--gap-sm-6);
  }
  .cancel-button,
  .frame-group,
  .frame-parent {
    align-self: stretch;
    display: flex;
    align-items: flex-start;
    justify-content: flex-start;
  }
  .cancel-button {
    border: 0;
    background-color: var(--color-white);
    height: 347.6px;
    width: auto;
    outline: 0;
    border-radius: var(--br-mini);
    flex-direction: row;
    padding: var(--padding-lgi) var(--padding-lg-5);
    box-sizing: border-box;
    font-family: var(--font-inter);
    font-weight: 600;
    font-size: var(--font-size-xs);
    color: var(--color-gray-100);
    z-index: 1;
    resize: none;
  }
  .frame-parent {
    flex-direction: column;
    padding: 0 0 var(--padding-sm-3);
    gap: var(--gap-6xs-4);
    font-size: var(--font-size-mini);
    color: var(--color-dimgray-100);
  }
  .frame-inner {
    width: 632px;
    position: relative;
    border-radius: var(--br-mini);
    max-height: 100%;
    display: none;
    max-width: 100%;
  }
  .image-upload-input {
    height: 55.6px;
    width: 64.2px;
    position: relative;
    z-index: 2;
  }
  .thumbnail-label {
    display: flex;
    flex-direction: row;
    align-items: flex-start;
    justify-content: flex-start;
    padding: 0 var(--padding-50xl) 0 var(--padding-48xl-2);
  }
  .div5 {
    position: relative;
    line-height: 15.2px;
    font-weight: 600;
    z-index: 2;
  }
  .day-of-week-label,
  .vector-parent {
    align-self: stretch;
    display: flex;
    flex-direction: column;
    justify-content: flex-start;
    box-sizing: border-box;
    max-width: 100%;
  }
  .vector-parent {
    border-radius: var(--br-mini);
    background-color: var(--color-white);
    align-items: center;
    padding: 20px;
    gap: var(--gap-8xl-4);
    z-index: 1;
    font-size: var(--font-size-xs);
    color: var(--color-gray-100);
  }
  .day-of-week-label {
    border-radius: var(--br-xl);
    background-color: var(--color-gainsboro-100);
    align-items: flex-start;
    padding: var(--padding-xs-6) var(--padding-15xl-6) var(--padding-7xl-9)
    var(--padding-17xl-7);
    gap: var(--gap-xs-7);
  }
  .rectangle-div {
    height: 48.4px;
    width: 129.9px;
    position: relative;
    border-radius: var(--br-mini);
    background-color: var(--color-gold);
    display: none;
  }
  .div6 {
    position: relative;
    font-size: var(--font-size-2xl);
    font-weight: 600;
    font-family: var(--font-inter);
    color: var(--color-white);
    text-align: left;
    display: inline-block;
    min-width: 78px;
    white-space: nowrap;
    z-index: 1;
  }
  .rectangle-parent {
    cursor: pointer;
    border: 0;
    padding: var(--padding-xs-7) var(--padding-6xl-8) var(--padding-xs-7)
    var(--padding-7xl-1);
    background-color: var(--color-gold);
    flex: 1;
    border-radius: var(--br-mini);
    display: flex;
    flex-direction: row;
    align-items: flex-start;
    justify-content: flex-start;
    box-sizing: border-box;
    min-width: 84px;
  }
  .rectangle-parent:hover {
    background-color: var(--color-goldenrod);
  }
  .frame-child1 {
    height: 48.4px;
    width: 81px;
    position: relative;
    border-radius: var(--br-mini);
    background-color: var(--color-salmon);
    display: none;
  }
  .div7 {
    position: relative;
    font-size: var(--font-size-2xl);
    font-weight: 600;
    font-family: var(--font-inter);
    color: var(--color-white);
    text-align: center;
    display: inline-block;
    min-width: 42px;
    z-index: 1;
  }
  .rectangle-group {
    cursor: pointer;
    border: 0;
    padding: var(--padding-smi-7) var(--padding-lgi) var(--padding-2xs-7);
    background-color: var(--color-salmon);
    border-radius: var(--br-mini);
    display: flex;
    flex-direction: row;
    align-items: flex-start;
    justify-content: flex-start;
  }
  .rectangle-group:hover {
    background-color: var(--color-brown);
  }
  .frame-child2 {
    height: 48.4px;
    width: 129.9px;
    position: relative;
    border-radius: var(--br-mini);
    background-color: var(--color-royalblue-100);
    display: none;
  }
  .div8 {
    position: relative;
    font-size: var(--font-size-2xl);
    font-weight: 600;
    font-family: var(--font-inter);
    color: var(--color-white);
    text-align: center;
    display: inline-block;
    min-width: 78px;
    white-space: nowrap;
    z-index: 1;
  }
  .day-of-week-label-parent,
  .frame-container,
  .rectangle-container {
    display: flex;
    flex-direction: row;
    align-items: flex-start;
    justify-content: flex-start;
  }
  .rectangle-container {
    cursor: pointer;
    border: 0;
    padding: var(--padding-xs-7) var(--padding-6xl) var(--padding-xs-7)
    var(--padding-7xl-9);
    background-color: var(--color-royalblue-100);
    flex: 1;
    border-radius: var(--br-mini);
    box-sizing: border-box;
    min-width: 84px;
  }
  .rectangle-container:hover {
    background-color: var(--color-royalblue-200);
  }
  .day-of-week-label-parent,
  .frame-container {
    width: 410.6px;
    gap: var(--gap-15xl-9);
    max-width: 100%;
  }
  .day-of-week-label-parent {
    margin: 90px;
    width: 703px;
    height: 500px;
    flex-direction: column;
    align-items: flex-end;
    gap: var(--gap-9xl-2);
  }
  .div,
  .inner {
    display: flex;
    box-sizing: border-box;
  }
  .inner {
    align-self: stretch;
    flex-direction: row;
    align-items: flex-start;
    justify-content: center;
    padding: 0 0 0 var(--padding-11xs);
    max-width: 100%;
    text-align: left;
    font-size: var(--font-size-4xl);
    color: var(--color-black);
    font-family: var(--font-inter);
  }
  .div {
    width: 100%;
    position: relative;
    background-color: var(--color-white);
    overflow: hidden;
    flex-direction: column;
    align-items: flex-end;
    justify-content: flex-start;
    padding: var(--padding-4xs) var(--padding-24xl) var(--padding-5xl-6)
    var(--padding-21xl);
    gap: 63.8px;
    line-height: normal;
    letter-spacing: normal;
  }
  @media screen and (max-width: 975px) {
    .h3 {
      display: none;
    }
    .div {
      gap: var(--gap-13xl);
      padding-right: var(--padding-2xl);
      box-sizing: border-box;
    }
  }
  @media screen and (max-width: 700px) {
    .div {
      gap: var(--gap-base);
    }
  }
  @media screen and (max-width: 450px) {
    .div2 {
      font-size: var(--font-size-lg);
    }

    .div6 {
      font-size: var(--font-size-mid);
    }

    .rectangle-parent {
      flex: 1;
    }

    .div7,
    .div8 {
      font-size: var(--font-size-mid);
    }

    .rectangle-container {
      flex: 1;
    }
  }




  @media screen and (max-width: 900px) {
    .day-of-week-label-parent {
      gap: var(--gap-mid);
    }
    .date-picker-parent {
      align-items: center;
      display: flex;
      justify-content: center;
      padding: 10px;
    }
  }
  @media screen and (max-width: 930px) {
    .mood-palette {
      margin-bottom: 10%;
    }
  }
  @media screen and (max-width: 930px){
    .parent {
      margin-bottom: 10%;
    }
    .frame-wrapper {
      margin-left: auto;
      margin-right: auto;
    }
  }
  @media screen and (max-width: 930px) {
    .day-of-week-label {
      width: 500px;
      margin-left: 14.5%;
    }
  }
  @media screen and (max-width: 930px) {
    .frame-container {
      display: inline-grid;
      gap: var(--gap-mid);
    }

    .div7 {
      margin-left: 25px;
    }
  }
  @media screen and (max-width: 930px){
    .day-of-week-label-parent {
      margin: 90px;
      width: 703px;
      height: 500px;
      align-items: flex-end;
      gap: var(--gap-9xl-2);
    }
  }
  @media screen and (max-width: 930px){
    .image-button {
      margin-left:10%;
      width: 400px;
    }
  }
</style>
