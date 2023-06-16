<<<<<<< Updated upstream
import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import PretragaView from '../views/PretragaView.vue'
=======
import { createRouter, createWebHistory } from 'vue-router';
import HomeNeprijavljeniView from '../views/HomeNeprijavljeniView.vue';
import HomeCitalacView from '../views/HomeCitalacView.vue';
import HomeAutorView from '../views/HomeAutorView.vue';
import HomeAdministratorView from '../views/HomeAdministratorView.vue';
import PretragaNeprijavljeniView from '../views/PretragaNeprijavljeniView.vue';
import PretragaCitalacView from '../views/PretragaCitalacView.vue';
import PretragaAutorView from '../views/PretragaAutorView.vue';
import PretragaAdministratorView from '../views/PretragaAdministratorView.vue';
import LoginSection from '../components/LoginSection.vue';
import RegisterSection from '../components/RegisterSection.vue';
>>>>>>> Stashed changes

const routes = [
  {
    path: '/',
    name: 'home',
<<<<<<< Updated upstream
    component: HomeView
  },
  {
    path: '/pretraga',
    name: 'pretraga',
    component: PretragaView
=======
    component: HomeNeprijavljeniView,
    meta: {
      title: 'Home'
    }
  },
  {
    path: '/homeCitalac',
    name: 'homeCitalac',
    component: HomeCitalacView,
    meta: {
      title: 'Home'
    }
  },
  {
    path: '/homeAutor',
    name: 'homeAutor',
    component: HomeAutorView,
    meta: {
      title: 'Home'
    }
  },
  {
    path: '/homeAdministrator',
    name: 'homeAdministrator',
    component: HomeAdministratorView,
    meta: {
      title: 'Home'
    }
  },
  {
    path: '/pretragaNeprijavljeni',
    name: 'pretragaNeprijavljeni',
    component: PretragaNeprijavljeniView,
    meta: {
      title: 'Pretraga'
    }
  },
  {
    path: '/pretragaCitalac',
    name: 'pretragaCitalac',
    component: PretragaCitalacView,
    meta: {
      title: 'Pretraga'
    }
  },
  {
    path: '/pretragaAutor',
    name: 'pretragaAutor',
    component: PretragaAutorView,
    meta: {
      title: 'Pretraga'
    }
  },
  {
    path: '/pretragaAdministrator',
    name: 'pretragaAdministrator',
    component: PretragaAdministratorView,
    meta: {
      title: 'Pretraga'
    }
  },
  {
    path: '/login',
    name: 'login',
    component: LoginSection,
    meta: {
      title: 'Pretraga'
    }
  },
  {
    path: '/register',
    name: 'register',
    component: RegisterSection,
    meta: {
      title: 'Pretraga'
    }
>>>>>>> Stashed changes
  }
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
});

router.beforeEach((to, from, next) => {
  const pageTitle = to.meta.title || 'My Website'; // Use the default title if no title is defined for the route
  document.title = pageTitle;
  next();
});

export default router;
