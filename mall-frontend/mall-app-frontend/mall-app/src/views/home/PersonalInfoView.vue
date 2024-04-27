<template>
  <div style="position: fixed; top:0px;width: 100%;">
    <van-nav-bar class="page-nav-bar"
                 style="--van-nav-bar-title-font-size:22px;--van-nav-bar-height:55px;height: 60px;padding-top: 10px;"
                 title="个人信息"
                 left-text="返回"
                 left-arrow
                 left-text-class="custom-nav-bar-text"
                 @click-left="onClickLeft()"
    />
    <van-cell-group>
      <van-cell title="用户名" :value="userInfo.username"/>
      <van-cell title="昵称" :value="userInfo.nickname"/>
      <van-cell title="等级" :value="userInfo.userType"/>
      <van-cell title="电话号码" :value="userInfo.contactPhone"/>
      <van-cell title="优惠券" :value="userInfo.couponCount"/>
    </van-cell-group>

    <div style="margin: 16px;">
      <van-button round block native-type="submit" style="background-color: #D54431;color:#fff;font-size: 16px;"
                  @click="logout()">
        退出登录
      </van-button>
    </div>
  </div>

</template>

<script setup>
import {onMounted, ref} from "vue";
import router from "@/router";
import {showToast} from "vant";

const userInfo = ref({});
onMounted(() => {
  let info = localStorage.getItem("userInfo");
  if (info != null) {
    userInfo.value = JSON.parse(info);
  }
})

const onClickLeft = () => {
  router.push("/personal");
}

const logout = () =>{
  localStorage.removeItem("userInfo");
  localStorage.removeItem("token");
  showToast({
    message: '<div style="font-size: 20px;margin: 20px;">' +
        '<div style="margin: 10px auto;text-align: center;"><span class="van-icon van-icon-success" style="color:#13DEA5;"></span></div>' +
        '<div style="text-align: center;">成功登出</div></div>',
    type: 'html',
    overlay: true,
    duration: 800,
    'close-on-click-overlay': true
  })
  router.push("/personal");
}
</script>

<style scoped>

</style>