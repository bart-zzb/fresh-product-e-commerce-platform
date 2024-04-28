import {createApp} from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import { Toast } from 'vant';
import "./assets/baseCSS.css";
import "vant/lib/popup/index.css"
import "vant/lib/toast/index.css"
import "vant/es/dialog/style"

const BASE_URL = 'http://localhost:8080/';
//在window中添加的都是全局属性 这样在JS代码中就可以使用这个值了 JavaScript
window.BASE_URL = BASE_URL;

const app = createApp(App)
//在vue实例,template中也可以使用BASE_URL, HTML
app.config.globalProperties.BASE_URL = BASE_URL;


app.use(store).use(router).use(Toast).mount('#app')