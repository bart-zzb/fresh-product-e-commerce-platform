<template>
  <div style="margin-bottom: 80px;">
    <router-view/>
  </div>

  <div style="text-align: center;">
    <!-- 共有的尾部 -->
    <van-tabbar style="height: 80px;" route>
      <van-tabbar-item to="/index">
        <van-icon name="wap-home-o" size="45"/>
        <div style="font-size: 16px;margin-top: 3px;">首页</div>
      </van-tabbar-item>
      <van-tabbar-item to="/list">
        <van-icon name="apps-o" size="45"/>
        <div style="font-size: 16px;margin-top: 3px;">分类</div>
      </van-tabbar-item>
      <van-tabbar-item to="/live">
        <van-icon name="video-o" size="45"/>
        <div style="font-size: 16px;margin-top: 3px;">直播</div>
      </van-tabbar-item>
      <van-tabbar-item to="/cart">
        <van-icon name="shopping-cart-o" size="45" />
        <div style="font-size: 16px;margin-top: 3px;">购物车</div>
      </van-tabbar-item>
      <van-tabbar-item to="/personal">
        <van-icon name="manager-o" size="45"/>
        <div style="font-size: 16px;margin-top: 3px;">我的</div>
      </van-tabbar-item>
    </van-tabbar>
  </div>

</template>

<script setup>
import {ref} from "vue";

const active = ref();

</script>
