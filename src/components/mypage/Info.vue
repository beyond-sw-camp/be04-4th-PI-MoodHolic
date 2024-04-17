<template>
  <div class="popup-container" @click.self="closePopup">
    <div class="popup-content">
      <div class="vector-group">
        <img
            class="vector-icon3"
            alt=""
            src="../../assets/icon/MyPage/x.png"
        />
        <div class="wrapper">
          <h1 class="h1">로그인 정보</h1>
        </div>
      </div>
      <div class="frame-container">
        <div class="frame-group">
          <div class="container">
            <div class="div3">닉네임</div>
          </div>
          <div class="frame-div">
            <div class="konan-parent">
              <b class="konan1">{{ nickname }}</b>
              <div class="frame">
                <div class="div4">변경하기</div>
              </div>
            </div>
          </div>
          <div class="line-div"></div>
        </div>
      </div>
      <div class="frame-wrapper1">
        <div class="frame-parent1">
          <div class="wrapper1">
            <div class="div5">연결된 계정</div>
          </div>
          <div class="frame-parent2">
            <img
                class="frame-child1"
                loading="lazy"
                alt=""
                src="../../assets/icon/MyPage/google.png"
            />
            <div class="dlwldms0902gmailcom-wrapper">
              <b class="dlwldms0902gmailcom">{{email}}</b>
            </div>
          </div>
          <div class="wrapper2">
            <div class="div6">변경하기</div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
.popup-container {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.5);
  display: flex;
  justify-content: center;
  align-items: center;
  z-index: 9999;
}

.popup-content {
  background-color: white;
  padding: 20px;
  border-radius: 10px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
}

.vector-group {
  display: flex;
  align-items: center;
}

.vector-icon3 {
  width: 20px;
  height: 20px;
  margin-right: 10px;
}

.wrapper {
  flex: 1;
}

.h1 {
  font-size: 24px;
  margin: 0;
}

.frame-group {
  margin-top: 20px;
}

.container {
  display: flex;
  align-items: center;
}

.div3 {
  flex: 1;
  font-weight: bold;
}

.konan-parent {
  display: flex;
  align-items: center;
}

.konan1 {
  margin-right: 10px;
}

.line-div {
  margin-top: 20px;
  border-top: 1px solid #ccc;
}

.frame-wrapper1 {
  margin-top: 20px;
}

.frame-parent1 {
  display: flex;
  align-items: center;
}

.wrapper1 {
  flex: 1;
}

.div5 {
  font-weight: bold;
}

.frame-parent2 {
  display: flex;
  align-items: center;
  margin-left: 10px;
}

.frame-child1 {
  width: 20px;
  height: 20px;
  margin-right: 10px;
}

.dlwldms0902gmailcom-wrapper {
  flex: 1;
}

.dlwldms0902gmailcom {
  font-weight: bold;
}

.wrapper2 {
  margin-left: 10px;
}

.div6 {
  font-weight: bold;
  color: blue;
  cursor: pointer;
}
</style>
<script setup>
import { useStore } from 'vuex';
import router from "@/router/router.js";

const store = useStore();
let memberId = store.state.memberId;
let nickname = store.state.nickname;
let email = store.state.email;

const closePopup = () => {
  const popupContainer = document.querySelector('.popup-container');
  if (popupContainer) {
    popupContainer.style.display = 'none'; // 팝업을 숨깁니다.
    window.history.go(-1); // 뒤로가기를 수행합니다.
  }
};
</script>

