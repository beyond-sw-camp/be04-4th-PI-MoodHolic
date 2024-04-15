import { createRouter, createWebHistory } from 'vue-router';
import LogIn from "@/components/main/LogIn/LogIn.vue";
import SignUp from "@/components/main/SighUp/SignUp.vue";
import BeforeLogInMain from "@/components/main/Main.vue";


const routes = [
    {   // 로그인
        path: '/login',
        component: LogIn
    },
    {   // 회원가입
        path: '/signup',
        component: SignUp
    },
    {   // 비회원 메인
        path: '/',
        component: BeforeLogInMain
    }
];

const router = createRouter({
    history: createWebHistory(),
    routes
});

export default router;
