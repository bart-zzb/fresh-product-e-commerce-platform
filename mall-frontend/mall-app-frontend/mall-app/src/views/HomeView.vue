<template>
  <view>
    <router-view/>
  </view>
  <!-- 共有的尾部 -->
  <view>
    <van-tabbar style="height: 80px;" v-model="active">
      <van-tabbar-item>
        <van-icon name="wap-home-o" size="45"/>
        <div style="font-size: 16px;margin-top: 3px;">首页</div>
      </van-tabbar-item>
      <van-tabbar-item>
        <van-icon name="apps-o" size="45"/>
        <div style="font-size: 16px;margin-top: 3px;">分类</div>
      </van-tabbar-item>
      <van-tabbar-item>
        <van-icon name="video-o" size="45"/>
        <div style="font-size: 16px;margin-top: 3px;">直播</div>
      </van-tabbar-item>
      <van-tabbar-item>
        <van-icon name="shopping-cart-o" size="45"/>
        <div style="font-size: 16px;margin-top: 3px;">购物车</div>
      </van-tabbar-item>
      <van-tabbar-item>
        <van-icon name="manager-o" size="45"/>
        <div style="font-size: 16px;margin-top: 3px;">我的</div>
      </van-tabbar-item>
    </van-tabbar>
  </view>
  <van-tabbar style="height: 80px;" v-model="active">
    <van-tabbar-item>
      <van-icon name="wap-home-o" size="45"/>
      <div style="font-size: 16px;margin-top: 3px;">首页</div>
    </van-tabbar-item>
    <van-tabbar-item>
      <van-icon name="apps-o" size="45"/>
      <div style="font-size: 16px;margin-top: 3px;">分类</div>
    </van-tabbar-item>
    <van-tabbar-item>
      <van-icon name="video-o" size="45"/>
      <div style="font-size: 16px;margin-top: 3px;">直播</div>
    </van-tabbar-item>
    <van-tabbar-item>
      <van-icon name="shopping-cart-o" size="45"/>
      <div style="font-size: 16px;margin-top: 3px;">购物车</div>
    </van-tabbar-item>
    <van-tabbar-item>
      <van-icon name="manager-o" size="45"/>
      <div style="font-size: 16px;margin-top: 3px;">我的</div>
    </van-tabbar-item>
  </van-tabbar>
</template>

<script setup>
import {ref} from "vue";

const active = ref();
</script>
