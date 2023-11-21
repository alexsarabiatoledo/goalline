import { createApp } from 'vue';
import App from './App.vue';
import 'bootstrap/dist/css/bootstrap.css';
import 'bootstrap/dist/js/bootstrap.bundle.min.js';
import 'bootstrap-icons/font/bootstrap-icons.css';
import { createRouter, createWebHashHistory } from 'vue-router';
import LoginView from '../src/views/LoginView'
import RegisterView from '../src/views/RegisterView'
import SearchView from '../src/views/SearchView'
import ProductDetail from '../src/views/ProductDetail'
import HomePage from '../src/views/HomePage';
import store from './store';
import ProductCategory from '../src/views/ProductCategory.vue';
import WishList from '../src/views/WishList.vue';

const routes = [
    {
        path: '/login',
        component: LoginView,

        beforeEnter: (to, from, next) => {
            // Oculta el componente en la vista de inicio de sesión
            store.commit('setVirtualAssistantVisible', false);
            next(); 
        }
    },
    {
        path: '/register',
        component: RegisterView,
        beforeEnter: (to, from, next) => {
            // Oculta el componente en la vista de inicio de sesión
            store.commit('setVirtualAssistantVisible', false);
            next(); 
        }
    },
    {
        path: '/search',
        component: SearchView,
        props: (route) => ({ query: route.query.q })
    },
    {
        path: '/detail/:id',
        name: 'detail',
        component: ProductDetail
    },
    {
      path: '/',
      component: HomePage
    },
    {
        path: '/categoria/:category', // Define el patrón de la URL con el parámetro :category
        name: 'productCategory',
        component: ProductCategory,
        props: true,
    },
    {
        path: '/wishlist', // Ruta para la lista de deseos
        name: 'wishList',
        component: WishList,
    },

]

const router = createRouter({
  history: createWebHashHistory(),
  routes,
});

const app = createApp(App);

app.use(store);
app.use(router);

app.mount('#app');