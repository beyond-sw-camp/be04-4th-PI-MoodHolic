import { createApp } from 'vue'
import App from '@/App.vue'
import router from "@/router/router.js";
import store from "@/store/store.js";
import VCalendar from 'v-calendar';
import 'v-calendar/style.css';

// Vue 애플리케이션 생성
const app = createApp(App)


// Vue 라우터 및 Vuex 스토어를 애플리케이션에 연결
app.use(router)
app.use(store);
app.use(VCalendar);

// 애플리케이션을 DOM에 마운트
app.mount('#app')
