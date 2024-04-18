import { createRouter, createWebHistory } from 'vue-router';
import store from "@/store/store.js";
import LogIn from "@/components/logIn/LogIn.vue";
import SignUp from "@/components/signup/SignUp.vue";
import Mypage from "@/components/mypage/Mypage.vue";
import Info from "@/components/mypage/Info.vue";
import Card from "@/components/mypage/AIrecommended/Card/Card.vue";
import List from "@/components/mypage/Diary/List/List.vue";
import Statistics from "@/components/header/statistics/Statistics.vue";
import Welcome from "@/components/signup/Welcome.vue";
import MemberMain from "@/components/common/MemberMain.vue";
import NonMemberMain from "@/components/common/NonMemberMain.vue";

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
        {
            path: '/mypage',
            component: Mypage
        },
        {
            path: "/mypage/info",
            component: Info
        },
        {
            path: "/mypage/aigood",
            component: List
        },
        {
            path: "/mypage/diary",
            component: Card
        },
        {
            path: '/statistics',
            component: Statistics
        },
        {
            path: '/welcome',
            component: Welcome
        },
        {
            path: '/',
            component: () => {
                if (store.getters.isAuthenticated) {
                    return MemberMain;
                } else {
                    return NonMemberMain;
                }
            }
        },
    ]
});

router.beforeEach((to, from, next) => {
    const isLoggedIn = store.getters.isAuthenticated;

    // 로그인 상태에서 로그인 또는 회원가입 페이지 접근 시 홈으로 리다이렉트
    if (isLoggedIn && (to.path === '/login' || to.path === '/signup')) {
        if (from.path !== '/') {
            next('/');
        } else {
            next(false); // 이 경우 현재 위치 유지
        }
        return;
    }

    // 로그인하지 않은 상태에서 보호된 라우트 접근 시 로그인 페이지로 리다이렉트
    if (!isLoggedIn && !['/login', '/signup', '/'].includes(to.path)) {
        next('/login');
        return;
    }

    next();
});

export default router;
