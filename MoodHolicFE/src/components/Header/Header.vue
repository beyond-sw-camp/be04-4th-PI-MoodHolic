<template>
  <header class="line-parent">
    <!-- 날씨 정보 -->
    <div class="weather-container">
      <img :src="'https://openweathermap.org/img/wn/' + weatherInfo.icon + '.png'" alt="Weather Icon" class="weather-icon"/>
      <span class="temperature">{{ weatherInfo.temperature }}°C</span>
    </div>
    <!-- 메인 로고 -->
    <img class="moodholiclogo-icon" loading="lazy" src="@/assets/icon/header/moodholiclogo.png"/>

    <!-- 맨 오른쪽 아이콘들 -->
    <div class="right-icons">
      <img class="header-icon" alt="Profile Shortcut" src="@/assets/icon/header/profile.png" />
      <img class="header-icon" alt="Statistics Page Shortcut" src="@/assets/icon/header/statistics.png" />
      <img class="header-icon" alt="Logout Button" src="@/assets/icon/header/logout.png" />
    </div>
  </header>
</template>

<style scoped>
.line-parent {
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: 20px 10px;
  border-bottom: 2px solid #ccc;
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







<script>
export default {
  name: 'HeaderComponent',
  data() {
    return {
      weatherInfo: {
        temperature: 'Loading...',
        icon: null
      },
    };
  },
  methods: {
    async fetchWeather() {
      const city = 'Seoul';
      const apiKey = 'aa237cabd8bd6dd1e5374da90756d5b5';
      const url = `https://api.openweathermap.org/data/2.5/weather?q=${city}&appid=${apiKey}&units=metric`;

      try {
        const response = await fetch(url);
        if (!response.ok) {
          throw new Error('Failed to fetch weather data');
        }
        const data = await response.json();
        this.weatherInfo = {
          temperature: data.main.temp,
          icon: data.weather[0].icon
        };
      } catch (error) {
        console.error('Failed to fetch weather data:', error);
        this.weatherInfo = {
          temperature: 'N/A',
          icon: null
        };
      }
    },
  },
  mounted() {
    this.fetchWeather();
  },
};
</script>
