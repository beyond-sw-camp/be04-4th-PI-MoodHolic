import { createRouter, createWebHistory } from 'vue-router';
import store from '@/store/store.js';
import LogIn from "@/components/main/LogIn/LogIn.vue";
import SignUp from "@/components/main/SighUp/SignUp.vue";
import Calendar from "@/components/main/MemberMain.vue";
// import View from "@/components/Profile/Diary/View/View.vue";
// import Write from "@/components/Profile/Diary/Write/Write.vue";
// import Info from "@/components/Profile/Info/Info.vue";
// import Mypage from "@/components/header/Mypage.vue";
// import Welcome from "@/components/main/SighUp/Welcome.vue";
// import NonMemberMain from "@/components/main/NonMemberMain.vue";
import Preview from "@/components/Profile/Diary/Preview/Preview.vue";

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
        //     path: '/view',
        //     component: View
        // },
        // {
        //     path: '/write',
        //     component: Write
        // },
        // {
        //     path: '/info',
        //     component: Info
        // },
        // {
        //     path: '/mypage',
        //     component: Mypage
        // },
        // {
        //     path: '/welcome',
        //     component: Welcome
        // },
        {
            path: '/preview',
            component: Preview
        },
        {
            path: '/',
            name: 'Home',
            component: () => {
                if (store.getters.isAuthenticated) {
                    return Calendar;
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
