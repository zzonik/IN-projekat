import { createRouter, createWebHistory } from 'vue-router'
import HomeNeprijavljeniView from '../views/HomeNeprijavljeniView.vue'
import HomeCitalacView from '../views/HomeCitalacView.vue'
import HomeAutorView from '../views/HomeAutorView.vue'
import HomeAdministratorView from '../views/HomeAdministratorView.vue'

import PretragaNeprijavljeniView from '../views/PretragaNeprijavljeniView.vue'
import PretragaCitalacView from '../views/PretragaCitalacView.vue'
import PretragaAutorView from '../views/PretragaAutorView.vue'
import PretragaAdministratorView from '../views/PretragaAdministratorView.vue'

import LoginSection from '../components/LoginSection.vue'
import RegisterSection from '../components/RegisterSection.vue'

const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeNeprijavljeniView
  },
  {
    path: '/homeCitalac',
    name: 'homeCitalac',
    component: HomeCitalacView
  },
  {
    path: '/homeAutor',
    name: 'homeAutor',
    component: HomeAutorView
  },
  {
    path: '/homeAdministrator',
    name: 'homeAdministrator',
    component: HomeAdministratorView
  },
  {
    path: '/pretragaNeprijavljeni',
    name: 'pretragaNeprijavljeni',
    component: PretragaNeprijavljeniView
  },
  {
    path: '/pretragaCitalac',
    name: 'pretragaCitalac',
    component: PretragaCitalacView
  },
  {
    path: '/pretragaAutor',
    name: 'pretragaAutor',
    component: PretragaAutorView
  },
  {
    path: '/pretragaAdministrator',
    name: 'pretragaAdministrator',
    component: PretragaAdministratorView
  },
  {
    path: '/login',
    name: 'login',
    component: LoginSection
  },
  {
    path: '/register',
    name: 'register',
    component: RegisterSection
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
