<template>
  <header class="line-parent">
    <!-- Weather Information -->
    <div class="weather-container">
      <img :src="'https://openweathermap.org/img/wn/' + weatherInfo.icon + '.png'" alt="Weather Icon" class="weather-icon"/>
      <span class="temperature">{{ weatherInfo.temperature }}°C</span>
    </div>


    <!-- Main Logo -->
    <img class="moodholiclogo-icon" style="cursor: pointer;" @click="changeRouter('/')" loading="lazy" src="@/assets/icon/header/moodholiclogo.png"/>

    <!-- Right Icons -->
    <div class="right-icons" v-if="isAuthenticated">
      <img class="header-icon" style="cursor: pointer;" @click="changeRouter('/mypage')" alt="Profile Shortcut" src="@/assets/icon/header/profile.png" />
      <img class="header-icon" style="cursor: pointer;" @click="changeRouter('/statistics')" alt="Statistics Page Shortcut" src="@/assets/icon/header/statistics.png" />
      <img class="header-icon" style="cursor: pointer;" @click="logout('/logout')" alt="Logout Button" src="@/assets/icon/header/logout.png" />

    </div>
  </header>
</template>

<script setup>
import { ref, onMounted, computed } from 'vue';
import { useStore } from 'vuex';
import router from "@/router/router.js";

const store = useStore();
const isAuthenticated = computed(() => store.getters.isAuthenticated);

const weatherInfo = ref({
  temperature: 'Loading...',
  icon: null
});

const fetchWeather = async () => {
  const city = 'Seoul';
  const apiKey = 'aa237cabd8bd6dd1e5374da90756d5b5';
  const url = `https://api.openweathermap.org/data/2.5/weather?q=${city}&appid=${apiKey}&units=metric`;

  try {
    const response = await fetch(url);
    if (!response.ok) {
      throw new Error('Failed to fetch weather data');
    }
    const data = await response.json();
    weatherInfo.value = {
      temperature: data.main.temp,
      icon: data.weather[0].icon
    };
  } catch (error) {
    console.error('Failed to fetch weather data:', error);
    weatherInfo.value = {
      temperature: 'N/A',
      icon: null
    };
  }
};

onMounted(() => {
  fetchWeather();
});

const changeRouter = (route) => {
  router.push(route);
};

// 로그아웃 처리
const logout = async () => {
  // 로그아웃 API 호출
  try {
    const response = await fetch('http://localhost:8888/logout', {
      method: 'POST',
      credentials: 'include'  // 쿠키 포함시킴
    });
    if (response.ok) {
      await store.dispatch('logout');  // 상태 업데이트
      await router.push('/');
    } else {
      throw new Error('Failed to logout');
    }
  } catch (error) {
    console.error('Logout error:', error);
  }
};

</script>


<style scoped>
.line-parent {
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: 20px 10px;
  border-bottom: 2px solid #ccc;
  align-self: stretch;
}

.weather-container {
  display: flex;
  align-items: center;
  justify-content: flex-start;
}

.weather-icon {
  width: 40px;
  height: 40px;
  margin-right: 10px;
}

.temperature {
  font-size: 18px;
  color: #333;
}

.moodholiclogo-icon {
  position: absolute;
  left: 50%;
  transform: translateX(-50%);
  max-width: 100px;
  height: auto;
}

.right-icons {
  display: flex;
  align-items: center;
}

.header-icon {
  width: 30px; /* 아이콘 크기 일관성 */
  height: 30px; /* 아이콘 크기 일관성 */
  margin-left: 10px; /* 아이콘 간 간격 */
}

</style>

