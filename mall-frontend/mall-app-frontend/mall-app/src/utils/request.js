// 首先先引入aixos
import axios from 'axios'
// 这是我放基准地址的文件
import env from './env'
import {showFailToast} from "vant";
import router from "@/router";

// 创建一个axios 实例
const api = axios.create({
    baseURL: env.local.baseURL, // 基准地址
    // timeout: 5000 // 超时时间
})
//请求拦截
api.interceptors.request.use((config) => {
    if(localStorage.getItem("token")){
        //console.log(localStorage.getItem("token"))
        config.headers['Authorization'] = 'Bearer ' + localStorage.getItem("token")
        //console.log(config.headers);
    }
    return config      // 请求拦截里面的需求可以自己写，有的项目需要配置很多，有的很少，这里就
                       // 不放了
}, error => {
    Promise.reject(error)
})
//响应拦截
/**
 响应拦截也是一样，最基本的可以把返回出来的一些数据做简单处理，
 比如把请求回来的提示信息解构出来，给予提示，这样就不用在每次请求完成后单独去写
 **/
api.interceptors.response.use((res) => {
    if(res.data.state == 60000 || res.data.state == 60100 || res.data.state == 60400){
        if(res.data.state === 60100 || res.data.state == 60400){
            showFailToast('未登录,请先登录');
        }else{
            showFailToast('已过期,请重新登录')
        }
        localStorage.setItem('redirectPath',router.currentRoute.value.fullPath);
        setTimeout(() => {
            router.push("/login");
        });
    }else if(res.data.state == 60300){
        showFailToast('用户名或密码错误')

    }else if(res.data.state == 60500){
        showFailToast('用户名已存在')

    }else if(res.data.state== 40000){
        showFailToast(res.data.message);
    }

    return res
}, error => {
    Promise.reject(error)
})

// 最后导出
export default api