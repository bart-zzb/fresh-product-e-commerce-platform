import {createApp} from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'

createApp(App).use(store).use(router).mount('#app')

import {Popup} from "vant";

Vue.use(Popup);

const BASE_URL = 'http://localhost:8080';
window.BASE_URL = BASE_URL;