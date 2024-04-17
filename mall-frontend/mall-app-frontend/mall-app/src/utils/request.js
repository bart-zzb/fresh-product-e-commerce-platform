// 首先先引入aixos
import axios from 'axios'
// 这是我放基准地址的文件
import env from './env'
import {showFailToast, showSuccessToast} from "vant";
// 创建一个axios 实例
const api = axios.create({
    baseURL: env.local.baseURL, // 基准地址
    timeout: 5000 // 超时时间
})
// 请求拦截
api.interceptors.request.use((config) => {
    //配置不拦截的路径
    let excludePathList = ["mall/carousel/index", "mall/banner/index", "mall/label/**", "mall/live/**","mall/product/**","mall/live_card/**"]
    console.log(config.url)
    if(excludePathList.includes(config.url)){
        console.log("yes")
        return config;
    }
    if(localStorage.getItem("token")){
        console.log(localStorage.getItem("token"))
        config.headers['Authorization'] = 'Bearer ' + localStorage.getItem("token")

    }
    return config      // 请求拦截里面的需求可以自己写，有的项目需要配置很多，有的很少，这里就
                       // 不放了
}, error => {
    Promise.reject(error)
})
// 响应拦截
/**
 响应拦截也是一样，最基本的可以把返回出来的一些数据做简单处理，
 比如把请求回来的提示信息解构出来，给予提示，这样就不用在每次请求完成后单独去写
 **/
api.interceptors.response.use((res) => {

    if(res.data.code === 401 || res.data.code === 403){
        console.log("请登录!")
        // router.push("/login")
    }
    if(res.data.code === 20000){
        showSuccessToast(res.data.message)
    }else{
        showFailToast(res.data.message)
    }

    return res.data
}, error => {
    Promise.reject(error)
})
// 最后导出
export default api