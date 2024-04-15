<template>
  <header>
    <Header/>
  </header>

  <main>
    <router-view/>
  </main>

</template>

<script setup>
  import Header from "@/components/Header/Header.vue";
  import { onMounted } from 'vue';
  import { useStore } from 'vuex';
  import router from "@/router/index.js";
  import Example from "@/components/Calendar/Example.vue";
  import Calendar from "@/components/Calendar/calendar.vue";


const store = useStore();

onMounted(async () => {
  await store.dispatch('checkAuth');
  // URL에서 토큰이 있는 경우 로그인 처리
  const urlParams = new URLSearchParams(window.location.search);
  const token = urlParams.get('token');
  if (token) {
    await store.dispatch('login', token);
    router.push(router.currentRoute.value); // 강제로 새로 고침
  }
});

// 쿠키에서 refreshToken 검색 후 로컬 스토리지에 저장
document.addEventListener('DOMContentLoaded', async () => {
  const cookies = document.cookie.split('; ');
  let authToken = '';

  for (let i = 0; i < cookies.length; i++) {
    if (cookies[i].startsWith('refreshToken=')) {
      authToken = cookies[i].split('=')[1];
      break;
    }
  }

  if (authToken) {
    localStorage.setItem('authToken', authToken);
    await store.dispatch('login', authToken);
    router.push(router.currentRoute.value); // 상태 갱신 후 현재 라우트로 리다이렉트
  }
});
</script>
