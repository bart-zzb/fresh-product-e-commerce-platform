<template>
  <div style="position: fixed; top:0px;width: 100%;">
    <van-nav-bar class="page-nav-bar"
                 style="--van-nav-bar-title-font-size:22px;--van-nav-bar-height:55px;height: 60px;padding-top: 10px;"
                 title="登录"
                 left-text="返回"
                 left-arrow
                 left-text-class="custom-nav-bar-text"
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
          <van-button round block native-type="submit" style="background-color: #D54431;color:#fff;font-size: 16px;"
                      @click="submit">
            登录
          </van-button>
        </div>
        <div style="text-align: center;">
          <span @click="register()"><u>未有账号, 注册新账号</u></span>
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
import {showSuccessToast} from "vant";

const onClickLeft = () => {
  router.push("/personal")
}

const user = ref({
  username: "",
  password: ""
})

onMounted(() => {
  user.value.username = "xiaoy";
  user.value.password = "123456";
})

const submit = () => {
  let data = qs.stringify(user.value)
  axios.post("/admin/user/loginByUsernameAndPassword", data).then((response) => {
    if (response.data.state == 20000) {
      localStorage.setItem("token", response.data.data.token);
      localStorage.setItem("userInfo",JSON.stringify(response.data.data));
      let redirectPath = localStorage.getItem('redirectPath');
      showSuccessToast('登录成功');
      //跳转上一页
      if (redirectPath) {
        localStorage.removeItem('redirectPath');
        router.push(redirectPath);
      }
    }
  })
}

const register = ()=>{
  router.push("/reg")
}
</script>

<style>
.page-nav-bar {
  background-color: #F5F5F5;
}

.page-nav-bar .van-nav-bar__title {
  color: #000000;
}
</style>