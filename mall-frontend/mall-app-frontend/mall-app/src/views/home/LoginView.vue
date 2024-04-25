<template>
  <div>
    <van-nav-bar class="page-nav-bar" style="--van-nav-bar-title-font-size:22px;--van-nav-bar-height:55px;"
        title="登录"
        left-text="返回"
        left-arrow
        @click-left="onClickLeft"
    />
    <van-form>
      <van-form>
          <van-field
              name="用户名"
              placeholder="请输入用户名"
              left-icon="manager"
              v-model="user.username"
          />
          <van-field
              type="password"
              name="密码"
              placeholder="请输入密码"
              left-icon="lock"
              v-model="user.password"
          />
        <div style="margin: 16px;">
          <van-button round block type="primary" native-type="submit" @click="submit">
            登录
          </van-button>
        </div>
      </van-form>

    </van-form>
  </div>


</template>

<script setup>
import {onMounted, ref} from "vue";
import axios from '@/utils/request'
import qs from "qs";
import router from "@/router";
import {showToast} from "vant";

const onClickLeft = ()=>{

}

const user = ref({
  username:"",
  password:""
})

onMounted(()=>{
  user.value.username="xiaoy";
  user.value.password="123456";
})

const submit =()=>{
  let data = qs.stringify(user.value)
  axios.post("/admin/user/loginByUsernameAndPassword",data).then((response)=>{
    if(response.data.state==20000){
      localStorage.setItem("token",response.data.data);
      let redirectPath = localStorage.getItem('redirectPath');
      showToast({
        message: '<div style="font-size: 20px;margin: 20px;">' +
            '<div style="margin: 10px auto;text-align: center;"><span class="van-icon van-icon-success" style="color:#13DEA5;"></span></div>' +
            '<div style="text-align: center;">登录成功</div></div>',
        type: 'html',
        overlay: true,
        duration: 800,
        'close-on-click-overlay': true
      })
      if(redirectPath){
        localStorage.removeItem('redirectPath');
        router.push(redirectPath);
      }
    }
  })
}

</script>

<style>
.page-nav-bar{
  background-color: #3296fa;
}
.page-nav-bar .van-nav-bar__title{
  color:#fff;
}
</style>