import {createApp, ref} from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'

//配置根路径
//const BASE_URL = "http://176.117.104.127:8080"
const BASE_URL = 'http://localhost:8080';

//在window中添加的都是全局属性 这样在JS代码中就可以使用这个值了 JavaScript
window.BASE_URL = BASE_URL;


const app = createApp(App)
//在vue实例,template中也可以使用BASE_URL, HTML
app.config.globalProperties.BASE_URL = BASE_URL;

app.use(store).use(router).mount('#app')