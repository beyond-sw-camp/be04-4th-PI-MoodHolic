<template>
  <div class="div">
    <div class="line-parent"></div>
    <div class="inner">
      <div class="frame-parent">
        <div class="emotion-diary-parent">
          <div class="emotion-diary">
            <img
                class="image-3-icon"
                loading="lazy"
                alt=""
                src="../../assets/icon/MainPage/BeforeLogInMain/image-3@2x.png"/>
            <div class="div3">
              <b class="bb">인공지능 심리전문가</b>
              <span class="span">가 다이어리에 &nbsp;적은 오늘의 기분을 분석해</span>
            </div>
          </div>
          <div class="wrapper">
            <h3 class="h3">
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
                <b class="ai">
                  다이어리 작성 후 AI가 점수를 내주고 그 점수에 따라 감정
                  아이콘이 자동으로 기록됩니다!
                </b>
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
    </div>
  </div>
</template>

<script setup>
import { useRouter } from 'vue-router';

const router = useRouter();

const redirectToLogin = () => {
  router.replace('/login');
};
</script>

<style scoped>
body {
  margin: 0;
  line-height: normal;
}

:root {
  /* fonts */
  --font-inter: Inter;

  /* font sizes */
  --font-size-xl: 20px;
  --font-size-base: 16px;
  --font-size-6xl: 25px;
  --font-size-11xl: 30px;
  --font-size-lg: 18px;
  --font-size-5xl: 24px;
  --font-size-2xl: 21px;
  --font-size-31xl: 50px;
  --font-size-4xl: 23px;
  --font-size-mini: 15px;
  --font-size-xs: 12px;
  --font-size-mid: 17px;
  --font-size-4xs: 9px;
  --font-size-3xs: 10px;

  /* Colors */
  --color-white: #fff;
  --color-cornsilk: #fff8de;
  --color-gainsboro: #e6e6e6;
  --color-gainsboro-100: #d9d9d9;
  --color-silver: #bfbfbf;
  --color-black: #000;
  --color-dimgray-200: #5e5e5e;
  --color-dimgray: #535353;
  --color-dimgray-100: #505050;
  --color-royalblue-200: #617aff;
  --color-royalblue-100: #3a54d9;
  --color-gray-200: #939393;
  --color-gray-100: #777;
  --color-gray-300: #757575;
  --color-gray-400: #262626;
  --color-whitesmoke: #ededed;
  --color-darkgray-100: #adadad;
  --color-darkgray: #aaa;
  --color-darkslategray-100: #494949;
  --color-darkslategray: #464646;
  --color-darkslategray-200: #3f3f3f;
  --color-darkslategray-300: #353535;
  --color-oldlace: #fffaea;
  --color-salmon: #ff7878;
  --color-brown: #cc4545;
  --color-gold: #fedb56;
  --color-goldenrod: #e6c23d;
  --color-lemonchiffon: #fff1bd;
  --color-lavender: #e6eaff;
  --color-lightsteelblue: #cbd4ff;
  --color-tomato: #ff4f4f;
  --color-pink: #ffc1c1;

  /* Gaps */
  --gap-base: 16px;
  --gap-14xl: 33px;
  --gap-3xl: 22px;
  --gap-xl: 20px;
  --gap-6xs-6: 6.6px;
  --gap-lgi: 19px;
  --gap-9xl: 28px;
  --gap-4xl: 23px;
  --gap-12xl-8: 31.8px;
  --gap-16xl: 35px;
  --gap-lg: 18px;
  --gap-21xl: 40px;
  --gap-20xl: 39px;
  --gap-mid: 17px;
  --gap-4xs-8: 8.8px;
  --gap-5xl-8: 24.8px;
  --gap-3xs-9: 9.9px;
  --gap-mini-6: 14.6px;
  --gap-7xs-1: 5.1px;
  --gap-3xs-6: 9.6px;
  --gap-5xs-6: 7.6px;
  --gap-xs-7: 11.7px;
  --gap-8xs-4: 4.4px;
  --gap-2xs-4: 10.4px;
  --gap-mini-8: 14.8px;
  --gap-xs-3: 11.3px;
  --gap-lg-5: 18.5px;
  --gap-4xs: 9px;
  --gap-sm-5: 13.5px;
  --gap-8xs-8: 4.8px;
  --gap-7xl: 26px;
  --gap-13xl: 32px;
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
  --gap-8xs-3: 4.3px;
  --gap-5xs-4: 7.4px;
  --gap-5xl: 24px;
  --gap-5xl-4: 24.4px;

  /* Paddings */
  --padding-4xs: 9px;
  --padding-24xl: 43px;
  --padding-21xl: 40px;
  --padding-2xl: 21px;
  --padding-xl: 20px;
  --padding-8xl: 27px;
  --padding-30xl: 49px;
  --padding-6xs-7: 6.7px;
  --padding-lg: 18px;
  --padding-5xs-5: 7.5px;
  --padding-lgi: 19px;
  --padding-10xs-5: 2.5px;
  --padding-6xs: 7px;
  --padding-8xs: 5px;
  --padding-11xs: 2px;
  --padding-12xs: 1px;
  --padding-3xl: 22px;
  --padding-66xl: 85px;
  --padding-36xl: 55px;
  --padding-sm: 14px;
  --padding-51xl: 70px;
  --padding-58xl: 77px;
  --padding-lgi-1: 19.1px;
  --padding-7xl: 26px;
  --padding-6xl: 25px;
  --padding-10xs: 3px;
  --padding-49xl: 68px;
  --padding-25xl: 44px;
  --padding-mini: 15px;
  --padding-base: 16px;
  --padding-7xs: 6px;
  --padding-5xs: 8px;
  --padding-4xl: 23px;
  --padding-8xs-5: 4.5px;
  --padding-22xl-1: 41.1px;
  --padding-9xl-7: 28.7px;
  --padding-smi: 13px;
  --padding-2xs: 11px;
  --padding-3xl-7: 22.7px;
  --padding-xl-5: 20.5px;
  --padding-xs-7: 11.7px;
  --padding-mini-1: 14.1px;
  --padding-794xl: 813px;
  --padding-387xl: 406px;
  --padding-184xl: 203px;
  --padding-16xl-3: 35.3px;
  --padding-7xs-6: 5.6px;
  --padding-5xs-6: 7.6px;
  --padding-23xl: 42px;
  --padding-7xl-9: 26.9px;
  --padding-smi-7: 12.7px;
  --padding-2xs-7: 10.7px;
  --padding-6xl-8: 25.8px;
  --padding-xs-6: 11.6px;
  --padding-lg-5: 18.5px;
  --padding-base-5: 15.5px;
  --padding-4xs-4: 8.4px;
  --padding-34xl: 53px;
  --padding-6xl-3: 25.3px;
  --padding-22xl-8: 41.8px;
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
  --padding-base-4: 15.4px;
  --padding-4xs-9: 8.9px;
  --padding-17xl: 36px;
  --padding-xs: 12px;
  --padding-4xs-6: 8.6px;
  --padding-smi-4: 12.4px;
  --padding-23xl-4: 42.4px;
  --padding-6xs-5: 6.5px;
  --padding-smi-9: 12.9px;
  --padding-21xl-9: 40.9px;
  --padding-5xl: 24px;
  --padding-smi-1: 12.1px;
  --padding-3xs-1: 9.1px;
  --padding-15xl-4: 34.4px;
  --padding-2xs-4: 10.4px;
  --padding-2xs-3: 10.3px;
  --padding-12xl-8: 31.8px;
  --padding-11xs-3: 1.3px;
  --padding-smi-6: 12.6px;
  --padding-smi-3: 12.3px;
  --padding-xs-1: 11.1px;
  --padding-5xs-3: 7.3px;
  --padding-7xs-5: 5.5px;
  --padding-sm-1: 13.1px;
  --padding-10xs-1: 2.1px;
  --padding-8xs-9: 4.9px;
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
  --padding-11xs-4: 1.4px;
  --padding-2xs-1: 10.1px;
  --padding-8xs-2: 4.2px;
  --padding-smi-2: 12.2px;
  --padding-3xl-1: 22.1px;
  --padding-3xs-5: 9.5px;
  --padding-4xs-5: 8.5px;
  --padding-54xl-7: 73.7px;
  --padding-2xs-5: 10.5px;
  --padding-54xl-3: 73.3px;
  --padding-3xs-7: 9.7px;
  --padding-mini-7: 14.7px;

  /* Border radiuses */
  --br-11xl: 30px;
  --br-6xl: 25px;
  --br-mini: 15px;
  --br-11xs: 2px;
  --br-xl: 20px;
  --br-3xs: 10px;
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
.div1 {
  position: absolute;
  top: 61px;
  left: 29.6px;
  font-weight: 500;
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
.div2 {
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
.line-parent {
  align-self: stretch;
  height: 122.2px;
  position: relative;
  text-align: center;
  font-size: var(--font-size-lg);
  color: var(--color-gray-300);
  font-family: var(--font-inter);
}
.image-3-icon {
  height: 460.9px;
  width: 467.4px;
  position: absolute;
  top: -220.3px;
  right: 200.6px;
  display: block;
}
.span {
  font-size: 35px;
  font-weight: 500;
}
.div3 {
  height: 61px;
  width: 1020px;
  position: relative;
  display: inline-block;
  z-index: 1;
}

.emotion-diary {
  display: flex;
  flex-direction: row;
  align-items: flex-start;
  justify-content: flex-start;
  position: relative;
  max-width: 100%;
}
.b {
  color: blue;
}
.h3 {
  margin: 0;
  width: 695px;
  position: relative;
  font-size: 20px;
  display: inline-block;
  max-width: 100%;
  z-index: 1;
  font-family: inherit;
}
.emotion-diary-parent,
.wrapper {
  align-self: stretch;
  display: flex;
  align-items: flex-start;
  max-width: 100%;
}
.wrapper {
  flex-direction: row;
  justify-content: flex-end;
  font-size: var(--font-size-11xl);
}
.emotion-diary-parent {
  flex-direction: column;
  justify-content: flex-start;
  gap: 41.3px;
}
.frame-item {
  height: 47.1px;
  width: 344.9px;
  position: relative;
  border-radius: var(--br-mini);
  background-color: var(--color-gainsboro-100);
  display: none;
  max-width: 100%;
}
.mood-holic {
  position: relative;
  font-size: var(--font-size-6xl);
  line-height: 30.4px;
  font-family: var(--font-inter);
  color: #717171;
  text-align: left;
  z-index: 1;
  margin-top: 2px;
  /* padding: 10px 20px;
  background-color: #939191;
  border-radius: 20px;
  display: inline-block;
  border: none;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
  cursor: pointer;
  font-weight: bold;  */
}

.diary-writing-icon {
  width: 20.3px;
  height: 18.7px;
  position: relative;
  z-index: 2;
  margin-top: 6px;
}
.diary-writing-wrapper {
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  justify-content: flex-start;
  padding: var(--padding-6xs-7) 0 0;
}
.frame-container,
.rectangle-parent {
  display: flex;
  flex-direction: row;
  align-items: flex-start;
  box-sizing: border-box;
  max-width: 100%;
  position: relative;
}
.rectangle-parent {
  cursor: pointer;
  border: 0;
  padding: 8.3px 24.1px 8.8px 43.9px;
  border-radius: 1rem;
  justify-content: flex-start;
  gap: 20.6px;
}
.frame-container {
  width: 717.1px;
  justify-content: center;
  padding: 0 var(--padding-xl);
  background-color: var(--color-gainsboro-100);
}
.ai {
  width: 733px;
  position: relative;
  font-weight: 500;
  display: inline-block;
}
.ai-wrapper {
  display: flex;
  flex-direction: row;
  align-items: flex-start;
  justify-content: flex-start;
  padding: 0 var(--padding-11xs) 0 var(--padding-12xs);
}
.frame-inner,
.icon {
  position: relative;
  max-width: 100%;
}
.frame-inner {
  height: 220.2px;
  width: 736.5px;
  border-radius: var(--br-11xl);
  background-color: var(--color-cornsilk);
  display: none;
}
.icon {
  height: 127.2px;
  overflow: hidden;
  object-fit: cover;
  flex-shrink: 0;
  debug_commit: f6aba90;
  z-index: 1;
}
.container {
  align-self: stretch;
  display: flex;
  flex-direction: row;
  align-items: flex-start;
  justify-content: flex-start;
  padding: 0 var(--padding-8xs) 0 4.4px;
}
.rectangle-div {
  height: 38.2px;
  width: 98.5px;
  position: relative;
  border-radius: var(--br-6xl);
  background-color: var(--color-gainsboro-100);
  display: none;
}
.b1 {
  position: relative;
  font-size: var(--font-size-xl);
  display: inline-block;
  font-family: var(--font-inter);
  color: var(--color-black);
  text-align: left;
  min-width: 60px;
  z-index: 2;
  margin-left: 30px;
}
.frame-parent1,
.rectangle-container {
  display: flex;
  align-items: flex-start;
  justify-content: flex-start;
}
.rectangle-container {
  cursor: pointer;
  border: 0;
  padding: var(--padding-6xs-7) var(--padding-lg) var(--padding-5xs-5);
  background-color: var(--color-gainsboro-100);
  align-self: stretch;
  border-radius: var(--br-6xl);
  flex-direction: row;
  flex-shrink: 0;
  debug_commit: f6aba90;
  z-index: 1;
}
.group-button:hover,
.rectangle-container:hover,
.rectangle-parent1:hover {
  background-color: var(--color-silver);
}
.frame-parent1 {
  width: 98.5px;
  flex-direction: column;
  gap: var(--gap-6xs-6);
}
.icon1 {
  height: 127.2px;
  position: relative;
  max-width: 100%;
  overflow: hidden;
  object-fit: cover;
  flex-shrink: 0;
  debug_commit: f6aba90;
  z-index: 1;
}
.good-category {
  align-self: stretch;
  display: flex;
  flex-direction: row;
  align-items: flex-start;
  justify-content: flex-end;
  padding: 0 var(--padding-10xs-5) 0 var(--padding-6xs);
}
.frame-child1 {
  height: 38.2px;
  width: 98.5px;
  position: relative;
  border-radius: var(--br-6xl);
  background-color: var(--color-gainsboro-100);
  display: none;
}
.b2 {
  position: relative;
  font-size: var(--font-size-xl);
  display: inline-block;
  font-family: var(--font-inter);
  color: var(--color-black);
  text-align: left;
  min-width: 60px;
  z-index: 1;
  margin-right: 5px;
}
.good-category-parent,
.group-button {
  display: flex;
  justify-content: flex-start;
}
.group-button {
  cursor: pointer;
  border: 0;
  padding: var(--padding-6xs-7) var(--padding-lgi) var(--padding-5xs-5);
  background-color: var(--color-gainsboro-100);
  border-radius: var(--br-6xl);
  flex-direction: row;
  align-items: flex-start;
  flex-shrink: 0;
  debug_commit: f6aba90;
  z-index: 1;
}
.good-category-parent {
  width: 98.5px;
  flex-direction: column;
  align-items: flex-end;
  gap: var(--gap-6xs-6);
}
.icon2 {
  width: 96.2px;
  position: relative;
  max-height: 100%;
  object-fit: cover;
  flex-shrink: 0;
  debug_commit: f6aba90;
  z-index: 1;
}
.frame-child2 {
  height: 38.2px;
  width: 98.5px;
  position: relative;
  border-radius: var(--br-6xl);
  background-color: var(--color-gainsboro-100);
  display: none;
}
.b3 {
  position: relative;
  font-size: var(--font-size-xl);
  display: inline-block;
  font-family: var(--font-inter);
  color: var(--color-black);
  text-align: left;
  min-width: 60px;
  z-index: 1;
  margin-left: 30px;
}
.parent,
.rectangle-parent1 {
  display: flex;
  align-items: flex-start;
  justify-content: flex-start;
}
.rectangle-parent1 {
  cursor: pointer;
  border: 0;
  padding: var(--padding-6xs-7) var(--padding-lg) var(--padding-5xs-5)
  var(--padding-xl);
  background-color: var(--color-gainsboro-100);
  border-radius: var(--br-6xl);
  flex-direction: row;
  flex-shrink: 0;
  debug_commit: f6aba90;
  z-index: 1;
}
.parent {
  height: 172px;
  flex-direction: column;
  gap: var(--gap-6xs-6);
}
.frame-div,
.frame-group,
.rectangle-group {
  display: flex;
  align-items: flex-start;
  max-width: 100%;
}
.rectangle-group {
  align-self: stretch;
  border-radius: var(--br-11xl);
  background-color: var(--color-cornsilk);
  flex-direction: row;
  flex-wrap: wrap;
  justify-content: space-between;
  padding: 21.2px 99.4px var(--padding-8xl) 103.6px;
  box-sizing: border-box;
  gap: var(--gap-xl);
}
.frame-div,
.frame-group {
  flex-direction: column;
  justify-content: flex-start;
}
.frame-div {
  align-self: stretch;
  gap: 27.5px;
}
.frame-group {
  width: 736.5px;
  gap: 44.4px;
  text-align: left;
  font-size: var(--font-size-xl);
  color: #6f6f6f;
  font-family: var(--font-inter);
}
.bb {
  font-size: 40px;
}
.frame-wrapper {
  width: 1202.9px;
  flex-direction: row;
  justify-content: center;
  padding: 0 var(--padding-xl);
  box-sizing: border-box;
}
.frame-parent,
.frame-wrapper,
.inner {
  display: flex;
  align-items: flex-start;
  max-width: 100%;
}
.frame-parent {
  width: 1261.5px;
  flex-direction: column;
  justify-content: flex-start;
  gap: 131.2px;
}
.inner {
  width: 1769.9px;
  flex-direction: row;
  justify-content: center;
  text-align: left;
  font-size: var(--font-size-31xl);
  color: var(--color-black);
  font-family: var(--font-inter);
}
.div {
  width: 100%;
  position: relative;
  background-color: var(--color-white);
  overflow: hidden;
  display: flex;
  flex-direction: column;
  align-items: flex-end;
  justify-content: flex-start;
  padding: var(--padding-4xs) var(--padding-24xl) 55.8px var(--padding-21xl);
  gap: 160.3px;

}
@media screen and (max-width: 1200px) {
  .frame-parent {
    gap: 66px;
  }
}
@media screen and (max-width: 1050px) {
  .span {
    font-size: 1.5rem;
  }
  .bb {
    font-size: 2rem;
  }
  .h3 {
    font-size: var(--font-size-5xl);
  }
  .div {
    gap: 130px;
    padding-right: var(--padding-2xl);
    box-sizing: border-box;
  }
}
@media screen and (max-width: 850px) {
  .span {
    font-size: .85rem;
  }
}
@media screen and (max-width: 750px) {
  .emotion-diary-parent {
    gap: 21px;
  }
  .rectangle-group {
    padding-left: 52px;
    padding-right: var(--padding-30xl);
    box-sizing: border-box;
  }
  .frame-group {
    gap: var(--gap-3xl);
  }
  .frame-parent {
    gap: var(--gap-14xl);
  }
  .div {
    gap: 65px;
  }
}
@media screen and (max-width: 450px) {
  .div3 {
    font-size: var(--font-size-2xl);
  }
  .mood-holic {
    font-size: var(--font-size-xl);
  }
  .rectangle-parent {
    padding-left: var(--padding-xl);
    box-sizing: border-box;
  }
  .b1,
  .b2,
  .b3 {
    font-size: var(--font-size-base);
  }
  .rectangle-group {
    padding-left: var(--padding-xl);
    padding-right: var(--padding-xl);
    box-sizing: border-box;
  }
  .frame-parent {
    gap: var(--gap-base);
  }
}
@media screen and (max-width: 750px) {
  .rectangle-group {
    margin-right: 50px;
  }
  .frame-wrapper {
    margin-top: 50px;
  }
}
@media screen and (max-width: 1200px) {
  .frame-group {
    margin-top: 100px;
  }
}
@media screen and (max-width: 1050px) {
  .image-3-icon {
    margin-top: 30px;
  }
  .h3 {
    margin-top: 40px;
  }
  .emotion-diary-parent {
    gap: 40px;
  }
}
@media screen and (max-width: 750px) {
  .div {
    margin-top: 65px;
  }
  .ai {
    margin-top: 30px;
  }
}
@media screen and (max-width: 675px) {
  .ai {
    font-size: 0.9rem;
  }
}
</style>
