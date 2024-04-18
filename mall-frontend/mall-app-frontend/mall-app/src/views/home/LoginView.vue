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
import {ref} from "vue";
import axios from '@/utils/request'
import qs from "qs";

const onClickLeft = ()=>{

}

const user = ref({
  username:"",
  password:""
})

const submit =()=>{
  let data = qs.stringify(user.value)
  axios.post("/admin/user/loginByUsernameAndPassword",data).then((response)=>{
    localStorage.setItem("token",response.data.data);
    console.log(localStorage);
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