import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'

const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView,
    redirect:'/index',
    children:[
      {path:'/reg', component:()=>import('../views/home/RegView')},
      {path:'/login', component:()=>import('../views/home/LoginView')},
      {path:'/index', component:()=>import('../views/home/IndexView')},
      {path:'/list', component:()=>import('../views/home/ListView')},
      {path:'/cart', component:()=>import('../views/home/CartView')},
      {path:'/live', component:()=>import('../views/home/LiveView')},
      {path:'/personal', component:()=>import('../views/home/PersonalView')}

    ]
  },
  {
    path:'/detail',
    component:()=>import('../views/home/DetailView')
  },
  {path:'/payment', component:()=>import('../views/payment/PaymentView')}
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
