import { createRouter, createWebHistory } from 'vue-router';
import store from '@/store/store.js';
import LogIn from "@/components/main/LogIn/LogIn.vue";
import Main from "@/components/main/Main.vue";
import Calendar from "@/components/calendar/Calendar.vue";
import SignUp from "@/components/main/SighUp/SignUp.vue";
import Info from "@/components/Profile/Info/Info.vue";
// import Write from "@/components/Profile/Diary/Write/Write.vue";

const router = createRouter({
    history: createWebHistory(),
    routes: [
        {
            path: '/login',
            component: LogIn
        },
        {
            path: '/signup',
            component: SignUp
        },
        // {
        //     path: '/write',
        //     component: Write
        // },
        {
            path: '/info',
            component: Info
        },
        {
            path: '/',
            name: 'Home',
            component: () => {
                if (store.getters.isAuthenticated) {
                    return Calendar;
                } else {
                    return Main;
                }
            }
        }
    ]
});

router.beforeEach((to, from, next) => {
    const isLoggedIn = store.getters.isAuthenticated;

    // 로그인 상태에서 로그인 또는 회원가입 페이지 접근 시 홈으로 리다이렉트
    if (isLoggedIn && (to.path === '/login' || to.path === '/signup')) {
        next('/');
        return;
    }

    // 로그인하지 않은 상태에서 보호된 라우트 접근 시 로그인 페이지로 리다이렉트
    if (!isLoggedIn && to.path !== '/login' && to.path !== '/signup' && to.path !== '/') {
        next('/login');
        return;
    }

    // 같은 경로(`/`)로 이동 시 로그인/로그아웃 후 페이지 새로 고침
    if (to.path === from.path && to.path === '/' && to.meta.forceUpdate) {
        window.location.reload();
        return;
    }

    next();
});

export default router;
