import { createRouter, createWebHistory } from 'vue-router'
import RegistrationComponent from "@/components/RegistrationComponent.vue";
import AuthComponent from "@/components/AuthComponent.vue";
import HomeComponent from "@/components/HomeComponent.vue";

const routes = [
    {
        path: '/',
        name: 'Home',
        component: HomeComponent
    },
    {
        path: '/auth',
        name: 'Auth',
        component: AuthComponent
    },
    {
        path: '/register',
        name: 'Register',
        component: RegistrationComponent
    }
]

const router = createRouter({
    history: createWebHistory(),
    routes
})

export default router